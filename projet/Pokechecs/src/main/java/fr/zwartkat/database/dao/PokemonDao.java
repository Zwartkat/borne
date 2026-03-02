package fr.zwartkat.database.dao;

import fr.zwartkat.database.IDatabase;
import fr.zwartkat.pokemon.Pokemon;
import fr.zwartkat.pokemon.PokemonFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDao implements IPokemonDao {

    private IDatabase<Pokemon> service;

    public PokemonDao(IDatabase<Pokemon> service) {
        this.service = service;
    }

    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        String request = "SELECT id, nom, hp, atk, defense, speed, type1, type2 from pokemon";
        try (Connection conn = service.getConnection(); PreparedStatement stmt = conn.prepareStatement(request); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pokemons.add(pokemonFromRequest(rs));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return pokemons;
    }

    public Pokemon getPokemon(String name) {
        name = name.replace("-", "_").replace(".", "_").replace("É", "E").replace("é", "e").replace("ê", "e").replace("è","e").replace("î", "i").replace("ï", "i").replace("ô", "o").replace("♀", "F").replace("♂", "M").replace(" ","").replace("ç", "c").replace("â", "a");
        String request = "SELECT id, nom, hp, atk, defense, speed, type1, type2 from pokemon where nom = ?";
        try (Connection conn = service.getConnection(); PreparedStatement stmt = conn.prepareStatement(request)) {
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            Pokemon p = pokemonFromRequest(rs);
            stmt.close();
            rs.close();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pokemon pokemonFromRequest(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        int hp = rs.getInt(3);
        int atk = rs.getInt(4);
        int def = rs.getInt(5);
        int spd = rs.getInt(6);
        String type1 = rs.getString(7);
        String type2 = rs.getString(8);
        return PokemonFactory.createPokemon(id, name, hp, atk, def, spd, type1, type2);
    }
}