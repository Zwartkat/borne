package fr.zwartkat.services;

import fr.zwartkat.Piece;
import fr.zwartkat.Position;
import fr.zwartkat.Tray;
import fr.zwartkat.events.GameEvent;
import fr.zwartkat.pokemon.IPokemon;
import java.util.ArrayList;
import java.util.List;

public class ActionService {

    private EventBus eventBus;
    private CombatService combatService;

    public ActionService(EventBus eventBus, CombatService combatService){
        this.eventBus = eventBus;
        this.combatService = combatService;
    };

    public List<Position> calculatePossibleMoves(Tray tray, Piece piece){

        List<Position> movement = getAroundCase(tray, piece,1);

         movement = movement
                .stream()
                .filter(position -> tray.getCase(position) == null)
                .toList();
         return movement;
    }

    public List<Position> calculatePossibleAttacks(Tray tray, Piece piece){

        List<Position> attacks = getAroundCase(tray, piece,1);

        return attacks
                .stream()
                .filter(position -> {
                        Piece target = tray.getCase(position);
                        return target != null && !target.getPlayer().equals(piece.getPlayer());
                    }
                )
                .toList();

    }

    public List<Position> getAroundCase(Tray tray, Piece piece, int range) {

        List<Position> aroundCase = new ArrayList<>();

        int startX = Math.max(0, piece.getPosition().getX() - range);
        int endX = Math.min(tray.getSize()-1, piece.getPosition().getX() + range);
        int startY = Math.max(0, piece.getPosition().getY() - range);
        int endY = Math.min(tray.getSize()-1, piece.getPosition().getY() + range);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Position position = new Position(i,j);
                if (!(piece.getPosition().equals(position))) {
                    aroundCase.add(position);
                }
            }
        }
        return aroundCase;
    }

    public void move(Tray tray, Piece piece, Position position){
        System.out.println(piece);
        // System.out.println("Event" + tray.getCase(piece.getPosition().toString()) + tray.getCase(position).toString());
        tray.remove(piece.getPosition());
        tray.add(position,piece);
        eventBus.publish(new GameEvent.Move(piece,piece.getPosition(),position));
        piece.setPosition(position);
    }

    public CombatResult attack(Piece attacker, Piece defender){
        IPokemon pokemon1 = attacker.getPokemon();
        IPokemon pokemon2 = defender.getPokemon();

        CombatResult result = combatService.fight(pokemon1,pokemon2);

        eventBus.publish(new GameEvent.Fight(attacker,defender,result));

        return result;
    }
}
