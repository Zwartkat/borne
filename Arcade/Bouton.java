package Arcade;

import java.awt.Font;

import MG2D.Couleur;
import MG2D.geometrie.Point;
import MG2D.geometrie.Texture;
import MG2D.geometrie.Texte;

public class Bouton {
	private Texte text;
	private String name;
	private Texture texture;
	private int gameId;
	private Game game;
	private boolean textIsVisible = true;

	/**
	 * Constructeur par défaut pour un bouton vide.
	 * Initialise les attributs à null ou à des valeurs par défaut.
	 * Le jeu est initialisé avec un objet Game vide et l'identifiant du jeu est défini à -1.
	 */
	public Bouton() {
		this.text = null;
		this.texture = null;
		this.name = null;
		this.game = new Game();
		this.gameId = -1;
	}

	/**
	 * Constructeur pour un bouton lié à un jeu existant.
	 * @param game Le jeu auquel le bouton est associé. 
	 * Le texte du bouton est initialisé avec le nom du jeu, et la texture utilise une image par défaut.
	 */
	public Bouton(Game game) {
		this.text = new Texte(
				new Texte(Couleur.NOIR, game.getName(), new Font("Calibri", Font.TYPE1_FONT, 30), new Point(310, 510)));
		this.texture = new Texture("img/bouton2.png", new Point(100, 478), 400, 65);
		this.gameId = game.getGameId();
		this.name = game.getName();
		this.game = game;
	}

	/**
	 * Constructeur pour un bouton avec texte et nom définis.
	 * @param text Le texte à afficher sur le bouton.
	 * @param name Le nom du bouton. 
	 * La texture utilise une image par défaut et le jeu est initialisé avec un objet Game vide.
	 */
	public Bouton(String text, String name) {
		this.text = new Texte(
				new Texte(Couleur.NOIR, text, new Font("Calibri", Font.TYPE1_FONT, 30), new Point(310, 510)));
		this.texture = new Texture("img/bouton2.png", new Point(100, 478), 400, 65);
		this.name = name;
		this.game = new Game();
	}

	public boolean isTextVisible() {
		return textIsVisible;
	}

	/**
	 * Inverte la visibilité du texte du bouton.
	 * @return La nouvelle visibilité du texte du bouton (true si visible, false sinon).
	 * Utile pour activer/désactiver le texte dans l'interface graphique.
	 */
	public boolean toggleTextVisibility() {
		textIsVisible = !textIsVisible;
		return textIsVisible;
	}

	/**
	 * Définit la visibilité du texte du bouton.
	 * @param visible La nouvelle visibilité du texte du bouton (true si visible, false sinon).
	 * Permet de contrôler explicitement l'affichage du texte.
	 */
	public void setTextVisible(boolean visible) {
		textIsVisible = visible;
	}

	public void translater(int i, int i1) {
		texture.translater(i, i1);
		text.translater(i, i1);
	}

	/**
	 * Retourne le jeu auquel le bouton est associé.
	 * @return Le jeu auquel le bouton est associé.
	 * Utilisé pour accéder aux données ou contexte lié au jeu.
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Définit le jeu auquel le bouton est associé.
	 * @param game Le nouveau jeu auquel le bouton est associé.
	 * Permet de lier le bouton à un jeu différent dynamiquement.
	 */
	private void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Retourne le nom du bouton.
	 * @return Le nom du bouton.
	 * Utilisé pour l'identification ou l'étiquetage.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Définit le nom du bouton.
	 * @param name Le nouveau nom du bouton.
	 * Permet de modifier le nom pour des besoins d'affichage ou de gestion.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne l'objet Texte représentant le texte affiché sur le bouton.
	 * @return L'objet Texte représentant le texte affiché sur le bouton.
	 * Gère l'affichage du texte dans l'interface graphique.
	 */
	public Texte getText() {
		return text;
	}

	/**
	 * Définit l'objet Texte à afficher sur le bouton.
	 * @param text L'objet Texte à afficher sur le bouton.
	 * Permet de personnaliser le texte affiché sur le bouton.
	 */
	public void setText(Texte text) {
		this.text = text;
	}

	/**
	 * Retourne l'objet Texture représentant l'image du bouton.
	 * @return L'objet Texture représentant l'image du bouton.
	 * Gère l'affichage visuel du bouton (image ou fond).
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Définit l'objet Texture à utiliser pour l'image du bouton.
	 * @param texture L'objet Texture à utiliser pour l'image du bouton.
	 * Permet de personnaliser l'apparence visuelle du bouton.
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * Retourne l'identifiant du jeu auquel le bouton est associé.
	 * @return L'identifiant du jeu auquel le bouton est associé.
	 * Utilisé pour l'identification unique du jeu lié au bouton.
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * Définit l'identifiant du jeu auquel le bouton est associé.
	 * @param gameId L'identifiant du jeu auquel le bouton est associé.
	 * Permet de lier le bouton à un jeu via son identifiant unique.
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * Retourne une représentation textuelle de l'objet Bouton.
	 * @return Une représentation textuelle de l'objet Bouton.
	 * Utile pour le débogage ou le journal de logs.
	 */
	public String toString() {
		return "Class bouton: Id(" + gameId + "),Name(" + name + ")";
	}
}