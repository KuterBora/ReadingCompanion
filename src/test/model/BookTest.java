package model;

import static org.junit.jupiter.api.Assertions.*;
import model.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Character char1;
    Character char2;
    Location loc1;
    Location loc2;
    PlotPoint pp1;
    PlotPoint pp2;
    Book book;
    ArrayList<Information> characters;
    ArrayList<Information> locations;
    ArrayList<Information> plots;

    @BeforeEach
    void setUp() {
        char1 = new Character("Frodo", 1,"A hobbit from Shire.");
        char2 = new Character("Tom Bombadil", 6, "A merry fellow.");
        loc1 = new Location("Shire", 1,"The home of the hobbits.");
        loc2 = new Location("Old Forest", 6, "The ancient forest where Tom Bombadil dewlls.");
        pp1 = new PlotPoint(1, "Bilbo hosts a birthday party.");
        pp2 = new PlotPoint(6, "Tom Bombadil saves the hobbits from the Willowman.");
        characters = new ArrayList<>();
        characters.add(char1);
        characters.add(char2);
        locations = new ArrayList<>();
        locations.add(loc1);
        locations.add(loc2);
        plots = new ArrayList<>();
        plots.add(pp1);
        plots.add(pp2);
        book = new Book("Lord of the Rings",22, characters, locations, plots);
    }

    @Test
    void testNextChapter() {
        assertEquals(1, book.getCurrentChapter());
        book.nextChapter();
        assertEquals(2,book.getCurrentChapter());
        book.nextChapter();
        book.nextChapter();
        assertEquals(4,book.getCurrentChapter());
    }

    @Test
    void testAddCharacter() {
        book.addCharacter(new Character("a", 1, "b"));
        assertEquals(3, book.getCharacters().size());
    }

    @Test
    void testAddLocation() {
        book.addLocation(new Location("a", 1, "b"));
        assertEquals(3, book.getLocations().size());
    }

    @Test
    void testAddPlotPoint() {
        book.addPlotPoint(new PlotPoint(1,"z"));
        assertEquals(3, book.getPlots().size());
    }

    @Test
    void testGetAvailables() {
        assertEquals(char1, book.getAvailables("characters").get(0));
        assertEquals(1, book.getAvailables("characters").size());

        assertEquals(loc1, book.getAvailables("locations").get(0));
        assertEquals(1, book.getAvailables("locations").size());
        book.setCurrentChapter(6);
        assertEquals(pp1, book.getAvailables("plots").get(0));
        assertEquals(pp2, book.getAvailables("plots").get(1));
        assertEquals(2, book.getAvailables("plots").size());
        assertNull(book.getAvailables(""));
    }

    @Test
    void testCoverMethods() {
        assertEquals(book.getCover(), "./data/tobs.jpg");
        book.setCover("./data/The_Fellowship_of_the_Ring_cover.gif");
        assertEquals("./data/The_Fellowship_of_the_Ring_cover.gif", book.getCover());
    }
    
    @Test
    void testSetName() {
        book.setName("book 2");
        assertEquals("book 2", book.getName());
    }

}