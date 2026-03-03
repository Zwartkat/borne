# GitHub Actions - Vue d'Ensemble

Les workflows GitHub Actions automatisent les tâches de compilation, test et déploiement.

## 📋 Workflows Disponibles

### Déploiement de Documentation
**Fichier:** `deploy-docs.yml`

Génère et publie la documentation sur GitHub Pages automatiquement.

**Déclenché par:**
- Push sur `main` (dossier `docs/`)
- Pull request (prévew)
- Manuellement

### Build
**Fichier:** `build.yml`

Compile tous les modules Java.

**Déclenché par:**
- Push sur `main` ou `develop`
- Pull request

### Tests
**Fichier:** `test.yml`

Exécute les tests JUnit.

### Code Quality
**Fichier:** `quality.yml`

Analysedualité du code, statistiques, TODOs.

### Release
**Fichier:** `release.yml`

Crée une release officielle et publie les artefacts.

**Déclenché par:**
- Push d'un tag `v*` (ex: `v1.0.0`)

## 🚀 Utiliser les Workflows

### Voir l'État
1. Aller à **Actions** sur GitHub
2. Voir l'historique d'exécution
3. Cliquer pour voir les détails

### Déclencher Manuellement
Certains workflows supportent `workflow_dispatch` :

1. Aller à **Actions**
2. Sélectionner le workflow
3. Cliquer "Run workflow"

### Vérifier les Erreurs
1. Cliquer sur un workflow échoué
2. Voir l'onglet "Logs"
3. Chercher les erreurs (🔴)

## 📚 Documentation Complète

Voir [workflows.md](workflows.md) pour les détails de chaque workflow.

## 🔗 Lien Utile

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
