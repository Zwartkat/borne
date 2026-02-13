package Arcade;

import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Texture;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

public class BoiteImage extends Boite {

    Texture image;

    /**
     * Constructeur de la classe BoiteImage
     * @param rectangle Rectangle qui détermine la position et la taille de la boite
     * @param image Chemin vers l'image à afficher dans la boîte. Si vide, une image par défaut est utilisée.
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
     * Retourne l'image associée à la boîte.
     * @return La texture de l'image affichée dans la boîte.
     */
    public Texture getImage() {
        return this.image;
    }

    /**
     * Modifie l'image affichée dans la boîte.
     * @param chemin Chemin vers le nouvel image à afficher.
     */
    public void setImage(String chemin) {
        if (!chemin.isEmpty()) {
            this.image.setImg(chemin + "/photo_small.png");
        }
        // this.image.setTaille(400, 320);
    }

}  



