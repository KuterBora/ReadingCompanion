package model;

import org.json.JSONObject;

// Represents a location in the book with a name, the first chapter it is seen and its description
public class Location extends Information {

    private String name;
    private String description;

    // EFFECTS: constructs a Location object
    public Location(String name, int chapterSeen, String description) {
        super(chapterSeen);
        this.name = name;
        this.description = description;
    }

    // EFFECTS: return a string consisting of the loction's name and description
    @Override
    public String display() {
        return name + ": " + description;
    }

    // EFFECTS: returns this as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("chapterSeen", super.getChapterOfEncounter());
        json.put("description", description);
        return json;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }

}
