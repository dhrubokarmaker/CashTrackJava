package ui;

import model.AllWeeks;
import model.Week;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Represents a Week Menu in the GUI.

public class WeekMenu extends Menu implements ActionListener {
    private JButton addPurchaseButton;
    private JButton setThresholdButton;
    private JButton endWeekButton;
    private JButton submitButton;
    private JLabel weekNumLabel;
    private JLabel thresholdLabel;
    private JLabel totalLabel;
    private JTextField thresholdField;
    private JFrame enterThreshold;
    private AllWeeks weeks;
    private Week currentWeek;

    // EFFECTS: Constructs a WeekMenu frame and initialize weeks,week and initialize labels in frame.
    public WeekMenu(AllWeeks weeks, Week week) {
        super();
        this.weeks = weeks;
        this.currentWeek = week;
        initializeLabels();
    }

    // MODIFIES: this
    // EFFECTS: initialize all the buttons and labels in current frame
    @Override
    public void initializeButtonsAndLabels() {
        addPurchaseButton = new JButton("Add Purchase");
        addPurchaseButton.setBounds(150, 100, 200, 50);
        addPurchaseButton.setFocusable(false);
        addPurchaseButton.addActionListener(this);

        setThresholdButton = new JButton("Set Threshold");
        setThresholdButton.setBounds(150, 200, 200, 50);
        setThresholdButton.setFocusable(false);
        setThresholdButton.addActionListener(this);

        endWeekButton = new JButton("End & Save Week");
        endWeekButton.setBounds(150, 300, 200, 50);
        endWeekButton.setFocusable(false);
        endWeekButton.addActionListener(this);

        this.add(addPurchaseButton);
        this.add(setThresholdButton);
        this.add(endWeekButton);
    }

    // MODIFIES: this
    // EFFECTS: Assigns actions to all the buttons in current frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPurchaseButton) {
            new PurchaseMenu(currentWeek, weeks);
            dispose();
        }
        if (e.getSource() == setThresholdButton) {
            System.out.println("GetWeek");
            thresholdForm();
        }
        if (e.getSource() == endWeekButton) {
            saveWeekData();
            new MainMenuFrame(weeks);
            new SummaryTable(currentWeek);
        }
        if (e.getSource() == submitButton) {
            Integer threshold = 0;
            try {
                threshold = Integer.parseInt(thresholdField.getText());
                setThreshold(threshold);
                thresholdLabel.setText("Threshold: " + thresholdField.getText());

            } catch (NumberFormatException exception) {
                invalidInputError();
            }
        }

    }

    // EFFECTS: Produces an error message and an error clip if input is invalid.
    private void invalidInputError() {
        new ErrorClip();
        JOptionPane.showMessageDialog(this, "Put valid input.");
    }

    // MODIFIES: this
    // EFFECTS: Sets threshold of current week to threshold.
    private void setThreshold(Integer threshold) {
        currentWeek.setThreshold(threshold);
        JOptionPane.showMessageDialog(this, "Threshold was set to :" + threshold.toString());
        enterThreshold.dispose();
    }

    // EFFECTS: Save the weeks' data to file.
    private void saveWeekData() {
        try {
            JsonWriter jsonWriter = DashboardGUI.JSON_WRITER;
            jsonWriter.open();
            jsonWriter.write(weeks);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + DashboardGUI.JSON_STORE);
        }
        dispose();
    }

    // MODIFIES: this
    // EFFECTS: Initialize labels in the frame.
    private void initializeLabels() {
        Integer weekNum = new Integer(currentWeek.getWeekNum());
        weekNumLabel = new JLabel("Week: " + weekNum);
        weekNumLabel.setBounds(210, 20, 100, 25);
        weekNumLabel.setFont(new Font(weekNumLabel.getFont().getName(), Font.BOLD, 20));
        this.add(weekNumLabel);

        Integer threshold = new Integer(currentWeek.getThreshold());
        thresholdLabel = new JLabel("Threshold: " + threshold.toString());
        thresholdLabel.setBounds(10, 10, 100, 25);
        this.add(thresholdLabel);

        Integer total = new Integer(currentWeek.produceWeekTotal());
        totalLabel = new JLabel("Total: " + total.toString());
        totalLabel.setBounds(10, 30, 100, 25);
        this.add(totalLabel);
    }

    // EFFECTS: Creates a new frame for a form that sets threshold.
    private void thresholdForm() {
        enterThreshold = new JFrame();
        enterThreshold.setSize(300, 100);
        enterThreshold.setDefaultCloseOperation(EXIT_ON_CLOSE);
        enterThreshold.setVisible(true);
        enterThreshold.setLayout(null);
        enterThreshold.setResizable(false);
        enterThreshold.setTitle("Enter Threshold");
        enterThreshold.setLocationRelativeTo(null);

        JLabel formLabel = new JLabel("Enter Threshold");
        formLabel.setBounds(10, 10, 100, 25);
        enterThreshold.add(formLabel);

        thresholdField = new JTextField();
        thresholdField.setBounds(110, 10, 100, 25);
        enterThreshold.add(thresholdField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(210, 10, 80, 25);
        submitButton.setFocusable(false);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        enterThreshold.add(submitButton);

    }
}
