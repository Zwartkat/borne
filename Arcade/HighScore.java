package Arcade;

import MG2D.*;
import MG2D.geometrie.*;
import java.io.File;
import java.awt.Font;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class HighScore {

	public static char suivant(char c) {
		if (c >= 'A' && c < 'Z')
			return (char) (c + 1);
		if (c == 'Z')
			return '.';
		if (c == '.')
			return ' ';
		return 'A';
	}

	public static char precedent(char c) {
		if (c > 'A' && c <= 'Z')
			return (char) (c - 1);
		if (c == 'A')
			return ' ';
		if (c == ' ')
			return '.';
		return 'Z';
	}

	public static void demanderEnregistrerNom(Fenetre f, ClavierBorneArcade clavier, Texture t, int s,
			String fichierHighScore) {

		ArrayList<LigneHighScore> list = lireFichier(fichierHighScore);
		for (LigneHighScore l : list)
			System.out.println(l);

		int position = 0;
		boolean fin = false;
		while (!fin) {
			if (position == list.size())
				fin = true;
			else if (s <= list.get(position).getScore())
				position++;
			else {
				fin = true;
			}
		}

		// System.out.println("position : "+position);
		if (position >= 10)
			System.exit(0);

		String score = s + "";

		char cprec[] = { ' ', ' ', ' ' };
		char c[] = { 'A', ' ', ' ', '#' };
		char csuiv[] = { ' ', ' ', ' ' };
		int indexSelec = 0;

		Font font;
		font = null;
		try {
			File in = new File("/home/pi/git/borne_arcade/fonts/PrStart.ttf");
			font = font.createFont(Font.TRUETYPE_FONT, in);
			font = font.deriveFont(40.0f);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		Texte highscore = new Texte(Couleur.NOIR, "H  I  G  H  S  C  O  R  E", font, new Point(640, 950));
		Texte scoreAtteint = new Texte(Couleur.NOIR, score, font, new Point(420, 400));
		Texte enterYourName = new Texte(Couleur.NOIR, "E n t e r   Y o u r   n a m e", font, new Point(640, 800));
		Texte posNum = new Texte(Couleur.NOIR, (position + 1) + "eme", font, new Point(120, 400));

		if (position == 0)
			posNum.setTexte("1er");

		Texte posNumPrec = new Texte(Couleur.NOIR, "", font, new Point(120, 580));
		Texte posNumSuiv = new Texte(Couleur.NOIR, "", font, new Point(120, 170));

		Texte caracteres[] = new Texte[4];
		caracteres[0] = new Texte(Couleur.NOIR, c[0] + "", font, new Point(690, 400));
		caracteres[1] = new Texte(Couleur.NOIR, c[1] + "", font, new Point(840, 400));
		caracteres[2] = new Texte(Couleur.NOIR, c[2] + "", font, new Point(990, 400));
		caracteres[3] = new Texte(Couleur.NOIR, c[3] + "", font, new Point(1140, 400));
		Texte caracteresPrec[] = new Texte[3];
		caracteresPrec[0] = new Texte(Couleur.NOIR, cprec[0] + "", font, new Point(690, 580));
		caracteresPrec[1] = new Texte(Couleur.NOIR, cprec[1] + "", font, new Point(840, 580));
		caracteresPrec[2] = new Texte(Couleur.NOIR, cprec[2] + "", font, new Point(990, 580));
		Texte scorePrec = new Texte(Couleur.NOIR, "", font, new Point(420, 580));
		Texte scoreSuiv = new Texte(Couleur.NOIR, "", font, new Point(420, 170));
		posNumPrec.setTexte(position + "eme");
		posNumSuiv.setTexte((position + 2) + "eme");



		fin = false;

		while (!fin) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}

			if (clavier.getJoyJ1DroiteTape()) {
				if (indexSelec < 3) {
					indexSelec++;
					select.translater(150, 0);
				}
			}

			if (clavier.getJoyJ1GaucheTape()) {
				if (indexSelec > 0) {
					indexSelec--;
					select.translater(-150, 0);
				}
			}

			if (clavier.getJoyJ1HautTape()) {
				if (indexSelec != 3) {
					c[indexSelec] = suivant(c[indexSelec]);
					caracteres[indexSelec].setTexte(c[indexSelec] + "");
				}
			}

			if (clavier.getJoyJ1BasTape()) {
				if (indexSelec != 3) {
					c[indexSelec] = precedent(c[indexSelec]);
					caracteres[indexSelec].setTexte(c[indexSelec] + "");
				}
			}

			if (clavier.getBoutonJ1ATape() && indexSelec == 3)
				fin = true;

			f.rafraichir();
		}

		enregistrerFichier(fichierHighScore, list, "" + c[0] + c[1] + c[2], s);

		System.exit(0);
	}

	public static ArrayList<LigneHighScore> lireFichier(String fichier) {
		ArrayList<LigneHighScore> l = new ArrayList<LigneHighScore>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				l.add(new LigneHighScore(currentLine));
			}
			reader.close();
		} catch (Exception e) {
		}

		return l;
	}

	public static void enregistrerFichier(String fichier, ArrayList<LigneHighScore> list, String nom, int score) {
		int position = 0;
		boolean fin = false;
		while (!fin) {
			if (position == list.size())
				fin = true;
			else if (score <= list.get(position).getScore())
				position++;
			else {
				fin = true;
			}
		}

		list.add(position, new LigneHighScore(nom, score));
		while (list.size() > 10)
			list.remove(list.size() - 1);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).toString());
				if (i != (list.size() - 1))
					writer.write("\n");
			}
			writer.close();
		} catch (Exception e) {
		}

	}
}