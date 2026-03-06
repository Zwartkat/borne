package Arcade.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Pointeur
 */
public class TestPointeur {
    
    private Pointeur pointeur;
    
    @BeforeEach
    public void setUp() {
        pointeur = new Pointeur(0, 0, 10, 10);
    }
    
    @Test
    public void testPointeurInitialisation() {
        assertNotNull(pointeur);
        assertEquals(0, pointeur.getValue());
    }
    
    @Test
    public void testPointeurSetValue() {
        pointeur.setValue(5);
        assertEquals(5, pointeur.getValue());
    }
    
    @Test
    public void testPointeurSetValueNegative() {
        pointeur.setValue(-1);
        assertEquals(-1, pointeur.getValue());
    }
    
    @Test
    public void testPointeurSetValueZero() {
        pointeur.setValue(0);
        assertEquals(0, pointeur.getValue());
    }
    
    @Test
    public void testPointeurAffichage() {
        // Test que l'affichage ne lève pas d'exception
        assertDoesNotThrow(() -> {
            pointeur.afficher(null); // Null car on teste juste que ça ne crash pas
        });
    }
}
