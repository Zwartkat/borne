# Documentation Technique - Architecture

Présentation de l'architecture générale de la Borne d'Arcade.

## 🏗️ Architecture Globale

### Vue d'Ensemble

```
┌─────────────────────────────────────────┐
│        Borne d'Arcade                   │
├─────────────────────────────────────────┤
│                                         │
│  ┌──────────────────────────────────┐  │
│  │     Couche Présentation          │  │
│  │  (Graphique.java)                │  │
│  └──────────────────────────────────┘  │
│           ↓                              │
│  ┌──────────────────────────────────┐  │
│  │   Couche Métier / Contrôle      │  │
│  │  (Menu, BoiteSelection, etc)    │  │
│  └──────────────────────────────────┘  │
│           ↓                              │
│  ┌──────────────────────────────────┐  │
│  │    Couche Données                │  │
│  │  (HighScore, Game)               │  │
│  └──────────────────────────────────┘  │
│           ↓                              │
│  ┌──────────────────────────────────┐  │
│  │   Bibliothèque MG2D              │  │
│  │  (Graphiques, Physique, Audio)   │  │
│  └──────────────────────────────────┘  │
│                                         │
└─────────────────────────────────────────┘
```

---

## 📦 Modules Principaux

### 1. Module Arcade (Core)

**Emplacement:** `Arcade/`

**Classes principales:**

```
Arcade/
├── Main.java              # Point d'entrée
├── Graphique.java         # Gestion graphique et UI
├── Game.java              # Interface pour les jeux
├── Menu.java              # Menu principal
├── BoiteSelection.java    # Sélecteur de jeu
├── Bouton.java            # Composant bouton
├── Pointeur.java          # Pointeur de sélection
├── HighScore.java         # Gestion des scores
├── LigneHighScore.java    # Ligne de score
├── ClavierBorneArcade.java # Gestion contrôles
└── Test*.java             # Tests JUnit
```

**Responsabilités:**
- ✅ Affichage principal
- ✅ Gestion du menu
- ✅ Chargement des jeux
- ✅ Persistence des scores
- ✅ Entrées utilisateur (clavier, joystick)

---

### 2. Jeux Individuels

**Emplacement:** `projet/`

**Structure par jeu:**

```
projet/[jeu]/
├── *.java / *.py / *.lua  # Code source
├── description.txt         # Description du jeu
├── bouton.txt             # Contrôles
├── highscore              # Fichier des scores
├── requirements.txt       # Dépendances (Python)
├── README.md             # Documentation
├── assets/               # Ressources
├── fonts/                # Polices
├── img/                  # Images
└── sounds/               # Audiages
```

---

### 3. Dépendances Externes

```
lib/
├── MG2D.jar              # Bibliothèque graphique
│   ├── Rendering         # Rendu 2D
│   ├── Geometry          # Géométrie
│   ├── Audio            # Son
│   └── Physics          # Physique
└── JUnit.jar (tests)    # Framework de tests
```

---

## 🔗 Flux de Données

### Démarrage

```
Main.main()
  ↓
Graphique.init()          // Initialiser fenêtre
  ↓
FenetrePleinEcran        // Créer fenêtre plein écran
  ↓
ClavierBorneArcade       // Enregistrer contrôles
  ↓
Menu.afficher()          // Afficher menu principal
  ↓
BoiteSelection           // Permet sélection jeux
```

### Sélection et Lancement d'un Jeu

```
BoiteSelection.selection()
  ↓
clavier.getTouche()      // Lire entrée utilisateur
  ↓
pointeur.setValue()      // Mettre à jour sélection
  ↓
Graphique.getGame()      // Charger le jeu
  ↓
Game.run()               // Exécuter le jeu
  ↓
Graphique.afficher()     // Afficher le jeu
  ↓
clavier.getJoystick()    // Contrôles du jeu
  ↓
Game.update()            // Logique du jeu
  ↓
HighScore.save()         // Persister les scores
```

---

## 📊 Diagramme UML Simplifié

```
┌─────────────────┐
│   Graphique     │◇──┐
└─────────────────┘   │
        │             │
        │      ┌──────────────────┐
        │      │  BoiteSelection  │
        │      └──────────────────┘
        │             │
        └─────────────┼─────────┐
                      ↓         ↓
          ┌─────────────────┐  ┌─────────────┐
          │  Menu           │  │  Bouton     │
          └─────────────────┘  └─────────────┘
                      ↓
          ┌─────────────────┐
          │  Game (Jeux)    │
          └─────────────────┘
                      ↓
          ┌─────────────────┐
          │  HighScore      │
          └─────────────────┘
```

---

## 💾 Gestion des Données

### Fichiers de Configuration

```
projet/[jeu]/
├── description.txt       # Format: "Titre\nPar Auteur"
├── bouton.txt           # Format: "Mouvement:Action:..."
├── highscore            # Format: "Score:Nom" (texte)
└── requirements.txt     # Format: pip packages
```

### Persistence des Scores

```java
// Chemin des scores
projet/[jeu]/highscore

// Format du fichier
1000 - Joueur1
800  - Joueur2
650  - Joueur3
```

---

## 🎮 Cycle de Vie d'une Partie

```
┌───────────────┐
│ Sélection     │
│ du jeu        │
└───────┬───────┘
        ↓
┌───────────────┐
│ Initialisation│
│ du jeu        │
└───────┬───────┘
        ↓
┌───────────────┐      ┌──────────────┐
│ Boucle        │──────│ Gestion      │
│ de jeu        │      │ des entrées  │
│ (Game loop)   │      └──────────────┘
└───────┬───────┘
        ↓
┌───────────────┐      ┌──────────────┐
│ Mise à jour   │──────│ Détection    │
│ de l'état     │      │ fin de partie│
└───────┬───────┘      └──────────────┘
        ↓
┌───────────────┐      ┌──────────────┐
│ Rendu         │──────│ Affichage    │
│ (Dessiner)    │      │ graphique    │
└───────┬───────┘      └──────────────┘
        ↓
┌───────────────┐
│ Fin de partie │
│ (Win/Lose)    │
└───────┬───────┘
        ↓
┌───────────────┐
│ Sauvegarde    │
│ du score      │
└───────┬───────┘
        ↓
┌───────────────┐
│ Retour au     │
│ menu          │
└───────────────┘
```

---

## 🔄 Interactions Clés

### Menu ↔ Jeu

1. **Menu choisi → Jeu lancé**
   ```java
   Game game = Graphique.getGame(selectedGame);
   game.run();
   ```

2. **Jeu terminé → Menu revient**
   ```java
   if (game.isFinished()) {
       HighScore.save(game.getScore());
       return; // Retour au menu
   }
   ```

### Clavier ↔ Jeu

1. **Entrée utilisateur**
   ```java
   ClavierBorneArcade clavier = ...;
   if (clavier.getJoyJ1HautTape()) {
       player.moveUp();
   }
   ```

---

## 🎯 Patterns de Conception

### 1. **MVC** (Model-View-Controller)
- **Model** : Logique des jeux, HighScore
- **View** : Graphique, Bouton, UI
- **Controller** : ClavierBorneArcade, Menu

### 2. **Singleton**
```java
// Graphique est un singleton
private static Graphique instance = new Graphique();
```

### 3. **Strategy**
```java
// Chaque jeu implémente Game
public interface Game {
    void run();
    int getScore();
}
```

### 4. **Observer**
- L'affichage observe les changements d'état
- Les scores observent les fins de partie

---

## 🔐 Considérations Techniques

### Thread Safety
- Utilisation de `volatile` pour les variables partagées
- Pas de multithreading complexe (affichage single-threaded)

### Performance
- Boucle de jeu à 60 FPS
- Optimisation graphique via MG2D
- Gestion légère de la mémoire

### Portabilité
- Code JVM (java bytecode)
- Compatible Windows, Mac, Linux
- Dépendance unique : Java 11+

---

## 📈 Évolutivité

### Ajouter un Nouveau Jeu

1. **Créer dossier** : `projet/[Nouveau_Jeu]/`
2. **Implémenter** l'interface `Game`
3. **Ajouter description** : `description.txt`
4. **Ajouter contrôles** : `bouton.txt`
5. **Enregistrer** dans le menu
6. **Tester** avec le framework

### Étendre le Module Core

- Ajouter des composants UI dans `Graphique`
- Créer de nouvelles boîtes (extends `Boite`)
- Étendre `ClavierBorneArcade` pour plus de touches

---

## 🔗 Références

- [Module Arcade](arcade-module.md) - Détails des classes core
- [Development](development.md) - Guide développement
- [API Reference](api-reference.md) - Documentation complète

---

**Architecture V1.0 - Stable et Maintenable** ✅
