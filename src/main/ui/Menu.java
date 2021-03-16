package ui;

import javax.swing.*;

public abstract class Menu extends JFrame {
    private JButton addWeekButton;
    private JButton getWeekButton;
    private JButton loadDataButton;

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

    protected abstract void initializeButtonsAndLabels();
}
