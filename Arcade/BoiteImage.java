package Arcade;

import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Texture;

import java.io.File;

public class BoiteImage extends Boite {

    Texture image;

    /**
     * Constructeur de la classe BoiteImage
     * Crée une boîte image à partir d'un rectangle et d'un chemin d'accès à l'image.
     * 
     * @param rectangle Rectangle qui définit la position et la taille de la boîte
     * @param image Chemin vers l'image à afficher. Si vide, une image par défaut est utilisée.
     *              Le chemin fourni est concaténé avec "/photo_small.png" pour charger le fichier.
     */
    BoiteImage(Rectangle rectangle, String image) {
        super(rectangle);
        System.out.println(new File(".").getAbsolutePath());

        if (!image.isEmpty()) {
            this.image = new Texture(image + "/photo_small.png", new Point(760, 648));
        } else {
            this.image = new Texture("img/blancTransparent.png", new Point(760, 648));
        }
    }

    /**
     * Retourne la texture associée à l'image affichée dans la boîte.
     * 
     * @return La texture de l'image affichée dans la boîte
     */
    public Texture getImage() {
        return this.image;
    }

    /**
     * Met à jour l'image affichée dans la boîte.
     * 
     * @param chemin Nouveau chemin d'accès à l'image. Si vide, l'image par défaut est utilisée.
     *              Le chemin fourni est concaténé avec "/photo_small.png" pour charger le fichier.
     */
    public void setImage(String chemin) {
        if (!chemin.isEmpty()) {
            this.image.setImg(chemin + "/photo_small.png");
        }
        // this.image.setTaille(400, 320);
    }

}