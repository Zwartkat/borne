package Arcade.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import MG2D.geometrie.Rectangle;

/**
 * Tests unitaires pour la classe Boite
 */
public class TestBoite {
    
    private Boite boite;
    private Rectangle rect;
    
    @BeforeEach
    public void setUp() {
        rect = new Rectangle(0, 0, 100, 100);
        boite = new Boite(rect);
    }
    
    @Test
    public void testBoiteInitialisation() {
        assertNotNull(boite);
        assertNotNull(rect);
    }
    
    @Test
    public void testBoiteAffichage() {
        // Test que l'affichage ne lève pas d'exception
        assertDoesNotThrow(() -> {
            boite.afficher(null);
        });
    }
    
    @Test
    public void testBoiteTransformation() {
        // Test basique de transformation
        assertDoesNotThrow(() -> {
            boite.translater(10, 10);
        });
    }
    
    @Test
    public void testBoiteMultipleTransformations() {
        // Test de transformations multiples
        assertDoesNotThrow(() -> {
            boite.translater(10, 10);
            boite.translater(5, -5);
            boite.translater(-15, -5);
        });
    }
}
