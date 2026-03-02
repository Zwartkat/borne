package fr.zwartkat.database.dao;

import fr.zwartkat.pokemon.Pokemon;
import java.util.List;

public interface IPokemonDao<T extends Pokemon> extends IDao<T> {

    List<T> getAllPokemons();
    Pokemon getPokemon(String name);
}
