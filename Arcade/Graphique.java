package Arcade;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import MG2D.geometrie.*;
import MG2D.geometrie.Point;
import MG2D.audio.*;
import MG2D.*;
import MG2D.FenetrePleinEcran;

/**
 * Classe Graphique, permet de gérer l'affichage de la borne d'arcade
 */
public class Graphique {

    //private final Fenetre f;
    private static final FenetrePleinEcran f = new FenetrePleinEcran("_Menu Borne D'arcade_");
    private int TAILLEX;
    private int TAILLEY;
    private ClavierBorneArcade clavier;
    private BoiteSelection bs;
    private BoiteImage bi;
    private BoiteDescription bd;
    public static Bouton[] tableau;
	private static ArrayList<Game> games;
	private static ArrayList<Bouton> buttons;
    private Pointeur pointeur;
    Font font;
    Font fontSelect;
	public static Bruitage musiqueFond;
	private static String[] tableauMusiques;
	private static int cptMus;


    public Graphique(){

	TAILLEX = 1280;
	TAILLEY = 1024;

	font = null;

	try{
	    File in = new File("fonts/PrStart.ttf");
	    font = font.createFont(Font.TRUETYPE_FONT, in);
	    font = font.deriveFont(32.0f);
	}catch (Exception e) {
	    System.err.println(e.getMessage());
	}

	//f = new Fenetre("_Menu Borne D'arcade_",TAILLEX,TAILLEY);
	f.setVisible(true);
	clavier = new ClavierBorneArcade();
	f.addKeyListener(clavier);
	f.getP().addKeyListener(clavier);

	loadGame();
	loadButtons();

	pointeur = new Pointeur();
	bs = new BoiteSelection(new Rectangle(Couleur .GRIS_CLAIR, new Point(0, 0), new Point(640, TAILLEY), true), pointeur);
	//f.ajouter(bs.getRectangle());
	System.out.println(pointeur.getValue());
	bi = new BoiteImage(new Rectangle(Couleur .GRIS_FONCE, new Point(640, 512), new Point(TAILLEX, TAILLEY), true), new String(buttons.get(pointeur.getValue()).getGame().getPath()));
	//f.ajouter(bi.getRectangle());
	bd = new BoiteDescription(new Rectangle(Couleur .GRIS, new Point(640, 0), new Point(TAILLEX, 512), true));

	bd.lireFichier(buttons.get(pointeur.getValue()).getGame().getPath());
	bd.lireHighScore(buttons.get(pointeur.getValue()).getGame().getPath());
	//f.ajouter(bd.getRectangle());

	Texture fond = new Texture("img/fondretro3.png", new Point(0, 0), TAILLEX, TAILLEY);
	f.ajouter(fond);

	//ajout apres fond car bug graphique sinon
	f.ajouter(bi.getImage());
	for(int i = 0 ; i < bd.getMessage().length ; i++){
	    f.ajouter(bd.getMessage()[i]);
	}
	//f.ajouter(bd.getMessage());
	f.ajouter(pointeur.getTriangleGauche());
	f.ajouter(pointeur.getTriangleDroite());
	for(int i = 0 ; i < buttons.size(); i++){
	    f.ajouter(buttons.get(i).getTexture());
	}
	f.ajouter(pointeur.getRectangleCentre());
	for(int i = 0 ; i < buttons.size() ; i++){
		Bouton button = buttons.get(i);
	    f.ajouter(buttons.get(i).getText());
	    button.getText().setPolice(font);
	    button.getText().setCouleur(Couleur.BLANC);
	}
	//add texture
	for(int i = 0 ; i < bd.getBouton().length ; i++){
	    f.ajouter(bd.getBouton()[i]);
	}
	f.ajouter(bd.getJoystick());
	//add texte
	for(int i = 0 ; i < bd.gettBouton().length ; i++){
	    f.ajouter(bd.gettBouton()[i]);
	}
	f.ajouter(bd.gettJoystick());
	f.ajouter(new Ligne(Couleur.NOIR,new Point(670,360), new Point(1250,360)));
	f.ajouter(new Ligne(Couleur.NOIR,new Point(670,190), new Point(1250,190)));
	f.ajouter(new Ligne(Couleur.NOIR,new Point(960,210), new Point(960,310)));
	f.ajouter(bd.getHighscore());
	for(int i = 0 ; i < bd.getListeHighScore().length ; i++){
	    f.ajouter(bd.getListeHighScore()[i]);
	}
	
	/*Musique de fond*/
	//Comptage du nombre de musiques disponibles
	Path cheminMusiques = FileSystems.getDefault().getPath("sound/bg/");
	cptMus=0;
	try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(cheminMusiques)) {
	    for (Path path : directoryStream) {
		cptMus++;
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

		//Creation d'un tableau de musiques
	tableauMusiques = new String[cptMus];
	try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(cheminMusiques)) {
	    int i = cptMus-1;
	    for (Path path : directoryStream) {
		tableauMusiques[i]=path.getFileName().toString();
		i--;
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	//Choix d'une musique aleatoire et lecture de celle-ci
	this.lectureMusiqueFond();
    }

	public static void addGame(Game game){
		games.add(game);
	}

	public static ArrayList<Game> getGames(){
		return games;
	};

	public static ArrayList<Bouton> getButtons(){
		return buttons;
	}

	/**
	 * Gère la sélection du jeu à lancer
	 */
    public void selectionJeu(){	
		Texture fondBlancTransparent = new Texture("./img/blancTransparent.png", new Point(0,0));
		Rectangle boutonNon = new Rectangle(Couleur.ROUGE, new Point(340, 600), 200, 100, true);
		Rectangle boutonOui = new Rectangle(Couleur.VERT, new Point(740, 600), 200, 100, true);
		Texte message = new Texte(Couleur.NOIR, "Voulez vous vraiment quitter ?", font, new Point(640, 800));
		Texte non = new Texte(Couleur.NOIR, "NON", font, new Point(440, 650));
		Texte oui = new Texte(Couleur.NOIR, "OUI", font, new Point(840, 650));
		Rectangle rectSelection = new Rectangle(Couleur.BLEU, new Point(330,590),220,120, true);
		int frame=0;
		boolean fermetureMenu=false;
		int selectionSur = 0;

		while(true){
			Bouton button = buttons.get(pointeur.getValue());

			if(frame == 0 && button.isTextVisible()){
				f.supprimer(button.getText());
				button.setTextVisible(false);
			}
			if(frame == 4 && !button.isTextVisible()){
				f.ajouter(button.getText());
				button.setTextVisible(true);
			}

			for (int i = 0; i < buttons.size(); i++){
				if(i == pointeur.getValue()) continue;
				Bouton b = buttons.get(i);
				// Affiche s'il ne l'est pas déjà
				if(!b.isTextVisible()){
					f.ajouter(b.getText());
					b.setTextVisible(true);
				}
			}

			// incrémentation du frame
			frame++;
			if(frame == 8) frame = 0;

			// pause
			try { Thread.sleep(50); } catch(Exception e){}
			if(!fermetureMenu){
				if(bs.selection(clavier)){
						bi.setImage(button.getGame().getPath());

						fontSelect = null;
						try {
							File in = new File("fonts/PrStart.ttf");
							fontSelect = fontSelect.createFont(Font.TRUETYPE_FONT, in);
							fontSelect = fontSelect.deriveFont(48.0f);
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}

						if (!button.getText().getPolice().equals(fontSelect)) {
							button.getText().setPolice(fontSelect);
						}


						button.getText().setPolice(font);

						bd.lireFichier(button.getGame().getPath());
						bd.lireHighScore(button.getGame().getPath());
						bd.lireBouton(button.getGame().getPath());
						pointeur.lancerJeu(clavier);


				}else{
					f.ajouter(fondBlancTransparent);
					f.ajouter(message);
					f.ajouter(rectSelection);
					f.ajouter(boutonNon);
					f.ajouter(boutonOui);
					f.ajouter(non);
					f.ajouter(oui);
					fermetureMenu=true;
					
				}
			}else{
					if(clavier.getJoyJ1DroiteEnfoncee()){
						selectionSur=1;
					}
						
					if(clavier.getJoyJ1GaucheEnfoncee()){
						selectionSur=0;
					}
					   
					
					if(selectionSur==0){
						rectSelection.setA(new Point(330,590));
						rectSelection.setB(new Point(550,710));
					}
					else{
						rectSelection.setB(new Point(950,710));
						rectSelection.setA(new Point(730,590));
						
					}
					if(clavier.getBoutonJ1ATape()){
						if(selectionSur==0){
							f.supprimer(fondBlancTransparent);
							f.supprimer(message);
							f.supprimer(rectSelection);
							f.supprimer(boutonNon);
							f.supprimer(boutonOui);
							f.supprimer(non);
							f.supprimer(oui);
							fermetureMenu=false;
						}
						else{
							System.exit(0);
						}
					}

			}
			f.rafraichir();
		}//fin while true
    }

	/**
	 * Lancer la musique de fond aléatoirement parmi la liste définie
	 */
	public static void lectureMusiqueFond() {
    	musiqueFond = new Bruitage ("sound/bg/"+tableauMusiques[(int)(Math.random()*cptMus)]);
    	musiqueFond.lecture();
    }

	/**
	 * Arrete la musique
	 */
	public static void stopMusiqueFond(){
		musiqueFond.arret();
	}

	public static void afficherTexte(int valeur){
		f.ajouter(buttons.get(valeur).getText());
	}

	public static ArrayList<Game> loadGame(){

		if(games == null) games = new ArrayList<>();

		Path yourPath = FileSystems.getDefault().getPath("projet/");

		try {
			List<String> lines = Files.readAllLines(Path.of("games.csv"));

			for (int i = 1; i < lines.size(); i++) {
				String[] parts = lines.get(i).split(",");
				System.out.println(parts[0] + " " + parts[1]);
				String name = parts[0];
				String description = "";
				String path = "projet/" + name;
				String imagePath = "";
				String lang = parts[1];
				String input = parts[2];

				Game g = new Game(i,name,description,path,imagePath,lang,input);
				games.add(g);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return games;
	}

	public static ArrayList<Bouton> loadButtons(){

		if(buttons == null) buttons = new ArrayList<>();


		Bouton addButton = new Bouton("Ajouter un jeu","Add");

		//buttons.add(addButton);

		games.forEach(game -> {
			Bouton button = new Bouton(game);
			button.translater(0, (game.getGameId()-1) * -110);
			buttons.add(button);
		});

		return buttons;

	};
}