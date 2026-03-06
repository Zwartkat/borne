package Arcade.tests;

import MG2D.*;
import MG2D.geometrie.*;

/***************************************************************************************/
/* Les joysticks et boutons pour la borne d'arcade correspondent :                     */
/* Pour le Joueur 1 : flèches haut/bas/gauche/droite et les touches f, g, h, r, t et y */
/* Pour le joueur 2 : o, k, l et m puis les touches q, s, d, a, z et e                 */
/***************************************************************************************/

class TestClavierBorneArcade {

	public static void main(String[] args) {
		// Création de la fenêtre principale avec un titre et des dimensions
		Fenetre f = new Fenetre("Test du clavier pour la borne d'arcade", 1000, 260);
		// Initialisation du clavier pour la borne d'arcade
		ClavierBorneArcade clavier = new ClavierBorneArcade();
		// Ajout du listener de clavier à la fenêtre
		f.addKeyListener(clavier);

		// Création des éléments graphiques pour le joueur 1
		Cercle joy1Haut = new Cercle(Couleur.GRIS_CLAIR, new Point(100, 200), 30, true);
		f.ajouter(joy1Haut);
		Cercle joy1Bas = new Cercle(Couleur.GRIS_CLAIR, new Point(100, 80), 30, true);
		f.ajouter(joy1Bas);
		Cercle joy1Gauche = new Cercle(Couleur.GRIS_CLAIR, new Point(50, 140), 30, true);
		f.ajouter(joy1Gauche);
		Cercle joy1Droite = new Cercle(Couleur.GRIS_CLAIR, new Point(150, 140), 30, true);
		f.ajouter(joy1Droite);

		// Création des boutons pour le joueur 1
		Cercle bouton1A = new Cercle(Couleur.GRIS_CLAIR, new Point(270, 80), 30, true);
		f.ajouter(bouton1A);
		Cercle bouton1B = new Cercle(Couleur.GRIS_CLAIR, new Point(350, 120), 30, true);
		f.ajouter(bouton1B);
		Cercle bouton1C = new Cercle(Couleur.GRIS_CLAIR, new Point(450, 100), 30, true);
		f.ajouter(bouton1C);

		Cercle bouton1X = new Cercle(Couleur.GRIS_CLAIR, new Point(250, 160), 30, true);
		f.ajouter(bouton1X);
		Cercle bouton1Y = new Cercle(Couleur.GRIS_CLAIR, new Point(330, 200), 30, true);
		f.ajouter(bouton1Y);
		Cercle bouton1Z = new Cercle(Couleur.GRIS_CLAIR, new Point(430, 180), 30, true);
		f.ajouter(bouton1Z);

		// Création des éléments graphiques pour le joueur 2
		Cercle joy2Haut = new Cercle(Couleur.GRIS_CLAIR, new Point(600, 200), 30, true);
		f.ajouter(joy2Haut);
		Cercle joy2Bas = new Cercle(Couleur.GRIS_CLAIR, new Point(600, 80), 30, true);
		f.ajouter(joy2Bas);
		Cercle joy2Gauche = new Cercle(Couleur.GRIS_CLAIR, new Point(550, 140), 30, true);
		f.ajouter(joy2Gauche);
		Cercle joy2Droite = new Cercle(Couleur.GRIS_CLAIR, new Point(650, 140), 30, true);
		f.ajouter(joy2Droite);

		// Création des boutons pour le joueur 2
		Cercle bouton2A = new Cercle(Couleur.GRIS_CLAIR, new Point(770, 80), 30, true);
		f.ajouter(bouton2A);
		Cercle bouton2B = new Cercle(Couleur.GRIS_CLAIR, new Point(850, 120), 30, true);
		f.ajouter(bouton2B);
		Cercle bouton2C = new Cercle(Couleur.GRIS_CLAIR, new Point(950, 100), 30, true);
		f.ajouter(bouton2C);

		Cercle bouton2X = new Cercle(Couleur.GRIS_CLAIR, new Point(750, 160), 30, true);
		f.ajouter(bouton2X);
		Cercle bouton2Y = new Cercle(Couleur.GRIS_CLAIR, new Point(830, 200), 30, true);
		f.ajouter(bouton2Y);
		Cercle bouton2Z = new Cercle(Couleur.GRIS_CLAIR, new Point(930, 180), 30, true);
		f.ajouter(bouton2Z);

		// Création d'une ligne de séparation
		Ligne ligne = new Ligne(new Point(500, 50), new Point(500, 250));
		f.ajouter(ligne);

		// Boucle principale pour la mise à jour des éléments graphiques
		while (true) {
			// Mise à jour des couleurs des joysticks et boutons en fonction des touches pressées
			if (clavier.estAppuyee(KeyEvent.VK_UP)) {
				joy1Haut.setCouleur(Couleur.ROUGE);
				joy1Bas.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy1Haut.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_DOWN)) {
				joy1Bas.setCouleur(Couleur.ROUGE);
				joy1Haut.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy1Bas.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_LEFT)) {
				joy1Gauche.setCouleur(Couleur.ROUGE);
				joy1Droite.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy1Gauche.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_RIGHT)) {
				joy1Droite.setCouleur(Couleur.ROUGE);
				joy1Gauche.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy1Droite.setCouleur(Couleur.GRIS_CLAIR);
			}

			// Gestion des boutons pour le joueur 1
			if (clavier.estAppuyee(KeyEvent.VK_F)) {
				bouton1A.setCouleur(Couleur.ROUGE);
			} else {
				bouton1A.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_G)) {
				bouton1B.setCouleur(Couleur.ROUGE);
			} else {
				bouton1B.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_H)) {
				bouton1C.setCouleur(Couleur.ROUGE);
			} else {
				bouton1C.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_R)) {
				bouton1X.setCouleur(Couleur.ROUGE);
			} else {
				bouton1X.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_T)) {
				bouton1Y.setCouleur(Couleur.ROUGE);
			} else {
				bouton1Y.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_Y)) {
				bouton1Z.setCouleur(Couleur.ROUGE);
			} else {
				bouton1Z.setCouleur(Couleur.GRIS_CLAIR);
			}

			// Gestion des joysticks pour le joueur 2
			if (clavier.estAppuyee(KeyEvent.VK_O)) {
				joy2Haut.setCouleur(Couleur.ROUGE);
				joy2Bas.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy2Haut.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_K)) {
				joy2Bas.setCouleur(Couleur.ROUGE);
				joy2Haut.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy2Bas.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_L)) {
				joy2Gauche.setCouleur(Couleur.ROUGE);
				joy2Droite.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy2Gauche.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_M)) {
				joy2Droite.setCouleur(Couleur.ROUGE);
				joy2Gauche.setCouleur(Couleur.GRIS_CLAIR);
			} else {
				joy2Droite.setCouleur(Couleur.GRIS_CLAIR);
			}

			// Gestion des boutons pour le joueur 2
			if (clavier.estAppuyee(KeyEvent.VK_Q)) {
				bouton2A.setCouleur(Couleur.ROUGE);
			} else {
				bouton2A.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_S)) {
				bouton2B.setCouleur(Couleur.ROUGE);
			} else {
				bouton2B.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_D)) {
				bouton2C.setCouleur(Couleur.ROUGE);
			} else {
				bouton2C.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_A)) {
				bouton2X.setCouleur(Couleur.ROUGE);
			} else {
				bouton2X.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_Z)) {
				bouton2Y.setCouleur(Couleur.ROUGE);
			} else {
				bouton2Y.setCouleur(Couleur.GRIS_CLAIR);
			}

			if (clavier.estAppuyee(KeyEvent.VK_X)) {
				bouton2Z.setCouleur(Couleur.ROUGE);
			} else {
				bouton2Z.setCouleur(Couleur.GRIS_CLAIR);
			}

			// Rafraîchissement de l'affichage
			f.rafraichir();
		}
	}

}