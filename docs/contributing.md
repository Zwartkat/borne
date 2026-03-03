# Contributing - Guide de Contribution

Merci de votre intérêt pour contribuer à la Borne d'Arcade ! 🎮

## 🎯 Comment Contribuer

Il y a plusieurs façons de contribuer :

### 1. 🐛 Signaler un Bug
Vous avez trouvé un problème ?

1. Allez à [Issues](https://github.com/Zwartkat/borne/issues)
2. Cliquez "New Issue" → "Bug Report"
3. Remplissez le template :
   - **Titre** : Résumé clair du bug
   - **Description** : Détails du problème
   - **Système** : OS, Version Java
   - **Reproduction** : Étapes pour reproduire
   - **Attendu** : Comportement attendu
   - **Réel** : Ce qu'il s'est passé

### 2. 💡 Proposer une Fonctionnalité
Vous avez une idée ?

1. Allez à [Discussions](https://github.com/Zwartkat/borne/discussions)
2. Créez une nouvelle discussion "Feature Request"
3. Décrivez :
   - L'idée
   - Pourquoi c'est utile
   - Cas d'usage
   - Implémentation suggérée

### 3. 📖 Améliorer la Documentation
Vous trouvez quelque chose à clarifier ?

1. Éditez directement les fichiers `.md` dans `docs/`
2. Soumettez une PR
3. Attendez la révision

### 4. 🎨 Ajouter un Nouveau Jeu
Vous voulez créer un nouveau jeu ?

Suivez le [guide de développement](documentation-technique/development.md).

### 5. 🔧 Corriger un Bug
Vous avez une solution à un problème ?

1. Forkez le repo
2. Créez une branche : `git checkout -b bugfix/description`
3. Faites vos changements
4. Testez
5. Poussez : `git push origin bugfix/description`
6. Ouvrez une PR

---

## 📋 Avant de Commencer

### Prérequis

- ✅ Git installé
- ✅ Java 17+ installé
- ✅ GitHub account
- ✅ Un peu de patience 😊

### Configuration Locale

```bash
# 1. Cloner le repo
git clone https://github.com/Zwartkat/borne.git
cd borne

# 2. Créer une branche
git checkout -b feature/your-feature-name

# 3. Installer dépendances
# Voir installation-dev/setup.md

# 4. Compiler
bash compilation.sh

# 5. Tester vos changements
./launch.sh
```

---

## 🌿 Workflow Git

### Conventions de Nommage de Branche

```
feature/[description]    # Nouvelle fonctionnalité
bugfix/[description]     # Correction de bug
hotfix/[description]     # Correction urgente
refactor/[description]   # Refactorisation
docs/[description]       # Documentation
test/[description]       # Tests
```

### Exemples
```bash
git checkout -b feature/new-game-menu
git checkout -b bugfix/joystick-lag
git checkout -b docs/update-installation
```

---

## 💻 Code Style

### Java (Principal)

```java
// Nommage
public class MyClass { }           // PascalCase
public void myMethod() { }         // camelCase
private int myVariable;            // camelCase
private static final int MY_CONST; // UPPER_SNAKE_CASE

// Format
public class Example {
    public void method() {
        if (condition) {
            // Code
        }
    }
}

// Documentation
/**
 * Brève description
 * 
 * @param param Détail du paramètre
 * @return Détail du retour
 */
public void method(String param) {
}
```

### Python

```python
# PEP 8 compliance
class MyClass:
    """Docstring"""
    
    def my_method(self):
        """Method description"""
        pass

# Type hints (recommandé)
def my_function(param: str) -> int:
    """Function"""
    return 0
```

### Lua

```lua
-- camelCase pour variables/fonctions
local myVariable = 10

function myFunction()
    -- Code
end

-- Commentaires déscriptifs
--- Full documentation (LDoc format)
-- @param param Description
-- @return Description
```

---

## ✅ Checklist avant PR

Avant d'ouvrir une Pull Request :

- [ ] Code compilable sans erreur
- [ ] Tests locaux réussis
- [ ] Pas de compilation warnings
- [ ] Commit messages clairs
- [ ] Documentation mise à jour
- [ ] Code formaté correctement
- [ ] Pas de fichiers temporaires
- [ ] Branche nommée correctement
- [ ] Base sur `main` (pas `develop`)

---

## 📝 Commit Messages

### Format

```
[TYPE] Titre clair et court

Description détaillée expliquant :
- Pourquoi ce changement
- Comment il fonctionne
- Toute information importante

Fix #123 (si applicable)
```

### Types de Commits

```
[FEAT]     Nouvelle fonctionnalité
[FIX]      Correction de bug
[DOCS]     Mise à jour doc
[STYLE]    Formatage, pas de logique
[REFACTOR] Restructuration du code
[PERF]     Amélioration perfs
[TEST]     Ajout/modification tests
[CI]       Configuration GitHub Actions
[CHORE]    Mise à jour dépendances
```

### Exemples

```
[FEAT] Add new menu system

Implemented a new hierarchical menu system with:
- Better navigation
- Smoother animations
- Support for game categories

Fix #42
```

```
[FIX] Fix joystick lag issue

The joystick input processing was too slow due to
inefficient polling. Optimized the input loop to use
event-driven architecture instead.

Fix #17
```

---

## 🧪 Tests

### Exécuter les Tests

```bash
# Compiler les tests
cd Arcade
javac -cp .:../lib/*:../MG2D.jar Test*.java

# Exécuter
java -cp .:../lib/*:../MG2D.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### Ajouter des Tests

1. Créer `TestYourClass.java`
2. Utiliser JUnit 5
3. Suivre les conventions :

```java
public class TestMyClass {
    
    @BeforeEach
    public void setUp() {
        // Setup avant chaque test
    }
    
    @Test
    public void testMethodName() {
        // Arrange
        MyClass obj = new MyClass();
        
        // Act
        int result = obj.method();
        
        // Assert
        assertEquals(expected, result);
    }
}
```

---

## 📚 Documentation

### Mettre à Jour les Docs

Les docs sont dans `docs/` avec MkDocs.

```bash
# Éditer les fichiers .md

# Prévisualiser localement (optionnel)
pip install mkdocs-material
mkdocs serve
# Ouvrir http://localhost:8000
```

### Ajouter une Page

1. Créer `docs/chemin/newpage.md`
2. Mettre à jour `mkdocs.yml`
3. Ajouter dans le navigation

---

## 🔄 Processus de PR

### 1. Créer la PR
```bash
git push origin feature/your-feature
# Cliquer le lien dans le terminal pour créer la PR
# Ou aller à GitHub et cliquer "New Pull Request"
```

### 2. Remplir le Template

```
## Description
Brève description des changements

## Type de Changement
- [x] Bug fix
- [ ] New feature
- [ ] Breaking change

## Comment Tester
Décrire comment tester

## Screenshots (si applicable)
Ajouter des captures

## Checklist
- [x] Mon code suit le style du projet
- [x] Tests écrits et réussis
- [x] Documentation mise à jour
```

### 3. Révision
Les maintainers vont :
- Examiner le code
- Faire des suggestions
- Poser des questions

### 4. Révisions (si nécessaire)
- Faire les changements demandés
- Pusher : `git push`
- La PR se met à jour automatiquement

### 5. Merge
Une fois approuvé, un maintainer va merger la PR ! 🎉

---

## 🐘 Gros Changements

Pour les **gros changements** (nouvea jeu, refactoring majeur) :

1. **Ouvrir une Discussion d'Abord**
   - Discutez de l'approche
   - Obtenez l'aval
   - Planifiez ensemble

2. **Créer une Issue de Tracking**
   - Décrivez le travail global
   - Listez les sous-tâches
   - Estimez l'effort

3. **Diviser en Petites PRs**
   - Plus facile à reviewer
   - Moins de conflits
   - Itérations rapides

---

## 🤝 Code Review

### Comment Reviewer

Si vous aidez à reviewer le code :

- Soyez **constructif** et **bienveillant**
- Posez des **questions**
- Expliquez le **pourquoi**
- Félicitez le bon code
- Suggérez des améliorations

### Comment Recevoir des Critiques

- C'est pas personnel
- Posez des questions si vous comprenez pas
- Apprenez des feedbacks
- Remerciez les reviewers

---

## 📞 Besoin d'Aide ?

- **Questions?** → [Discussions GitHub](https://github.com/Zwartkat/borne/discussions)
- **Bug?** → [Issues GitHub](https://github.com/Zwartkat/borne/issues)
- **Autre?** → Nous contacter directement

---

## 🎉 Merci de Contribuer !

Votre contribution, petite ou grande, est **vraiment appréciée**. Ensemble, on rend la Borne d'Arcade meilleure ! 🚀

**Happy Coding!** 🎮

---

### Ressources Utiles

- [GitHub Forking Workflow](https://guides.github.com/activities/forking/)
- [Git Documentation](https://git-scm.com/doc)
- [Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [PEP 8 (Python)](https://pep8.org/)
- [MkDocs Documentation](https://www.mkdocs.org/)
