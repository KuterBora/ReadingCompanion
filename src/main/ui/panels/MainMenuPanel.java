package ui.panels;

import model.Book;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the main menu screen of the reading companion app
public class MainMenuPanel extends MenuPanel {

    private Book book;
    private JButton display;
    private JButton edit;
    private JButton save;
    private ActionListener listener;
    private JLabel cover;

    // EFFECTS: constructs a MenuPanel object
    public MainMenuPanel(VisualHandler visualhandler, Book book) {
        super(book.getName(), visualhandler);
        this.book = book;
        cover = new JLabel(new ImageIcon(book.getCover()));
        display = new JButton("Display");
        edit = new JButton("Edit");
        save = new JButton("Save to File");
        setActionListeners();
        goBack.addActionListener(goBackListener());
        handleLayout();
    }

    // MODIFIES: this, visualListener
    // EFFECTS: sets up ActionListeners for the buttons and adds them
    @Override
    protected void setActionListeners() {
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == display) {
                    visualHandler.displayMenu("display");
                } else if (e.getSource() == edit) {
                    visualHandler.displayMenu("edit");
                } else if (e.getSource() == save) {
                    visualHandler.displayMenu("save");
                }
            }
        };
        display.addActionListener(listener);
        edit.addActionListener(listener);
        save.addActionListener(listener);
    }

    // MODIFIES: this
    // EFFECTS: sets the layout of the panel, add buttons/labels
    @Override
    protected  void handleLayout() {
        setLayout(new GridLayout(7, 5));
        addDoubleEmptyJLabel();
        add(title);
        addDoubleEmptyJLabel();
        addEmptyRow();
        addDoubleEmptyJLabel();
        add(cover);
        addDoubleEmptyJLabel();
        addEmptyRow();
        add(new JLabel());
        add(display);
        add(new JLabel());
        add(edit);
        addDoubleEmptyJLabel();
        add(save);
        add(new JLabel());
        add(goBack);
        add(new JLabel());
        addEmptyRow();
    }

    // MODIFIES: this
    // EFFECTS: adds two empty JLabels to this
    public void addDoubleEmptyJLabel() {
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this
    // EFFECTS: adds an empty row of labels to this
    public void addEmptyRow() {
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this, visualListener
    // EFFECTS: returns the ActionListener to be used for the goBack button
    @Override
    protected ActionListener goBackListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.displayMenu("initial");
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: sets text of  the title to given String
    public void setTitle(String text) {
        this.title.setText(text);
    }

    // MODIFIES: this
    // EFFECTS: sets this.book to given Book
    public void setBook(Book book) {
        this.book = book;
    }

    // MODIFIES: this
    // EFFECTS: sets the source of the cover's image to given string
    public void setCover(String source) {
        cover.setIcon(new ImageIcon(source));
    }

    // MODIFIES: this
    // EFFECTS: updates the title and cover displayed on screen
    public void update() {
        setTitle(book.getName());
        setCover(book.getCover());
    }

}
