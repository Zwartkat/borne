package fr.zwartkat.engine;

import MG2D.audio.Bruitage;

public class GameMusic {

    private final String musicPath = "audio/Battle-Sinnoh.mp3";
    private final String legendaryPath = "audio/Legendary.mp3";
    private Bruitage music;
    private Bruitage legendary;

    public GameMusic(){
        this.music = new Bruitage(this.musicPath);
    }

    public void play(){
        if(!music.isAlive()){
            music.arret();
            music = new Bruitage(this.musicPath);
            music.lecture();
        }
    }
}
