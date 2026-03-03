#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script pour générer la documentation utilisateur des jeux en analysant le code source
"""

from pathlib import Path
import re
import os

PROJET_DIR = Path("projet")
DOCS_DIR = Path("docs_jeux")
DOCS_DIR.mkdir(exist_ok=True)

def parse_controls(bouton_text):
    """Parse les contrôles depuis le fichier bouton.txt"""
    parts = bouton_text.strip().split(":")
    controls = []
    
    control_names = ["Mouvement", "Interaction", "Action 1", "Retour", "Action 2", "Action 3"]
    
    for i, part in enumerate(parts):
        if i < len(control_names) and part.strip() and part.strip() != "aucun":
            controls.append(f"<li><strong>{control_names[i]}:</strong> {part.strip()}</li>")
    
    return "\n".join(controls)

def analyze_java_game(game_dir):
    """Analyse les fichiers Java du jeu"""
    info = {
        'classes': [],
        'game_type': 'Jeu Java',
        'multi_player': False,
        'features': []
    }
    
    # Chercher le fichier main
    java_files = list(game_dir.glob("*.java"))
    
    for java_file in java_files:
        content = java_file.read_text(encoding="utf-8", errors="ignore")
        
        # Chercher la classe principale avec main
        if "public static void main" in content:
            # Analyser les imports pour détecter les système de jeu
            if "ClavierBorneArcade" in content:
                info['features'].append("Contrôles arcade")
            if "J2" in content or "Joueur2" in content or "Player2" in content:
                info['multi_player'] = True
                info['game_type'] = 'Jeu 2 joueurs'
            else:
                info['game_type'] = 'Jeu 1 joueur'
            
            # Extraire les classes utilisées
            for class_match in re.finditer(r'new\s+(\w+)\s*\(', content):
                class_name = class_match.group(1)
                if class_name not in info['classes'] and not class_name[0].isupper():
                    info['classes'].append(class_name)
        
        # Chercher les imports pour les fonctionnalités
        if "Audio" in content or "Bruitage" in content or "Musique" in content:
            if "Audio" not in info['features']:
                info['features'].append("Musique et sons")
        if "HighScore" in content:
            if "Scores" not in info['features']:
                info['features'].append("Système de scores")
        if "Partie" in content or "Menu" in content:
            if "Menu" not in info['features']:
                info['features'].append("Menu principal")
    
    return info

def analyze_python_game(game_dir):
    """Analyse les fichiers Python du jeu"""
    info = {
        'classes': [],
        'game_type': 'Jeu Python',
        'features': [],
        'description': ''
    }
    
    # Chercher les fichiers Python
    python_files = list((game_dir / "src").glob("*.py")) if (game_dir / "src").exists() else []
    
    for py_file in python_files:
        content = py_file.read_text(encoding="utf-8", errors="ignore")
        
        # Extraire le docstring
        docstring_match = re.search(r'"""(.*?)"""', content, re.DOTALL)
        if docstring_match and not info['description']:
            info['description'] = docstring_match.group(1).strip()[:200]
        
        # Détecter les classes principales
        for class_match in re.finditer(r'class\s+(\w+)', content):
            class_name = class_match.group(1)
            if class_name not in info['classes']:
                info['classes'].append(class_name)
        
        # Détecter les fonctionnalités
        if "pygame" in content:
            if "Graphiques" not in info['features']:
                info['features'].append("Graphiques Pygame")
        if "Player" in content or "player" in content:
            if "J2" in content or "player2" in content:
                info['game_type'] = 'Jeu 2 joueurs'
    
    # Déterminer le type si c'est 1 joueur
    if info['game_type'] == 'Jeu Python':
        info['game_type'] = 'Jeu 1 joueur'
    
    return info

def analyze_lua_game(game_dir):
    """Analyse les fichiers Lua du jeu"""
    info = {
        'game_type': 'Jeu Lua',
        'features': [],
        'description': ''
    }
    
    main_lua = game_dir / "main.lua"
    if main_lua.exists():
        content = main_lua.read_text(encoding="utf-8", errors="ignore")
        
        # Extraire les commentaires
        comments = re.findall(r'--\s*(.+?)(?=\n|$)', content)
        if comments:
            info['description'] = comments[0][:200]
        
        # Détecter les systèmes
        if "love2d" in content.lower() or "love" in content:
            if "Love2D" not in info['features']:
                info['features'].append("Love2D Framework")
        if "Player2" in content or "player2" in content:
            info['game_type'] = 'Jeu 2 joueurs'
        else:
            info['game_type'] = 'Jeu 1 joueur'
    
    return info

def get_objective_from_description(desc_text):
    """Extrait l'objectif principal du fichier description.txt"""
    lines = desc_text.strip().split('\n')
    # Cherche les lignes qui ne sont pas des crédits
    for line in lines:
        if line.strip() and not "par" in line.lower() and not "©" in line:
            return line.strip()
    return ""

def get_readme_content(game_dir):
    """Récupère le contenu du README.md si disponible"""
    readme_path = game_dir / "README.md"
    if readme_path.exists():
        return readme_path.read_text(encoding="utf-8", errors="ignore")
    return None

def markdown_to_html(md_content):
    """Convertit le markdown simple en HTML"""
    if not md_content:
        return ""
    
    html = md_content
    # En-têtes
    html = re.sub(r'^### (.+?)$', r'<h3>\1</h3>', html, flags=re.MULTILINE)
    html = re.sub(r'^## (.+?)$', r'<h2>\1</h2>', html, flags=re.MULTILINE)
    # Listes
    html = re.sub(r'^- (.+?)$', r'<li>\1</li>', html, flags=re.MULTILINE)
    # Gras
    html = re.sub(r'\*\*(.+?)\*\*', r'<strong>\1</strong>', html)
    # Italique
    html = re.sub(r'\*(.+?)\*', r'<em>\1</em>', html)
    
    return html

def generate_html_doc(game_name, game_dir):
    """Génère la documentation HTML pour un jeu"""
    
    # Lire les fichiers
    description_file = game_dir / "description.txt"
    bouton_file = game_dir / "bouton.txt"
    
    if not description_file.exists():
        print(f"⚠️ {game_name}: aucun fichier description.txt trouvé")
        return
    
    description = get_objective_from_description(description_file.read_text(encoding="utf-8", errors="ignore"))
    
    # Analyser le code
    game_info = {
        'game_type': 'Jeu',
        'features': [],
        'description_detail': ''
    }
    
    # Chercher le type de fichier source
    has_java = bool(list(game_dir.glob("*.java")))
    has_python = bool((game_dir / "src").exists() and list((game_dir / "src").glob("*.py")))
    has_lua = bool((game_dir / "main.lua").exists())
    
    if has_java:
        game_info.update(analyze_java_game(game_dir))
    elif has_python:
        game_info.update(analyze_python_game(game_dir))
    elif has_lua:
        game_info.update(analyze_lua_game(game_dir))
    
    # Analyser les contrôles
    controls_html = ""
    joystick_controls = []
    
    if bouton_file.exists():
        bouton_content = bouton_file.read_text(encoding="utf-8", errors="ignore")
        controls_html = parse_controls(bouton_content)
        
        # Analyser les contrôles au format standard
        parts = bouton_content.strip().split(":")
        if len(parts) > 0 and parts[0].strip():
            joystick_controls.append(f"<strong>Mouvement:</strong> {parts[0].strip()}")
        if len(parts) > 1 and parts[1].strip() and parts[1].strip() != "aucun":
            joystick_controls.append(f"<strong>Action:</strong> {parts[1].strip()}")
    
    # Lire le README pour les règles
    readme_content = get_readme_content(game_dir)
    rules_html = ""
    if readme_content:
        rules_html = f"""
        <div class="section">
            <h2>📖 Règles du jeu</h2>
            <div class="rules">
                {markdown_to_html(readme_content)}
            </div>
        </div>
        """
    
    # Construire le HTML
    html_content = f"""<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{game_name} - Documentation</title>
    <style>
        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}
        
        body {{
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #333;
            line-height: 1.6;
        }}
        
        .container {{
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
        }}
        
        header {{
            background: rgba(255, 255, 255, 0.95);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            margin-bottom: 30px;
        }}
        
        h1 {{
            color: #667eea;
            margin-bottom: 10px;
            font-size: 2.5em;
        }}
        
        .meta {{
            display: flex;
            gap: 20px;
            margin-top: 15px;
            flex-wrap: wrap;
        }}
        
        .meta-item {{
            background: #f0f0f0;
            padding: 8px 15px;
            border-radius: 5px;
            font-size: 0.95em;
            color: #667eea;
            font-weight: bold;
        }}
        
        .description {{
            color: #666;
            font-size: 1.1em;
            margin-top: 15px;
            font-style: italic;
        }}
        
        .section {{
            background: rgba(255, 255, 255, 0.95);
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }}
        
        h2 {{
            color: #667eea;
            margin-bottom: 15px;
            font-size: 1.8em;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }}
        
        h3 {{
            color: #764ba2;
            margin-top: 15px;
            margin-bottom: 10px;
        }}
        
        ul, ol {{
            list-style-position: inside;
            margin-left: 20px;
        }}
        
        li {{
            margin-bottom: 10px;
            background: #f9f9f9;
            padding: 10px;
            border-left: 3px solid #667eea;
            margin-left: 0;
            border-radius: 3px;
        }}
        
        .features {{
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-top: 15px;
        }}
        
        .feature-tag {{
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 0.85em;
        }}
        
        .rules {{
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            border-left: 4px solid #764ba2;
        }}
        
        .rules h2, .rules h3, .rules h4 {{
            color: #764ba2;
            margin-top: 15px;
        }}
        
        .rules li {{
            border-left-color: #764ba2;
        }}
        
        footer {{
            text-align: center;
            padding: 20px;
            color: white;
            margin-top: 30px;
            font-size: 0.9em;
        }}
        
        .back-button {{
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }}
        
        .back-button:hover {{
            background: #764ba2;
        }}
    </style>
</head>
<body>
    <div class="container">
        <a href="index.html" class="back-button">← Retour au menu</a>
        
        <header>
            <h1>🎮 {game_name}</h1>
            <p class="description">"{description if description else "Jeu de la borne d'arcade"}"</p>
            <div class="meta">
                <span class="meta-item">📊 {game_info['game_type']}</span>
                {"".join(f'<span class="meta-item">{feature}</span>' for feature in game_info['features'][:3])}
            </div>
        </header>
        
        <div class="section">
            <h2>🎮 Contrôles</h2>
            <ul>
                {controls_html if controls_html else "<li>Contrôles standard de la borne d'arcade</li>"}
            </ul>
        </div>
        
        {rules_html}
        
        <div class="section">
            <h2>ℹ️ Informations techniques</h2>
            <p><strong>Type:</strong> {game_info['game_type']}</p>
            {"<p><strong>Fonctionnalités:</strong></p>" if game_info['features'] else ""}
            {"<ul class='features'>" + "".join(f"<li class='feature-tag'>{f}</li>" for f in game_info['features']) + "</ul>" if game_info['features'] else ""}
        </div>
        
    </div>
    
    <footer>
        <p>📚 Documentation technique - Borne d'Arcade © 2025</p>
    </footer>
</body>
</html>
"""
    
    # Écrire le fichier HTML
    output_file = DOCS_DIR / f"{game_name.lower().replace(' ', '_')}.html"
    output_file.write_text(html_content, encoding="utf-8")
    print(f"✅ {game_name:20} {game_info['game_type']:20} → documentation générée")

def generate_index():
    """Génère l'index avec la liste de tous les jeux"""
    
    games = []
    for game_dir in sorted(PROJET_DIR.iterdir()):
        if game_dir.is_dir():
            desc_file = game_dir / "description.txt"
            if desc_file.exists():
                full_desc = desc_file.read_text(encoding="utf-8", errors="ignore")
                description = get_objective_from_description(full_desc)
                
                # Analyser le type de jeu
                has_java = bool(list(game_dir.glob("*.java")))
                has_python = bool((game_dir / "src").exists() and list((game_dir / "src").glob("*.py")))
                has_lua = bool((game_dir / "main.lua").exists())
                
                game_type = "🎮"
                if has_java:
                    game_type = "☕"
                elif has_python:
                    game_type = "🐍"
                elif has_lua:
                    game_type = "🌙"
                
                games.append({
                    'name': game_dir.name,
                    'description': description,
                    'type_icon': game_type
                })
    
    game_cards = ""
    for game in games:
        game_file = f"{game['name'].lower().replace(' ', '_')}.html"
        game_cards += f"""
        <div class="game-card">
            <h3>{game['type_icon']} {game['name']}</h3>
            <p>{game['description']}</p>
            <a href="{game_file}" class="play-button">Voir la documentation →</a>
        </div>
        """
    
    index_html = f"""<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documentation des Jeux - Borne d'Arcade</title>
    <style>
        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}
        
        body {{
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #333;
            min-height: 100vh;
            padding: 20px;
        }}
        
        .container {{
            max-width: 1200px;
            margin: 0 auto;
        }}
        
        header {{
            text-align: center;
            color: white;
            margin-bottom: 50px;
            padding: 30px 0;
        }}
        
        h1 {{
            font-size: 3em;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }}
        
        .subtitle {{
            font-size: 1.2em;
            opacity: 0.9;
        }}
        
        .games-grid {{
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }}
        
        .game-card {{
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }}
        
        .game-card:hover {{
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
        }}
        
        .game-card h3 {{
            color: #667eea;
            margin-bottom: 10px;
            font-size: 1.5em;
        }}
        
        .game-card p {{
            color: #666;
            margin-bottom: 15px;
            font-size: 0.95em;
            line-height: 1.5;
            min-height: 50px;
        }}
        
        .play-button {{
            display: inline-block;
            padding: 10px 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
            font-weight: bold;
        }}
        
        .play-button:hover {{
            background: linear-gradient(135deg, #764ba2 0%, #667eea 100%);
        }}
        
        footer {{
            text-align: center;
            color: white;
            padding: 20px;
            margin-top: 40px;
            opacity: 0.9;
        }}
        
        .stats {{
            color: white;
            text-align: center;
            margin-bottom: 30px;
            font-size: 1.1em;
        }}
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>🎮 Borne d'Arcade</h1>
            <p class="subtitle">Documentation Officielle des Jeux</p>
        </header>
        
        <div class="stats">
            <p><strong>{len(games)} jeux disponibles</strong></p>
            <p>Cliquez sur un jeu pour lire sa documentation complète</p>
        </div>
        
        <div class="games-grid">
            {game_cards}
        </div>
        
        <footer>
            <p>📚 Documentation technique complète - Borne d'Arcade © 2025</p>
        </footer>
    </div>
</body>
</html>
"""
    
    index_file = DOCS_DIR / "index.html"
    index_file.write_text(index_html, encoding="utf-8")
    print(f"\n✅ Index généré → {index_file}")
    print(f"📊 {len(games)} jeu(x) documenté(s)")

def main():
    print("🚀 Génération de la documentation utilisateur des jeux (avec analyse du code)...\n")
    
    # Générer la doc pour chaque jeu
    game_num = 0
    for game_dir in sorted(PROJET_DIR.iterdir()):
        if game_dir.is_dir():
            game_name = game_dir.name
            generate_html_doc(game_name, game_dir)
            game_num += 1
    
    print()
    # Générer l'index
    generate_index()
    
    print(f"\n✨ Documentation disponible dans le dossier: {DOCS_DIR}")

if __name__ == "__main__":
    main()
