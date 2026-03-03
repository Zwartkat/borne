package Arcade;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.InputStreamReader;

import MG2D.Couleur;
import MG2D.geometrie.Texture;
import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Texte;

/**
 * Classe représentant une boîte de description dans un jeu, contenant des messages, des boutons et des éléments de haut score.
 * Hérite de la classe Boite pour gérer la position et les propriétés graphiques.
 */
public class BoiteDescription extends Boite {

	private Texte[] message;
	private boolean stop;
	private int nombreLigne;
	private Texture joystick;
	private Texture[] bouton;
	private Texte tJoystick;
	private Texte[] tBouton;
	private String[] texteBouton;
	private Texte highscore;
	private Texte[] listeHighScore;

	/* HACKED BY BENDAL */
	private Font font1 = null;
	private Font font2 = null;
	private Font font3 = null;
	private Font font4 = null;

	/****************/

	/**
	 * Constructeur de la classe BoiteDescription.
	 * Initialise la boîte avec un rectangle de position et de taille, charge les polices, et configure les éléments graphiques.
	 * @param rectangle Le rectangle définissant la position et la taille de la boîte.
	 */
	public BoiteDescription(Rectangle rectangle) {
		super(rectangle);

		/* HACKED BY BENDAL */
		try {

			Font font = null;
			Font fontTexte = null;
			File in = new File("fonts/PrStart.ttf");
			font = font.createFont(Font.TRUETYPE_FONT, in);
			in = new File("fonts/Volter__28Goldfish_29.ttf");
			fontTexte = fontTexte.createFont(Font.TRUETYPE_FONT, in);
			font1 = fontTexte.deriveFont(15.0f);
			font2 = fontTexte.deriveFont(20.0f);
			font3 = font.deriveFont(25.0f);
			font4 = font.deriveFont(14.0f);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		/****************/

		bouton = new Texture[6];
		tBouton = new Texte[6];
		texteBouton = new String[7];

		// declaration des texture bouton + joystick
		this.joystick = new Texture("img/joystick2.png", new Point(740, 100), 40, 40);
		for (int i = 0; i < 3; i++) {
			this.bouton[i] = new Texture("img/ibouton2.png", new Point(890 + 130 * i, 130), 40, 40);
		}
		for (int i = 3; i < 6; i++) {
			this.bouton[i] = new Texture("img/ibouton2.png", new Point(890 + 130 * (i - 3), 50), 40, 40);
		}

		// declaration des textes bouton + joystick
		this.tJoystick = new Texte(Couleur.NOIR, "...", font1, new Point(760, 80));
		for (int i = 0; i < 3; i++) {
			this.tBouton[i] = new Texte(Couleur.NOIR, "...", font1, new Point(910 + 130 * i, 120));
		}
		for (int i = 3; i < 6; i++) {
			this.tBouton[i] = new Texte(Couleur.NOIR, "...", font1, new Point(910 + 130 * (i - 3), 40));
		}
		stop = false;
		message = new Texte[10];
		for (int i = 0; i < message.length; i++) {
			message[i] = new Texte(Couleur.NOIR, "", font2, new Point(960, 590));
			message[i].translater(0, -i * 30);

		}
		nombreLigne = 0;

		highscore = new Texte(Couleur.NOIR, "...", font2, new Point(960, 590));
		listeHighScore = new Texte[10];
		for (int i = 0; i < listeHighScore.length; i++) {
			listeHighScore[i] = new Texte(Couleur.NOIR, "...", font1, new Point(960, 590 + i * 30));
		}
	}

	/**
	 * Lit le contenu d'un fichier de description et met à jour les messages affichés.
	 * @param path Le chemin vers le fichier de description.
	 */
	public void lireFichier(String path) {
		String fichier = path + "/description.txt";

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				if (nombreLigne < message.length) {
					message[nombreLigne].setTexte(ligne);
					nombreLigne++;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		;

	}

	/**
	 * Lit le contenu d'un fichier de haut score et met à jour les éléments de haut score affichés.
	 * @param path Le chemin vers le fichier de haut score.
	 */
	public void lireHighScore(String path) {
		String fichier = path + "/highscore.txt";

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int i = 0;
			while ((ligne = br.readLine()) != null && i < listeHighScore.length) {
				listeHighScore[i].setTexte(ligne);
				i++;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		;

	}

	/**
	 * Lit le contenu d'un fichier de configuration des boutons et met à jour les textes des boutons.
	 * @param path Le chemin vers le fichier de configuration des boutons.
	 */
	public void lireBouton(String path) {
		// System.out.println(path);
		String fichier = Paths.get("").toAbsolutePath().toString() + "/" + path + "/bouton.txt";

		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			ligne = br.readLine();
			if (ligne == null) {
				System.err.println("le fichier bouton est surement vide!" + fichier);
			} else {
				texteBouton = ligne.split(":");
				// changer le texte des boutons
				settJoystick(texteBouton[0]);
				for (int i = 0; i < 6; i++) {
					settBouton(texteBouton[i + 1], i);
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		;

	}

	public Texte[] getMessage() {
		return message;
	}

	/**
	 * Met à jour un message spécifique dans le tableau de messages.
	 * @param message Le texte à afficher.
	 * @param a L'indice du message à mettre à jour.
	 */
	public void setMessage(String message, int a) {
		this.message[a].setTexte(message);
	}

	public Texture[] getBouton() {
		return this.bouton;
	}

	public Texture getJoystick() {
		return this.joystick;
	}

	public Texte[] gettBouton() {
		return this.tBouton;
	}

	public Texte gettJoystick() {
		return this.tJoystick;
	}

	public Texte getHighscore() {
		return this.highscore;
	}

	public Texte[] getListeHighScore() {
		return this.listeHighScore;
	}

	/**
	 * Met à jour le texte du bouton joystick.
	 * @param s Le nouveau texte à afficher.
	 */
	public void settJoystick(String s) {
		this.tJoystick.setTexte(s);
	}

	/**
	 * Met à jour le texte d'un bouton spécifique.
	 * @param s Le nouveau texte à afficher.
	 * @param a L'indice du bouton à mettre à jour.
	 */
	public void settBouton(String s, int a) {
		this.tBouton[a].setTexte(s);
	}

	/*
	 * public Texte getMessage() {
	 * return message;
	 * }
	 */
}