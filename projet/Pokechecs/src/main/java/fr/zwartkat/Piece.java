package fr.zwartkat;

import java.util.ArrayList;

import fr.zwartkat.pokemon.Pokemon;

public class Piece {
    private Pokemon pokemon;
    private IPlayer player;
    private Position position;

    public Piece(Piece piece){
        this.pokemon = piece.getPokemon();
        this.player = piece.getPlayer();
        this.position = new Position(piece.getPosition());
    }

    public Piece(Pokemon pokemon, IPlayer player, int positionX, int positionY){

        this.pokemon = pokemon;
        this.player = player;
        this.position = new Position(positionX, positionY);
    }

    public Piece(Pokemon pokemon, IPlayer player, String position){

        this.pokemon = pokemon;
        this.player = player;
        this.position = new Position(position);
    }

    public Piece(Pokemon pokemon, IPlayer player, Position position){
        this.pokemon = pokemon;
        this.player = player;
        this.position = position;
    }

    // Get the species of the pokemon
    public Pokemon getPokemon(){
        return this.pokemon;
    }

    // Get the owner of the piece
    public IPlayer getPlayer(){
        return this.player;
    }

    // Get the position of the piece
    public Position getPosition(){
        return this.position;
    }

    // Set the position of the piece
    public void setPosition(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public boolean equals(Object obj){

        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Piece otherPiece = (Piece) obj;

        return this.pokemon.equals(otherPiece.pokemon) && this.position.equals(otherPiece.position) && this.player.getId() == otherPiece.player.getId();
    }

    public String toString(){
        return new String("" + this.pokemon.getName() + " du joueur " + this.player.getId() + " en " + this.position);
    }
}