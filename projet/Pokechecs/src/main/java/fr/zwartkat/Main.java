package fr.zwartkat;

import fr.zwartkat.engine.GameEngine;
import fr.zwartkat.views.Menu;

public class Main {

    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();

        gameEngine.startGame("/config/"+ "gen1"+".yml");
    }

}
