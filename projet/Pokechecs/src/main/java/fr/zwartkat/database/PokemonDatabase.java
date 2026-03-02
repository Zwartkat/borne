package fr.zwartkat.database;

import fr.zwartkat.Main;
import fr.zwartkat.pokemon.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PokemonDatabase implements IDatabase<Pokemon> {

    private static PokemonDatabase instance;
    private Connection connection;
    private String url = "jdbc:sqlite:pokemon.db";

    private PokemonDatabase() throws SQLException, IOException {
        Path target = Paths.get("pokemon.db").toAbsolutePath();

        if (!Files.exists(target)) {
            try (InputStream is = Main.class.getResourceAsStream("/pokemon.db")) {
                if (is == null) throw new RuntimeException("DB introuvable");
                Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        this.url = "jdbc:sqlite:" + target;
        this.connection = getConnection();

    }

    public static PokemonDatabase getInstance() throws SQLException, IOException {
        if (instance != null) return instance;
        instance = new PokemonDatabase();
        return instance;
    }

    public Connection getConnection()  {
        try {

            return DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
