# Borne d'Arcade - Documentation

Bienvenue dans la documentation complète de la **Borne d'Arcade** ! 🎮

Cette documentation couvre tous les aspects du projet : du guide utilisateur à la documentation technique pour les développeurs.

## 🎯 Sections Principales

### 📖 [Guide Utilisateur](guide-utilisateur/overview.md)
Pour les utilisateurs finaux qui veulent jouer et comprendre comment utiliser la borne.

- 🎮 [Les jeux disponibles](guide-utilisateur/jeux/overview.md)
- ⌨️ [Contrôles](guide-utilisateur/controls.md)
- 📦 [Installation](guide-utilisateur/installation.md)
- 🚀 [Premiers pas](guide-utilisateur/premiers-pas.md)

### 🛠️ [Documentation Technique](documentation-technique/architecture.md)
Pour les développeurs qui veulent comprendre l'architecture et le code.

- 🏗️ [Architecture](documentation-technique/architecture.md)
- 📦 [Module Arcade](documentation-technique/arcade-module.md)
- 🔧 [Développement](documentation-technique/development.md)
- 📚 [API Reference](documentation-technique/api-reference.md)

### 🔧 [Installation pour Développeurs](installation-dev/setup.md)
Guide complet pour mettre en place l'environnement de développement.

- 🖥️ [Configuration](installation-dev/setup.md)
- 🐍 [Environnement Python](installation-dev/environment.md)
- 📝 [Workflow Git](installation-dev/git-workflow.md)

### ⚡ [GitHub Actions](github-actions/overview.md)
Documentation sur les workflows automatisés du projet.

---

## 🌟 Caractéristiques

- **15 jeux** développés en Java, Python et Lua
- **Système de scores** persistant avec leaderboards
- **Contrôles arcade** mappés pour joystick
- **Documentation automatisée** via GitHub Pages
- **CI/CD complet** avec tests automatiques
- **Code open-source** et bien organisé

---

## 📊 Statistiques du Projet

| Métrique | Valeur |
|----------|--------|
| **Jeux** | 15 |
| **Langages** | Java, Python, Lua |
| **Fichiers** | 100+ |
| **Lignes de code** | 10,000+ |
| **Tests** | JUnit 5 |
| **Documentation** | MkDocs |

---

## 🗂️ Architecture Générale

```
borne_arcade/
├── Arcade/                 # Module core (Java)
├── projet/                 # Jeux individuels
│   ├── ball-blast/        # Python
│   ├── columns/           # Java
│   ├── cursedware/        # Lua
│   └── ...
├── docs/                  # Documentation (markdown)
├── .github/workflows/     # GitHub Actions
└── mkdocs.yml            # Configuration MkDocs
```

---

## 🚀 Démarrage Rapide

### Pour les utilisateurs
```bash
# 1. Cloner le projet
git clone https://github.com/Zwartkat/borne.git

# 2. Suivre le guide d'installation
# Voir: guide-utilisateur/installation.md

# 3. Lancer la borne
./launch.sh
```

### Pour les développeurs
```bash
# 1. Cloner et naviguer
git clone https://github.com/Zwartkat/borne.git
cd borne

# 2. Suivre le guide de configuration
# Voir: installation-dev/setup.md

# 3. Compiler
bash compilation.sh

# 4. Exécuter
java -cp .:./MG2D.jar Main
```

---

## 📚 Navigation

- **Habitude utilisateur ?** → Commencez par le [Guide Utilisateur](guide-utilisateur/overview.md)
- **Développeur ?** → Consultez la [Documentation Technique](documentation-technique/architecture.md)
- **Nouvelle installation ?** → Suivez le [Guide d'Installation](guide-utilisateur/installation.md)
- **Questions fréquentes ?** → Consultez la [FAQ](faq.md)

---

## 🤝 Contribuer

Vous voulez contribuer ? Excellent ! Consultez le guide [Contribuer](contributing.md) pour :

- Comment signaler un bug
- Comment proposer une fonctionnalité
- Comment faire une pull request
- Conventions de code

---

## 📄 Licence

Ce projet est sous licence libre. Consultez le fichier [LICENSE](../LICENSE) pour plus de détails.

---

## 📞 Support

- 🐛 **Bug ?** → Ouvrez une [issue GitHub](https://github.com/Zwartkat/borne/issues)
- 💡 **Idée ?** → Créez une [discussion](https://github.com/Zwartkat/borne/discussions)
- 📧 **Question ?** → Consultez la [FAQ](faq.md)

---

## ✨ Derniers Jeux Ajoutés

1. **Ball Blast** - Détruisez les boules
2. **CursedWare** - Un monde de malédictions
3. **InitialDrift** - Course arcade
4. **Kowasu_Renga** - Puzzle en colonnes

---

**Dernière mise à jour:** 2025
**Version de la documentation:** v1.0.0
