package ui;

import model.Book;
import model.Information;
import model.Location;
import model.PlotPoint;
import model.Character;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.exceptions.FileEmptyException;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

// Reading Companion Application
public class ReadingCompanionApp {

    private static final String JSON_STORE1 = "./data/book1.json";
    private static final String JSON_STORE2 = "./data/book2.json";
    private static final String JSON_STORE3 = "./data/book3.json";

    private VisualHandler visualHandler;
    // private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Book book;
    // private boolean saved;

    // EFFECTS: runs the reading companion app with an empty book
    public ReadingCompanionApp() {
        // input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE1);
        jsonReader = new JsonReader(JSON_STORE1);
        book = new Book("",1, new ArrayList<Information>(),
                new ArrayList<Information>(), new ArrayList<Information>());
        // saved = true;
        visualHandler = new VisualHandler(this, book);
        // runReadingCompanion();
    }

    // EFFECTS: returns a string representing the availability of the save slot with given number
    public String getSlotAvailability(int destination) {
        String availability;
        try {
            updateSaveOrLoadLocation(destination);
            availability = jsonReader.getBookName();
        } catch (FileEmptyException e) {
            availability = "Empty";
        } catch (IOException e) {
            availability = "Cannot Read File";
        }
        return availability;
    }

    // EFFECTS: saves book to the file with given number
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(book);
            jsonWriter.close();
            // System.out.println("Saved " + book.getName() + " to " + JSON_STORE1);
            // System.out.println();
        } catch (FileNotFoundException e) {
            // System.out.println("Unable to write to file: " + JSON_STORE1);
            // System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads book from file with the given number
    public void load() {
        try {
            book = jsonReader.read();
            // System.out.println("Loaded " + book.getName() + " from " + JSON_STORE1);
            // System.out.println();
        } catch (IOException e) {
            // System.out.println("Unable to read from file: " + JSON_STORE1);
            // System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: changed the destianation of the Json Reader and Writer
    public void updateSaveOrLoadLocation(int i) {
        if (i == 1) {
            jsonWriter.setDestination(JSON_STORE1);
            jsonReader.setSource(JSON_STORE1);
        } else if (i == 2) {
            jsonWriter.setDestination(JSON_STORE2);
            jsonReader.setSource(JSON_STORE2);
        } else if (i == 3) {
            jsonWriter.setDestination(JSON_STORE3);
            jsonReader.setSource(JSON_STORE3);
        }
    }

    // EFFECTS: returns book
    public Book getBook() {
        return book;
    }

    /*
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runReadingCompanion() {
        String keyPressed;
        boolean keepRunning = true;
        while (keepRunning) {
            showInitialMenu();
            keyPressed = input.nextLine();
            if (keyPressed.equals("q")) {
                remindSaving();
                keepRunning = false;
            } else {
                processInitialInput(keyPressed);
            }
        }
    }

    // EFFECTS: processes user input
    public void processInitialInput(String key) {
        if (key.equals("n")) {
            processMainMenu();
        } else if (key.equals("l")) {
            showLoadOptions();
        } else {
            System.out.println("invalid input.");
        }
    }


    // EFFECTS: prints out the main menu, recives and processes user input
    public void processMainMenu() {
        String keyPressed;
        boolean keepDisplaying = true;
        while (keepDisplaying) {
            showMainMenu();
            keyPressed = input.nextLine();
            if (keyPressed.equals("d")) {
                processDisplayMenu();
            } else if (keyPressed.equals("e")) {
                processEditorMenu();
            } else if (keyPressed.equals("s")) {
                showSaveOptions();
            } else if (keyPressed.equals("b")) {
                keepDisplaying = false;
            } else {
                System.out.println("invalid input");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out the display menu, asks for user input
    public void processDisplayMenu() {
        String keyPressed;
        boolean keepDisplaying = true;
        while (keepDisplaying) {
            showDisplayingMenu();
            keyPressed = input.nextLine();
            if (keyPressed.equals("b")) {
                keepDisplaying = false;
            } else if (keyPressed.equals("c")) {
                displayCharacters();
            } else if (keyPressed.equals("l")) {
                displayLocations();
            } else if (keyPressed.equals("p")) {
                displayPlots();
            } else if (keyPressed.equals("a")) {
                displayAll();
            } else if (keyPressed.equals(">") && keyPressed.equals(">")) {
                changeChapter(keyPressed);
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out the editor menu, asks for user input
    public void processEditorMenu() {
        String keyPressed;
        boolean keepEditing = true;
        while (keepEditing) {
            showEditingMenu();
            keyPressed = input.nextLine();
            if (keyPressed.equals("b")) {
                keepEditing = false;
            } else if (keyPressed.equals("c")) {
                createCharacter();
            } else if (keyPressed.equals("l")) {
                createLocation();
            } else if (keyPressed.equals("p")) {
                createPlot();
            } else if (keyPressed.equals("a")) {
                displayAll();
            } else if (keyPressed.equals(">") && keyPressed.equals(">")) {
                changeChapter(keyPressed);
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the current chapter according to user input
    public void changeChapter(String direction) {
        if (direction.equals(">")) {
            saved = false;
            book.nextChapter();
        } else if (direction.equals("<")) {
            saved = false;
            int previous = book.getCurrentChapter() - 1;
            book.setCurrentChapter(previous);
        }
    }


    // EFFECTS: prints out the intial menu screen
    public void showInitialMenu() {
        System.out.println();
        System.out.println("                READING COMPANION                ");
        System.out.println();
        System.out.println("            Press n to open new file             ");
        System.out.println("               Press l to load                   ");
        System.out.println("               Press q to quit                   ");
        System.out.println();
    }

    // EFFECTS: prints out the main menu
    public void showMainMenu() {
        System.out.println();
        System.out.println("                " + book.getName() +  "    CHAPTER: " + book.getCurrentChapter() + "   ");
        System.out.println();
        System.out.println("          Press d to display info, e to edit      ");
        System.out.println("                  Press s to save,                ");
        System.out.println("       Press b to get back to the title screen    ");
        System.out.println();
    }

    // EFFECTS: prints out the menu for displaying choices.
    public void showDisplayingMenu() {
        System.out.println("           DISPLAYING MENU      CHAPTER: " + book.getCurrentChapter() + "  ");
        System.out.println("Press 'a' to display all info.                        ");
        System.out.println("Press 'c' to display the characters.                  ");
        System.out.println("Press 'l' to display the locations.                   ");
        System.out.println("Press 'p' to display a summary of the plot.           ");
        System.out.println("Press < or > to progress by a chapter.                ");
        System.out.println("Press 'b' to go back to the main menu.                ");

    }

    // EFFECTS: prints out the menu for editing
    public void showEditingMenu() {
        System.out.println("              EDITING MENU      CHAPTER: " + book.getCurrentChapter() + "  ");
        System.out.println("Press 'a' to display all info.                        ");
        System.out.println("Press 'c' to add new characters.                      ");
        System.out.println("Press 'l' to add new locations.                       ");
        System.out.println("Press 'p' to add new plot points.                     ");
        System.out.println("Press < or > to progress by a chapter.                ");
        System.out.println("Press 'b' to go back to the main menu.                ");
    }

    // MODIFIES: this
    // EFFECTS: adds a new character to the character list
    public void createCharacter() {
        saved = false;
        String name;
        String description;
        int chapter;
        System.out.println("Enter the new character's name: ");
        name = input.nextLine();
        System.out.println("Enter the new character's description: ");
        description = input.nextLine();
        System.out.println("Enter the first chapter the character appears in:");
        chapter = Integer.valueOf(input.nextLine());
        book.addCharacter(new Character(name, chapter, description));
    }

    // MODIFIES: this, book
    // EFFECTS: adds a new location to the location list
    public void createLocation() {
        saved = false;
        String name;
        String description;
        int chapter;
        System.out.println("Enter the new location's name: ");
        name = input.nextLine();
        System.out.println("Enter the new location's description: ");
        description = input.nextLine();
        System.out.println("Enter the first chapter the location is encountered:");
        chapter = Integer.valueOf(input.nextLine());
        book.addLocation(new Location(name, chapter, description));
    }

    // MODIFIES: this, book
    // EFFECTS: adds a new plot to the plot list
    public void createPlot() {
        saved = false;
        String description;
        int chapter;
        System.out.println("Enter when this plot point occurs: ");
        chapter = Integer.valueOf(input.nextLine());
        System.out.println("Enter the description of the events: ");
        description = input.nextLine();
        book.addPlotPoint(new PlotPoint(chapter, description));
    }

    // EFFECTS: prints out the characters on screen
    public void displayCharacters() {
        System.out.println("Characters:");
        for (Information character : book.getCharacters()) {
            if (character.isDisplayable(book.getCurrentChapter())) {
                System.out.println(character.display());
                System.out.println();
            }
        }
    }

    // EFFECTS: prints out the locations on screen
    public void displayLocations() {
        System.out.println("Locations:");
        for (Information location : book.getLocations()) {
            if (location.isDisplayable(book.getCurrentChapter())) {
                System.out.println(location.display());
                System.out.println();
            }
        }
    }

    // EFFECTS: prints out the plot points on screen
    public void displayPlots() {
        System.out.println("Summary of Plot:");
        for (Information plot : book.getPlots()) {
            if (plot.isDisplayable(book.getCurrentChapter())) {
                System.out.println(plot.display());

            }
        }
    }

    // EFFECTS: prints out all the information in the book
    public void displayAll() {
        displayCharacters();
        displayLocations();
        displayPlots();
    }

    // EFFECTS: reminds the user to save their work if they intend to,
    //          only if it has not already been saved since their last edit
    public void remindSaving() {
        String keyPressed;
        if (!saved) {
            System.out.println();
            System.out.println("Do you want to save your work?  Press y for yes, n for no.");
            keyPressed = input.nextLine();
            if (keyPressed.equals("y")) {
                showSaveOptions();
            }
        }
    }

    // EFFECTS: shows the user the options for loading
    public void showLoadOptions() {
        String keyPressed;
        boolean keepRunning = true;
        String slot1 = getSlotAvailability(1);
        String slot2 = getSlotAvailability(2);
        String slot3 = getSlotAvailability(3);
        while (keepRunning) {
            System.out.println("Save Slot 1: " + slot1);
            System.out.println("Save Slot 2: " + slot2);
            System.out.println("Save Slot 3: " + slot3);
            System.out.println("Tpye the number of slot to load from.");
            keyPressed = input.nextLine();
            if (!keyPressed.equals("1") && !keyPressed.equals("2") && !keyPressed.equals("3")) {
                System.out.println("invalid input");
            } else {
                updateSaveOrLoadLocation(Integer.valueOf(keyPressed));
                load();
                keepRunning = false;
            }
        }
    }

    // EFFECTS: shows the user the options for saving
    public void showSaveOptions() {
        String keyPressed;
        boolean keepRunning = true;
        String slot1 = getSlotAvailability(1);
        String slot2 = getSlotAvailability(2);
        String slot3 = getSlotAvailability(3);
        while (keepRunning) {
            System.out.println("Save Slot 1: " + slot1);
            System.out.println("Save Slot 2: " + slot2);
            System.out.println("Save Slot 3: " + slot3);
            System.out.println("Tpye the number of slot to save.");
            keyPressed = input.nextLine();
            if (!keyPressed.equals("1") && !keyPressed.equals("2") && !keyPressed.equals("3")) {
                System.out.println("invalid input");
            } else {
                updateSaveOrLoadLocation(Integer.valueOf(keyPressed));
                System.out.println("Type name of the save file: ");
                book.setName(input.nextLine());
                save();
                keepRunning = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the value of saved
    public void setSaved(Boolean x) {
        saved = x;
    }

    // EFFECTS: returns saved
    public boolean isSaved() {
        return saved;
    }

 */
}

