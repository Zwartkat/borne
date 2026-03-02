package fr.zwartkat.engine;

import fr.zwartkat.*;
import fr.zwartkat.services.EventBus;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private List<IPlayer> players = new ArrayList<>();
    private IPlayer currentPlayer;
    private Tray tray;
    private Selector selector;
    private boolean gameOver;
    private Piece selectedPiece;
    private Position pendingClick;
    private List<Position> pendingMoves, pendingAttacks;
    private int winner;

    public List<IPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<IPlayer> players) {
        this.players = players;
    }

    public void addPlayer(IPlayer player){
        this.players.add(player);
    }

    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(IPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Tray getTray() {
        return tray;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public Position getPendingClick() {
        return pendingClick;
    }

    public void setPendingClick(Position pendingClick) {
        this.pendingClick = pendingClick;
    }

    public List<Position> getPendingMoves() {
        return pendingMoves;
    }

    public void setPendingMoves(List<Position> pendingMoves) {
        this.pendingMoves = pendingMoves;
    }

    public List<Position> getPendingAttacks() {
        return pendingAttacks;
    }

    public void setPendingAttacks(List<Position> pendingAttacks) {
        this.pendingAttacks = pendingAttacks;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}