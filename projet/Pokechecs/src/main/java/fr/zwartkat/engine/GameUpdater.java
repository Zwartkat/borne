package fr.zwartkat.engine;

import fr.zwartkat.*;
import fr.zwartkat.events.GameEvent;
import fr.zwartkat.services.ActionService;
import fr.zwartkat.services.EventBus;
import fr.zwartkat.services.TurnManager;

import java.util.List;

public class GameUpdater {

    private final TurnManager turnManager;
    private final ActionService actionService;
    private final EventBus eventBus;
    private final GameState gameState;
    private long lastInteractTime = 0;
    private static final long INTERACT_DELAY = 300;

    public GameUpdater(TurnManager turnManager,
                       ActionService actionService,
                       EventBus eventBus,
                       GameState gameState) {
        this.turnManager = turnManager;
        this.actionService = actionService;
        this.eventBus = eventBus;
        this.gameState = gameState;

        this.eventBus.subscribe(GameEvent.Interact.class,this::onInteract);
        this.eventBus.subscribe(GameEvent.ClickOn.class,this::onSelect);
        this.eventBus.subscribe(GameEvent.SelectorMoved.class,this::onSelectorMoved);
    }

    public void update() {

        Piece selectedPiece = this.gameState.getSelectedPiece();
        Position pendingClick = this.gameState.getPendingClick();
        Tray tray = this.gameState.getTray();

        this.checkGameOver();

        if (selectedPiece != null && pendingClick != null) {
            boolean turnFinished = turnManager.executeTurn(this.gameState);
            if (turnFinished) {
                turnManager.nextRound(this.gameState);
                this.resetSelection();
            }
        }

        if (selectedPiece != null) {
            this.gameState.setPendingMoves(actionService.calculatePossibleMoves(tray,selectedPiece));
            this.gameState.setPendingAttacks(actionService.calculatePossibleAttacks(tray,selectedPiece));
        }

    }

    private void onSelectorMoved(GameEvent.SelectorMoved event){
        Piece piece = this.gameState.getTray().getCase(event.position);
        if(piece == null) return;
        this.eventBus.publish(new GameEvent.SelectorOn(piece));
    }

    private void onInteract(GameEvent.Interact event) {

        long now = System.currentTimeMillis();
        if (now - lastInteractTime < INTERACT_DELAY) return;

        lastInteractTime = now;

        Position clickedPosition = this.gameState.getSelector().getCurrentPosition();
        Tray tray = this.gameState.getTray();
        Piece clickedPiece = tray.getCase(clickedPosition);
        Piece selected = gameState.getSelectedPiece();

        if(selected == null){
            if(clickedPiece != null){
                this.eventBus.publish(new GameEvent.ClickOn(clickedPiece));
            }
            return;
        }

        if(selected.equals(clickedPiece)){
            this.resetSelection();
            this.eventBus.publish(new GameEvent.UnSelect());
            return;
        }

        if(clickedPiece != null && clickedPiece.getPlayer().equals(gameState.getCurrentPlayer())){
            this.eventBus.publish(new GameEvent.ClickOn(clickedPiece));
            return;
        }

        gameState.setPendingClick(clickedPosition);
    }

    private void onSelect(GameEvent.ClickOn event) {
        Piece piece = event.piece;

        if (piece == null) return;

        if (!piece.getPlayer().equals(gameState.getCurrentPlayer())) return;

        gameState.setSelectedPiece(piece);
        gameState.setPendingAttacks(
                actionService.calculatePossibleAttacks(gameState.getTray(), piece)
        );
        gameState.setPendingMoves(
                actionService.calculatePossibleMoves(gameState.getTray(), piece)
        );

        eventBus.publish(new GameEvent.Select(piece));
    }

    private void resetSelection(){
        this.gameState.setSelectedPiece(null);
        this.gameState.setPendingClick(null);
        this.gameState.setPendingMoves(null);
        this.gameState.setPendingAttacks(null);
    }

    public void checkGameOver(){
        List<IPlayer> players = this.gameState.getPlayers();
        IPlayer winner = null, looser = null;
        if(players.getFirst().getMasterPiece().getPokemon().isKo()){
            winner = players.get(1);
            looser = players.getFirst();
        } else if (players.get(1).getMasterPiece().getPokemon().isKo()) {
            winner = players.getFirst();
            looser = players.get(1);
        }

        if(winner != null && looser != null){
            this.eventBus.publish(new GameEvent.GameOver(winner,looser));
            this.gameState.setGameOver(true);
        }
    }
}