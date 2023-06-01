package ui.panels;

import ui.VisualHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a JPanel that represents the initial menu of the reading companion app
public class InitialMenuPanel extends MenuPanel {

    private JButton newFile;
    private JButton load;

    ActionListener newFileListener;
    ActionListener loadListener;

    // EFFECTS: constructs an InitialMenuPanel object
    public InitialMenuPanel(VisualHandler visualHandler) {
        super("Reading Companion", visualHandler);
        newFile = new JButton("New File");
        load = new JButton("Load");
        goBack.addActionListener(goBackListener());
        setActionListeners();
        handleLayout();
    }

    // MODIFIES: this
    // EFFECTS: adds labels and buttons to the panel
    @Override
    protected void handleLayout() {
        this.setLayout(new GridLayout(4,5));
        add(new JLabel());
        add(new JLabel());
        add(title);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        this.add(newFile);
        add(new JLabel());
        this.add(load);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }

    // MODIFIES: this, visualHandler
    // EFFECTS: sets up ActionListener objects
    @Override
    protected void setActionListeners() {
        newFileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.displayMenu("main");
            }
        };

        loadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.displayMenu("load");
            }
        };
        newFile.addActionListener(newFileListener);
        load.addActionListener(loadListener);

    }

    // MODIFIES: this, visualHandler
    // EFFECTS: returns an ActionListener to be used for the goBack button
    @Override
    protected ActionListener goBackListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualHandler.dispose();
            }
        };
    }
}
