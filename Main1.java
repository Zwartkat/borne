import Arcade.Graphique;

public class Main1 {
	/**
	 * Méthode principale permettant de lancer la borne d'arcade
	 * @param args
	 */
    public static void main(String[] args){
	Graphique g = new Graphique();
	while(true){
	    try{
		// Thread.sleep(250);		fy
	    }catch(Exception e){};
	    g.selectionJeu();
	}
    }
}
