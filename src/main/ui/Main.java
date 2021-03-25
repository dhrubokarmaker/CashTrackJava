package ui;
// Starts the program

public class Main {
    //  EFFECTS: Starts the program with the console UI
    public static void main(String[] args) {
        System.out.println("Welcome to Expense Tracker!");
        // Starts the application with a new Dashboard GUI
        DashboardGUI gui = new DashboardGUI();
    }
}
