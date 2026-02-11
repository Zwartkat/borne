
public class Pong {
    


    public static void main ( String [] args ){
	//à régler en fonction de l'ordinateur utilisé
	int vitesse = 10;
	int status = 0;
	
	
	Game p = new Game();
	
	
	
	while ( true ) { 
	    try {
		Thread.sleep ( vitesse );
	    }		
	    catch ( Exception e ) {		
		System.out.println ( e );
	    }
	    if(Game.nbRebond == 1){
		if(vitesse > 5){
		    vitesse = vitesse - 1;
		}
	    }
	    if(!Game.demarrer){
		vitesse = 10;
	    }
	    
	   if(status==0) {
	   p.majMenu();
		status = p.getStatus();
	   		
	   }else {
		   p.maj();
		   status = p.getStatus();
	   }
	}
}
	
   }//main




//class Columns
