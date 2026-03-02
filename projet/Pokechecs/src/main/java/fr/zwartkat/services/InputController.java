package fr.zwartkat.services;

import MG2D.Clavier;
import MG2D.Fenetre;
import MG2D.Souris;
import fr.zwartkat.Position;
import fr.zwartkat.Selector;
import fr.zwartkat.events.GameEvent;

public class InputController {
    private final Souris mouse;
    private final Clavier keyboard;
    private final int casePixel;
    private final int traySize;
    private final EventBus eventBus;


    public InputController(Fenetre fenetre,int casePixel, int traySize, EventBus eventBus) {
        this.mouse = fenetre.getSouris();
        this.keyboard = fenetre.getClavier();
        this.casePixel = casePixel;
        this.traySize = traySize;
        this.eventBus = eventBus;
    }

    public void poll(){
        if(this.keyboard.getFEnfoncee()){
            this.eventBus.publish(new GameEvent.Interact());
        } else if (this.keyboard.getBasEnfoncee()) {
            this.eventBus.publish(new GameEvent.SelectorMove(Selector.Direction.DOWN));
        } else if (this.keyboard.getHautEnfoncee()) {
            this.eventBus.publish(new GameEvent.SelectorMove(Selector.Direction.UP));
        } else if (this.keyboard.getGaucheEnfoncee()) {
            this.eventBus.publish(new GameEvent.SelectorMove(Selector.Direction.LEFT));
        } else if (this.keyboard.getDroiteEnfoncee()){
            this.eventBus.publish(new GameEvent.SelectorMove(Selector.Direction.RIGHT));
        }
    }

    private Position getMousePosition(Souris souris){
        if(souris.getPosition().getX()/casePixel < traySize && souris.getPosition().getY()/casePixel < traySize){
            return new Position(souris.getPosition().getX()/casePixel, souris.getPosition().getY()/casePixel);
        }
        return null;
    }
}