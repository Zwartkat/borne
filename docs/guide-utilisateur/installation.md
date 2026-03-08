# Installation - Guide Complet

Guide d'installation de la Borne d'Arcade pour tous les systèmes d'exploitation.

## 📋 Prérequis

### Système d'Exploitation
- ✅ Raspberry (de préférence)
- ✅ Linux 

### Logiciels Requis
- Java
- Python
- Love (lua)

---

## 🖥️ Installation par Système

### Linux / Raspberry

Dans le répertoire que vous souhaitez

```bash

curl -L -O https://github.com/Zwartkat/borne/releases/latest/download/installer.sh

chmod +x ./installer.sh

sudo ./installer.sh
```

Après l'installation des dépendances, vous devrez renseigner le chemin d'installation

Ensuite, toute la borne sera téléchargée puis configurée.

---

## 🐛 Dépannage de l'Installation

### "Permission denied"
```bash
sudo chmod +x ./installer.sh
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
├── installer.sh        # Script d'installation
├── compilation.sh      # Script de compilation
├── update-games.sh     # Script d'actualisation des jeux
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
---

## 🆘 Support

- 🐛 [Dépannage](troubleshooting.md)
- 💬 [Issues GitHub](https://github.com/Zwartkat/borne/issues)
