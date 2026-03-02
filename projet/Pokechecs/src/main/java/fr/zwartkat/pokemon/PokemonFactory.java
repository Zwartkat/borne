package fr.zwartkat.pokemon;

public class PokemonFactory{

    public static Pokemon createPokemon(int id, String name, int hp, int atk, int def, int spd, String type1, String type2) {
        return new Pokemon(id,name,hp,atk,def,spd,type1,type2);
    }
}
