package fr.zwartkat.services;

import fr.zwartkat.IPlayer;
import fr.zwartkat.Piece;
import fr.zwartkat.Tray;
import fr.zwartkat.Position;
import fr.zwartkat.engine.GameState;
import fr.zwartkat.events.GameEvent;

import java.util.List;

public class TurnManager {

    private ActionService actionService;
    private EventBus eventBus;

    public TurnManager(ActionService actionService, EventBus eventBus){
        this.actionService = actionService;
        this.eventBus = eventBus;
    }

    public boolean executeTurn(GameState gameState){

        Piece currentPiece = gameState.getSelectedPiece();
        Position clickedPosition = gameState.getPendingClick();
        Tray tray = gameState.getTray();

        if(currentPiece == null || clickedPosition == null) return false;

        if(currentPiece.getPosition().equals(clickedPosition)){
            cancelMove(gameState);
        } else if (gameState.getPendingMoves().contains(clickedPosition)) {
            this.actionService.move(tray,currentPiece,clickedPosition);
            return true;
        } else if(gameState.getPendingAttacks().contains(clickedPosition)){
            Piece adverse = tray.getCase(clickedPosition);
            CombatResult result = this.actionService.attack(currentPiece,adverse);

            if (result.isAttackerKo()){
                eventBus.publish(new GameEvent.Ko(currentPiece));
                this.removePiece(tray,currentPiece);
            }
            if (result.isDefenderKo()){
                eventBus.publish(new GameEvent.Ko(adverse));
                this.removePiece(tray,adverse);
                this.actionService.move(tray,currentPiece,adverse.getPosition());
            }
            return true;

        } else if (tray.getCase(clickedPosition) != null) {
            currentPiece = tray.getCase(clickedPosition);
            this.eventBus.publish(new GameEvent.ClickOn(currentPiece));
        }

        return false;
    }

    public void nextRound(GameState gameState){
        List<IPlayer> players = gameState.getPlayers();
        int idx = (players.indexOf(gameState.getCurrentPlayer()) + 1) % players.size();
        gameState.setCurrentPlayer(players.get(idx));
        this.eventBus.publish(new GameEvent.ChangePlayer(gameState.getCurrentPlayer()));
    }

    public void cancelMove(GameState gameState){
        //gameState.setSelectedPiece(null);
    }

    public void removePiece(Tray tray, Piece piece){
        tray.remove(piece.getPosition());
        piece.getPlayer().removePiece(piece);
    }
}
