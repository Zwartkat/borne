# 🎮 Borne Arcade - Retro Gaming Platform

Une borne d'arcade rétro multimédia programmée en Java, Python et Lua pour Raspberry Pi avec support 2 joueurs.

## 📋 Table des matières

- [Vue d'ensemble](#-vue-densemble)
- [Jeux disponibles](#-jeux-disponibles)
- [Spécifications techniques](#-spécifications-techniques)
- [Installation](#-installation)
- [Contrôles](#-contrôles)
- [Contribution](#-contribution)

---

## 🎯 Vue d'ensemble

**Borne Arcade** est un projet pluridisciplinaire développé à l'IUT du Littoral Côte d'Opale. Il s'agit d'une plateforme de jeu rétro pour arcade personnalisée, permettant de jouer à plusieurs jeux écrits en différents langages (Java, Python, Lua).

### Caractéristiques principales
- Gestion de 2 joueurs simultanés
- Support de 3 langages : Java, Python, Lua

---

## 🎮 Jeux disponibles

| Jeu | Langage |
|-----|---------|
| **Ball Blast** | Python |
| **Columns** | Python |
| **CursedWare** | Lua |
| **DinoRail** | Java |
| **InitialDrift** | Java |
| **JavaSpace** | Java |
| **Kowasu Renga** | Python |
| **Minesweeper** | Python |
| **OsuTile** | Python |
| **PianoTile** | Python |
| **Pong** | Java |
| **Puissance X** | Java |
| **Snake Eater** | Python |
| **TronGame** | Java |

---

## 💻 Spécifications techniques

### Configuration matérielle **recommandée**
- **Processeur** : Raspberry Pi 3 ou supérieur
- **Écran** : 4:3 (résolution 1280x1024)
- **Entrées** : 2 joysticks + 6 boutons par joueur
- **OS** : Raspbian / Raspberry Pi OS

### Dépendances logicielles
- **Java** : JDK 21+ (OpenJDK)
- **Python** : 3.13+
- **Lua** : 5.4+
- **Git** : pour le versioning

---

## 🚀 Installation

```bash
curl -L -O https://github.com/Zwartkat/borne/releases/latest/download/installer.sh
chmod +x ./installer.sh
sudo ./installer.sh
```

Le script d'installation téléchargera automatiquement toutes les dépendances logicielles et installera la borne à l'endroit souhaité

---

## 🎮 Contrôles

### Joystick Joueur 1
```
Haut/Bas/Gauche/Droite → Flèches du clavier
```

### Joystick Joueur 2
```
o=Haut, l=Bas, k=Gauche, m=Droite
```

### Boutons Joueur 1
```
r=Bouton1, t=Bouton2, y=Bouton3
f=Bouton4, g=Bouton5, h=Bouton6
```

### Boutons Joueur 2
```
a=Bouton1, z=Bouton2, e=Bouton3
q=Bouton4, s=Bouton5, d=Bouton6
```

### Navigation du menu
- **Haut/Bas** (J1) : Sélectionner un jeu
- **Bouton Bas Gauche** (J1) : Lancer un jeu 

### Configuration spéciale


---

## 📁 Architecture

```
borne_arcade/
├── Arcade/                    # Module principal Java
│   ├── Boite.java
│   ├── BoiteDescription.java
│   ├── BoiteSelection.java
│   ├── Bouton.java
│   ├── Pointeur.java
│   ├── Graphique.java
│   ├── Game.java
│   ├── HighScore.java
│   └── ClavierBorneArcade.java
├── projet/                    # Jeux divisés par langage
│   ├── ball-blast/           (Python)
│   ├── Pong/                 (Java)
│   ├── CursedWare/           (Lua)
│   └── ...
├── docs/                      # Documentation technique
├── .github/workflows/         # CI/CD (Tests automatiques)
├── Main.java                  # Point d'entrée
├── MG2D.jar                   # Bibliothèque graphique
└── compilation.sh             # Script de compilation
```

---

### Tests automatisés

Le projet utilise GitHub Actions pour tester les jeux présents:
- ✅ Compilation Java
- ✅ Syntaxe Python
- ✅ Syntaxe Lua

Voir [`.github/workflows/test-games.yml`](.github/workflows/test-games.yml)

---

## 📚 Documentation

- [Documentation technique](docs/documentation-technique/architecture.md)
- [Guide utilisateur](docs/guide-utilisateur/)
- [Guide d'installation développeur](docs/installation-dev/)
---

## 📝 Crédits

**Projet initial** : 
- Romaric Bougard
- Pierre Delobel
- Bastien Ducloy

**Institution** : IUT du Littoral Côte d'Opale (IUTLCO)

**Retravail** :
- Zwartkat

---

## 📧 Support

Pour toute question ou problème :
- Créer une issue
- Voir la [documentation technique](docs/documentation-technique/)

**Dernière mise à jour** : Mars 2026
