package ui;


import model.AllWeeks;
import model.Purchase;
import model.Week;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseMenu extends JFrame implements ActionListener {
    private Week week;
    private AllWeeks weeks;
    private JButton addPurchaseButton;
    private JLabel purchaseNameLabel = new JLabel("Item Name");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel dayLabel = new JLabel("Purchase Day");
    private JLabel categoryLabel = new JLabel("Item Category");
    private JTextField categoryName;
    private JTextField purchaseName;
    private JTextField price;
    private JTextField day;


    public PurchaseMenu(Week week, AllWeeks weeks) {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Expense Tracker");
        this.week = week;
        this.weeks = weeks;
        this.setLocationRelativeTo(null);

        addLabelsAndButtons();
        addPurchaseButton = new JButton("Add Purchase");
        addPurchaseButton.setBounds(175, 150, 150, 50);
        addPurchaseButton.setFocusable(false);
        addPurchaseButton.addActionListener(this);
        this.add(addPurchaseButton);

    }

    private void addLabelsAndButtons() {
        purchaseNameLabel.setBounds(10, 20, 80, 25);
        this.add(purchaseNameLabel);

        purchaseName = new JTextField(20);
        purchaseName.setBounds(100, 20, 165, 25);
        this.add(purchaseName);

        priceLabel.setBounds(10, 50, 80, 25);
        this.add(priceLabel);

        price = new JTextField(10);
        price.setBounds(100, 50, 165, 25);
        this.add(price);

        categoryLabel.setBounds(10, 80, 80, 25);
        this.add(categoryLabel);

        categoryName = new JTextField(10);
        categoryName.setBounds(100, 80, 165, 25);
        this.add(categoryName);

        dayLabel.setBounds(10, 110, 80, 25);
        this.add(dayLabel);

        day = new JTextField(10);
        day.setBounds(100, 110, 165, 25);
        this.add(day);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPurchaseButton) {
            String name = purchaseName.getText();
            String dayText = day.getText();
            String category = categoryName.getText();
            Integer itemPrice = 0;
            try {
                itemPrice = Integer.parseInt(price.getText());
                Purchase purchase = new Purchase(itemPrice,dayText,name,category);
                week.addPurchase(purchase);
                JOptionPane.showMessageDialog(this, "Purchase added successfully.");
                dispose();
                new WeekMenu(weeks,week);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Put valid input.");
            }

        }
    }
}
