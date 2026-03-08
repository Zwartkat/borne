# Dépannage - Résolution des Problèmes

Guide pour résoudre les problèmes courants avec la Borne d'Arcade.

## 🔴 Problèmes de Démarrage

### "Java command not found"

**Symptôme:** L'erreur apparaît au démarrage  
**Cause:** Java n'est pas installé ou pas dans le PATH

**Solution:**

1. Vérifier que Java est installé :
```bash
java -version
```

1. Si vous voyez "command not found" :
   - **Linux:** `sudo apt-get install openjdk-21-jdk`


---

### "MG2D.jar not found"

**Symptôme:** Erreur lors du lancement  
**Cause:** Le fichier `MG2D.jar` est manquant ou au mauvais endroit

**Solution:**

1. Vérifier que le fichier existe

2. Si manquant :
   - Télécharger à nouveau le projet
   - Ne JAMAIS supprimer ce fichier
   - S'assurer qu'il est à la racine du projet

3. Vérifier les chemins (doit être à côté de `Main.java`)

---

### "Permission denied" (Linux)

**Symptôme:** Impossible d'exécuter `launch.sh`  
**Cause:** Fichier sans permissions d'exécution

**Solution:**

```bash
# Donner les permissions
chmod +x launch.sh
chmod +x compilation.sh

# Essayer à nouveau
./launch.sh
```

---

## 🔴 Problèmes de Compilation

### "cannot find symbol" ou "class not found"

**Symptôme:** Erreurs de compilation  
**Cause:** Dépendances manquantes ou mauvais classpath

**Solution:**

```bash
# S'assurer que MG2D.jar est présent
ls MG2D.jar

# Recompiler avec le bon classpath
bash compilation.sh

# Ou manuellement :
cd Arcade
javac -cp .;../MG2D.jar *.java
cd ..
javac -cp .;./MG2D.jar *.java
```

### "❌ sur un jeu lors de la compilation"

Le jeu n'a pas eu être chargé comme il le devait

Problèmes possibles
#### Java
1. Erreur Java
2. Problèmes d'importation de dépendances (vérifiez que vos dépendances sont dans libs/)

#### Python
   1.  Le fichier `requirements.txt` n'existe pas dans le répertoire du jeu

#### Lua

Aucun problème possible

---

## 🔴 Problèmes de Contrôles

### "Touches ne répondent pas"

**Symptôme:** Les touches du clavier ne fonctionnent pas  
**Cause:** Focus perdu ou problème de clavier

**Solution:**

1. Cliquer dans la fenêtre pour récupérer le focus
2. Vérifier si le mappage des touches est fonctionnel
3. Essayer à nouveau en réinstallant

---

## 🔴 Problèmes de Performance

### "Ça lag / c'est lent"

**Symptôme:** FPS bas, jeu saccadé  
**Cause:** Ressources insuffisantes

**Solutions:**

1. Fermer les autres applications
2. Redémarrer le système
3. Utiliser un appareil plus puissant

---

## 🔴 Problèmes Spécifiques aux Jeux

### "Jeu crash au démarrage"

**Symptôme:** Exception
**Cause:** Plusieurs possibilités

**Solution:**

1. Regarder l'erreur dans la console
2. Essayer un autre jeu
3. Signaler le bug → [GitHub Issues](https://github.com/Zwartkat/borne/issues)
4. Inclure :
   - Système d'exploitation (version)
   - Version Java
   - Erreur

---

### "Python game ne démarre pas"

**Symptôme:** Ball Blast (Python) n'ouvre pas  
**Cause:** Python ou pygame manquant

**Solution:**

```bash
# Installer Python 3
# Voir https://www.python.org/

# Installer pygame
pip install pygame

# Relancer
```

---

### "Lua game crash"

**Symptôme:** CursedWare ne démarre pas  
**Cause:** Love2D pas installé

**Solution:**

```bash
sudo apt install love
cd projet/CursedWare
love .
```

## 🎯 Procédure Complète de Resetup

Si rien ne fonctionne, faire un reset :

```bash
# 1. Supprimer le repo
rm -rf borne

curl 
```

---

**Problème non résolu ?** Ouvrez une [issue](https://github.com/Zwartkat/borne/issues) !

---

**Dernière mise à jour:** 2026
