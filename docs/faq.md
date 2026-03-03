# FAQ - Questions Fréquemment Posées

Réponses aux questions les plus courantes sur la Borne d'Arcade.

## 🎮 Questions Générales

### Q: C'est quoi la Borne d'Arcade ?
**R:** La Borne d'Arcade est une console de jeu multiplateforme proposant 15 jeux différents. Elle combine une interface rétro avec une technologie moderne (Java, Python, Lua).

### Q: Combien de jeux y a-t-il ?
**R:** Il y a actuellement **15 jeux** :
- 7 jeux en Java
- 1 jeu en Python (Ball Blast)
- 2 jeux en Lua (CursedWare)
- 5 autres jeux variés

### Q: C'est gratuit ?
**R:** Oui, c'est 100% **open-source et gratuit** !

### Q: Sur quels systèmes ça fonctionne ?
**R:** 
- ✅ Windows 10/11
- ✅ macOS 10.12+
- ✅ Linux (Ubuntu, Debian, Fedora, etc.)

---

## 📦 Installation

### Q: Qu'est-ce que j'ai besoin pour installer ?
**R:** Vous avez besoin de :
- **Java 11+** (obligatoire)
- **Git** (optionnel, pour cloner)
- Un peu de patience ! 😄

### Q: Où est le fichier d'installation ?
**R:** Il n'y a pas d'installateur. C'est une application multiplateforme. Suivez le [guide d'installation](guide-utilisateur/installation.md).

### Q: Le MG2D.jar, c'est quoi ?
**R:** C'est la bibliothèque graphique utilisée par la Borne. **Ne pas supprimer ce fichier !**

### Q: Comment je mets à jour ?
**R:** Faire un `git pull` :
```bash
git pull origin main
```

---

## 🎮 Jeux et Gameplay

### Q: Comment je sauvegarde mon score ?
**R:** C'est **automatique** ! Vos scores sont sauvegardés dans les fichiers `projet/[jeu]/highscore`.

### Q: Où sont les highscores stockés ?
**R:** Chemin : `projet/[jeu]/highscore` (fichier texte)

### Q: Je peux jouer en deux joueurs ?
**R:** Certains jeux supportent 2 joueurs :
- ✅ Columns
- ✅ Minesweeper
- ✅ TronGame
- ✅ Pong (potentiellement)

Les autres sont 1 joueur.

### Q: Comment je change de jeu rapidement ?
**R:** Appuyez sur `Retour` pour revenir au menu.

### Q: Y a-t-il des achievements/succès ?
**R:** Non, juste un système de **highscores/leaderboards**.

---

## ⌨️ Contrôles

### Q: Comment je mappe mon joystick ?
**R:** Le joystick est **détecté automatiquement**. Aucune configuration nécessaire normalement.

### Q: Mes touches ne répondent pas...
**R:** 
1. Cliquer dans la fenêtre
2. Appuyer sur `Entrée`
3. Essayer à nouveau

### Q: Peut-on utiliser la souris ?
**R:** Non, la Borne n'utilise que le clavier et le joystick.

### Q: Comment je configure les contrôles ?
**R:** Éditer `Arcade/ClavierBorneArcade.java`. Voir le [guide technique](documentation-technique/development.md).

Voir aussi : [Guide des contrôles complet](guide-utilisateur/controls.md)

---

## 🐛 Dépannage

### Q: "Java command not found"
**R:** Java n'est pas installé ou pas dans le PATH.
```bash
# Vérifier
java -version

# Installer (voir guide d'installation)
```

### Q: "MG2D.jar not found"
**R:** Le fichier `MG2D.jar` a été supprimé ou déplacé. Ne pas faire ça ! Télécharger à nouveau.

### Q: La fenêtre ne s'ouvre pas
**R:** 
1. Vérifier que Java fonctionne : `java -version`
2. Vérifier le terminal pour les erreurs
3. Essayer avec : `java -cp .:./MG2D.jar Main`

### Q: Le jeu crash au démarrage
**R:**
1. Vérifier l'espace disque disponible
2. Vérifier les permissions des fichiers
3. Réinstaller le projet

Pour plus d'aide : [Guide de dépannage](guide-utilisateur/troubleshooting.md)

---

## 🛠️ Développement

### Q: Comment contribuer ?
**R:** Voir [Comment contribuer](contributing.md)

### Q: Comment ajouter un nouveau jeu ?
**R:** Voir [Documentation technique - Développement](documentation-technique/development.md)

### Q: Quel langage de programmation ?
**R:** 
- **Principale:** Java (Module Arcade)
- **Jeux:** Java, Python, Lua
- **Tests:** JUnit 5

### Q: Comment compiler le projet ?
**R:** 
```bash
# Linux/Mac
bash compilation.sh

# Windows
compilation.bat
```

### Q: Peut-on utiliser Maven ?
**R:** Oui, il y a un support pariel. Voir la [doc technique](documentation-technique/build.md).

---

## 📚 Documentation

### Q: Où trouver la documentation ?
**R:** Vous êtes dedans ! Voir :
- [Documentation Utilisateur](guide-utilisateur/overview.md)
- [Documentation Technique](documentation-technique/architecture.md)
- [API Reference](documentation-technique/api-reference.md)

### Q: C'est en français ou anglais ?
**R:** Principalement en **français**.

### Q: Comment générer la JavaDoc ?
**R:** 
```bash
javadoc -d doc_borne -cp ./MG2D.jar -sourcepath . Arcade/*.java Main.java
```

---

## 🔄 GitHub et Contribution

### Q: Comment faire une pull request ?
**R:** Voir [Contribuer](contributing.md)

### Q: Les workflows GitHub Actions, c'est quoi ?
**R:** Processus automatiques pour :
- ✅ Compiler le code
- ✅ Exécuter les tests
- ✅ Générer la documentation
- ✅ Créer les releases

Détails : [GitHub Actions](github-actions/overview.md)

### Q: Comment créer une issue ?
**R:** 
1. Aller à [GitHub Issues](https://github.com/Zwartkat/borne/issues)
2. Cliquer "New Issue"
3. Bien décrire le problème

---

## 🚀 Performance

### Q: Est-ce que ça marche sur un Raspberry Pi ?
**R:** Oui ! Il faut :
- Java 11+ installé
- Au moins 512 MB RAM
- GHz de CPU decent

### Q: Quel est la consomation CPU/RAM ?
**R:** Estimations :
- **CPU:** 5-15% (selon le jeu)
- **RAM:** 200-400 MB

### Q: À quelle FPS ça tourne ?
**R:** La Borne cible **60 FPS**, selon votre hardware.

---

## 💾 Données et Stockage

### Q: Est-ce que mes données sont sûres ?
**R:** Oui, les scores sont stockés **localement** sur votre disque.

### Q: Comment je sauvegarde mes données ?
**R:** Les données sont dans le dossier `projet/`. Sauvegarder ce dossier.

### Q: Peut-on utiliser un serveur distant pour les scores ?
**R:** Actuellement non, c'est local. C'est une feature future possible.

---

## 🎨 Personnalisation

### Q: Peut-on changer le thème/couleurs ?
**R:** Oui, éditer `Arcade/Graphique.java` et `Couleur.java`.

### Q: Peut-on changer la résolution ?
**R:** Oui, éditer les valeurs `TAILLEX` et `TAILLEY` dans `Arcade/Graphique.java`.

### Q: Peut-on ajouter de la musique ?
**R:** Oui, les ressources sont dans `projet/[jeu]/assets/sounds/`.

---

## 📧 Contact et Support

### Q: Où signaler un bug ?
**R:** [GitHub Issues](https://github.com/Zwartkat/borne/issues)

### Q: Comment demander une nouvelle fonctionnalité ?
**R:** [GitHub Discussions](https://github.com/Zwartkat/borne/discussions)

### Q: Comment me contacter directement ?
**R:** Via les issues GitHub ou les discussions.

---

## ✨ Dernière Maj

Les réponses ont été mises à jour : **2025**

Vous ne trouvez pas votre réponse ? [Ouvrir une issue !](https://github.com/Zwartkat/borne/issues)
