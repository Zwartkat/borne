#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Script pour générer la documentation utilisateur de tous les jeux de la borne
"""

from pathlib import Path
import re

PROJET_DIR = Path("projet")
DOCS_DIR = Path("docs_jeux")
DOCS_DIR.mkdir(exist_ok=True)

def parse_controls(bouton_text):
    """Parse les contrôles depuis le fichier bouton.txt"""
    # Format: Movement:gauche et droite:Interagir:...:Retour:...
    parts = bouton_text.strip().split(":")
    controls = []
    
    control_names = ["Mouvement", "Interaction", "Action 1", "Retour", "Action 2", "Action 3"]
    
    for i, part in enumerate(parts):
        if i < len(control_names) and part.strip() and part.strip() != "aucun":
            controls.append(f"<li><strong>{control_names[i]}:</strong> {part.strip()}</li>")
    
    return "\n".join(controls)

def get_readme_content(game_dir):
    """Récupère le contenu du README.md si disponible"""
    readme_path = game_dir / "README.md"
    if readme_path.exists():
        return readme_path.read_text(encoding="utf-8", errors="ignore")
    return None

def generate_html_doc(game_name, game_dir):
    """Génère la documentation HTML pour un jeu"""
    
    # Lire les fichiers
    description_file = game_dir / "description.txt"
    bouton_file = game_dir / "bouton.txt"
    
    if not description_file.exists():
        print(f"⚠️ {game_name}: aucun fichier description.txt trouvé")
        return
    
    description = description_file.read_text(encoding="utf-8", errors="ignore").strip()
    
    controls_html = ""
    if bouton_file.exists():
        bouton_content = bouton_file.read_text(encoding="utf-8", errors="ignore")
        controls_html = parse_controls(bouton_content)
    
    readme_content = get_readme_content(game_dir)
    rules_html = ""
    if readme_content:
        # Convertir le markdown simple en HTML
        rules_html = f"""
        <h2>Règles du jeu</h2>
        <div class="rules">
            {readme_content.replace('#', '').strip()}
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
        
        .description {{
            color: #666;
            font-size: 1.1em;
            margin-top: 15px;
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
        
        ul {{
            list-style-position: inside;
            margin-left: 20px;
        }}
        
        li {{
            margin-bottom: 8px;
            background: #f9f9f9;
            padding: 10px;
            border-left: 3px solid #667eea;
            margin-left: 0;
        }}
        
        .rules {{
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            border-left: 4px solid #764ba2;
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
        <a href="index.html" class="back-button">← Retour aux jeux</a>
        
        <header>
            <h1>🎮 {game_name}</h1>
            <p class="description">{description}</p>
        </header>
        
        <div class="section">
            <h2>Contrôles</h2>
            <ul>
                {controls_html if controls_html else "<li>Aucune information de contrôle disponible</li>"}
            </ul>
        </div>
        
        {rules_html if rules_html else ""}
        
    </div>
    
    <footer>
        <p>Documentation technique de la Borne d'Arcade © 2025</p>
    </footer>
</body>
</html>
"""
    
    # Écrire le fichier HTML
    output_file = DOCS_DIR / f"{game_name.lower().replace(' ', '_')}.html"
    output_file.write_text(html_content, encoding="utf-8")
    print(f"✅ {game_name}: documentation générée → {output_file}")

def generate_index():
    """Génère l'index avec la liste de tous les jeux"""
    
    games = []
    for game_dir in sorted(PROJET_DIR.iterdir()):
        if game_dir.is_dir():
            desc_file = game_dir / "description.txt"
            if desc_file.exists():
                description = desc_file.read_text(encoding="utf-8", errors="ignore").strip().split("\n")[0]
                games.append({
                    'name': game_dir.name,
                    'description': description
                })
    
    game_cards = ""
    for game in games:
        game_file = f"{game['name'].lower().replace(' ', '_')}.html"
        game_cards += f"""
        <div class="game-card">
            <h3>{game['name']}</h3>
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
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
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
            <p class="subtitle">Documentation des Jeux</p>
        </header>
        
        <div class="stats">
            <p><strong>{len(games)} jeux disponibles</strong></p>
        </div>
        
        <div class="games-grid">
            {game_cards}
        </div>
        
        <footer>
            <p>📚 Documentation technique complète - Borne d'Arcade © 2025</p>
            <p>Sélectionnez un jeu pour consulter sa documentation complète</p>
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
    print("🚀 Génération de la documentation utilisateur des jeux...\n")
    
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
