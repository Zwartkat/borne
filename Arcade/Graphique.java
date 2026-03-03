package Arcade;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import MG2D.geometrie.*;
import MG2D.geometrie.Point;
import MG2D.audio.*;
import MG2D.*;
import MG2D.FenetrePleinEcran;

/**
 * Classe Graphique, permet de gérer l'affichage de la borne d'arcade
 */
public class Graphique {

	// private final Fenetre f;
	private static final FenetrePleinEcran f = new FenetrePleinEcran("_Menu Borne D'arcade_");
	private int TAILLEX;
	private int TAILLEY;
	private ClavierBorneArcade clavier;
	private BoiteSelection bs;
	private BoiteImage bi;
	private BoiteDescription bd;
	public static Bouton[] tableau;
	private static ArrayList<Game> games;
	private static ArrayList<Bouton> buttons;
	private Pointeur pointeur;
	Font font;
	Font fontSelect;
	public static Bruitage musiqueFond;
	private static String[] tableauMusiques;
	private static int cptMus;

	/**
	 * Constructeur de la classe Graphique, initialise l'interface graphique et charge les jeux et boutons
	 */
	public Graphique() {

		TAILLEX = 1280;
		TAILLEY = 1024;

		font = null;

		try {
			File fontFile = new File("fonts/PrStart.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			font = font.deriveFont(24.0f);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// Initialisation de l'interface graphique
		f.afficher();

		// Chargement des jeux et boutons
		loadGame();
		loadButtons();
	}

	/**
	 * Charge les jeux depuis le répertoire projet et le fichier games.csv
	 * @return Liste des jeux chargés
	 */
	public static ArrayList<Game> loadGame() {

		if (games == null)
			games = new ArrayList<>();

		Path yourPath = FileSystems.getDefault().getPath("projet/");
		int cpt = 1;
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(yourPath)) {
			for (Path dirPath : directoryStream) {
				String name = dirPath.getFileName().toString();
				String description = "";
				String path = "projet/" + name;
				String imagePath = "";
				String lang = detectLang(dirPath);
				System.out.println(lang);
				String input = "";

				Game g = new Game(cpt, name, description, path, imagePath, lang, input);
				games.add(g);
				cpt++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<String> lines = Files.readAllLines(Path.of("games.csv"));

			for (int i = 1; i < lines.size(); i++) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return games;
	}

	/**
	 * Charge les boutons associés aux jeux
	 * @return Liste des boutons chargés
	 */
	public static ArrayList<Bouton> loadButtons() {

		if (buttons == null)
			buttons = new ArrayList<>();

		Bouton addButton = new Bouton("Ajouter un jeu", "Add");

		// buttons.add(addButton);

		games.forEach(game -> {
			Bouton button = new Bouton(game);
			button.translater(0, (game.getGameId() - 1) * -110);
			buttons.add(button);
		});

		return buttons;

	};

	/**
	 * Méthode pour détecter la langue d'un jeu en analysant son répertoire
	 * @param gameDir Répertoire du jeu
	 * @return Langue détectée ou "Unknown" si non trouvée
	 * @throws IOException Si une erreur d'entrée/sortie survient
	 */
	public static String detectLang(Path gameDir) throws IOException {

		String lang = scanOneLevel(gameDir);
		if (!lang.equals("Unknown"))
			return lang;

		Path srcDir = gameDir.resolve("src");
		if (Files.exists(srcDir) && Files.isDirectory(srcDir)) {
			lang = scanOneLevel(srcDir);
		}

		return lang;
	}

	/**
	 * Analyse un répertoire pour détecter la langue d'un jeu
	 * @param dir Répertoire à analyser
	 * @return Langue détectée ou "Unknown" si non trouvée
	 * @throws IOException Si une erreur d'entrée/sortie survient
	 */
	public static String scanOneLevel(Path dir) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path p : stream) {
				if (!Files.isRegularFile(p))
					continue;

				String file = p.getFileName().toString().toLowerCase();

				if(file.endsWith(".jar"))
					return "Jar";
				if (file.endsWith(".java"))
					return "Java";
				if (file.endsWith(".py"))
					return "Python";
			}
		}
		return "Unknown";
	}

	/**
	 * Méthode pour lancer la sélection d'un jeu
	 */
	public void selectionJeu() {
		// Implémentation de la sélection d'un jeu
	}

	/**
	 * Lancer la musique de fond aléatoirement parmi la liste définie
	 */
	public static void lectureMusiqueFond() {
		musiqueFond = new Bruitage("sound/bg/" + tableauMusiques[(int) (Math.random() * cptMus)]);
		musiqueFond.lecture();
	}

	/**
	 * Arrete la musique
	 */
	public static void stopMusiqueFond() {
		musiqueFond.arret();
	}

	/**
	 * Affiche un texte spécifique à l'écran
	 * @param valeur Index du bouton à afficher
	 */
	public static void afficherTexte(int valeur) {
		f.ajouter(buttons.get(valeur).getText());
	}

	/**
	 * Ajoute un jeu à la liste des jeux
	 * @param game Jeu à ajouter
	 */
	public static void addGame(Game game) {
		games.add(game);
	}

	/**
	 * Récupère la liste des jeux
	 * @return Liste des jeux
	 */
	public static ArrayList<Game> getGames() {
		return games;
	}

	/**
	 * Récupère la liste des boutons
	 * @return Liste des boutons
	 */
	public static ArrayList<Bouton> getButtons() {
		return buttons;
	}
}