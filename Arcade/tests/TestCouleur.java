package Arcade.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Couleur
 */
public class TestCouleur {
    
    @Test
    public void testCouleurBlanc() {
        Couleur blanc = Couleur.BLANC;
        assertNotNull(blanc);
    }
    
    @Test
    public void testCouleurNoir() {
        Couleur noir = Couleur.NOIR;
        assertNotNull(noir);
    }
    
    @Test
    public void testCouleurRouge() {
        Couleur rouge = Couleur.ROUGE;
        assertNotNull(rouge);
    }
    
    @Test
    public void testCouleurVert() {
        Couleur vert = Couleur.VERT;
        assertNotNull(vert);
    }
    
    @Test
    public void testCouleurBleu() {
        Couleur bleu = Couleur.BLEU;
        assertNotNull(bleu);
    }
}
