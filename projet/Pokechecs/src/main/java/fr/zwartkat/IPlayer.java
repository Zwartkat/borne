package fr.zwartkat;

import MG2D.Couleur;

public interface IPlayer {

    int getId();
    Couleur getColor();
    void setColor(Couleur color);
    void addPiece(Piece piece);
    boolean removePiece(Piece piece);
    boolean removePieceAtPosition(Position position);
    void setPieces(Piece piece);
    Piece getMasterPiece();
    void setMasterPiece(Piece piece);
}
