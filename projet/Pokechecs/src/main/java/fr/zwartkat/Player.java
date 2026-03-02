package fr.zwartkat;

import MG2D.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements IPlayer{

    private int id;
    private Couleur color;
    private List<Piece> pieces;
    private Piece masterPiece;

    public Player(int id, Couleur color){
        this.id = id;
        this.color = color;
        this.pieces = new ArrayList<>();
        this.masterPiece = null;
    }

    public Player(int id, Couleur color, List<Piece> pieces, Piece masterPiece){
        this.id = id;
        this.color = color;
        this.pieces = pieces;
        this.masterPiece = masterPiece;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Couleur getColor() {
        return this.color;
    }

    @Override
    public void setColor(Couleur color) {
        this.color = color;
    }

    @Override
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    @Override
    public boolean removePiece(Piece piece) {
        return this.pieces.remove(piece);
    }

    @Override
    public boolean removePieceAtPosition(Position position) {
        return this.pieces.removeIf(piece -> piece.getPosition().equals(position));
    }

    @Override
    public void setPieces(Piece piece) {

    }

    @Override
    public Piece getMasterPiece() {
        return this.masterPiece;
    }

    @Override
    public void setMasterPiece(Piece piece) {
        this.masterPiece = piece;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Player player = (Player) obj;

        return id == player.id
                && (Objects.equals(color, player.color))
                && (Objects.equals(masterPiece, player.masterPiece))
                && (Objects.equals(pieces, player.pieces));
    }

}
