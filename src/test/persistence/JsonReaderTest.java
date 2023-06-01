package persistence;

import model.Book;
import model.Information;
import org.junit.jupiter.api.Test;
import persistence.exceptions.FileEmptyException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Book book = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testChangeSource() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Book book = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
        reader.setSource("./data/testEmptyBook.json");
        try {
            Book book = reader.read();
            assertEquals("", book.getName());
            assertEquals(0, book.getCharacters().size());
            assertEquals(0, book.getLocations().size());
            assertEquals(0, book.getPlots().size());

        } catch (IOException e) {
            fail("IOException thrown while not expected");
        }
    }

    @Test
    void testReaderEmptyBook() {
        JsonReader reader = new JsonReader("./data/testEmptyBook.json");
        try {
            Book book = reader.read();
            assertEquals("", book.getName());
            assertEquals(0, book.getCharacters().size());
            assertEquals(0, book.getLocations().size());
            assertEquals(0, book.getPlots().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBook() {
        JsonReader reader = new JsonReader("./data/testGeneralBook.json");
        try {
            Book book = reader.read();
            assertEquals("The Lord of the Rings", book.getName());
            ArrayList<Information> characters = book.getCharacters();
            assertEquals(2, characters.size());
            assertEquals("Frodo", characters.get(0).getName());
            assertEquals("A hobbit from the Shire.", characters.get(0).getDescription());
            assertEquals(1, characters.get(0).getChapterOfEncounter());
            ArrayList<Information> locations = book.getLocations();
            assertEquals(2, locations.size());
            assertEquals("Shire", locations.get(0).getName());
            assertEquals("The home of the hobbits.", locations.get(0).getDescription());
            assertEquals(1, locations.get(0).getChapterSeen());
            ArrayList<Information> plots = book.getPlots();
            assertEquals(2, plots.size());
            assertEquals("Bilbo hosts a birthday party.", plots.get(0).getDescription());
            assertEquals(1, plots.get(0).getChapterSeen());
            assertEquals("Tom Bombadil saves the hobbits from the Willowman.", plots.get(1).getDescription());
            assertEquals(6, plots.get(1).getChapterSeen());
            assertEquals(22, book.getNumChapters());
            assertEquals(1, book.getCurrentChapter());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGetBookName() {

        JsonReader reader = new JsonReader("./data/testEmptyBook.json");

        try {
            reader.getBookName();
            fail();
        } catch (FileEmptyException e) {
            // expected
        } catch (IOException e) {
            fail("IOException thrown, not expected.");
        }

        reader.setSource("./data/testGeneralBook.json");
        try {
            Book book = reader.read();
            try {
                assertEquals(book.getName(), reader.getBookName());
            } catch (FileEmptyException e) {
                fail("FileEmptyExceptioj thrown, but not expected.");
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
