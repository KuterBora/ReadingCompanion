package model;

import model.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharcacterTest {

    Character char1;
    Character char2;

    @BeforeEach
    void setUp() {
        char1 = new Character("Frodo", 1,"A hobbit from Shire.");
        char2 = new Character("Tom Bombadil", 6, "A merry fellow.");
    }

    @Test
    void testDisplay() {
        assertEquals("Frodo: A hobbit from Shire.", char1.display());
    }

    @Test
    void testIsDisplayable() {
        assertTrue(char1.isDisplayable(1));
        assertFalse(char2.isDisplayable(2));
        assertTrue(char2.isDisplayable(6));
        assertTrue(char2.isDisplayable(8));
    }

}
