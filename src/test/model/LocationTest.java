package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    Location loc1;
    Location loc2;

    @BeforeEach
    void setUp() {
        loc1 = new Location("Shire", 1,"The home of the hobbits.");
        loc2 = new Location("Old Forest", 6, "The ancient forest where Tom Bombadil dewlls.");
    }

    @Test
    void testDisplay() {
        assertEquals("Shire: The home of the hobbits.", loc1.display());
    }

    @Test
    void testIsDisplayable() {
        assertTrue(loc1.isDisplayable(1));
        assertFalse(loc2.isDisplayable(2));
        assertTrue(loc2.isDisplayable(6));
        assertTrue(loc2.isDisplayable(8));
    }
}
