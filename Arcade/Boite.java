package Arcade;


import MG2D.geometrie.Rectangle;

/**
 * Classe abstraite représentant une boîte géométrique.
 */
public abstract class Boite {
    private Rectangle rectangle;
	
    /**
     * Constructeur de la classe Boite.
     * 
     * @param rectangle Le rectangle qui définit les dimensions de la boîte.
     */
    Boite(Rectangle rectangle){
	this.rectangle = rectangle;
    }

    /**
     * Retourne le rectangle associé à la boîte.
     * 
     * @return Le rectangle représentant la boîte.
     */
    public Rectangle getRectangle() {
	return rectangle;
    }

    /**
     * Définit le rectangle associé à la boîte.
     * 
     * @param rectangle Le nouveau rectangle pour la boîte.
     */
    public void setRectangle(Rectangle rectangle) {
	this.rectangle = rectangle;
    }
}