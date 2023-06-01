package persistence;

import model.Book;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of a book to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // Method taken from JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of a book to file
    public void write(Book book) {
        JSONObject json = book.toJson();
        saveToFile(json.toString(TAB));
    }

    // Method taken from JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // Method taken from JSONWriter class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: sets the destination as the given string
    public void setDestination(String destination) {
        this.destination = destination;
    }

    // EFFECTS: returns destination
    public String getDestination() {
        return destination;
    }
}
