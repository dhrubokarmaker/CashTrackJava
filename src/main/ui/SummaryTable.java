package ui;

import model.Purchase;
import model.Week;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

// Represents a summary table for a given week

public class SummaryTable extends JFrame {
    private Week currentWeek;
    private DefaultTableModel model;
    private JTable table;
    private JPanel panel;

    // EFFECTS: Constructs a frame with the summary table and initialize currentWeek to week.
    public SummaryTable(Week week) {
        table = new JTable();
        this.currentWeek = week;
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Week Summary",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        Object[] columns = {"Item", "Price", "Category", "Day"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        addPurchaseData();
        JScrollPane pane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);
        addLabels();
        this.setSize(350, 400);
        panel.add(pane);
        this.add(panel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS : Adds purchase data to the table
    private void addPurchaseData() {
        for (Purchase p : currentWeek.getPurchases()) {
            Vector<String> result = new Vector<>();
            result.add(p.getItemName());
            Integer price = new Integer(p.getPrice());
            result.add(price.toString());
            result.add(p.getItemCategory());
            result.add(p.getPurchaseDay());
            model.addRow(result);
        }
    }

    // MODIFIES: this
    // EFFECTS : Adds labels to the frame.
    private void addLabels() {
        JLabel thresholdLabel = new JLabel();
        JLabel totalLabel = new JLabel("Total Expenses: " + ((Integer) currentWeek.produceWeekTotal()).toString());
        totalLabel.setBounds(10, 250, 300, 25);
        thresholdLabel.setBounds(10, 280, 300, 25);
        if (currentWeek.thresholdMet()) {
            thresholdLabel.setText("Threshold was met this week.");
        } else {
            thresholdLabel.setText("Threshold was not met this week.");
        }
        this.add(totalLabel);
        this.add(thresholdLabel);
    }
}
