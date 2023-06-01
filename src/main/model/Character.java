package model;

import org.json.JSONObject;

// Represents a character in the book with a name, the first chapter he is seen and his description.
public class Character extends Information {

    private String charName;
    private String description;

    // EFFECTS: Constructs  a character with a name, the first chapter he is seen, and his description
    public Character(String name, int chapterSeen, String description) {
        super(chapterSeen);
        this.charName = name;
        this.description = description;
    }

    // EFFECTS: return a string consisting of the character's name and description.
    @Override
    public String display() {
        return charName + ": " + description;
    }

    // returns this as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("charName", charName);
        json.put("chapterSeen", super.getChapterOfEncounter());
        json.put("description", description);
        return json;
    }

    // EFFECTS: return charName
    public String getName() {
        return charName;
    }

    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }


}
