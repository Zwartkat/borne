# Contrôles - Guide Complet

Guide détaillé de tous les contrôles disponibles sur la Borne d'Arcade.

## ⌨️ Touches de Clavier Standard

| Fonction | Touche |
|----------|--------|
| **Haut** | ↑ (Flèche Haut) |
| **Bas** | ↓ (Flèche Bas) |
| **Gauche** | ← (Flèche Gauche) |
| **Droite** | → (Flèche Droite) |
| **Action 1** | `Z` ou `A` |
| **Action 2** | `X` ou `B` |
| **Action 3** | `C` |
| **Menu/Pause** | `Entrée` |
| **Retour/Quitter** | `Échap` |

---

## 🎮 Joystick/Gamepad

### Contrôles Directionnels

| Fonction | Joystick |
|----------|----------|
| **Haut** | Stick Analogique ↑ ou D-Pad ↑ |
| **Bas** | Stick Analogique ↓ ou D-Pad ↓ |
| **Gauche** | Stick Analogique ← ou D-Pad ← |
| **Droite** | Stick Analogique → ou D-Pad → |

### Boutons d'Action

| Fonction | Joystick | Boutons |
|----------|----------|---------|
| **Action 1** | Bouton `A` | Green Button (Xbox) / Cross (PS) |
| **Action 2** | Bouton `B` | Red Button (Xbox) / Circle (PS) |
| **Action 3** | Bouton `X` | Blue Button (Xbox) / Square (PS) |
| **Menu** | Bouton `Start` | Menu Button |
| **Retour** | Bouton `Select` | Select Button |

---

## 🎯 Contrôles par Section

### 🎮 Menu Principal

```
Haut/Bas        : Naviguer entre les jeux
Gauche/Droite   : Parcourir les pages (si multiple)
Action (A)      : Sélectionner et lancer le jeu
Retour (Esc)    : Quitter la borne
```

### 🎮 Écran de Sélection

```
Haut/Bas        : Sélectionner le jeu
Action (A)      : Confirmer et lancer
Retour (Esc)    : Revenir au menu
```

### 🎮 Pendant un Jeu

Les contrôles dépendent du jeu spécifique. Voici les mappings communs:

#### Ball Blast
```
Gauche/Droite   : Déplacer le canon
Action (A)      : Tirer
Retour (Esc)    : Quitter le jeu
```

#### Columns
```
Haut/Bas        : Rotation pièce
Gauche/Droite   : Déplacer pièce
Action (A)      : Descendre rapide
Retour (Esc)    : Quitter le jeu
```

#### Pong
```
Haut/Bas        : Déplacer raquet
Action (A)      : Accelerer
Retour (Esc)    : Quitter le jeu
```

#### Snake Eater
```
Haut/Bas        : Changer direction (Haut/Bas)
Gauche/Droite   : Changer direction (Gauche/Droite)
Action (A)      : Pause/Resume
Retour (Esc)    : Quitter le jeu
```

#### DinoRail
```
Haut/Bas        : Sauter/Pencher
Gauche/Droite   : Décaler position
Retour (Esc)    : Quitter le jeu
```

#### TronGame
```
Haut/Bas        : Changer direction Haut/Bas
Gauche/Droite   : Changer direction Gauche/Droite
Action (A)      : Boost (si disponible)
Retour (Esc)    : Quitter le jeu
```

---

## 📋 Tableau de Référence Rapide

### Clavier AZERTY (Français)

| Action | AZERTY | QWERTY |
|--------|--------|--------|
| Haut | ↑ | ↑ |
| Bas | ↓ | ↓ |
| Gauche | ← | ← |
| Droite | → | → |
| Action 1 | Z | W |
| Action 2 | X | X |
| Action 3 | C | C |
| Menu | Entrée | Entrée |
| Retour | Échap | Échap |

---

## 🎮 Configuration Joystick Supportée

### Gamepad Supportés

- ✅ Xbox 360/One/Series X|S
- ✅ PlayStation 4/5
- ✅ Nintendo Joy-Con
- ✅ Manettes Arcade Personnalisées
- ✅ Joystick USB génériques

### Activation Joystick

Le joystick est **détecté automatiquement** au démarrage.

Si ce n'est pas détecté :
1. Brancher le joystick USB
2. Redémarrer la borne
3. Appuyer sur un bouton du joystick
4. Click sur le jeu pour activer

---

## 🔧 Personnalisation (Développeurs)

### Changer les Contrôles

Éditer `Arcade/ClavierBorneArcade.java` :

```java
public class ClavierBorneArcade implements KeyListener {
    
    // Modifier les KeyCode pour vos touches
    case KeyEvent.VK_Z:      // Touche Z
        a = true;
        aTape = true;
        break;
    
    // Ajouter de nouvelles touches
    case KeyEvent.VK_SPACE:  // Barre d'espace
        // Ajouter logique
        break;
}
```

### Mapper un Joystick

Pour mapper un joystick personnalisé :

```java
// Éditer la méthode de capturejoystick
public void captureJoystick(JoystickEvent event) {
    // Ajouter vos mappings
    if (event.getButton() == CUSTOM_BUTTON) {
        // Votre action
    }
}
```

---

## 🆘 Dépannage des Contrôles

### Les touches ne répondent pas

**Solution :**
1. Cliquer dans la fenêtre
2. Appuyer sur `Entrée`
3. Appuyer à nouveau sur la touche

### Le joystick n'est pas reconnu

**Solution :**
1. Débrancher et rebrancher
2. Redémarrer la borne
3. Vérifier les pilotes USB

### Touches inversées (AZERTY vs QWERTY)

**Solution :**
- Les touches sont mappées logiquement (Z = Action1)
- Aucun changement de configuration nécessaire
- Fonctionne sur tous les claviers

---

## 🎯 Conseils d'Optimisation

### Pour une Meilleure Réactivité

1. **Utiliser un Joystick** plutôt que le clavier
2. **Vérifier le Focus** : cliquer dans la fenêtre
3. **Réduire la Latence** : fermer les autres applications
4. **Calibrer le Joystick** si disponible

### Profil de Jeu Optimal

| Profil | Clavier | Joystick |
|--------|---------|----------|
| **Casual** | ✅ | Recommandé |
| **Compétition** | ✅ | ✅ Très bon |
| **Speedrun** | Acceptable | ✅ Meilleur |

---

## 🔗 Liens Utiles

- [Dépannage général](troubleshooting.md)
- [FAQ](../faq.md)
- [Code source ClavierBorneArcade](../../Arcade/ClavierBorneArcade.java)

---

**Dernière mise à jour:** 2025
**Version:** 1.0
