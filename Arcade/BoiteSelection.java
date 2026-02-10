package Arcade;

import java.awt.Font;

import MG2D.geometrie.Rectangle;
import MG2D.audio.*;
import java.io.File;
import java.util.ArrayList;

import MG2D.Couleur;


public class BoiteSelection extends Boite {
    Pointeur pointeur;
    Font font;

    public BoiteSelection(Rectangle rectangle, Pointeur pointeur) {
	super(rectangle);
	this.pointeur = pointeur;

    }
	public boolean selection(ClavierBorneArcade clavier){

		Bruitage selectionSound = new Bruitage("sound/bip.mp3");

		font = null;
		try{
			File in = new File("fonts/PrStart.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, in);
			font = font.deriveFont(26.0f);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ArrayList<Bouton> buttons = Graphique.getButtons();
		if (buttons.isEmpty()) return true;

		if(clavier.getJoyJ1HautTape() && pointeur.getValue() < buttons.size()){
			System.out.println(pointeur.getValue() + " " + buttons.size());
			try { selectionSound.lecture(); } catch(Exception e){}
			this.nextSelection();
		}


		if(clavier.getJoyJ1BasTape() && pointeur.getValue() >= 0){

			try { selectionSound.lecture(); } catch(Exception e){}
			this.previousSelection();
		}

		if(clavier.getBoutonJ1ZTape()){
			return false;
		}

		return true;
	}


    public Pointeur getPointeur() {
		return pointeur;
    }

    public void setPointeur(Pointeur pointeur) {
	this.pointeur = pointeur;
    }

	public void nextSelection(){
		ArrayList<Bouton> buttons = Graphique.getButtons();

		if(pointeur.getValue() == 0){
			pointeur.setValue(buttons.size() - 1);
			for(Bouton b : buttons){
				b.translater(0, 110 * (buttons.size() - 1));
				b.getText().setPolice(font);
				b.getText().setCouleur(Couleur.BLANC);
			}
		} else {
			for(Bouton b : buttons){
				b.translater(0, -110);
				b.getText().setPolice(font);
				b.getText().setCouleur(Couleur.BLANC);
			}
			pointeur.setValue(pointeur.getValue() - 1);
		}
	}

	public void previousSelection(){
		ArrayList<Bouton> buttons = Graphique.getButtons();

		if(pointeur.getValue() == buttons.size() - 1){

			pointeur.setValue(0);
			for(Bouton b : buttons){
				b.translater(0, -110 * (buttons.size() - 1));
				b.getText().setPolice(font);
				b.getText().setCouleur(Couleur.BLANC);
			}
		} else {
			for(Bouton b : buttons){
				b.translater(0, 110);
				b.getText().setPolice(font);
				b.getText().setCouleur(Couleur.BLANC);
			}
			pointeur.setValue(pointeur.getValue() + 1);
		}

	}
}
