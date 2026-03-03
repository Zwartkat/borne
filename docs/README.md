# Documentation - Structure et Navigation

Bienvenue dans la documentation de la Borne d'Arcade ! Ce fichier explique la structure.

## 📚 Sections Principales

### 📖 Guide Utilisateur
**Pour:** Joueurs, utilisateurs finaux  
**Contient:**
- Comment installer
- Comment démarrer
- Contrôles et gameplay
- Liste des jeux
- Dépannage

**Accès:** [guide-utilisateur/](guide-utilisateur/overview.md)

### 🛠️ Documentation Technique
**Pour:** Développeurs, contributeurs  
**Contient:**
- Architecture du projet
- Détails des modules
- Guide de développement
- API Reference
- Build et compilation

**Accès:** [documentation-technique/](documentation-technique/architecture.md)

### 🔧 Installation pour Développeurs
**Pour:** Développeurs qui configurent l'env  
**Contient:**
- Setup initial
- Configuration de l'environnement
- Workflow Git
- Dépendances

**Accès:** [installation-dev/](installation-dev/setup.md)

### ⚡ GitHub Actions
**Pour:** CI/CD, automatisation  
**Contient:**
- Vue d'ensemble des workflows
- Détails de chaque action
- Déploiement automatique

**Accès:** [github-actions/](github-actions/overview.md)

### ❓ FAQ
**Pour:** Questions fréquentes  
**Accès:** [faq.md](faq.md)

### 🤝 Contribuer
**Pour:** Contributeurs au projet  
**Accès:** [contributing.md](contributing.md)

---

## 🗂️ Structure des Dossiers

```
docs/
├── index.md                          # Page d'accueil
├── faq.md                           # Questions fréquentes
├── contributing.md                  # Guide de contribution
│
├── guide-utilisateur/               # Pour les utilisateurs
│   ├── overview.md                 # Vue d'ensemble
│   ├── installation.md             # Installation
│   ├── premiers-pas.md             # Quick start
│   ├── controls.md                 # Contrôles
│   ├── troubleshooting.md          # Dépannage
│   └── jeux/
│       ├── overview.md             # Liste des jeux
│       ├── ball-blast.md
│       ├── columns.md
│       ├── pong.md
│       ├── snake-eater.md
│       ├── trongame.md
│       └── ...                     # Autres jeux
│
├── documentation-technique/         # Pour les développeurs
│   ├── architecture.md             # Architecture
│   ├── arcade-module.md            # Module core
│   ├── development.md              # Guide développement
│   ├── build.md                    # Compilation
│   ├── tests.md                    # Fichier des tests
│   └── api-reference.md            # API Reference
│
├── installation-dev/               # Pour les dev
│   ├── setup.md                    # Configuration
│   ├── environment.md              # Env variables
│   └── git-workflow.md             # Git workflow
│
├── github-actions/                 # Pour CI/CD
│   ├── overview.md                 # Vue d'ensemble
│   └── workflows.md                # Détails workflows
│
└── assets/                         # Ressources
    ├── images/
    ├── schemas/
    └── scripts/
```

---

## 🧭 Navigation par Cas d'Usage

### ✨ Je suis un nouvel utilisateur
1. Lisez [index.md](index.md) - Vue d'ensemble
2. Suivez [guide-utilisateur/installation.md](guide-utilisateur/installation.md)
3. Lisez [guide-utilisateur/premiers-pas.md](guide-utilisateur/premiers-pas.md)
4. Explorez [guide-utilisateur/jeux/overview.md](guide-utilisateur/jeux/overview.md)

### 💻 Je suis un développeur
1. Commencez par [documentation-technique/architecture.md](documentation-technique/architecture.md)
2. Lisez [installation-dev/setup.md](installation-dev/setup.md)
3. Consultez [documentation-technique/development.md](documentation-technique/development.md)
4. Voir [contributing.md](contributing.md) pour contribuer

### 🎮 Je veux jouer à un jeu spécifique
Voir [guide-utilisateur/jeux/overview.md](guide-utilisateur/jeux/overview.md) pour la liste.

### 🐛 J'ai un problème
1. Consulter [faq.md](faq.md)
2. Voir [guide-utilisateur/troubleshooting.md](guide-utilisateur/troubleshooting.md)
3. Ouvrir un [issue GitHub](https://github.com/Zwartkat/borne/issues)

### ⚙️ Je veux contribuer
1. Lire [contributing.md](contributing.md)
2. Consulter [installation-dev/setup.md](installation-dev/setup.md)
3. Lire [installation-dev/git-workflow.md](installation-dev/git-workflow.md)

---

## 🔗 Architecture de la Doc

```
Début (index.md)
    ↓
┌─────────────────────────────────────────┐
│  Pour qui êtes-vous?                    │
├─────────────────────────────────────────┤
│  ├─ Je suis joueur                      │
│  │  → guide-utilisateur/                │
│  │                                      │
│  ├─ Je suis développeur                 │
│  │  → documentation-technique/          │
│  │  → installation-dev/                 │
│  │                                      │
│  └─ Je veux contribuer                  │
│     → contributing.md                   │
│     → installation-dev/                 │
│                                         │
│  Questions?                             │
│  → faq.md                               │
└─────────────────────────────────────────┘
```

---

## 📝 Comment Lire la Documentation

### Symboles Utilisés

| Symbole | Signification |
|---------|--------------|
| 📖 | Obligatoire |
| ✨ | Important |
| ⚠️ | Attention |
| 💡 | Conseil |
| 🔗 | Lien interne |
| 🌐 | Lien externe |

### Format de Code

```bash
# Commandes à copier-coller
bash script.sh
```

```java
// Code source en exemple
public class Example {}
```

### Boîtes d'Info

!!! note "Note"
    Information supplémentaire

!!! warning "Attention"
    Chose à faire attention

!!! success "Conseil"
    Bonne pratique

---

## 🚀 Où Démarrer ?

**Vous ne savez pas par où commencer?**

- [Page d'accueil](index.md) pour une vue d'ensemble
- [Guide utilisateur](guide-utilisateur/overview.md) pour jouer
- [Documentation technique](documentation-technique/architecture.md) pour développer
- [FAQ](faq.md) pour les questions

---

## 🔄 Maintien de la Documentation

La documentation est automatiquement déployée sur GitHub Pages via le workflow `deploy-docs.yml`.

Pour **contribuer à la documentation:**

1. Éditez les fichiers `.md` dans `docs/`
2. Faites une PR
3. Une fois approuvée, elle est automatiquement publiée

Voir [contributing.md](contributing.md) pour les détails.

---

## 📊 Statistiques

| Métrique | Valeur |
|----------|--------|
| Pages | 20+ |
| Sections | 4 |
| Jeux documentés | 15 |
| Workflows docs | 1 |
| Processus de déploiment | Automatisé |

---

## ✨ Dernière Mise à Jour

Documentation générée et maintenue automatiquement.  
**Date:** 2025  
**Processus:** GitHub Actions  
**Plateforme:** GitHub Pages

---

## 🙋 Questions ou Suggestions?

- Ouvrir une [issue](https://github.com/Zwartkat/borne/issues)
- Faire une [PR](https://github.com/Zwartkat/borne/pulls)
- Discuter dans les [discussions](https://github.com/Zwartkat/borne/discussions)

**Merci de contribuer à la documentation!** 📚
