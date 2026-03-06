package Arcade;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Arcade.ClavierBorneArcade;
import MG2D.geometrie.Texture;
import MG2D.geometrie.Point;

/**
 * Classe representant un pointeur utilise dans un jeu arcade.
 * Gere les textures et les actions de lancement de jeu.
 */
public class Pointeur {
    /**
     * Valeur associee au pointeur.
     */
    private int value;
    /**
     * Texture du triangle gauche du pointeur.
     */
    private Texture triangleGauche;
    /**
     * Texture du triangle droit du pointeur.
     */
    private Texture triangleDroite;
    /**
     * Texture du rectangle central du pointeur.
     */
    private Texture rectangleCentre;

    /**
     * Constructeur de la classe Pointeur.
     * Initialise les textures et la valeur par defaut.
     */
    public Pointeur() {
        this.triangleGauche = new Texture("img/star.png", new Point(30, 492), 40, 40);
        // this.triangleDroite = new Triangle(Couleur .ROUGE, new Point(550, 560), new
        // Point(520, 510), new Point(550, 460), true);
        this.triangleDroite = new Texture("img/star.png", new Point(530, 492), 40, 40);
        this.rectangleCentre = new Texture("img/select2.png", new Point(80, 460), 440, 100);
        this.value = 0;
    }

    /**
     * Methode lance le jeu associe au pointeur.
     * @param clavier Objet ClavierBorneArcade pour detecter les evenements clavier.
     */
    public void lancerJeu(ClavierBorneArcade clavier) {
        if (clavier.getBoutonJ1ATape()) {
            try {
                Graphique.stopMusiqueFond();
                Game game = Graphique.getButtons().get(getValue()).getGame(); // chemin du dossier
                String absFolderPath = Paths.get(game.getPath()).toAbsolutePath().toString();

                ProcessBuilder processBuilder = null;
                if (game.getLang().equals("Python")) {
                    processBuilder = new ProcessBuilder("python", "./src/__main__.py");
                    processBuilder.directory(new File(absFolderPath));
                } else if (game.getLang().equals("Java")) {
                    Path jar = Paths.get("./MG2D.jar").toAbsolutePath();
                    Path parent = Paths.get(".").toAbsolutePath();
                    String classpath = "." + ":" + jar + ":" + parent;

                    processBuilder = new ProcessBuilder("java","-Dprism.forceGPU = true", "-Dsun.java2d.opengl=True", "-cp", classpath, game.getName());
                processBuilder.directory(new File(absFolderPath));
                } else if (game.getLang().equals("Jar")) {
                
                    Path jar = Paths.get("./MG2D.jar").toAbsolutePath();
                    Path parent = Paths.get(".").toAbsolutePath();
                    String classpath = "." + ":" + jar + ":" + parent;

                    processBuilder = new ProcessBuilder("java", "-Dprism.forceGPU = true", "-Dsun.java2d.opengl=True","-jar", game.getName()+".jar");
                    processBuilder.directory(new File(absFolderPath));
                } else if (game.getLang().equals("Lua")) {
                    processBuilder = new ProcessBuilder("love", ".");
                    processBuilder.directory(new File(absFolderPath));
                } else {
                    System.out.println("Lang:" + game.getLang());
                    return;
                }
                if (processBuilder == null) {
                    System.out.println("Lang:" + game.getLang());
                    return;
                }
                ;
                processBuilder.inheritIO();
                Process process = processBuilder.start();
                // Process process =
                // Runtime.getRuntime().exec("./"+Graphique.tableau[getValue()].getNom()+".sh");
                int exitCode = process.waitFor(); // ajouté afin d'attendre la fin de l'exécution du jeu pour reprendre
                                                  // le contrôle sur le menu
                Graphique.lectureMusiqueFond();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) { // on catche toutes les exceptions, nécessaire pour le waitFor()
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            // System.out.println("le process sur
            // "+Graphique.tableau[getValue()].getChemin()+" est bien lancé");
        }
    }

    /**
     * Retourne la valeur associee au pointeur.
     * @return Valeur du pointeur.
     */
    public int getValue() {
        return value;
    }

    /**
     * Modifie la valeur associee au pointeur.
     * @param value Nouvelle valeur.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Retourne la texture du triangle gauche.
     * @return Texture du triangle gauche.
     */
    public Texture getTriangleGauche() {
        return triangleGauche;
    }

    /**
     * Modifie la texture du triangle gauche.
     * @param triangleGauche Nouvelle texture.
     */
    public void setTriangleGauche(Texture triangleGauche) {
        this.triangleGauche = triangleGauche;
    }

    /**
     * Retourne la texture du triangle droit.
     * @return Texture du triangle droit.
     */
    public Texture getTriangleDroite() {
        return triangleDroite;
    }

    /**
     * Modifie la texture du triangle droit.
     * @param triangleDroite Nouvelle texture.
     */
    public void setTriangleDroite(Texture triangleDroite) {
        this.triangleDroite = triangleDroite;
    }

    /**
     * Retourne la texture du rectangle central.
     * @return Texture du rectangle central.
     */
    public Texture getRectangleCentre() {
        return rectangleCentre;
    }

    /**
     * Modifie la texture du rectangle central.
     * @param rectangleCentre Nouvelle texture.
     */
    public void setRectangleCentre(Texture rectangleCentre) {
        this.rectangleCentre = rectangleCentre;
    }

}