# GitHub Actions Workflows

Documentation des workflows GitHub Actions disponibles pour ce projet.

## 📋 Workflows Disponibles

### 1. **Build** (`build.yml`)
Compile les modules Java du projet.

**Déclenché par:**
- Push sur `main` ou `develop`
- Pull request sur `main` ou `develop`

**Matrice:**
- Java 11, 17

**Modules compilés:**
- ✅ Arcade
- ✅ Main
- ✅ Columns
- ✅ Pong
- ✅ DinoRail

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
- 📖 Documentation JavaDoc (API)
- 🎮 Guide des jeux
- Publie sur GitHub Pages (si activé)

**Répertoires créés:**
- `doc_borne/` - JavaDoc API
- `docs_jeux/` - Documentation des jeux

---

### 4. **Python Games Validation** (`validate-python.yml`)
Valide la syntaxe des jeux Python.

**Déclenché par:**
- Push sur les fichiers Python des jeux
- Pull request modifiant les jeux

**Matrice:**
- Python 3.8, 3.10, 3.11

**Vérifie:**
- ✅ Syntaxe Python
- ✅ Imports (requirements.txt)
- ✅ Jeu Ball Blast

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
- Temps de passage du pipeline
- Taille des fichiers

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

**Conventions de nommage:**
```
feature/description
bugfix/description
hotfix/description
refactor/description
docs/description
test/description
```

---

### 7. **Release** (`release.yml`)
Crée une version officielle du projet.

**Déclenché par:**
- Push d'un tag `v*` (ex: `v1.0.0`)
- Déclenchement manuel

**Actions:**
- 🔄 Compilation complète
- 📚 Génération de JavaDoc
- 📚 Génération de doc des jeux
- 📦 Création d'archives
- 🎉 Création d'une release GitHub

**Artefacts de release:**
- `api-documentation-vX.Y.Z.zip`
- `game-documentation-vX.Y.Z.zip`
- Fichiers compilés

---

## 🚀 Utilisation

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

## 📊 État des Workflows

Pour voir l'état des workflows:
1. Aller à l'onglet **Actions** du repository
2. Voir l'historique des exécutions
3. Cliquer sur un workflow pour voir les détails

---

## 🧪 Tests Arcade

Les tests pour le module Arcade sont disponibles dans le dossier `Arcade/`:

- **TestPointeur.java** - Tests du système de pointeur
- **TestBoite.java** - Tests des boîtes UI
- **TestCouleur.java** - Tests des couleurs
- **TestHighScore.java** - Tests des scores
- **TestClavierBorneArcade.java** - Tests des contrôles

### Exécuter les tests localement

```bash
# Compilation des tests
cd Arcade
javac -cp .:../lib/*:../MG2D.jar TestPointeur.java TestBoite.java TestCouleur.java

# Exécution avec JUnit
java -cp .:../lib/*:../MG2D.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

---

## 📝 Notes

- Les workflows utilisent **Java 17** par défaut (avec fallback à Java 11)
- La documentation est générée uniquement sur push à `main`
- Les releases créent automatiquement une page de release avec les artefacts
- Les TODOs/FIXMEs sont trackés et rapportés dans les analyses de qualité

---

## 🔗 Liens Utiles

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [JUnit 5 Documentation](https://junit.org/junit5/)
- [JavaDoc Guide](https://www.oracle.com/java/technologies/javase/javadoc-guide.html)
