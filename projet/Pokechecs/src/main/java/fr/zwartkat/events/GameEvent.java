package fr.zwartkat.events;

import fr.zwartkat.IPlayer;
import fr.zwartkat.Piece;
import fr.zwartkat.Position;
import fr.zwartkat.Selector;
import fr.zwartkat.services.CombatResult;

import java.awt.*;

public abstract class GameEvent {

    public static class Interact extends GameEvent {
        public Interact(){}
    }

    public static class SelectorMove extends GameEvent {
        public Selector.Direction direction;
        public SelectorMove(Selector.Direction direction){
            this.direction = direction;
        }
    }

    public static class SelectorMoved extends GameEvent {
        public Position position;
        public SelectorMoved(Position position){
            this.position = position;
        }
    }

    public static class SelectorOn extends GameEvent {
        public Piece piece;
        public SelectorOn(Piece piece){
            this.piece = piece;
        }
    }

    public static class ChangePlayer extends GameEvent {
        public IPlayer player;
        public ChangePlayer(IPlayer player){
            this.player = player;
        }
    }

    public static class ClickOn extends GameEvent {
        public Piece piece;
        public ClickOn(Piece piece){
            this.piece = piece;
        }
    }

    public static class Select extends GameEvent {
        public Piece selected;
        public Select(Piece piece){
            this.selected = piece;
        }
    }

    public static class UnSelect extends GameEvent {
        public UnSelect(){};
    }

    public static class Action extends GameEvent {
        public Position position;
        public Piece piece;
        public Action(Piece piece, Position position){
            this.position = position;
            this.piece = piece;
        }
    }

    public static class Move extends GameEvent {
        public Piece piece;
        public Position from;
        public Position to;
        public Move(Piece piece, Position from, Position to){
            this.piece = piece;
            this.from = from;
            this.to = to;
        }
    }

    public static class Fight extends GameEvent {
        public CombatResult result;
        public Piece attacker, defender;
        public Fight(Piece attacker, Piece defender, CombatResult result){
            this.result = result;
            this.attacker = attacker;
            this.defender = defender;
        }
    }

    public static class Ko extends GameEvent {
        public Piece piece;
        public Ko(Piece piece){
            this.piece = piece;
        }
    }

    public static class GameOver extends GameEvent {
        public IPlayer winner, looser;
        public GameOver(IPlayer winner, IPlayer looser){
            this.winner = winner;
            this.looser = looser;
        }
    }
}
