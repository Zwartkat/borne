package fr.zwartkat;

import fr.zwartkat.events.GameEvent;
import fr.zwartkat.services.EventBus;

public class Selector {

    private final EventBus eventBus;
    private final int traySize;
    private Position currentPosition;
    private long lastMoveTime = 0;
    private static final long MOVE_DELAY = 300;

    public enum Direction {
        UP,DOWN,LEFT,RIGHT
    }

    public Selector(EventBus eventBus, int traySize, Position initialPosition){
        this.eventBus = eventBus;
        this.traySize = traySize;

        this.currentPosition = initialPosition;

        this.eventBus.subscribe(GameEvent.SelectorMove.class, this::onSelectorMove);
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    private void onSelectorMove(GameEvent.SelectorMove event){

        long now = System.currentTimeMillis();
        if (now - lastMoveTime < MOVE_DELAY) return;

        Direction direction = event.direction;
        Position futurePosition = new Position(this.currentPosition);

        if(direction == Direction.UP){
            futurePosition.setY(futurePosition.getY() + 1);
        } else if (direction == Direction.DOWN) {
            futurePosition.setY(futurePosition.getY() - 1);
        } else if (direction == Direction.RIGHT) {
            futurePosition.setX(futurePosition.getX() + 1);
        } else if (direction == Direction.LEFT) {
            futurePosition.setX(futurePosition.getX() - 1);
        }

        System.out.println(futurePosition);

        if(isValidPosition(futurePosition)){
            this.eventBus.publish(new GameEvent.SelectorMoved(futurePosition));
            this.currentPosition = futurePosition;
            this.lastMoveTime = now;
        }
    }

    private boolean isValidPosition(Position position){
        if(position.getX() < 0 || position.getY() < 0 || position.getX() > this.traySize - 1 || position.getY() > this.traySize - 1) return false;
        return true;
    }
}