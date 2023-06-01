package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Represents a book having number of chapters, chapters that have been read, a cover, plot points, characters
// and locations
public class Book implements Writable {

    private String name;
    private int numChapters;
    private int currentChapter;
    private ArrayList<Information> characters;
    private ArrayList<Information> locations;
    private ArrayList<Information> plots;
    private String cover;

    // EFFECTS: constructs a book with given characters, locations, plot points, and number of chapters, sets the
    // amount of chapters read so far to 0, intializes the cover to ./data/tobs.jpg.
    public Book(String name, int numChapters, ArrayList<Information> characters, ArrayList<Information> locations,
                ArrayList<Information> plots) {
        this.numChapters = numChapters;
        this.characters = characters;
        this.locations = locations;
        this.plots = plots;
        currentChapter = 1;
        this.name = name;
        cover = "./data/tobs.jpg";
    }

    // MODIFIES: this
    // EFFECTS: increases the current chapter by 1,
    public void nextChapter() {
        currentChapter += 1;
    }

    // MODIFIES: this, theLog
    // EFFECTS: adds a new character to the list, records this to the log
    public void addCharacter(Information character) {
        characters.add(character);
        Event event = new Event("Character: " + character.getName() + ", was added to the book");
        EventLog.getInstance().logEvent(event);
    }

    // MODIFIES: this, logEvent
    // EFFECTS: adds a new location to the list, records this to the log
    public void addLocation(Information location) {
        locations.add(location);
        Event event = new Event("Location: " + location.getName() + ", was added to the book");
        EventLog.getInstance().logEvent(event);
    }

    // MODIFIES: this, theLog
    // EFFECTS: adds a new location to the list, records this to the log
    public void addPlotPoint(Information plot) {
        plots.add(plot);
        Event event = new Event("New plot point was added to chapter " + plot.getChapterSeen() + ".");
        EventLog.getInstance().logEvent(event);
    }

    // EFFECTS: returns curretChapter
    public int getCurrentChapter() {
        return currentChapter;
    }

    // EFFECTS: returns the max number of chapters
    public int getNumChapters() {
        return numChapters;
    }

    // EFFECTS: returns characters.
    public ArrayList<Information> getCharacters() {
        return characters;
    }

    // EFFECTS: returns locations.
    public ArrayList<Information> getLocations() {
        return locations;
    }

    // EFFECTS: return plots.
    public ArrayList<Information> getPlots() {
        return plots;
    }

    // EFFECTS: retruns name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: setter method for currentChapter
    public void setCurrentChapter(int chapter) {
        currentChapter = chapter;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cover", cover);
        json.put("numChapters", numChapters);
        json.put("currentChapter", currentChapter);
        json.put("characters", infoToJson(characters));
        json.put("locations", infoToJson(locations));
        json.put("plots", infoToJson(plots));
        return json;
    }

    // EFFECTS: returns any list of information in this book as a JSON array
    public JSONArray infoToJson(ArrayList<Information> information) {
        JSONArray jsonArray = new JSONArray();
        for (Information i : information) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the book to the given string
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns members of the given group that can be displayed
    public ArrayList<Information> getAvailables(String type) {
        ArrayList<Information> information;
        ArrayList<Information> availables = new ArrayList<>();
        switch (type) {
            case "characters":
                information = characters;
                break;
            case "locations":
                information = locations;
                break;
            case "plots":
                information = plots;
                break;
            default:
                return null;
        }
        for (Information info : information) {
            if (info.isDisplayable(currentChapter)) {
                availables.add(info);
            }
        }
        return availables;
    }

    // MODIFIES: theLog
    // EFFECTS: records to theLog whenever characters/locations/plots have been displayed
    public void recordDiplay() {
        Event event = new Event("The characters, locations and the summary of plot were displayed.");
        EventLog.getInstance().logEvent(event);
    }

    // MODIFIES: this
    // EFFECTS: sets the value of cover to given string
    public void setCover(String cover) {
        this.cover = cover;
    }

    // EFFECTS: returns cover
    public String getCover() {
        return cover;
    }

    // EFFECTS: prints out the log of events recorded
    public void printLog() {
        System.out.println("The Log:");
        Iterator<Event> events = EventLog.getInstance().iterator();
        while (events.hasNext()) {
            System.out.println(events.next().getDescription());
        }
    }

}
