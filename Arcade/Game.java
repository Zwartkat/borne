package Arcade;

/**
 * Représente un jeu dans la borne d'arcade.
 * <p>
 * Cette classe modélise les informations associées à un jeu, notamment son identifiant unique,
 * son nom, sa description, le chemin d'accès à son exécutable, le chemin de son image associée,
 * ainsi que ses configurations de langage et d'entrée. Elle permet de gérer les données des jeux
 * dans un système de borne d'arcade.
 * <p>
 * Les attributs sont tous initialisés à des valeurs vides ou par défaut lors de la création d'une instance.
 */
public class Game {

    private String name, description, path, imagePath, lang, input;
    private int gameId;

    /**
     * Constructeur par défaut initialisant les champs à des valeurs vides ou par défaut.
     * <p>
     * Tous les attributs sont initialisés à vide ou à zéro, sauf le gameId qui est mis à 0.
     */
    public Game() {
        this.name = "";
        this.gameId = 0;
        this.path = "";
        this.imagePath = "";
        this.input = "";
        this.lang = "";
    }

    /**
     * Crée une instance de Game avec les informations fournies.
     *
     * @param gameId      L'identifiant unique du jeu
     * @param name        Le nom affiché du jeu
     * @param description Une brève description du jeu
     * @param path        Le chemin d'accès au fichier exécutable ou au dossier contenant le jeu
     * @param imagePath   Le chemin d'accès à l'image associée (thumbnail ou icône)
     * @param lang        Code du langage supporté par le jeu (ex: "en", "fr")
     * @param input       Configuration spécifique d'entrée (ex: "keyboard", "joystick")
     */
    public Game(int gameId, String name, String description, String path, String imagePath, String lang, String input) {
        this.gameId = gameId;
        this.name = name;
        this.description = description;
        this.path = path;
        this.imagePath = imagePath;
        this.input = input;
        this.lang = lang;
    }

    /**
     * Retourne l'identifiant unique du jeu.
     *
     * @return L'identifiant numérique unique du jeu
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Retourne le nom affiché du jeu.
     *
     * @return Le nom du jeu tel qu'il apparaît à l'utilisateur
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la description du jeu.
     *
     * @return Une brève explication du contenu ou des caractéristiques du jeu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le chemin d'accès au fichier exécutable ou au dossier contenant le jeu.
     *
     * @return Le chemin complet vers l'exécutable ou le dossier du jeu
     */
    public String getPath() {
        return path;
    }

    /**
     * Retourne le chemin d'accès à l'image associée au jeu.
     *
     * @return Le chemin complet vers l'image (thumbnail ou icône)
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Retourne la configuration d'entrée spécifique au jeu.
     *
     * @return La configuration d'entrée (ex: "keyboard", "joystick", "touchscreen")
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Retourne le code du langage supporté par le jeu.
     *
     * @return Code ISO du langage (ex: "en" pour anglais, "fr" pour français)
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Définit l'identifiant unique du jeu.
     *
     * @param gameId Nouvel identifiant numérique du jeu
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Définit le nom affiché du jeu.
     *
     * @param name Nouveau nom du jeu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit la description du jeu.
     *
     * @param description Nouvelle description du jeu
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Définit le chemin d'accès au fichier exécutable ou au dossier contenant le jeu.
     *
     * @param path Nouveau chemin d'accès à l'exécutable ou au dossier
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Définit le chemin d'accès à l'image associée au jeu.
     *
     * @param imagePath Nouveau chemin d'accès à l'image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Définit la configuration d'entrée spécifique au jeu.
     *
     * @param input Nouvelle configuration d'entrée
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Retourne une représentation string de l'objet Game.
     *
     * @return Chaîne contenant l'identifiant, le nom et le langage du jeu
     */
    public String toString() {
        return "Class Game: Id( " + gameId + "),Name(" + name + "),Lang(" + lang + ")";
    }
}