package ui.panels;

import model.*;
import model.Character;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// panel that represents the editor menu of the reading companion app
public class EditorMenuPanel extends InformationMenuPanel {

    private JButton changeName;
    private JTextField field;
    private JLabel character;
    private JButton addCharName;
    private JButton addCharDescription;
    private JButton addCharChapter;
    private JButton addChar;
    private ActionListener charListener;
    private String charName;
    private String charDescription;
    private int charChapter;
    private JLabel location;
    private JButton addLocName;
    private JButton addLocDescription;
    private JButton addLocChapter;
    private JButton addLoc;
    private ActionListener locListener;
    private String locName;
    private String locDescription;
    private int locChapter;
    private JLabel plot;
    private JButton addPlotDescription;
    private JButton addPlotChapter;
    private JButton addPlot;
    private ActionListener plotListener;
    private String plotDescription;
    private int plotChapter;
    private JButton reset;
    private ActionListener generalListener;
    private JButton setCover;

    // EFFECTS: constructs an EditorMenuPanel object
    public EditorMenuPanel(VisualHandler visualHandler, Book book) {
        super("Editor Menu", visualHandler, book);
        initializeFields();
        character = new JLabel("Character: " + charName + " " + charDescription + " " + charChapter,
                SwingConstants.CENTER);
        location = new JLabel("Location: " + locName + " " + locDescription + " Chapter: " + locChapter,
                SwingConstants.CENTER);
        plot = new JLabel("Chapter: " + plotChapter + " " + plotDescription, SwingConstants.CENTER);
        field = new JTextField(50);
        setActionListeners();
        goBack.addActionListener(goBackListener());
        handleLayout();
    }

    // MODIFIES: this
    // EFFECTS: initializes the fields
    public void initializeFields() {
        addCharName = new JButton("Add the Name of This Character");
        addCharDescription = new JButton("Add the Description of This Character");
        addCharChapter = new JButton("Add the Chapter This Character Was First Seen");
        addChar = new JButton("Add the Character");
        charName = "No Name";
        charDescription = "No Description";
        charChapter = 1;
        addLocName = new JButton("Add the Name of This Location");
        addLocDescription = new JButton("Add the Description of this Location");
        addLocChapter = new JButton("Add the Chapter This Place Was First Seen");
        addLoc = new JButton("Add the Location");
        locName = "Untitled";
        locDescription = "No Description";
        locChapter = 1;
        addPlotDescription = new JButton("Add the Description of this Event.");
        addPlotChapter = new JButton("Add the Chapter in Which This Occured.");
        addPlot = new JButton("Add the Plot Point");
        plotDescription = "No Description";
        plotChapter = 1;
        reset = new JButton("Reset");
        changeName = new JButton("Set Book's Name");
        setCover = new JButton("Set Book Cover");
    }

    // EFFECTS: calls methods to implement listeners and adds them to their buttons
    @Override
    protected void setActionListeners() {
        setCharListener();
        setLocListener();
        setPlotListener();
        setGeneralListener();
    }

    // MODIFIES: this
    // EFFECTS: implements generalListener and adds it to buttons
    public void setGeneralListener() {
        generalListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == reset) {
                    reset();
                } else if (e.getSource() == changeName) {
                    book.setName(field.getText());
                } else if (e.getSource() == setCover) {
                    book.setCover(field.getText());
                }
            }
        };
        setCover.addActionListener(generalListener);
        changeName.addActionListener(generalListener);
        reset.addActionListener(generalListener);
    }

    // MODIFIES: this
    // EFFECTS: sets the variables to their initial values
    private void reset() {
        charName = "No Name";
        locName = "Untitled";
        charDescription = "No Description";
        locDescription = "No Description";
        plotDescription = "No Description";
        charChapter = 1;
        locChapter = 1;
        plotChapter = 1;
        character.setText("Character:" + charName + " " + charDescription + " " + "Chapter:" + charChapter);
        location.setText(locName +  " " + locDescription + " "  + "Chapter: " + locChapter);
        plot.setText("Chapter: " + plotChapter + " " + plotDescription);
    }

    // MODIFIES: this, book
    // EFFECTS: implements the character related listener and adds it to buttons
    private void setCharListener() {
        charListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addCharName) {
                    charName = field.getText();
                } else if (e.getSource() == addCharDescription) {
                    charDescription = field.getText();
                } else if (e.getSource() == addCharChapter) {
                    charChapter = Integer.valueOf(field.getText());
                } else if (e.getSource() == addChar) {
                    Information info = new Character(charName, charChapter, charDescription);
                    book.addCharacter(info);
                }
                character.setText("Character: " + charName + " " + charDescription + " " + "Chapter: " + charChapter);
            }
        };
        addCharName.addActionListener(charListener);
        addCharDescription.addActionListener(charListener);
        addCharChapter.addActionListener(charListener);
        addChar.addActionListener(charListener);
    }

    // MODIFIES: this, book
    // EFFECTS: implements location related listener and adds the to buttons
    private void setLocListener() {
        locListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addLocName) {
                    locName = field.getText();
                } else if (e.getSource() == addLocDescription) {
                    locDescription = field.getText();
                } else if (e.getSource() == addLocChapter) {
                    locChapter = Integer.valueOf(field.getText());
                } else if (e.getSource() == addLoc) {
                    Information info = new Location(locName, locChapter, locDescription);
                    book.addLocation(info);
                }
                location.setText("Location: " + locName + " " + locDescription + " " + "Chapter: " + locChapter);
            }
        };
        addLocName.addActionListener(locListener);
        addLocDescription.addActionListener(locListener);
        addLocChapter.addActionListener(locListener);
        addLoc.addActionListener(locListener);
    }

    // MODIFIES: this, book
    // EFFECTS: implements plot related listener and adds the to buttons
    private void setPlotListener() {
        plotListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addPlotDescription) {
                    plotDescription = field.getText();
                } else if (e.getSource() == addPlotChapter) {
                    plotChapter = Integer.valueOf(field.getText());
                } else if (e.getSource() == addPlot) {
                    Information info = new PlotPoint(plotChapter, plotDescription);
                    book.addPlotPoint(info);
                }
                plot.setText("Chapter: " + plotChapter + " " + plotDescription);
            }
        };
        addPlotDescription.addActionListener(plotListener);
        addPlotChapter.addActionListener(plotListener);
        addPlot.addActionListener(plotListener);
    }


    // MODIFIES: this
    // EFFECTS: sets the layOut of this and adds buttons/labels in specific order
    @Override
    protected void handleLayout() {
        this.setLayout(new GridLayout(14, 3));
        add(new JLabel());
        add(title);
        add(new JLabel());
        add(new JLabel());
        add(field);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        handleCharacterLayout();
        handleLocationLayout();
        handlePlotLayout();
        add(changeName);
        add(setCover);
        add(reset);
        add(new JLabel());
        add(goBack);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons/labels related to characters in specific order
    public void handleCharacterLayout() {
        add(character);
        add(addCharName);
        add(addCharDescription);
        add(addCharChapter);
        add(addChar);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this
    // EFFECTS: adds buttons/labels related to locations in specific order
    public void handleLocationLayout() {
        add(location);
        add(addLocName);
        add(addLocDescription);
        add(addLocChapter);
        add(addLoc);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this
    // EFFECTS: adds buttons/labels related to plots in specific order
    public void handlePlotLayout() {
        add(plot);
        add(addPlotDescription);
        add(addPlotChapter);
        add(addPlot);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this
    // EFFECTS: sets book to given Book
    public void setBook(Book book) {
        this.book = book;
    }

}
