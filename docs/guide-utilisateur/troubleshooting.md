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

2. Si vous voyez "command not found" :
   - **Windows:** Télécharger Java depuis [oracle.com](https://www.oracle.com/java/technologies/downloads/)
   - **Mac:** `brew install openjdk@17`
   - **Linux:** `sudo apt-get install openjdk-17-jre`

3. Redémarrer le terminal après installation

---

### "MG2D.jar not found"

**Symptôme:** Erreur lors du lancement  
**Cause:** Le fichier `MG2D.jar` est manquant ou au mauvais endroit

**Solution:**

1. Vérifier que le fichier existe :
```bash
ls -la MG2D.jar  # Linux/Mac
dir MG2D.jar     # Windows
```

2. Si manquant :
   - Télécharger à nouveau le projet
   - Ne JAMAIS supprimer ce fichier
   - S'assurer qu'il est à la racine du projet

3. Vérifier les chemins (doit être à côté de `Main.java`)

---

### "Permission denied" (Linux/Mac)

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

---

### "class ... is public, should be declared in file..."

**Symptôme:** Erreur de compilation  
**Cause:** Classe publique mal nommée

**Solution:**

Vérifier que le nom de fichier Java correspond au nom de la classe publique :

```java
// Fichier MonClasse.java
public class MonClasse {  // ✅ Correct
}

// Fichier MonClasse.java
public class AutreClasse {  // ❌ Erreur !
}
```

---

## 🔴 Problèmes Graphiques

### "Fenêtre ne s'ouvre pas"

**Symptôme:** Rien n'apparaît à l'écran  
**Cause:** Plusieurs possibilités...

**Solutions à essayer:**

1. Vérifier les erreurs dans le terminal :
```bash
java -cp .:./MG2D.jar Main
# Chercher les messages d'erreur
```

2. Vérifier l'espace disque :
```bash
df -h  # Linux/Mac
diskpart  # Windows
```

3. Redémarrer la borne et Java:
```bash
# Tuer tous les processus Java
pkill java

# Relancer
./launch.sh
```

---

### "Resolution incorrecte" ou "écran noir"

**Symptôme:** L'affichage n'est pas bon  
**Cause:** Résolution incompatible

**Solution:**

Éditer `Arcade/Graphique.java` :

```java
private int TAILLEX = 1280;  // Changer la largeur
private int TAILLEY = 1024;  // Changer la hauteur
```

Essayer des résolutions standards :
- 1024 x 768
- 1280 x 1024
- 1920 x 1080

Puis recompiler.

---

## 🔴 Problèmes de Contrôles

### "Touches ne répondent pas"

**Symptôme:** Les touches du clavier ne fonctionnent pas  
**Cause:** Focus perdu ou problème de clavier

**Solution:**

1. Cliquer dans la fenêtre pour récupérer le focus
2. Appuyer sur Entrée pour activer les contrôles
3. Essayer à nouveau

---

### "Joystick non reconnu"

**Symptôme:** Le joystick n'est pas détecté  
**Cause:** Problème d'USB ou pilotes

**Solutions:**

1. Débrancher et rebrancher le joystick
2. Redémarrer la borne
3. Appuyer sur un bouton du joystick
4. Vérifier les pilotes USB :
   - **Windows:** Gestionnaire d'appareils
   - **Mac:** Aucun pilote spécial nécessaire
   - **Linux:** `lsusb` pour voir les appareils

---

### "Contrôles inversés"

**Symptôme:** Les touches ne correspondent pas  
**Cause:** Clavier AZERTY vs QWERTY

**Solution:**

Les touches sont **mappées logiquement** (Z = Action) et devraient fonctionner peu importe votre clavier. Si ce n'est pas le cas, éditer `Arcade/ClavierBorneArcade.java`.

---

## 🔴 Problèmes de Performance

### "Ça lag / c'est lent"

**Symptôme:** FPS bas, jeu saccadé  
**Cause:** Ressources insuffisantes

**Solutions:**

1. Fermer les autres applications
2. Cloner le disque (libérer de l'espace)
3. Redémarrer le système
4. Réduire la résolution :
   - Éditer `Arcade/Graphique.java`
   - Réduire `TAILLEX` et `TAILLEY`

---

### "Utilisation CPU/RAM élevée"

**Symptôme:** Processus Java consomme beaucoup  
**Cause:** Boucle infinie ou allocation mémoire excessive

**Solution:**

1. Identifier le processus :
```bash
top    # Linux/Mac
tasklist | findstr java  # Windows
```

2. Tuer le processus :
```bash
pkill java
```

3. Signaler le bug avec détails → [Issues GitHub](https://github.com/Zwartkat/borne/issues)

---

## 🔴 Problèmes de Données

### "Mes scores ne se sauvegardent pas"

**Symptôme:** Score perdu après relance  
**Cause:** Permissions ou chemin incorrect

**Solution:**

1. Vérifier le dossier :
```bash
ls -la projet/[jeu]/highscore
```

2. Vérifier les permissions :
```bash
chmod 666 projet/[jeu]/highscore
```

3. Vérifier l'espace disque (voir au-dessus)

---

### "Le fichier highscore est corrompu"

**Symptôme:** Erreur lors du chargement des scores  
**Cause:** Fichier endommagé

**Solution:**

1. Sauvegarder le fichier actuel (au cas où)
2. Supprimer le fichier :
```bash
rm projet/[jeu]/highscore
```

3. Relancer le jeu - il créer un nouveau fichier

---

## 🔴 Problèmes Spécifiques aux Jeux

### "Jeu crash au démarrage"

**Symptôme:** Exception ou segfault  
**Cause:** Plusieurs possibilités

**Solution:**

1. Regarder l'erreur dans la console
2. Essayer un autre jeu
3. Signaler le bug → [GitHub Issues](https://github.com/Zwartkat/borne/issues)
4. Inclure :
   - Système d'exploitation
   - Version Java
   - Numéro d'erreur exact

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
# Installer Love2D
# Voir https://love2d.org/

# Ou compiler manuellement
cd projet/CursedWare
love .
```

---

## 🔴 Problèmes GitHub

### "git command not found"

**Symptôme:** Impossible de cloner le repo  
**Cause:** Git pas installé

**Solution:**

- **Windows:** Télécharger depuis [git-scm.com](https://git-scm.com/)
- **Mac:** `brew install git`
- **Linux:** `sudo apt-get install git`

---

## ✅ Vérification de Santé du Système

Exécuter ce script pour vérifier tout :

```bash
#!/bin/bash
echo "=== Vérification Système ==="
echo "Java:"
java -version

echo "Dossiers clés:"
ls -d Arcade projet docs

echo "Fichiers clés:"
ls MG2D.jar *.java

echo "Permissions:"
ls -l launch.sh compilation.sh

echo "Espace disque:"
df -h

echo "ProcessJava en cours:"
ps aux | grep java

echo "=== OK si tout est ci-dessus ==="
```

---

## 📞 Besoin d'Aide Supplémentaire ?

1. **FAQ:** [Consultez la FAQ](../faq.md)
2. **Issues:** [Ouvrir une issue GitHub](https://github.com/Zwartkat/borne/issues)
3. **Discussions:** [Discussions GitHub](https://github.com/Zwartkat/borne/discussions)

---

## 🎯 Procédure Complète de Resetup

Si rien ne fonctionne, faire un reset :

```bash
# 1. Supprimer le repo
rm -rf borne

# 2. Récloner
git clone https://github.com/Zwartkat/borne.git
cd borne

# 3. Vérifier Java
java -version

# 4. Recompiler
bash compilation.sh

# 5. Relancer
./launch.sh
```

---

**Problème non résolu ?** Consultez la [FAQ](../faq.md) ou ouvrez une [issue](https://github.com/Zwartkat/borne/issues) !

---

**Dernière mise à jour:** 2025
