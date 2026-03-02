package fr.zwartkat.views;

import java.awt.Font;

import MG2D.*;
import MG2D.geometrie.*;
import fr.zwartkat.IPlayer;
import fr.zwartkat.Piece;
import fr.zwartkat.events.GameEvent;
import fr.zwartkat.pokemon.Type;
import fr.zwartkat.services.EventBus;

public class Sidebar {

    private final String bitmapPath = "images/bitmap.png";
    private final EventBus eventBus;
    private final int caseSize;
    private final IPlayer player1, player2;
    private final Font stats = new Font("affichage",Font.BOLD,15);
    private Fenetre fenetre;
    private Rectangle round1,round2;
    private Texture affichage,type1,type2;
    private Texte name,hp,atk,def,speed;
    

    public Sidebar(EventBus eventBus, Fenetre fenetre, IPlayer player1, IPlayer player2, int caseSize){

        this.eventBus = eventBus;

        this.eventBus.subscribe(GameEvent.SelectorOn.class, this::onSelectorOn);
        this.eventBus.subscribe(GameEvent.ChangePlayer.class,this::onChangePlayer);

        this.fenetre = fenetre;
        this.caseSize = caseSize;

        this.player1 = player1;
        this.player2 = player2;

        this.round1 = new Rectangle(player1.getColor(),new Point(caseSize*9,0),new Point(caseSize*12,caseSize*2),true);
        this.round2 = new Rectangle(player2.getColor(),new Point(caseSize*9,caseSize*7),new Point(caseSize*12,caseSize*9));

        this.affichage = new Texture(this.bitmapPath,new Point(caseSize*9,caseSize*4),caseSize*3,caseSize*3);

        this.type1 = new Texture("images/types/normal.png", new Point(0,0));
        this.type2 = new Texture("images/types/normal.png", new Point(0,0));

        int x = caseSize*9+10;
        int y = caseSize*3+caseSize/4 - stats.getSize()/2;

        this.name = new Texte(Couleur.NOIR," ",stats,new Point(caseSize*10+caseSize/2,caseSize*4-caseSize/4 + stats.getSize()/2),2);
        this.hp = new Texte(Couleur.NOIR," ",stats, new Point(x,y),1);
        this.atk = new Texte(Couleur.NOIR," ", stats, new Point(x, hp.getA().getY()-hp.getHauteur()),1);
        this.def = new Texte(Couleur.NOIR," ", stats, new Point(x, atk.getA().getY()-atk.getHauteur()),1);
        this.speed = new Texte(Couleur.NOIR," ", stats, new Point(x, def.getA().getY()-def.getHauteur()),1);

        this.fenetre.ajouter(this.name);
        this.fenetre.ajouter(this.hp);
        this.fenetre.ajouter(this.atk);
        this.fenetre.ajouter(this.def);
        this.fenetre.ajouter(this.speed);

        this.fenetre.ajouter(round1);
        this.fenetre.ajouter(round2);
    }

    private void changeRound(IPlayer player){
        if(player1.equals(player)){
            round2.setPlein(false);
            round1.setPlein(true);
        }
        else{
            round1.setPlein(false);
            round2.setPlein(true);
        }
    }

    private void setTypeTexture(int type1,int type2){

        fenetre.supprimer(this.type1);
        fenetre.supprimer(this.type2);
        this.type1.setImg("images/types/" + Type.getNomType(type1).toLowerCase() + ".png");
        if(type2 == Type.SANS){
            this.type1.setA(new Point(caseSize*10, caseSize*4-caseSize/2-this.type1.getHauteur()/2));
        }
        else {
            this.type1.setA(new Point(caseSize*10-caseSize/2, caseSize*4-caseSize/2-this.type1.getHauteur()/2));
            this.type2.setImg("images/types/" + Type.getNomType(type2).toLowerCase() + ".png");
            this.type2.setA(new Point(caseSize*10+caseSize/2+10, caseSize*4-caseSize/2-this.type2.getHauteur()/2));
            fenetre.ajouter(this.type2);
        }
        fenetre.ajouter(this.type1);
    }

    private void showPokemonData(Piece selectPiece){

        fenetre.supprimer(affichage);
        affichage.setImg(selectPiece.getPokemon().getTexture());
        affichage.setHauteur(caseSize*3-20);
        affichage.setLargeur(caseSize*3-20);
        fenetre.ajouter(affichage);

        this.setTypeTexture(selectPiece.getPokemon().getType1(), selectPiece.getPokemon().getType2());
        
        this.name.setTexte(selectPiece.getPokemon().getName());
        this.hp.setTexte("PV: " + selectPiece.getPokemon().getHp());
        this.atk.setTexte("Attaque: " + selectPiece.getPokemon().getAtk());
        this.def.setTexte("Défense: " + selectPiece.getPokemon().getDef());
        this.speed.setTexte("Vitesse: " + selectPiece.getPokemon().getSpeed());

    }

    private void onSelectorOn(GameEvent.SelectorOn event){
        Piece piece = event.piece;
        this.showPokemonData(piece);
    }

    private void onChangePlayer(GameEvent.ChangePlayer event){
        this.changeRound(event.player);
    }

}
