package ui;

import model.Book;
import model.EventLog;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// handles the visual components of the ReadingCompanion class
public class VisualHandler extends JFrame {
    private ReadingCompanionApp app;
    private Book book;
    private JPanel panel;
    private final int width = 600;
    private final int height = 600;

    private InitialMenuPanel initialMenu;
    private MainMenuPanel mainMenu;
    private DisplayerMenuPanel displayerMenu;
    private EditorMenuPanel editorMenu;
    private SaveMenuPanel saveMenu;
    private LoadMenuPanel loadMenu;

    // EFFECTS: constructs a VisualHandler object
    public VisualHandler(ReadingCompanionApp app, Book book) {
        super("Reading Companion");
        this.app = app;
        this.book = book;
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPanels();
        displayMenu("initial");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                book.printLog();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays menu of given tpye on screen
    public void displayMenu(String type) {
        this.remove(panel);
        setMenuType(type);
        tock();
    }

    // MODIFIES: this
    // EFFECTS: assigns the correct menu to panel
    public void setMenuType(String type) {
        switch (type) {
            case "initial":
                panel = initialMenu;
                break;
            case "main":
                panel = mainMenu;
                mainMenu.update();
                break;
            case "load":
                panel = loadMenu;
                break;
            case "save":
                panel = saveMenu;
                break;
            case "display":
                book.recordDiplay();
                panel = displayerMenu;
                displayerMenu.updateInformation();
                break;
            case "edit":
                panel = editorMenu;
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the panels
    private void setPanels() {
        initialMenu = new InitialMenuPanel(this);
        mainMenu = new MainMenuPanel(this, book);
        displayerMenu = new DisplayerMenuPanel(this, book);
        editorMenu = new EditorMenuPanel(this, book);
        saveMenu = new SaveMenuPanel(this, app);
        loadMenu = new LoadMenuPanel(this, app);
        panel = initialMenu;
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: revalidates and repaints this
    public void tock() {
        this.add(panel);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: sets book to the given book for this anf the panels
    public void setBook(Book book) {
        this.book = book;
        displayerMenu.setBook(book);
        editorMenu.setBook(book);
        mainMenu.setBook(book);
    }
}