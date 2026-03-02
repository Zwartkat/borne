package fr.zwartkat.engine;

import MG2D.Couleur;
import MG2D.Fenetre;
import MG2D.geometrie.*;

import MG2D.geometrie.Point;
import fr.zwartkat.IPlayer;
import fr.zwartkat.Position;
import fr.zwartkat.Piece;
import fr.zwartkat.views.Sidebar;
import fr.zwartkat.events.GameEvent;
import fr.zwartkat.services.EventBus;
import fr.zwartkat.services.PieceViewManager;
import fr.zwartkat.views.PieceDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRenderer {

    private final EventBus eventBus;
    private final Fenetre fenetre;
    private final PieceViewManager pieceViewManager;
    private final int caseSize, traySize;
    private final Sidebar sidebar;
    private final GameState gameState;

    private List<Carre> squares;
    private List<Dessin> selectionSquares;
    private Cercle selector;
    private Map<Position,PieceDraw> pieceDraws;

    public GameRenderer(EventBus eventBus, Fenetre fenetre, int caseSize, int traySize, GameState gameState){
        this.eventBus = eventBus;
        this.pieceViewManager = new PieceViewManager();
        this.fenetre = fenetre;
        this.caseSize = caseSize;
        this.traySize = traySize;
        this.gameState = gameState;

        IPlayer player1 = this.gameState.getPlayers().getFirst();
        IPlayer player2 = this.gameState.getPlayers().get(1);

        this.sidebar = new Sidebar(eventBus,fenetre,player1,player2,caseSize);

        this.squares = new ArrayList<Carre>();
        this.selectionSquares = new ArrayList<Dessin>();

        this.pieceDraws = new HashMap<Position, PieceDraw>();

        this.eventBus.subscribe(GameEvent.Move.class,this::onMove);
        this.eventBus.subscribe(GameEvent.Fight.class,this::onFight);
        this.eventBus.subscribe(GameEvent.Ko.class,this::onKo);
        this.eventBus.subscribe(GameEvent.Select.class,this::onSelect);
        this.eventBus.subscribe(GameEvent.UnSelect.class,this::onUnSelect);
        this.eventBus.subscribe(GameEvent.SelectorMoved.class, this::onSelectorMoved);

        initPlateau();
    }

    private void initPlateau(){

        Font text = new Font("smallText",Font.BOLD,10);
        int offset = caseSize / 8;

        for(int i = 0; i < traySize; i++){
            int x = i * caseSize;
            for(int j = 0; j < traySize; j++){
                int y = j * caseSize;

                Carre square = new Carre(Couleur.NOIR,new Point(x,y),caseSize);
                squares.add(square);
                this.fenetre.ajouter(square);

                Piece piece = this.gameState.getTray().getCase(i, j);

                if(piece == null) continue;

                Position pos = piece.getPosition();

                int circleX = x + caseSize - offset;
                int circleY = y + caseSize - offset;

                Texture texture = new Texture(
                        piece.getPokemon().getTexture(),
                        new Point(x,y),
                        caseSize,
                        caseSize
                );

                Cercle circle = new Cercle(
                        piece.getPlayer().getColor(),
                        new Point(circleX, circleY),
                        caseSize/12,
                        true
                );


                Texte hp = new Texte(
                        Couleur.NOIR,
                        String.valueOf(piece.getPokemon().getHp()),
                        text,
                        new Point(x + offset, y + caseSize - offset),
                        2
                );

                PieceDraw pieceDraw = new PieceDraw(texture,circle,hp);

                this.addAtPosition(pos,pieceDraw);
            }

            this.gameState.getPlayers().forEach(player ->{
                Piece masterPiece = player.getMasterPiece();
                if (masterPiece == null) return;
                PieceDraw pieceDraw = this.pieceDraws.get(masterPiece.getPosition());
                if (pieceDraw == null) return;
                pieceDraw.getHp().setCouleur(Couleur.ORANGE);
                this.pieceViewManager.update(fenetre,pieceDraw);
            });
        }

        Position selectorPos = this.gameState.getSelector().getCurrentPosition();

        this.selector = new Cercle(Couleur.NOIR,new Point(selectorPos.getX() * caseSize + caseSize / 2, selectorPos.getY() * caseSize + caseSize / 2),caseSize/2,false);

        fenetre.ajouter(this.selector);
    }

    private void onSelectorMoved(GameEvent.SelectorMoved event){
        this.selector.setO(new Point(event.position.getX() * caseSize + caseSize / 2, event.position.getY() * caseSize + caseSize / 2));
    }

    private void onSelect(GameEvent.Select event){
        this.removeSelection();

        Piece selectedPiece = event.selected;
        if(selectedPiece == null) return;

        selectedPiece.getPokemon().playCry();

        List<Dessin> selections = new ArrayList<>();

        if(this.gameState.getPendingMoves() != null){
            this.gameState.getPendingMoves().forEach(position ->{
                Carre moveSquare = new Carre(Couleur.BLEU,new Point(position.getX()*caseSize+2,position.getY()*caseSize+2),caseSize-4);
                selections.add(moveSquare);
            });
        }

        if(this.gameState.getPendingAttacks() != null){
            this.gameState.getPendingAttacks().forEach(position -> {
                Carre attackSquare = new Carre(Couleur.ROUGE,new Point(position.getX()*caseSize+2,position.getY()*caseSize+2),caseSize-4);
                selections.add(attackSquare);
            });
        }

        Carre currentSquare = new Carre(Couleur.VERT,new Point(selectedPiece.getPosition().getX()*caseSize+2,selectedPiece.getPosition().getY()*caseSize+2),caseSize-4);
        selections.add(currentSquare);

        this.showSelection(selections);

    }

    private void onUnSelect(GameEvent.UnSelect event){
        this.removeSelection();
    }

    private void onFight(GameEvent.Fight event){
        if(!event.result.isAttackerKo()){
            Position pos = event.attacker.getPosition();
            PieceDraw pieceDraw = this.pieceDraws.get(pos);
            if(pieceDraw == null) return;
            Texte texte = pieceDraw.getHp();
            texte.setTexte(String.valueOf(event.attacker.getPokemon().getHp()));
            this.pieceViewManager.update(fenetre,pieceDraw);
        }

        if(!event.result.isDefenderKo()){
            Position pos = event.defender.getPosition();
            PieceDraw pieceDraw = this.pieceDraws.get(pos);
            if(pieceDraw == null) return;
            Texte texte = pieceDraw.getHp();
            this.fenetre.supprimer(texte);
            texte.setTexte(String.valueOf(event.defender.getPokemon().getHp()));
            this.fenetre.ajouter(texte);
        }
        this.removeSelection();
    }

    private void onMove(GameEvent.Move event){
        Piece piece = event.piece;
        Position from = event.from;
        Position to = event.to;

        int offset = this.caseSize / 8;

        PieceDraw pieceDraw = this.pieceDraws.get(from);

        int newX = to.getX() * caseSize;
        int newY = to.getY() * caseSize;

        pieceDraw.getTexture().setA(new Point(newX,newY));
        pieceDraw.getCircle().setO(new Point(newX + (caseSize - offset),newY + (caseSize - offset)));
        pieceDraw.getHp().setA(new Point(newX + offset,newY + caseSize - offset));

        this.addAtPosition(to,pieceDraw);
        this.removeAtPosition(from);
        this.removeSelection();
    }

    private void onKo(GameEvent.Ko event){
        Piece piece = event.piece;
        Position position = piece.getPosition();

        this.removeAtPosition(position);
    }

    private void addAtPosition(Position position, PieceDraw pieceDraw){
        this.pieceDraws.put(position,pieceDraw);
        this.fenetre.ajouter(pieceDraw.getTexture());
        this.fenetre.ajouter(pieceDraw.getCircle());
        this.fenetre.ajouter(pieceDraw.getHp());
    }

    private void removeAtPosition(Position position){

        PieceDraw pieceDraw = this.pieceDraws.get(position);

        if(pieceDraw == null) return;

        this.pieceViewManager.removePiece(fenetre,pieceDraw);
    }

    private void showSelection(List<Dessin> selections){
        this.selectionSquares.addAll(selections);
        this.selectionSquares.forEach(this.fenetre::ajouter);
    }

    private void removeSelection(){
        this.selectionSquares.forEach(this.fenetre::supprimer);
        this.selectionSquares.clear();
    }

    public void render(){
        this.fenetre.rafraichir();
    }
}
