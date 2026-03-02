package fr.zwartkat.services;

import MG2D.Fenetre;
import fr.zwartkat.views.PieceDraw;

public class PieceViewManager {

    public void showPiece(Fenetre fenetre, PieceDraw pieceDraw){
        fenetre.ajouter(pieceDraw.getTexture());
        fenetre.ajouter(pieceDraw.getCircle());
        fenetre.ajouter(pieceDraw.getHp());
    }

    public void removePiece(Fenetre fenetre, PieceDraw pieceDraw){
        fenetre.supprimer(pieceDraw.getTexture());
        fenetre.supprimer(pieceDraw.getCircle());
        fenetre.supprimer(pieceDraw.getHp());
    }

    public void update(Fenetre fenetre, PieceDraw pieceDraw){
        this.removePiece(fenetre,pieceDraw);
        this.showPiece(fenetre,pieceDraw);
    }

}
