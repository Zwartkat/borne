package fr.zwartkat.views;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MG2D.*;
import MG2D.geometrie.*;
import fr.zwartkat.engine.GameEngine;

public class Menu {
    
    private final Font mainTitle = new Font("mainTitle",Font.BOLD,72);
    private final Font smallText = new Font("smallText",Font.BOLD,20);
    private final Font middleText = new Font("middleText",Font.BOLD,30);
    private Fenetre window;
    private Clavier clavier;
    private Souris souris;
    private static String choosedMode;
    private String[] modes = {"Gen1", "Gen2", "Gen3", "Gen4", "Gen5", "Gen6", "Gen7", "Gen8", "Gen9", "Choix(Fichiers)", "Random", "Choix(Tray)"};

    private ArrayList<Dessin> button;
    private HashMap<String,ArrayList<Dessin>> modesButton = new HashMap<String,ArrayList<Dessin>>();
    private int caseSize,width,height;

    public Menu(Fenetre window,int caseSize, int width, int height) {

        this.window = window;
        this.caseSize = caseSize;
        this.width = width;
        this.height = height;
        clavier = window.getClavier();
        souris = window.getSouris();
        window.rafraichir();
        startMenu();
        chooseGameMode();
    }

    // Show the start screen
    public void startMenu() {

        Texte title = new Texte(Couleur.NOIR,"Pokéchecs", mainTitle, new Point(width/2, height-caseSize),2);
        Texte startMessage = new Texte(Couleur.NOIR, "Press Enter \nto start",smallText,new Point(width/2,caseSize/4),2);
        Texture logo = new Texture("images/bitmap.png", new Point((width-height)/2+caseSize,caseSize/2),height-caseSize*2,height-caseSize*2);

        window.ajouter(logo);
        window.ajouter(title);
        window.rafraichir();
        Runnable runnable = () -> {
            window.ajouter(startMessage);
            window.rafraichir();
            GameEngine.sleep(2000);
            window.supprimer(startMessage);
            window.rafraichir();
            GameEngine.sleep(1000);
        };
        Thread waiting = new Thread(runnable);
        while(true){
            if(waiting == null || !waiting.isAlive()){
                waiting = new Thread(runnable);
                waiting.start();
            }
            GameEngine.sleep(1);
            if(clavier.getEntreeEnfoncee()){
                break;
            }
            if(clavier.getHautEnfoncee()){
                window.supprimer(logo);
                logo = new Texture("./images/logo/legends.png",new Point(0,104),960,512);
                window.ajouter(logo);
                window.rafraichir();
            }
        }
        window.supprimer(title);
        window.supprimer(startMessage);
    }

    // Allow to player to choose the game mode between values in the list "modes"
    private void chooseGameMode(){

        boolean check = true;
        int buttonHeight = caseSize * 2;
        int buttonWidth = caseSize * 2;

        for(String mode : modes){

                button = new ArrayList<Dessin>();
                button.add(new Rectangle(Couleur.BLEU,new Point(buttonWidth, height - buttonHeight), caseSize*2, caseSize, true));
                
                if(mode.endsWith("s)")){
                    button.add(new Texte(Couleur.BLANC,mode,smallText, new Point(buttonWidth + caseSize, height - buttonHeight + caseSize/2),2));
                } 
                else if(mode.endsWith("u)") || mode == "Random"){
                    button.add(new Texte(Couleur.ROUGE,mode,smallText, new Point(buttonWidth + caseSize, height - buttonHeight + caseSize/2),2));
                }
                else {
                    button.add(new Texte(Couleur.BLANC,mode,middleText, new Point(buttonWidth + caseSize, height - buttonHeight + caseSize/2),2));
                }

                modesButton.put(mode, button);
                window.ajouter(button.get(0));
                window.ajouter(button.get(1));

            if(buttonWidth + caseSize*3 > width-caseSize*2) {
                buttonWidth = caseSize * 2;
                buttonHeight += caseSize*2;
            }
            else {
                buttonWidth += caseSize*3;
            }
        }

        while(check){
            if(souris.getBoutonGaucheEnfonce()){
                for ( Map.Entry<String,ArrayList<Dessin>> buttons : modesButton.entrySet()){
                    if(souris.getPosition().getBoiteEnglobante().intersection(buttons.getValue().get(0).getBoiteEnglobante())){
                        
                        choosedMode = buttons.getKey().toLowerCase();
                        System.out.println(choosedMode);
                        if(!choosedMode.contains("random") && !choosedMode.endsWith("u)")){
                            check = false;
                            break;
                        }
                    }
                }
            }
            GameEngine.sleep(10);
        }
        window.effacer();
    }

    // Get the mode which was been chosen
    public static String getChoosedMode(){
        return choosedMode;
    }
}
