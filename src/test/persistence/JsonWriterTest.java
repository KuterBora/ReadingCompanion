package persistence;

import model.*;
import model.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

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
        char1 = new Character("Frodo", 1,"A hobbit from the Shire.");
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
        book = new Book("The Lord of the Rings",22, characters, locations, plots);
        book.setCover("./data/The_Fellowship_of_the_Ring_cover.gif");
    }


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBook() {
        try {
            JsonWriter writer = new JsonWriter("./data/testEmptyBook.json");
            writer.open();
            writer.write(new Book("",1, new ArrayList<Information>(),
                    new ArrayList<Information>(), new ArrayList<Information>()));
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyBook.json");
            Book book1 = reader.read();
            assertEquals("", book1.getName());
            assertEquals(0, book1.getCharacters().size());
            assertEquals(0, book1.getLocations().size());
            assertEquals(0, book1.getPlots().size());
            assertEquals(1, book1.getCurrentChapter());
            assertEquals(1, book1.getNumChapters());
            assertEquals("./data/tobs.jpg", book1.getCover());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBook() {
        try {
            JsonWriter writer = new JsonWriter("./data/testGeneralBook.json");
            writer.open();
            writer.write(book);
            writer.close();
            JsonReader reader = new JsonReader("./data/testGeneralBook.json");
            Book book2 = reader.read();
            assertEquals("The Lord of the Rings", book2.getName());
            ArrayList<Information> characters = book2.getCharacters();
            assertEquals(2, characters.size());
            assertEquals("./data/The_Fellowship_of_the_Ring_cover.gif", book.getCover());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testSetDestination() {
        JsonWriter writer = new JsonWriter("./data/testGeneralBook.json");
        writer.setDestination("./data/testEmptyBook.json");
        assertEquals("./data/testEmptyBook.json", writer.getDestination());
    }
}
