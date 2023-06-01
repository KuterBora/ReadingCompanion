package ui.panels;

import model.Book;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the menu screens where information about the book in the app can be displayed/edited
public abstract class InformationMenuPanel extends MenuPanel {

    protected Book book;

    // EFFECTS: constructs an InitialMenuPanel object
    public InformationMenuPanel(String title, VisualHandler visualHandler, Book book) {
        super(title, visualHandler);
        this.book = book;
    }

    // MODIFIES: this
    // EFFECTS: implements the ActionListener for the goBack button
    @Override
    protected ActionListener goBackListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.displayMenu("main");
            }
        };
    }

}
