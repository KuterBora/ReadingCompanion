package ui.panels;

import ui.ReadingCompanionApp;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// panel that represents the menus for saving and loading
public abstract class PersistanceMenuPanel extends MenuPanel {

    protected JLabel slot1;
    protected JLabel slot2;
    protected JLabel slot3;
    protected JButton button1;
    protected JButton button2;
    protected JButton button3;
    protected ActionListener listener;
    protected ReadingCompanionApp app;

    // EFFECTS: constructs a PersistanceMenuPanel object
    public PersistanceMenuPanel(String title, VisualHandler visualHandler, ReadingCompanionApp app) {
        super(title, visualHandler);
        this.app = app;
        slot1 = new JLabel("Slot 1: " + app.getSlotAvailability(1));
        slot2 = new JLabel("Slot 2: " + app.getSlotAvailability(2));
        slot3 = new JLabel("Slot 3: " + app.getSlotAvailability(3));
    }


    // MODIFIES: this
    // EFFECTS:  returns the ActionListener fot the goBack button
    @Override
    protected ActionListener goBackListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.displayMenu("main");
            }
        };
    }

    // MDIFIES: this
    // EFFECTS: sets the layout and adds buttons/labels to this in specific order
    @Override
    protected void handleLayout() {
        setLayout(new GridLayout(5, 5));
        add(new JLabel());
        add(new JLabel());
        add(title);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(slot1);
        add(new JLabel());
        add(button1);
        add(new JLabel());
        add(new JLabel());
        add(slot2);
        add(new JLabel());
        add(button2);
        add(new JLabel());
        add(new JLabel());
        add(slot3);
        add(new JLabel());
        add(button3);
        add(new JLabel());
        add(new JLabel());
        add(goBack);
    }

    // MODIFIES: this
    // EFFECTS: add listener to all buttons
    public void addListeners() {
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
    }
}
