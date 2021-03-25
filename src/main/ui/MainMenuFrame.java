package ui;

import model.AllWeeks;
import model.Week;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
// Represents MainMenu frame in the GUI

public class MainMenuFrame extends Menu implements ActionListener {
    private JButton addWeekButton;
    private JButton getWeekButton;
    private JButton loadDataButton;
    private AllWeeks weeks;

    // EFFECTS: Constructs a MainMenuFrame and initialize weeks
    public MainMenuFrame(AllWeeks weeks) {
        super();
        this.weeks = weeks;
    }


    // EFFECTS: Assigns actions to all the buttons in current frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addWeekButton) {
            System.out.println("AddWeek");
            Week week = weeks.addWeek();
            week.setWeekNum(this.weeks.getWeeks().indexOf(week) + 1);
            new WeekMenu(weeks,week);
            dispose();
        }
        if (e.getSource() == getWeekButton) {
            System.out.println("GetWeek");
            new GetWeekMenu(weeks);
            dispose();
        }
        if (e.getSource() == loadDataButton) {
            loadData();
            System.out.println("loaded");
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize all the buttons and labels in current frame
    @Override
    public void initializeButtonsAndLabels() {
        addWeekButton = new JButton("Add Week");
        addWeekButton.setBounds(140, 100, 100, 50);
        addWeekButton.setFocusable(false);
        addWeekButton.addActionListener(this);


        getWeekButton = new JButton("Get Week");
        getWeekButton.setBounds(260, 100, 100, 50);
        getWeekButton.setFocusable(false);
        getWeekButton.addActionListener(this);

        loadDataButton = new JButton("Load Data");
        loadDataButton.setBounds(200, 200, 100, 50);
        loadDataButton.setFocusable(false);
        loadDataButton.addActionListener(this);

        this.add(addWeekButton);
        this.add(getWeekButton);
        this.add(loadDataButton);
    }

    // MODIFIES: this
    // EFFECTS: load weeks' data from file in weeks.
    private void loadData() {
        try {
            weeks = DashboardGUI.JSON_READER.read();
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + DashboardGUI.JSON_STORE);
        }
    }
}
