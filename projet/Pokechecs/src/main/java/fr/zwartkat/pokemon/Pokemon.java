package fr.zwartkat.pokemon;

import MG2D.audio.*;

public class Pokemon implements IPokemon{

    protected int pv,att,def,vitesse,type1,type2;
    private int id,damage;
    private Double efficiency;
    private String name,png;
    private Bruitage cry;
    
    public Pokemon(int id,String name, int pv, int att, int def, int vitesse, String type1,String type2){
        this.id = id;
        this.pv = pv;
        this.att = att;
        this.def = def;
        this.vitesse = vitesse;
        this.type1 = Type.getIndiceType(type1.toUpperCase());
        this.type2 = Type.getIndiceType(type2.toUpperCase());
        this.name = name;

        this.cry = new Bruitage("audio/cris/" + this.id + ".mp3");
        this.png = new String("images/" + this.id + ".png");
    }

    // Check if pokemons are the same
    public Boolean equals(Pokemon p1){
        
        if(this.id == p1.id && this.name == p1.name && this.pv == p1.pv && this.def == p1.def && this.vitesse == p1.vitesse && this.type1 == p1.type1 && this.type2 == p1.type2 && this.cry == p1.cry && this.png == p1.png){
            return true;
        }
        return false;
    }
    
    // Get all information about the pokemon
    public String toString(){
        return new String("Id:" + this.id + "\nEspèce:" + this.getName() + "\nNom:" + this.name + "\n PV:" + this.pv + "\nAttaque:" + this.att + "\nDéfense:" + this.def + "\nVitesse:" + this.vitesse + "\nType:" + Type.getNomType(this.type1) + "," + Type.getNomType(this.type2) + "\nCri:" + this.cry.toString() + "\nPNG:" + this.png + "\n");
    }

    // Get the name of the pokemon
    @Override
    public String getName(){
        return this.name;
    }

    // Get the id of the pokemon
    @Override
    public int getId(){
        return this.id;
    }

    // Get the number of health point of the pokemon
    @Override
    public int getHp(){
        return this.pv;
    }

    // Get the attack of the pokemon
    @Override
    public int getAtk(){
        return this.att;
    }

    // Get the defense of the pokemon
    @Override
    public int getDef(){
        return this.def;
    }

    // Get the speed of the pokemon
    @Override
    public int getSpeed(){
        return this.vitesse;
    }

    // Get the first type of the pokemon
    @Override
    public int getType1(){
        return this.type1;
    }

    // Get the second type of the pokemon
    @Override
    public int getType2(){
        return this.type2;
    }

    // Get the cry of the pokemon
    @Override
    public Bruitage getCry(){
        return this.cry;
    }

    // Get the path of the skin of the pokemon
    @Override
    public String getTexture(){
        return this.png;
    }

    // Play the cry of the pokemon
    @Override
    public void playCry(){
        cry = new Bruitage("audio/cris/" + this.id + ".mp3");
        cry.lecture();
    }

    @Override
    public void takeDamage(int damage) {
        this.pv -= damage;
    }

    @Override
    public boolean isKo(){
        return this.pv <= 0;
    }
}
