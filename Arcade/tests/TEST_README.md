# 🧪 Tests Arcade Module

Tests JUnit pour le module Arcade de la Borne d'Arcade.

## 📋 Tests Disponibles

### TestPointeur
Tests pour la classe `Pointeur`.

**Tests:**
- ✅ `testPointeurInitialisation()` - Vérifie l'initialisation du pointeur
- ✅ `testPointeurSetValue()` - Teste la modification de la valeur
- ✅ `testPointeurSetValueNegative()` - Teste les valeurs négatives
- ✅ `testPointeurSetValueZero()` - Teste la valeur zéro
- ✅ `testPointeurAffichage()` - Teste l'affichage sans erreur

**Classe testée:** `Arcade.Pointeur`

---

### TestBoite
Tests pour la classe `Boite`.

**Tests:**
- ✅ `testBoiteInitialisation()` - Vérifie l'initialisation
- ✅ `testBoiteAffichage()` - Teste l'affichage
- ✅ `testBoiteTransformation()` - Teste une translation
- ✅ `testBoiteMultipleTransformations()` - Teste transformations multiples

**Classe testée:** `Arcade.Boite`

---

### TestCouleur
Tests pour la classe `Couleur` (énumération).

**Tests:**
- ✅ `testCouleurBlanc()` - Vérifie `Couleur.BLANC`
- ✅ `testCouleurNoir()` - Vérifie `Couleur.NOIR`
- ✅ `testCouleurRouge()` - Vérifie `Couleur.ROUGE`
- ✅ `testCouleurVert()` - Vérifie `Couleur.VERT`
- ✅ `testCouleurBleu()` - Vérifie `Couleur.BLEU`

**Classe testée:** `Arcade.Couleur`

---

### TestHighScore
Tests pour le système de scores.

**Tests:**
- ✅ `testHighScoreInitialisation()` - Vérifie l'initialisation
- ✅ `testAddScore()` - Teste l'ajout d'un score
- ✅ `testGetHighScore()` - Teste la récupération du meilleur score
- ✅ `testGetAllScores()` - Teste la liste complète

**Classe testée:** `Arcade.HighScore`

---

### TestClavierBorneArcade
Tests pour les contrôles de la borne.

**Tests:**
- ✅ `testKeyboardInitialisation()` - Vérifie l'initialisation
- ✅ `testKeyMapping()` - Teste l'association touches-actions
- ✅ `testJoystickInput()` - Teste les entrées joystick

**Classe testée:** `Arcade.ClavierBorneArcade`

---

## 🚀 Exécution

#### 1. Télécharger JUnit 5

```bash
mkdir -p Arcade/lib
cd Arcade/lib
wget [https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.9.3/junit-jupiter-api-5.9.3.jar](https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.9.3/junit-jupiter-api-5.9.3.jar)
wget [https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.9.3/junit-jupiter-engine-5.9.3.jar](https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.9.3/junit-jupiter-engine-5.9.3.jar)
wget [https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/1.9.3/junit-platform-commons-1.9.3.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/1.9.3/junit-platform-commons-1.9.3.jar)
wget [https://repo1.maven.org/maven2/org/junit/platform/junit-platform-engine/1.9.3/junit-platform-engine-1.9.3.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-engine/1.9.3/junit-platform-engine-1.9.3.jar)
wget [https://repo1.maven.org/maven2/org/junit/platform/junit-platform-launcher/1.9.3/junit-platform-launcher-1.9.3.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-launcher/1.9.3/junit-platform-launcher-1.9.3.jar)
```

#### 2. Compiler les tests

```bash
cd ../..
javac -cp ".:Arcade/lib/*:Arcade/MG2D.jar" Arcade/tests/Test*.java
```

#### 3. Exécuter les tests

```bash
java -cp ".:Arcade/tests:Arcade/lib/*:Arcade/MG2D.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
cd ..
```

### Via GitHub Actions

Les tests s'exécutent automatiquement lors d'un push ou d'une pull request.

Pour voir l'historique:
1. Aller à **Actions** → **Tests - Arcade Module**
2. Voir les résultats de chaque exécution

---

## 🗂️ Structure

```
Arcade/
├── tests/
|  ├── TestPointeur.java          # Tests du pointeur
|  ├── TestBoite.java             # Tests des boîtes
|  ├── TestCouleur.java           # Tests des couleurs
|  ├── TestHighScore.java         # Tests du système de scores
|  └── TestClavierBorneArcade.java # Tests des contrôles
├── Pointeur.java              # Classe testée
├── Boite.java                 # Classe testée
├── Couleur.java               # Classe testée
└── ... (autres classe)
```

---

## ✅ Bonnes Pratiques

1. **Nommer les tests clairement**
   - Format: `test[FonctionnaliteTestee]()`
   - Exemple: `testPointeurSetValue()`

2. **Un test = une chose**
   - Chaque test vérifie un comportement spécifique
   - Utiliser Arrange-Act-Assert

3. **Utiliser les assertions**
   ```java
   assertEquals(expected, actual);
   assertTrue(condition);
   assertNull(object);
   assertThrows(Exception.class, () -> {});
   ```

4. **Isoler les tests**
   - Utiliser `@BeforeEach` pour l'initialisation
   - Chaque test doit être indépendant

5. **Tester les cas limites**
   - Valeurs nulles
   - Valeurs négatives
   - Limites de plages

---

## 🔗 Ressources

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Assertions](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html)

---

## 📝 Notes

- Les tests utilisent le framework **JUnit 5 (Jupiter)**
- Compatible avec **Java 11+**
- Tous les tests sont exécutés automatiquement sur GitHub Actions
- Voir `.github/workflows/test.yml` pour la configuration du CI/CD
