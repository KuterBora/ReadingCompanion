package persistence;

import model.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import model.Character;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.exceptions.FileEmptyException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents a reader that reads book from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads book from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Book read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBook(jsonObject);
    }

    // EFFECTS: reads book from file and returns it;
    // throws IOException if an error occurs reading data from file
    // throws FileEmptyException if nothing has been saved on this file so far
    public String getBookName() throws IOException, FileEmptyException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        String bookName = jsonObject.getString("name");
        if (bookName.equals("")) {
            throw new FileEmptyException();
        }
        return bookName;
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses book from JSON object and returns it
    private Book parseBook(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String cover = jsonObject.getString("cover");
        int numChapters = jsonObject.getInt("numChapters");
        int currentChapter = jsonObject.getInt("currentChapter");
        ArrayList<Information> characters = getInfo(jsonObject, "characters");
        ArrayList<Information> locations = getInfo(jsonObject, "locations");
        ArrayList<Information> plots = getInfo(jsonObject, "plots");
        Book book = new Book(name, numChapters, characters, locations, plots);
        book.setCurrentChapter(currentChapter);
        book.setCover(cover);
        return book;
    }

    // EFFECTS: parses information belonging to the group from JSON object and returns them as a list
    private ArrayList<Information> getInfo(JSONObject jsonObject, String group) {
        ArrayList<Information> information = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray(group);
        for (Object json : jsonArray) {
            JSONObject nextInfo = (JSONObject) json;
            if (group.equals("characters")) {
                information.add(getCharacter(nextInfo));
            } else if (group.equals("locations")) {
                information.add(getLocation(nextInfo));
            } else {
                information.add(getPlotPoint(nextInfo));
            }
        }
        return information;
    }

    // EFFECTS: parses a Character from JSON object and returns it
    public Character getCharacter(JSONObject jsonObject) {
        String charName = jsonObject.getString("charName");
        int chapterSeen = jsonObject.getInt("chapterSeen");
        String description = jsonObject.getString("description");
        Character character = new Character(charName, chapterSeen, description);
        return character;
    }

    // EFFECTS: parses a Location from JSON object and returns it
    public Location getLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int chapterSeen = jsonObject.getInt("chapterSeen");
        String description = jsonObject.getString("description");
        Location location = new Location(name, chapterSeen, description);
        return location;
    }

    // EFFECTS: parses a PlotPoint from JSON object and returns it
    public PlotPoint getPlotPoint(JSONObject jsonObject) {
        int chapterSeen = jsonObject.getInt("chapterSeen");
        String description = jsonObject.getString("description");
        PlotPoint plotPoint = new PlotPoint(chapterSeen, description);
        return plotPoint;
    }

    // MODIFIES: this
    // EFFECTS: sets the source as the given string
    public void setSource(String source) {
        this.source = source;
    }

}
