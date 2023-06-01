package ui.panels;

import ui.VisualHandler;

import javax.swing.*;
import java.awt.event.ActionListener;

// represents a menu screen in the reading companion app
public abstract class MenuPanel extends JPanel {

    protected VisualHandler visualHandler;
    protected JLabel title;
    protected JButton goBack;

    // EFFECTS: constructs a MenuPanel object
    public MenuPanel(String title, VisualHandler visualHandler) {
        super();
        this.visualHandler = visualHandler;
        this.title = new JLabel(title, SwingConstants.CENTER);
        goBack = new JButton("go back");

    }

    protected abstract void setActionListeners();

    protected abstract void handleLayout();

    protected abstract ActionListener goBackListener();
}
