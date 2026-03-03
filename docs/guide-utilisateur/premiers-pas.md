# Premiers Pas - Guide Rapide

Commencez à jouer à la Borne d'Arcade en moins de 5 minutes ! 🚀

## ⏱️ 5 Minutes pour Jouer

### Étape 1 : Vérifier Java (30 secondes)

```bash
java -version
```

Vous devez voir Java 11 ou supérieur. Si ce n'est pas le cas, [installer Java](installation.md).

### Étape 2 : Télécharger (1 minute)

```bash
# Option A : Avec Git
git clone https://github.com/Zwartkat/borne.git
cd borne

# Option B : ZIP direct
# Télécharger depuis GitHub et extraire
```

### Étape 3 : Lancer (30 secondes)

```bash
# Linux / Mac
./launch.sh

# Windows
launch.sh
```

Voilà ! 🎮

---

## 🎮 Votre Première Partie

### 1. Menu Principal

Quand vous lancez, vous verrez :

```
════════════════════════════════════════
  BORNE D'ARCADE 🎮
════════════════════════════════════════

Ball Blast
Columns
CursedWare
DinoRail
InitialDrift
JavaSpace
Kowasu_Renga
Minesweeper
OsuTile
PianoTile
Pokechecs
Pong
Puissance_X
Snake_Eater
TronGame

════════════════════════════════════════
Flèches: Naviguer | ACTION: Jouer
════════════════════════════════════════
```

### 2. Sélectionner un Jeu

- **Haut/Bas** : Sélectionner
- **Action** : Lancer

!!! info "Premier Jeu?"
    Si c'est votre première fois, essayez **Pong** - c'est simple et amusant !

### 3. Jouer

Chaque jeu a ses propres contrôles. Vous erez les instructions à l'écran.

### 4. Score Sauvegardé

Votre score est **automatiquement sauvegardé**. Pas besoin de clickClicker nulle part !

---

## ⌨️ Contrôles de Base

| Touche | Action |
|--------|--------|
| ↑ | Haut |
| ↓ | Bas |
| ← | Gauche |
| → | Droite |
| **Z** | Action |
| **X** | Action 2 |
| **Entrée** | Menu |
| **Échap** | Retour |

Voir le [guide complet des contrôles](controls.md).

---

## 🎮 5 Jeux pour Débuter

### 1. **Pong** - Classique Éternel
- Très simple : just paddle la balle
- Parfait pour tester les contrôles
- ⏱️ 5 minutes par partie

### 2. **Snake Eater** - Serpent Affamé
- Déplacez le serpent, mangez les fruits
- Contrôles directs (Haut/Bas/Gauche/Droite)
- Augmente en difficulté

### 3. **Columns** - Puzzle
- Aligner 3 gemmes de même couleur
- Bon pour réfléchir
- 2 joueurs possible

### 4. **Ball Blast** - Action
- Tirez sur les boules
- Explorez les power-ups
- Score élevé = défi !

### 5. **DinoRail** - Réflexes
- Évitez les obstacles
- Course infinie
- Tester vos réflexes

---

## 🆘 "Ça ne marche pas"

### Java pas trouvé
```bash
# Installer depuis https://www.oracle.com/java/technologies/downloads/
# Puis redémarrer le terminal
```

### Fenêtre ne s'ouvre pas
```bash
# Essayer directement via Java
java -cp .:./MG2D.jar Main
```

### Joystick pas reconnu
- Redémarrer la borne
- Le joystick devrait être détecté automatiquement

Voir le [guide de dépannage complet](troubleshooting.md).

---

## 📊 Votre Progression

Une fois lancé, vous avez accès à :

✅ **15 Jeux** différents
✅ **Highscores** sauvegardés automatiquement
✅ **Système de Points** persistant
✅ **Contrôles Arcade** intuitifs

---

## 📚 Prochaines Étapes

- **Vous voulez plus d'infos ?** → [Guide Utilisateur Complet](overview.md)
- **Besoin d'aide ?** → [FAQ](../faq.md)
- **Vous avez un problème ?** → [Dépannage](troubleshooting.md)
- **Vous êtes développeur ?** → [Doc Technique](../documentation-technique/architecture.md)

---

## 🎯 Objectifs de Score

Chaque jeu a ses propres objectifs. Voici quelques cibles :

| Jeu | Objectif |
|-----|----------|
| **Pong** | 5 points |
| **Snake** | 100 points |
| **Ball Blast** | 500 points |
| **Columns** | 1000 points |
| **Minesweeper** | Tous les carrés |

---

## 💾 Où Sont Mes Scores ?

Vos scores sont dans :
```
projet/[jeu]/highscore
```

Fichier texte simple avec format :
```
1000 - Votre Nom
800 - Autre Joueur
500 - C'est Vous !
```

---

## 🎉 Prêt ?

Lancez la borne :

```bash
./launch.sh
```

Et amusez-vous ! 🎮

---

**Tips:** Vous pouvez toujours revenir au menu en appuyant sur **Échap** dans n'importe quel jeu.

---

Besoin d'aide supplémentaire ? Consultez la [FAQ](../faq.md) !
