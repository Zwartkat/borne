package Arcade;


import MG2D.geometrie.Rectangle;

/**
 * Classe abstraite représentant une boîte géométrique définie par un rectangle.
 * Cette classe sert de base pour des implémentations concrètes de boîtes
 * avec des propriétés spécifiques (ex: collision, animation, etc.).
 */
public abstract class Boite {
    private Rectangle rectangle;
	
    /**
     * Constructeur de la classe Boite.
     * 
     * @param rectangle Le rectangle qui définit les dimensions et la position
     *                  de la boîte dans l'espace 2D.
     */
    Boite(Rectangle rectangle){
	this.rectangle = rectangle;
    }

    /**
     * Retourne le rectangle associé à la boîte.
     * 
     * @return Le rectangle représentant la boîte, qui définit ses dimensions
     *         et sa position dans le plan 2D.
     */
    public Rectangle getRectangle() {
	return rectangle;
    }

    /**
     * Définit le rectangle associé à la boîtE.
     * 
     * @param rectangle Le nouveau rectangle pour la boîte. Ce changement
     *                  affecte les dimensions et la position de la boîte.
     */
    public void setRectangle(Rectangle rectangle) {
	this.rectangle = rectangle;
    }
}