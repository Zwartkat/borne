package fr.zwartkat.engine;

import MG2D.FenetrePleinEcran;
import MG2D.Fenetre;
import fr.zwartkat.database.PokemonDatabase;
import fr.zwartkat.database.dao.PokemonDao;
import fr.zwartkat.services.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class GameEngine {
    private final int traySize = 9;
    private final int casePixel;

    private final Fenetre fenetre;

    private final GameInitializer gameInitializer;
    private final EventBus eventBus;
    private final ActionService actionService;
    private final CombatService combatService;
    private final TurnManager turnManager;
    private final InputController inputController;

    private GameState gameState;
    private GameMusic music;
    private GameUpdater updater;
    private GameRenderer renderer;
    private boolean running;

    public GameEngine() {

        double caseWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getWidth() / (double) traySize + 3;
        double caseHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight() / (double) traySize + 3;

        System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getWidth() + " " + GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight());

        this.casePixel = 80;
        //this.casePixel = Math.round((long)Math.min(caseWidth, caseHeight));
        this.fenetre = new Fenetre("Pokechecs",960,720);

        try {
            PokemonDatabase pokemonDatabase = PokemonDatabase.getInstance();
            PokemonDao pokemonDao = new PokemonDao(pokemonDatabase);
            this.gameInitializer = new GameInitializer(pokemonDao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.eventBus = new EventBus();

        this.combatService = new CombatService(new DamageCalculator());
        this.actionService = new ActionService(this.eventBus, this.combatService);
        this.turnManager = new TurnManager(this.actionService,this.eventBus);

        this.inputController = new InputController(fenetre,casePixel,traySize,eventBus);
    }

    public void startGame(String filename){

        gameState = this.gameInitializer.createGameState(eventBus,traySize,filename);

        this.music = new GameMusic();
        this.updater = new GameUpdater(turnManager,actionService,eventBus,gameState);
        this.renderer = new GameRenderer(this.eventBus,this.fenetre,this.casePixel,this.traySize,this.gameState);

        this.run();
    }

    private void run(){
        running = true;

        while(this.running){
            sleep(16);
            this.music.play();
            this.inputController.poll();
            this.updater.update();
            this.renderer.render();
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
