package fr.zwartkat.views;

import MG2D.Fenetre;
import MG2D.geometrie.Cercle;
import MG2D.geometrie.Point;
import MG2D.geometrie.Texte;
import MG2D.geometrie.Texture;

public class PieceDraw {
    Texture texture;
    Cercle circle;
    Texte hp;

    public PieceDraw(Texture texture, Cercle circle, Texte hp){
        this.texture = texture;
        this.circle = circle;
        this.hp = hp;
    }

    public void moveTo(int x, int y, int caseSize, int offset) {
        texture.setA(new Point(x, y));
        circle.setO(new Point(x + caseSize - offset, y + caseSize - offset));
        hp.setA(new Point(x + offset, y + caseSize - offset));
    }

    public Texture getTexture() {
        return texture;
    }

    public Cercle getCircle() {
        return circle;
    }

    public Texte getHp() {
        return hp;
    }
}
