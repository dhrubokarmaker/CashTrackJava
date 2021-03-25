package ui;

import javax.swing.*;
// Represents a Menu frame in the GUI.

public abstract class Menu extends JFrame {
    private JButton addWeekButton;
    private JButton getWeekButton;
    private JButton loadDataButton;

    // EFFECTS: Constructs a menu  and instantiates a frame
    public Menu() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Expense Tracker");
        this.setLocationRelativeTo(null);
        initializeButtonsAndLabels();

    }

    // EFFECTS: initialize all the buttons and labels in current frame
    protected abstract void initializeButtonsAndLabels();
}
