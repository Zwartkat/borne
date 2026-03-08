# Borne d'Arcade - Documentation

Bienvenue dans la documentation complète de la **Borne d'Arcade** !

Cette documentation couvre tous les aspects du projet : du guide utilisateur à la documentation technique pour les développeurs.

## Sections Principales

### [Guide Utilisateur](guide-utilisateur/overview.md)
Pour les utilisateurs finaux qui veulent jouer et comprendre comment utiliser la borne.

-  [Contrôles](guide-utilisateur/controls.md)
-  [Installation](guide-utilisateur/installation.md)

###  Documentation Technique
Pour les développeurs qui veulent comprendre l'architecture et le code.

- 📦 [Module Arcade](documentation-technique/arcade-module.md)
- 📚 [Build & Compilation](documentation-technique/build.md)

---

## 🌟 Caractéristiques

- **14 jeux** développés en Java, Python et Lua
- **Système de scores** persistant avec leaderboards
- **Contrôles arcade** mappés pour joystick
- **Documentation automatisée** via GitHub Pages
- **CI/CD complet** avec tests automatiques

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

## 🤝 Contribuer

Vous voulez contribuer ? Excellent ! Consultez le guide [Contribuer](contributing.md) pour :

- Comment signaler un bug
- Comment proposer une fonctionnalité
- Comment faire une pull request
- Conventions de code

---

## 📄 Licence

Ce projet est sous licence libre. 

---

## 📞 Support

- 🐛 **Bug ?** → Ouvrez une [issue GitHub](https://github.com/Zwartkat/borne/issues)
- 💡 **Idée ?** → Créez une [discussion](https://github.com/Zwartkat/borne/discussions)

---

**Dernière mise à jour:** 2026
**Version de la documentation:** v1.1.0
