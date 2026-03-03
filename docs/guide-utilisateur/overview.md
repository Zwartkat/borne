# Guide Utilisateur - Vue d'Ensemble

Bienvenue sur la Borne d'Arcade ! Cette section vous guide pour utiliser la borne arcade et jouer à ses jeux.

## 🎮 Qu'est-ce que la Borne d'Arcade ?

La Borne d'Arcade est une console de jeu rétro-futuriste qui propose 15 jeux différents, développés en Java, Python et Lua. Elle offre une expérience de jeu arcade classique avec des contrôles joystick et des systèmes de scores persistants.

### Caractéristiques

✅ **15 jeux** variés et amusants  
✅ **Contrôles arcade** avec joystick  
✅ **Leaderboards** avec système de scores  
✅ **Interface utilisateur** intuitive  
✅ **Multi-jeux** supportés  
✅ **Sauvegarde automatique** des résultats

---

## 📦 Jeux Disponibles

### Par Catégorie

#### 🎯 Jeux de Tir
- **Ball Blast** - Détruisez les boules
- **PianoTile** - Tuiles musicales

#### 🎨 Jeux de Puzzle
- **Columns** - Alignez les gemmes
- **Minesweeper** - Démineur classique
- **Puissance_X** - Puissance 4

#### 🏁 Jeux d'Action
- **DinoRail** - Évitez les obstacles
- **InitialDrift** - Course arcade
- **TronGame** - Motos lumineuses
- **Snake_Eater** - Le serpent affamé
- **OsuTile** - Jeu de rythme

#### 🎮 Classiques
- **Pong** - Le jeu éternel
- **JavaSpace** - Espace arcade
- **Kowasu_Renga** - Puzzle oriental
- **Pokechecs** - Pokémon + Échecs
- **CursedWare** - Aventure avec malédictions

---

## 🚀 Démarrage Rapide

### 1️⃣ Installation
[Voir le guide complet d'installation](installation.md)

Résumé rapide :
```bash
# Cloner ou télécharger
git clone https://github.com/Zwartkat/borne.git
cd borne

# Suivre les instructions pour votre OS
```

### 2️⃣ Lancer la Borne
```bash
# Linux/Mac
./launch.sh

# Windows
launch.sh (ou double-clic sur le fichier)
```

### 3️⃣ Naviguer
- **Haut/Bas** : Sélectionner les jeux
- **Action (Bouton A)** : Lancer le jeu
- **Retour** : Revenir au menu

---

## 📖 Guide Complet

### Pour les nouveaux utilisateurs
1. [Installation complète](installation.md)
2. [Premiers pas](premiers-pas.md)
3. [Contrôles et touches](controls.md)
4. [Liste des jeux](jeux/overview.md)

### Pour les utilisateurs expérimentés
- [Dépannage](troubleshooting.md)
- [FAQ](../faq.md)
- [Contrôles avancés](controls.md)

---

## ⌨️ Contrôles de Base

| Action | Touche | Joystick |
|--------|--------|----------|
| **Haut** | Flèche ↑ | Analogique ↑ |
| **Bas** | Flèche ↓ | Analogique ↓ |
| **Gauche** | Flèche ← | Analogique ← |
| **Droite** | Flèche → | Analogique → |
| **Action 1** | Z / A | Bouton A |
| **Action 2** | X / B | Bouton B |
| **Menu** | Entrée | Start |
| **Retour** | Échap | Bouton Select |

!!! note "Contrôles par Jeu"
    Chaque jeu peut avoir ses propres contrôles spécifiques. Consultez le [guide des contrôles](controls.md) pour plus de détails.

---

## 🎯 Votre Première Partie

### Étape 1 : Démarrer la Borne
```bash
./launch.sh
```

Vous verrez le menu principal avec la liste des jeux.

### Étape 2 : Sélectionner un Jeu
- Utilisez les **flèches** pour naviguer
- Appuyez sur **Action** pour lancer

### Étape 3 : Jouer
- Suivez les instructions du jeu
- Essayez de battre le high score !

### Étape 4 : Sauvegarde
- Vos scores sont **automatiquement sauvegardés**
- Consultez le leaderboard dans le menu

---

## 💾 Système de Scores

### Leaderboard
Chaque jeu a son propre leaderboard :
- **Top 10** des meilleurs scores
- **Votre meilleur score** surligné
- **Temps de jeu** et **dates**

### Sauvegarde
- ✅ Les scores sont **sauvegardés automatiquement**
- ✅ Les données sont **persistantes** entre les sessions
- ✅ Les fichiers de score sont dans `projet/[jeu]/highscore`

---

## 🆘 Aide et Support

### Questions Fréquentes
Consultez la [FAQ](../faq.md) pour les questions les plus courantes.

### Dépannage
Si vous rencontrez un problème, consultez le [guide de dépannage](troubleshooting.md).

### Signaler un Bug
1. Allez sur [GitHub Issues](https://github.com/Zwartkat/borne/issues)
2. Décrivez le problème
3. Incluez vos informations système

---

## 📚 Prochaines Étapes

- **Joueur curieux** ? → Explorez tous les [jeux disponibles](jeux/overview.md)
- **Vous avez besoin d'aide ?** → Consultez les [contrôles](controls.md)
- **Vous avez un problème ?** → Voir le [dépannage](troubleshooting.md)
- **Vous êtes développeur ?** → Allez à la [doc technique](../documentation-technique/architecture.md)

---

**Amusez-vous bien ! 🎮**
