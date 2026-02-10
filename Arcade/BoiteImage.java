package Arcade;

import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Texture;

import java.io.File;
import java.util.Objects;


public class BoiteImage extends Boite {

    Texture image;

    BoiteImage(Rectangle rectangle, String image) {
	super(rectangle);
        System.out.println(new File(".").getAbsolutePath());

        if(!image.isEmpty()){
        this.image = new Texture(image+"/photo_small.png", new Point(760, 648));
    }
    else {
        this.image = new Texture("img/blancTransparent.png", new Point(760,648));
    }
    }

    public Texture getImage() {
	return this.image;
    }

    public void setImage(String chemin) {
        if(!chemin.isEmpty()){
            this.image.setImg(chemin+"/photo_small.png");
        }
	//this.image.setTaille(400, 320);
    }

}
