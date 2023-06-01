package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlotPointTest {

    PlotPoint pp1;
    PlotPoint pp2;

    @BeforeEach
    void setUp() {
        pp1 = new PlotPoint(1, "Bilbo hosts a birthday party.");
        pp2 = new PlotPoint(6, "Tom Bombadil saves the hobbits from the Willowman.");
    }

    @Test
    void testDisplay() {
        assertEquals("Chapter 1: Bilbo hosts a birthday party.", pp1.display());
    }

    @Test
    void testIsDisplayable() {
        assertTrue(pp1.isDisplayable(1));
        assertFalse(pp2.isDisplayable(2));
        assertTrue(pp2.isDisplayable(6));
        assertTrue(pp2.isDisplayable(8));
    }

    @Test
    void testGetName() {
        assertEquals(null, pp1.getName());
    }
}
