package Arcade.tests;

/**
 * Package contenant des tests et exemples d'utilisation des classes de la bibliothèque MG2D.
 * Ce package inclut notamment des tests pour la gestion des scores (HighScore), 
 * ainsi que des exemples de gestion d'interfaces graphiques et de gestion d'entrées clavier.
 */
import MG2D.*;
/**
 * Import des classes de la bibliothèque MG2D pour la gestion graphique 2D.
 * Contient des classes fondamentales comme Fenetre, ClavierBorneArcade, 
 * et permet la création d'applications graphiques interactives.
 */
import MG2D.geometrie.*;
/**
 * Import des classes géométriques de la bibliothèque MG2D.
 * Permet la manipulation de points, textures, formes géométriques, 
 * et la gestion de rendu graphique avancé.
 */

class TestHighScore {

	/**
	 * Point d'entrée principal du programme.
	 * Crée une fenêtre, un clavier, et démarre le processus d'enregistrement d'un score.
	 * Cette méthode illustre l'utilisation de la classe HighScore pour sauvegarder un score.
	 * @param args Arguments de la ligne de commande (non utilisés dans ce cas)
	 */
	public static void main(String[] args) {
		/**
		 * Création d'une fenêtre de 1280x1024 pixels avec le titre "test".
		 * La fenêtre sert de conteneur principal pour l'interface graphique.
		 */
		Fenetre f = new Fenetre("test", 1280, 1024);
		/**
		 * Création d'un clavier de borne arcade pour la gestion des entrées clavier.
		 * Ce clavier permet de capturer les actions utilisateur via des touches physiques.
		 */
		ClavierBorneArcade clavier = new ClavierBorneArcade();
		/**
		 * Ajout du clavier en tant que listener d'événements clavier à la fenêtre.
		 * Permet de capturer les événements clavier au niveau de l'application.
		 */
		f.addKeyListener(clavier);

		/**
		 * Appel de la méthode statique demanderEnregistrerNom pour enregistrer un score.
		 * Paramètres :
		 * - f : fenêtre principale (conteneur graphique)
		 * - clavier : gestionnaire des entrées clavier
		 * - texture : image associée à l'élément (ici un vaisseau)
		 * - 40000 : score à enregistrer
		 * - "./fichierTestHighScore/text5.hig" : chemin du fichier de sauvegarde
		 */
		HighScore.demanderEnregistrerNom(f, clavier, new Texture("img/shoot.png", new Point(0, 0)), 40000,
				"./fichierTestHighScore/text5.hig");
	}

}