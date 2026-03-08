# GitHub Actions Workflows

Documentation des workflows GitHub Actions disponibles pour ce projet.

## Workflows Disponibles

### 1. **Build** (`build.yml`)
Compile les modules Java du projet.

**Déclenché par:**
- Push sur `main` ou `develop`
- Pull request sur `main` ou `develop`

**Matrice:**
- Java 21,25

**Modules compilés:**
- ✅ Arcade
- ✅ Main
- ✅ Columns
- ✅ Pong
- ✅ DinoRail
- ...

**Artefacts générés:**
- Fichiers `.class` compilés

---

### 2. **Tests** (`test.yml`)
Exécute les tests JUnit sur le module Arcade.

**Déclenché par:**
- Push sur `main` ou `develop`
- Pull request sur `main` ou `develop`

**Tests disponibles:**
- `TestPointeur` - Tests de la classe Pointeur
- `TestBoite` - Tests de la classe Boite
- `TestCouleur` - Tests de la classe Couleur
- `TestHighScore` - Tests du système de scores 
- `TestClavierBorneArcade` - Tests des contrôles

**Framework:**
- JUnit 5 (Jupiter)

---

### 3. **Documentation** (`docs.yml`)
Génère la documentation JavaDoc et celle des jeux.

**Déclenché par:**
- Push sur `main`
- Déclenchement manuel (workflow_dispatch)

**Génère:**
- Documentation JavaDoc 
- Publie sur GitHub Pages

---

### 4. **Python Games Validation** (`validate-python.yml`)
Valide la syntaxe des jeux Python.

**Déclenché par:**
- Push sur les fichiers Python des jeux
- Pull request modifiant les jeux

**Matrice:**
- Python 3.11,3.12,3.13

**Vérifie:**
- ✅ Syntaxe Python
- ✅ Imports (requirements.txt)

---

### 5. **Code Quality** (`quality.yml`)
Analyse la qualité du code.

**Déclenché par:**
- Push sur `main` ou `develop`
- Pull request sur `main` ou `develop`
- Hebdomadairement (dimanche minuit)

**Analysés:**
- Statistiques de code (lignes, fichiers)
- TODO/FIXME/HACK

---

### 6. **PR Checks** (`pr-checks.yml`)
Vérifications automatiques pour les PRs.

**Déclenché par:**
- Pull request sur `main` ou `develop`

**Vérifie:**
- ✅ Compilation Java
- ✅ Nommage des branches
- ✅ Code de débogage
- ✅ Taille des fichiers
- ✅ Documentation

---

### 7. **Release** (`release.yml`)
Crée une version officielle du projet.

**Déclenché par:**
- Push d'un tag `v*` (ex: `v1.0.0`)
- Déclenchement manuel

**Actions:**
-  Compilation complète
-  Génération de JavaDoc
-  Publication de l'installer
-  Création d'une release GitHub

---

## Utilisation

### Déclencher une compilation
```bash
git push origin main
```

### Déclencher une release
```bash
git tag v1.0.0
git push origin v1.0.0
```

### Déclencher la documentation manuellement
- Aller à "Actions" sur GitHub
- Cliquer sur "Generate Documentation"
- Cliquer "Run workflow"

---

## État des Workflows

Pour voir l'état des workflows:
1. Aller à l'onglet **Actions** du repository
2. Voir l'historique des exécutions
3. Cliquer sur un workflow pour voir les détails

