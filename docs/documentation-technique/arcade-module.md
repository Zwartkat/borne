# Module Arcade

Documentation détaillée du module core Arcade.

## 📦 Vue d'Ensemble

Le module Arcade est le cœur de la Borne. Il gère :

- Affichage principal
- Menu et navigation
- Chargement des jeux
- Systèmes de scores
- Entrées utilisateur

## 🏗️ Classes Principales

### Graphique.java
Gestion graphique et interface utilisateur principale.

**Responsabilités:**
- Fenêtre plein écran
- Rendu des éléments
- Gestion des états

### Menu.java
Menu de sélection des jeux.

### BoiteSelection.java
Navigation entre les jeux avec pointeur.

### Bouton.java
Composant bouton réutilisable.

### HighScore.java
Persistence et gestion des scores.

### ClavierBorneArcade.java
Gestion des entrées (clavier, joystick).

## 📚 Documentation Complète

Voir [architecture.md](architecture.md) pour le diagramme UML complet.

## 🧪 Tests

Les tests sont dans `Arcade/Test*.java`.

```bash
# Exécuter les tests
cd Arcade
javac -cp .:../lib/*:../MG2D.jar Test*.java
java -cp .:../lib/*:../MG2D.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## 🔗 Sujets Connexes

- [Architecture Globale](architecture.md)
- [Guide de Développement](development.md)
- [Build et Compilation](build.md)
- [API Reference](api-reference.md)
