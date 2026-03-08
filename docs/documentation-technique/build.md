# Build et Compilation - Guide Complet

Guide pour compiler la Borne d'Arcade.

## Compilation

### Avec le Script

```bash
# Linux 
bash compilation.sh
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
```

Concernant la compilation des jeux, référez vous à `update-games.sh`, il compile les jeux java et installe les dépendances python présentes dans un `requirements.txt`

## ℹ️ Commandes Disponibles

- `compilation.sh` - Compile tout
- `update-games.sh` - Compile uniquement les jeux
- `clean.sh` - Nettoie les fichiers compilés
- `./launch.sh` - Lance la borne