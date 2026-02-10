package Arcade;

import java.awt.Font;

import MG2D.Couleur;
import MG2D.FenetrePleinEcran;
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


    public Bouton(){
		this.text = null;
		this.texture = null;
		this.name = null;
		this.game = new Game();
		this.gameId = -1;
    }

	public Bouton(Game game){
		this.text = new Texte(new Texte(Couleur .NOIR, game.getName(), new Font("Calibri", Font.TYPE1_FONT, 30), new Point(310, 510)));
		this.texture = new Texture("img/bouton2.png", new Point(100, 478), 400, 65);
		this.gameId = game.getGameId();
		this.name = game.getName();
		this.game = game;
	}

    public Bouton(String text, String name){
		this.text = new Texte(new Texte(Couleur .NOIR, text, new Font("Calibri", Font.TYPE1_FONT, 30), new Point(310, 510)));
		this.texture = new Texture("img/bouton2.png", new Point(100, 478), 400, 65);
		this.name = name;
		this.game = new Game();
    }

	public boolean isTextVisible(){
		return textIsVisible;
	}

	public boolean toggleTextVisibility(){
		textIsVisible = !textIsVisible;
		return  textIsVisible;
	}

	public void setTextVisible(boolean visible){
		textIsVisible = visible;
	}

	public void translater(int i, int i1 ){
		texture.translater(i,i1);
		text.translater(i,i1);
	}

	public Game getGame(){
		return game;
	}

	private void setGame(Game game){
		this.game = game;
	}

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Texte getText() {
	return text;
    }

    public void setText(Texte text) {
	this.text = text;
    }

    public Texture getTexture() {
	return texture;
    }

    public void setTexture(Texture texture) {
	this.texture = texture;
    }

    public int getGameId() {
	return gameId;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

	public String toString(){
		return "Class bouton: Id("+gameId + "),Name(" + name + ")";
	}
}
