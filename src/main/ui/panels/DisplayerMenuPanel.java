package ui.panels;

import com.sun.javafx.binding.StringConstant;
import model.Book;
import model.Information;
import ui.ReadingCompanionApp;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// panel that represents the diplayer menu of the reading companion app
public class DisplayerMenuPanel extends InformationMenuPanel {

    ActionListener listener;
    private JButton nextChapter;
    private JButton displayBookCover;
    private ArrayList<JLabel> characters;
    private ArrayList<JLabel> locations;
    private ArrayList<JLabel> plots;
    private JLabel chapter;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    // EFFECTS: constructs a DisplayerMenuPanel
    public DisplayerMenuPanel(VisualHandler visualHandler, Book book) {
        super("Displayer Menu", visualHandler, book);
        nextChapter = new JButton("Next Chapter");
        characters = new ArrayList<JLabel>();
        locations = new ArrayList<JLabel>();
        plots = new ArrayList<JLabel>();
        chapter = new JLabel("Chapter: " + book.getCurrentChapter(), SwingConstants.CENTER);
        displayBookCover = new JButton("Display the Book's Cover");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        updateInformation();
        setActionListeners();
        goBack.addActionListener(goBackListener());
        handleLayout();
    }


    // MODIFIES: this, book, visualHandler
    // EFFECTS: implements the ActionListeners to be used for the class's buttons
    @Override
    protected void setActionListeners() {
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextChapter) {
                    book.nextChapter();
                    updateInformation();
                } else if (e.getSource() == displayBookCover) {
                    JFrame coverFrame = new JFrame();
                    coverFrame.setSize(1000, 1000);
                    JLabel cover = new JLabel(new ImageIcon(book.getCover()));
                    coverFrame.add(cover);
                    coverFrame.setVisible(true);
                }
            }
        };
        displayBookCover.addActionListener(listener);
        nextChapter.addActionListener(listener);
    }

    // MODIFIES: this, visualHandler
    // EFFECTS: adds any Information that can be viewd to the labels, diplaying them on the screen
    public void updateInformation() {
        characters = charactersToJLabels(book.getAvailables("characters"));
        locations =  locationsToJLabels(book.getAvailables("locations"));
        plots = plotsToJLabels(book.getAvailables("plots"));
        chapter.setText("Chapter: " + book.getCurrentChapter());
        handleLayout();
        visualHandler.repaint();
        visualHandler.revalidate();
    }

    // EFFECTS: converts each element of the given Character list to a JLabel, adds them to a list and return it
    public ArrayList<JLabel> charactersToJLabels(ArrayList<Information> characters) {
        ArrayList<JLabel> turnedToLabel = new ArrayList<JLabel>();
        for (Information character: characters) {
            String text = charactersToString(character);
            turnedToLabel.add(new JLabel(text));
        }
        return turnedToLabel;
    }

    // EFFECTS: converts each element of the given Location list to a JLabel, adds them to a list and return it
    public ArrayList<JLabel> locationsToJLabels(ArrayList<Information> locations) {
        ArrayList<JLabel> turnedToLabel = new ArrayList<JLabel>();
        for (Information location: locations) {
            String text = charactersToString(location);
            turnedToLabel.add(new JLabel(text));
        }
        return turnedToLabel;
    }

    // EFFECTS: converts each element of the given PlotPoint list to a JLabel, adds them to a list and return it
    public ArrayList<JLabel> plotsToJLabels(ArrayList<Information> plots) {
        ArrayList<JLabel> turnedToLabel = new ArrayList<JLabel>();
        for (Information plot: plots) {
            String text = charactersToString(plot);
            turnedToLabel.add(new JLabel(text));
        }
        return turnedToLabel;
    }

    // EFFECTS: returns a string representation of the given character list
    public String charactersToString(Information character) {
        return "Name: " + character.getName() + " Description: " + character.getDescription();
    }

    // EFFECTS: returns a string representation of the given location list
    public String locationsToString(Information location) {
        return "Location: " + location.getName() + " Description: " + location.getDescription();
    }

    // EFFECTS: returns a string representation of the given plot list
    public String plotsToString(Information plot) {
        return "Chapter " + plot.getChapterSeen() + ": " + plot.getDescription();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons/labels to this in specific order
    @Override
    protected void handleLayout() {
        remove();
        setLayout(new GridLayout(4, 3));
        add(new JLabel());
        add(title);
        add(chapter);
        add(new JLabel("Characters", SwingConstants.CENTER));
        add(new JLabel("Locations", SwingConstants.CENTER));
        add(new JLabel("Summary of Plot", SwingConstants.CENTER));
        for (JLabel label: characters) {
            panel1.add(label);
        }
        for (JLabel label: locations) {
            panel2.add(label);
        }
        for (JLabel label: plots) {
            panel3.add(label);
        }
        add(panel1);
        add(panel2);
        add(panel3);
        add(displayBookCover);
        add(nextChapter);
        add(goBack);
    }

    // MODIFIES: this
    // EFFECTS: sets book to given book
    public void setBook(Book book) {
        this.book = book;
    }

    // MODIFIES: this
    // EFFECTS: remove all buttons/panels/labels from this and its sub-panels
    public void remove() {
        removeAll();
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();
    }

}
