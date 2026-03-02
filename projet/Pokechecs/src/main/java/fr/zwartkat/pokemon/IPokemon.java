package fr.zwartkat.pokemon;

import MG2D.audio.Bruitage;

public interface IPokemon {

    int getId();
    String getName();
    int getHp();
    int getType1();
    int getType2();
    int getAtk();
    int getDef();
    int getSpeed();
    boolean isKo();
    Bruitage getCry();
    String getTexture();
    void playCry();
    void takeDamage(int damage);

}
