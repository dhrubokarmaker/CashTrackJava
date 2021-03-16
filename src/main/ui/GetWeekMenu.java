package ui;

import model.AllWeeks;
import model.Week;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GetWeekMenu extends Menu implements ActionListener {
    private JButton getWeekButton;
    private JButton backButton;
    private AllWeeks weeks;
    private JTextField weekNumField;
    private JLabel weekNumLabel;

    public GetWeekMenu(AllWeeks weeks) {
        super();
        this.weeks = weeks;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getWeekButton) {
            try {
                Week week = lookupWeek(Integer.parseInt(weekNumField.getText()));
                if (week != null) {
                    new MainMenuFrame(weeks);
                    new SummaryTable(week);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No data was found");
                }

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Put valid input.");
            }
        }
        if (e.getSource() == backButton) {
            new MainMenuFrame(weeks);
            dispose();
        }
    }

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

    private Week lookupWeek(int weekNum) {
        List<Week> allWeeks = this.weeks.getWeeks();
        for (Week week : allWeeks) {
            if (allWeeks.indexOf(week) == (weekNum - 1)) {
                return week;
            }
        }
        return null;
    }
}
