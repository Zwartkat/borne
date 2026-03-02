package fr.zwartkat.engine;

import MG2D.Couleur;
import fr.zwartkat.*;
import fr.zwartkat.database.dao.IPokemonDao;
import fr.zwartkat.pokemon.Pokemon;
import fr.zwartkat.services.EventBus;

import java.util.List;
import java.util.Map;

public class GameInitializer {

    private final IPokemonDao<Pokemon> pokemonDao;

    public GameInitializer(IPokemonDao<Pokemon> pokemonDao){
        this.pokemonDao = pokemonDao;
    }

    public GameState createGameState(EventBus eventBus, int traySize, String filename){
        GameState gameState = new GameState();

        Player player1 = new Player(1, Couleur.BLEU);
        Player player2 = new Player(2,Couleur.ROUGE);

        gameState.addPlayer(player1);
        gameState.addPlayer(player2);

        gameState.setCurrentPlayer(player1);

        Tray tray = this.loadConfig(gameState.getPlayers(),traySize,filename);

        gameState.setTray(tray);

        Selector selector = new Selector(eventBus,traySize,new Position(4,4));

        gameState.setSelector(selector);

        return gameState;
    }

    private Tray loadConfig(List<IPlayer> players, int size, String filename) {

        Tray tray = new Tray(size);
        Map<String, Map<String,Object>> config = Config.read(filename);
        System.out.println("Config :"+config);
        if(config == null) return null;

        if(players == null){
            throw new IllegalStateException("There is no players defined");
        } else if (players.isEmpty()){
            throw new IllegalStateException("There is no players");
        } else if (players.size() != 2){
            throw new IllegalStateException("There is not 2 players only");
        }

        for (Map.Entry<String, Map<String, Object>> entry : config.entrySet()) {

            Position position = new Position(entry.getKey());
            Map<String, Object> data = entry.getValue();
            Object pokemonObject = data.get("pokemon");

            String pokemonName = pokemonObject instanceof String ? (String) pokemonObject : null;
            if (pokemonName == null) {
                System.err.println("Invalid pokemon at " + entry.getKey());
                continue;
            }

            Object playerObject = data.get("player");
            Integer playerIndex = playerObject instanceof Integer ? ((Integer) playerObject) - 1 : null;

            if (playerIndex == null) {
                System.err.println("Invalid player at " + entry.getKey());
                continue;
            }
            if (playerIndex < 0 || playerIndex >= players.size()) {
                System.err.println("Player index out of range at position " + entry.getKey());
                continue;
            }

            Pokemon pokemon = pokemonDao.getPokemon((String) pokemonObject);
            IPlayer player = players.get(playerIndex);

            Piece piece = new Piece(pokemon,player,position);

            player.addPiece(piece);

            if(position.toString().equals("E1") || position.toString().equals("E9")){
                player.setMasterPiece(piece);
            }

            tray.add(position,piece);
        }
        return tray;
    }
}
