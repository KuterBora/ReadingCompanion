package model;

import org.json.JSONObject;

// Represnts an important turning point in the book/story, has the number of the chapter it happens at, and a
// description of the event.
public class PlotPoint extends Information {

    private String description;

    //EFFECTS: Constructs a PlotPoint object with its chapter number, and description
    public PlotPoint(int chapterSeen, String description) {
        super(chapterSeen);
        this.description = description;
    }

    // EFFECTS: returns the plot point's description and the chapter when it happened
    @Override
    public String display() {
        return "Chapter " + getChapterOfEncounter() + ": " + description;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("chapterSeen", super.getChapterOfEncounter());
        json.put("description", description);
        return json;
    }

    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns null, since PlotPoints do not have names
    public String getName() {
        return null;
    }
}
