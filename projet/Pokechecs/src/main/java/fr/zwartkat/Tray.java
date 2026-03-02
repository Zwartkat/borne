package fr.zwartkat;

import fr.zwartkat.database.dao.IPokemonDao;

import java.util.HashMap;
import java.util.List;

public class Tray {

    private final int size;
    private HashMap<String,Piece> pieces;

    public Tray(int size){
        this.size = size;
        this.pieces = new HashMap<>();
    }

    public void add(Position position, Piece piece){
        this.pieces.put(position.toString(),piece);
    }

    // Remove the piece of the tray
    public void remove(Position position){
        this.pieces.remove(position.toString());
    }

    public void remove(Piece piece){
        this.pieces.remove(piece.getPosition().toString());
    }

    public HashMap<String, Piece> getPieces() {
        return pieces;
    }

    // Get the case with the position
    public Piece getCase(Position position){
        return getCase(position.toString());
    }

    // Get the case with x and y values
    public Piece getCase(int positionX, int positionY){
        return getCase(new Position(positionX,positionY));
    }

    // Get the case with the name of the case
    public Piece getCase(String position){
        return this.pieces.get(position);
    }

    // Get the size of the tray
    public int getSize(){
        return size;
    }

}
