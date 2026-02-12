package Arcade;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Arcade.ClavierBorneArcade;
import MG2D.geometrie.Texture;
import MG2D.geometrie.Point;


public class Pointeur {
    private int value;
    private Texture triangleGauche;
    private Texture triangleDroite;
    private Texture rectangleCentre;

    public Pointeur(){
	this.triangleGauche = new Texture("img/star.png", new Point(30, 492), 40,40);
	// this.triangleDroite = new Triangle(Couleur .ROUGE, new Point(550, 560), new Point(520, 510), new Point(550, 460), true);
	this.triangleDroite = new Texture("img/star.png", new Point(530, 492), 40,40);
	this.rectangleCentre = new Texture("img/select2.png", new Point(80, 460), 440, 100);
	this.value = 0;
    }

    public void lancerJeu(ClavierBorneArcade clavier){
	if(clavier.getBoutonJ1ATape()){

	    try {
		Graphique.stopMusiqueFond();
            Game game = Graphique.getButtons().get(getValue()).getGame(); // chemin du dossier
            String absFolderPath = Paths.get(game.getPath()).toAbsolutePath().toString();

            ProcessBuilder processBuilder = null;
            System.out.println(game);
            if(game.getLang().equals("Python")){
                processBuilder = new ProcessBuilder("python","./src/__main__.py");
            }
            else if (game.getLang().equals("Java")){
                Path jar = Paths.get("./MG2D.jar").toAbsolutePath();
                Path parent = Paths.get(".").toAbsolutePath();
                String classpath = "." + ";" + jar + ";" + parent;

                processBuilder = new ProcessBuilder("java","-cp",classpath,game.getName());
            }
            if(processBuilder == null){
                System.out.println("Lang:" + game.getLang());
                return;
            };
            processBuilder.directory(new File(absFolderPath));
            System.out.println(processBuilder.command());
            System.out.println(processBuilder.directory());
            processBuilder.inheritIO();
        Process process = processBuilder.start();
		//Process process = Runtime.getRuntime().exec("./"+Graphique.tableau[getValue()].getNom()+".sh");
		int exitCode = process.waitFor();		//ajouté afin d'attendre la fin de l'exécution du jeu pour reprendre le contrôle sur le menu
		Graphique.lectureMusiqueFond();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch(Exception e){	//on catche toutes les exceptions, nécessaire pour le waitFor()
            System.out.println(e.getMessage());
			e.printStackTrace();
		}

	    //System.out.println("le process sur "+Graphique.tableau[getValue()].getChemin()+" est bien lancé");
	}
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }

    public Texture getTriangleGauche() {
	return triangleGauche;
    }

    public void setTriangleGauche(Texture triangleGauche) {
	this.triangleGauche = triangleGauche;
    }

    public Texture getTriangleDroite() {
	return triangleDroite;
    }

    public void setTriangleDroite(Texture triangleDroite) {
	this.triangleDroite = triangleDroite;
    }

    public Texture getRectangleCentre() {
	return rectangleCentre;
    }

    public void setRectangleCentre(Texture rectangleCentre) {
	this.rectangleCentre = rectangleCentre;
    }

}
