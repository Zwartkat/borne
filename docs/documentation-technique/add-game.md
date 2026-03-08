# Documentation technique — Ajouter un jeu

Ce document décrit la procédure pour ajouter un nouveau jeu dans la borne arcade.

---

# 1. Emplacement du jeu

Tous les jeux doivent être placés dans le dossier :

```
projet/
```

Chaque jeu doit être contenu dans **son propre dossier**.

Exemple :

```
projet/
 ├── snake/
 ├── tetris/
 └── pong/
```

Le nom du dossier correspond au nom du jeu utilisé par la borne.

Eviter les accents et caractères spéciaux

---

# 2. Structure minimale d’un jeu

Chaque dossier de jeu doit contenir les fichiers nécessaires à son exécution.

Exemple :

```
projet/mon-jeu/
 ├── Main.java
 ├── assets/
```

La structure interne dépend du langage utilisé.

---

# 3. Jeux Java

### Conditions

Un jeu Java doit contenir :

* un ou plusieurs fichiers `.java`
* une classe principale dont le nom est exactement celui du dossier

Si il s'agit d'un .jar executable, il suffit de le mettre avec le même nom que le dossier

### Exemple

```
projet/Example/
 ├── Example.java (Main)
 ├── Player.java
 └── Map.java

 projet/Example1/
 └── Example1.jar (Executable)

```

### Fonctionnement

Le système :

1. détecte les fichiers `.java`
2. compile les sources
3. exécute la classe `Main`

La compilation utilise le **classpath de la borne arcade**.

---

# 4. Jeux Python

### Conditions

Un jeu Python doit contenir :

* un fichier `__main__.py` principal dans un dossier src

Optionnel :

* un fichier `requirements.txt`

### Exemple

```
projet/mon-jeu/
 ├── src
 |   ├── /__main__.py
 |   └──game.py
 └── requirements.txt
```

### Fonctionnement

Le système :

1. détecte les fichiers Python
2. installe les dépendances si `requirements.txt` est présent
3. lance le jeu avec `python3`

---

# 5. Jeux Lua

### Conditions

Un jeu Lua doit contenir :

* un fichier `conf.lua`

### Exemple

```
projet/mon-jeu/
 ├── main.lua
 ├── conf.lua
 └── player.lua
```

### Fonctionnement

Le système :

1. détecte la présence de `conf.lua`
2. lance le jeu avec l’interpréteur Lua approprié.

---

# 6. Détection automatique

Le système de lancement analyse automatiquement le dossier du jeu.

Aucun script `.sh` spécifique n’est nécessaire.

Les anciens scripts de lancement par jeu ont été supprimés.

---

# 7. Vérification CI

Lorsqu’un jeu est ajouté :

* GitHub Actions vérifie automatiquement la compilation du jeu.
* Le workflow `test-games.yml` teste les jeux présents dans `projet/`.

Si la compilation échoue, la Pull Request est signalée comme invalide.

---

# 8. Bonnes pratiques

Recommandations :

* garder une structure simple
* placer les assets dans un dossier `assets/`
* ajouter un `README.md`
* éviter les dépendances système spécifiques

---

# 9. Exemple complet

```
projet/
 └── snake/
     ├── Snake.java
     ├── Board.java
     └── assets/
         ├── snake.png
         └── food.png
```

Une fois le dossier ajouté, le jeu sera automatiquement détecté et compilé lors de l'update de la borne. (script `update-games.sh`)
