package ui.panels;

import ui.ReadingCompanionApp;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the menu where the user can load from file in reading companion app
public class LoadMenuPanel extends PersistanceMenuPanel {

    // EFFECTS: constructs a LoadMenuPanel object
    public LoadMenuPanel(VisualHandler visualHandler, ReadingCompanionApp app) {
        super("Load from File", visualHandler, app);
        button1 = new JButton("Load from slot 1");
        button2 = new JButton("Load from slot 2");
        button3 = new JButton("Load from slot 3");
        goBack.addActionListener(goBackListener());
        setActionListeners();
        handleLayout();
    }

    // MODIFIES: this, visualHandler, app, book
    // EFFECTS: sets up ActionListeners
    @Override
    protected void setActionListeners() {
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1) {
                    app.updateSaveOrLoadLocation(1);
                    app.load();
                    visualHandler.setBook(app.getBook());
                } else if (e.getSource() == button2) {
                    app.updateSaveOrLoadLocation(2);
                    app.load();
                    visualHandler.setBook(app.getBook());
                } else if (e.getSource() == button3) {
                    app.updateSaveOrLoadLocation(3);
                    app.load();
                    visualHandler.setBook(app.getBook());
                }
            }
        };
        addListeners();
    }

}