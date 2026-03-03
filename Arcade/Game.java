package Arcade;

/**
 * Représente un jeu dans la borne d'arcade.
 * <p>
 * Chaque jeu contient les informations suivantes :
 * <ul>
 * <li>Numéro du jeu</li>
 * <li>Nom du jeu</li>
 * <li>Description</li>
 * <li>Chemin du dossier ou du fichier exécutable</li>
 * <li>Chemin de l'image associée au jeu (thumbnail ou icône)</li>
 * </ul>
 * Cette classe fournit des méthodes pour accéder et modifier ces informations.
 */
public class Game {

    private String name, description, path, imagePath, lang, input;
    private int gameId;

    /**
     * Constructeur par défaut initialisant les champs à des valeurs vides ou par défaut.
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
     * @param gameId      Le numéro du jeu
     * @param name        Le nom du jeu
     * @param description La description du jeu
     * @param path        Le chemin du dossier ou du fichier exécutable
     * @param imagePath   Le chemin de l'image associée au jeu
     * @param lang        Langage du jeu
     * @param input       Configuration d'entrée du jeu
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
     * Retourne le numéro du jeu.
     *
     * @return Le numéro du jeu
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Retourne le nom du jeu.
     *
     * @return Le nom du jeu
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la description du jeu.
     *
     * @return La description du jeu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le chemin du dossier ou fichier exécutable du jeu.
     *
     * @return Le chemin du jeu
     */
    public String getPath() {
        return path;
    }

    /**
     * Retourne le chemin de l'image associée au jeu.
     *
     * @return Le chemin de l'image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Retourne la configuration d'entrée du jeu.
     *
     * @return La configuration d'entrée
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Retourne le langage du jeu.
     *
     * @return Le langage du jeu
     */
    public String getLang() {
        return this.lang;
    }

    /**
     * Définit le numéro du jeu.
     *
     * @param gameId Nouveau numéro du jeu
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Définit le nom du jeu.
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
     * Définit le chemin du dossier ou fichier exécutable du jeu.
     *
     * @param path Nouveau chemin du jeu
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Définit le chemin de l'image associée au jeu.
     *
     * @param imagePath Nouveau chemin de l'image
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Définit la configuration d'entrée du jeu.
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