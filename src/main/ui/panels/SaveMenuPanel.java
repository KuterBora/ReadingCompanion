package ui.panels;

import ui.ReadingCompanionApp;
import ui.VisualHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the menu where the user can save to file in reading companion app
public class SaveMenuPanel extends PersistanceMenuPanel {

    // EFFECTS: constructs a SaveMenuPanel object
    public SaveMenuPanel(VisualHandler visualHandler, ReadingCompanionApp app) {
        super("Save to File", visualHandler, app);
        button1 = new JButton("Save to slot 1");
        button2 = new JButton("Save to slot 2");
        button3 = new JButton("Save to slot 3");
        goBack.addActionListener(goBackListener());
        setActionListeners();
        handleLayout();
    }

    // MODIFIES: this, visualHandler
    @Override
    protected void setActionListeners() {
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1) {
                    app.updateSaveOrLoadLocation(1);
                    app.save();
                    slot1.setText("Slot 1: " + app.getSlotAvailability(1));
                } else if (e.getSource() == button2) {
                    app.updateSaveOrLoadLocation(2);
                    app.save();
                    slot2.setText("Slot 2: " + app.getSlotAvailability(2));
                } else if (e.getSource() == button3) {
                    app.updateSaveOrLoadLocation(3);
                    app.save();
                    slot3.setText("Slot 3: " + app.getSlotAvailability(3));
                }
            }
        };
        addListeners();
    }


}
