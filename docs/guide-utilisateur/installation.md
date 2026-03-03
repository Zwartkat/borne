# Installation - Guide Complet

Guide d'installation de la Borne d'Arcade pour tous les systèmes d'exploitation.

## 📋 Prérequis

### Système d'Exploitation
- ✅ Windows 10/11
- ✅ macOS 10.12+
- ✅ Linux (Ubuntu, Debian, Fedora, etc.)

### Logiciels Requis

#### Java
- **Java Runtime Environment (JRE)** 11 ou supérieur
- [Télécharger Java](https://www.oracle.com/java/technologies/downloads/)

```bash
# Vérifier la version Java
java -version
```

!!! success "Java Installé?"
    Vous devez voir une version 11 ou supérieure.

### En Option (Pour Développeurs)
- Java Development Kit (JDK) 17
- Maven 3.8+
- Git

---

## 🖥️ Installation par Système

### Windows

#### 1. Installer Java
```bash
# Télécharger depuis https://www.oracle.com/java/technologies/downloads/
# Choisir "Windows x64 Installer"
# Suivre l'installation
```

#### 2. Cloner le Projet
```bash
# Option 1 : Avec Git
git clone https://github.com/Zwartkat/borne.git
cd borne

# Option 2 : Télécharger le ZIP
# Aller sur https://github.com/Zwartkat/borne
# Cliquer "Code" → "Download ZIP"
# Extraire le fichier
```

#### 3. Lancer la Borne
```bash
# Double-cliquer sur launch.sh dans l'explorateur
# OU via terminal :
cmd /c launch.sh
```

---

### macOS

#### 1. Installer Java
```bash
# Avec Homebrew (recommandé)
brew install openjdk@17

# Ou télécharger depuis https://www.oracle.com/java/technologies/downloads/
```

#### 2. Cloner le Projet
```bash
git clone https://github.com/Zwartkat/borne.git
cd borne
```

#### 3. Donner les Permissions
```bash
chmod +x launch.sh
```

#### 4. Lancer la Borne
```bash
./launch.sh
```

---

### Linux

#### 1. Installer Java
```bash
# Ubuntu/Debian
sudo apt-get install openjdk-17-jre-headless

# Fedora/RHEL
sudo dnf install java-17-openjdk

# Arch
sudo pacman -S jdk-openjdk
```

Vérifier l'installation :
```bash
java -version
```

#### 2. Cloner le Projet
```bash
git clone https://github.com/Zwartkat/borne.git
cd borne
```

#### 3. Donner les Permissions
```bash
chmod +x launch.sh
chmod +x compilation.sh
```

#### 4. Lancer la Borne
```bash
./launch.sh
```

---

## 🎯 Vérification de l'Installation

Après l'installation, vérifier que tout fonctionne :

```bash
# 1. Vérifier Java
java -version

# 2. Compiler le projet
bash compilation.sh

# 3. Vérifier les fichiers
ls -la Arcade/

# 4. Lancer un test
java -cp .:./MG2D.jar Main
```

!!! success "Tout Fonctionnel?"
    Si vous voyez le menu de la borne, l'installation est correcte ! 🎉

---

## 🐛 Dépannage de l'Installation

### "Java command not found"
```bash
# Vérifier que Java est installé
java -version

# Sur Windows, ajouter Java au PATH
# Propriétés système → Variables d'environnement → Ajouter JAVA_HOME
```

### "MG2D.jar not found"
```bash
# Vérifier que le fichier existe
ls -la *.jar

# Ne pas supprimer ce fichier !
```

### "Permission denied" (Linux/Mac)
```bash
# Donner les permissions
chmod +x *.sh
```

### Port déjà utilisé
```bash
# Si le port 1099 est utilisé
# Terminer le processus Java existant
# Ou changer le port dans le code
```

Voir le [guide complet de dépannage](troubleshooting.md).

---

## 📂 Structure des Fichiers Après Installation

```
borne/
├── Arcade/              # Module core
│   ├── *.java
│   └── *.class
├── projet/              # 15 jeux
│   ├── ball-blast/
│   ├── columns/
│   └── ...
├── MG2D.jar            # Bibliothèque graphique (IMPORTANT)
├── launch.sh           # Script de lancement
├── compilation.sh      # Script de compilation
├── Main.java           # Point d'entrée
└── docs/               # Documentation
```

---

## 🚀 Premiers Démarrages

### Premier Démarrage
```bash
./launch.sh

# Vous verrez :
# 1. Une fenêtre de chargement
# 2. Le menu principal
# 3. Une liste des 15 jeux
# 4. Des instructions à l'écran
```

### Configuration Initiale
- Les scripts du menu créent les dossiers nécessaires
- Les fichiers de scores seront créés automatiquement
- Les ressources (images, sons) seront chargées au démarrage

---

## ✨ Configuration Optionnelle

### Paramètres de Lancement
```bash
# Modifier dans launch.sh ou Main.java
java -cp .:./MG2D.jar Main \
    -Xmx512m \                    # Mémoire max
    -Xms256m \                    # Mémoire min
    -D...                         # Options système
```

### Résolution de l'Écran
Éditer `Arcade/Graphique.java` :
```java
private int TAILLEX = 1280;  // Largeur
private int TAILLEY = 1024;  // Hauteur
```

---

## 🆘 Support

- 📖 [FAQ](../faq.md)
- 🐛 [Dépannage](troubleshooting.md)
- 💬 [Issues GitHub](https://github.com/Zwartkat/borne/issues)
- 📧 [Email Support](mailto:support@example.com)

---

## 🎉 C'est Prêt !

Une fois installé et lancé, vous verrez :

```
=====================================
  BORNE D'ARCADE 🎮
=====================================

Sélectionnez un jeu :
> Ball Blast
  Columns
  CursedWare
  ...

Appuyez sur ACTION pour jouer
Appuyez sur RETOUR pour quitter
=====================================
```

**Amusez-vous bien ! 🎮**
