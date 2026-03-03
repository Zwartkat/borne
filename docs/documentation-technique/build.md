# Build et Compilation - Guide Complet

Guide pour compiler la Borne d'Arcade.

## 🔨 Compilation Basique

### Avec le Script

```bash
# Linux / Mac
bash compilation.sh

# Windows
compilation.bat
```

Ce script compile:
1. Le module Arcade
2. Le module Main
3. Tous les jeux individuels

### Manuellement

```bash
# Compiler Arcade
cd Arcade
javac -cp .;../MG2D.jar *.java

# Compiler Main
cd ..
javac -cp .;./MG2D.jar *.java

# Compiler un jeu
cd projet/Columns
javac -cp .;../..;../../MG2D.jar *.java
```

## 🏗️ Avec Maven (Optionnel)

```bash
mvn clean compile
mvn package
```

## ℹ️ Commandes Disponibles

- `compilation.sh` - Compile tout
- `clean.sh` - Nettoie les fichiers compilés
- `./launch.sh` - Lance la borne

## 📚 Documentation

- [Architecture](architecture.md)
- [Développement](development.md)
- [Tests](tests.md)
