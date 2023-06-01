package model;

import org.json.JSONObject;
import persistence.Writable;

// Reprsents any information in the book that can be displayed by the Reading Companion App.
public abstract class Information implements Writable {

    private int chapterOfEncounter;

    // EFFECTS: Constructs an Information object that stores the number of chapter when that information should be
    // revealed.
    public Information(int chapterOfEncounter) {
        this.chapterOfEncounter = chapterOfEncounter;
    }

    public abstract String display();

    // EFFECTS: return true if the information is allowed to be displayed (if the reader is in the given chapter)
    public boolean isDisplayable(int chapter) {
        return chapter >= chapterOfEncounter;
    }

    // EFFECTS: getter method for chapterOfEcounter
    public int getChapterOfEncounter() {
        return chapterOfEncounter;
    }

    public abstract String getDescription();

    public abstract String getName();

    // EFFECTS: returns chapterOfEncounter
    public int getChapterSeen() {
        return chapterOfEncounter;
    }
}
