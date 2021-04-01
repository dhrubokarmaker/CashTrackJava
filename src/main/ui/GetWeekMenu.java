package ui;

import exceptions.NotFoundException;
import model.AllWeeks;
import model.Week;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
// Represents GetWeekMenu in the GUI

public class GetWeekMenu extends Menu implements ActionListener {
    private JButton getWeekButton;
    private JButton backButton;
    private AllWeeks weeks;
    private JTextField weekNumField;
    private JLabel weekNumLabel;

    // EFFECTS: Constructs a GetWeekMenu frame and initialize weeks
    public GetWeekMenu(AllWeeks weeks) {
        super();
        this.weeks = weeks;
    }

    // EFFECTS: Assigns actions to all the buttons in current frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getWeekButton) {
            try {
                Week week = weeks.lookupWeek(Integer.parseInt(weekNumField.getText()));
                new MainMenuFrame(weeks);
                new SummaryTable(week);
                dispose();

            } catch (NumberFormatException exception) {
                new ErrorClip();
                JOptionPane.showMessageDialog(this, "Put valid input.");
            } catch (NotFoundException notFoundException) {
                JOptionPane.showMessageDialog(this, "No data was found");
            }
        }
        if (e.getSource() == backButton) {
            new MainMenuFrame(weeks);
            dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize all the buttons and labels in current frame
    @Override
    protected void initializeButtonsAndLabels() {
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 100, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        this.add(backButton);

        getWeekButton = new JButton("Search");
        getWeekButton.setBounds(200, 130, 100, 30);
        getWeekButton.setFocusable(false);
        getWeekButton.addActionListener(this);
        this.add(getWeekButton);

        weekNumLabel = new JLabel("Week Number");
        weekNumLabel.setBounds(150, 80, 100, 25);
        this.add(weekNumLabel);

        weekNumField = new JTextField(3);
        weekNumField.setBounds(250, 80, 80, 25);
        this.add(weekNumField);
    }

}
