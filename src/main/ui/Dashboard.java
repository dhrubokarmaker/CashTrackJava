package ui;

import model.AllWeeks;
import model.Purchase;
import model.Week;

import java.util.List;
import java.util.Scanner;

public class Dashboard {
    private static final String ADD_COMMAND = "ADD";
    private static final String GET_COMMAND = "GET";
    private static final String QUIT_COMMAND = "QUIT";
    private static final String SET_COMMAND = "SET";
    private static final String END_COMMAND = "END";
    private boolean runProgram = true;
    Scanner input;
    AllWeeks weeks;

    public Dashboard() {
        input = new Scanner(System.in);
        weeks = new AllWeeks();
    }

    public void printInstructions() {
        System.out.println("Enter one of the following");
        System.out.println("Enter " + ADD_COMMAND + " to add new week");
        System.out.println("Enter " + GET_COMMAND + " to retrieve data a previous week");
        System.out.println("Enter " + QUIT_COMMAND + " to quit the program");
    }

    //EFFECTS: parses user input until user quits
    public void handleUserInput() {
        printInstructions();
        String str;

        while (runProgram) {
            str = getUserInputString();
            mainMenuInput(str);
        }
    }

    private String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine();
        }
        return str;
    }

    //EFFECTS: prints menu options and info depending on input str
    private void mainMenuInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ADD_COMMAND:
                    Week week = weeks.addWeek();
                    System.out.println("Successfully added new week.");
                    weekMenu(week);
                    break;
                case GET_COMMAND:
                    System.out.println("retrieving...");
                    break;
                case QUIT_COMMAND:
                    runProgram = false;
                    endProgram();
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command. Please try again.");
                    printInstructions();
                    break;
            }
        }

    }

    //EFFECTS: stops receiving user input
    public void endProgram() {
        System.out.println("Quitting...");
        input.close();
    }

    public void weekMenu(Week week) {
        System.out.println("Threshold for this week: " + "$" + week.getThreshold());
        System.out.println("Total expenses for this week: " + "$" + week.getWeekTotal());
        System.out.println("Enter " + ADD_COMMAND + " to add a purchase");
        System.out.println("Enter " + SET_COMMAND + " to set a threshold");
        System.out.println("Enter " + END_COMMAND + " to end the week");
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_COMMAND:
                    purchaseMenu(week);
                    break;
                case SET_COMMAND:
                    setWeekThreshold(week);
                    break;
                case END_COMMAND:
                    endCurrentWeek(week);
                    break;
                default:
                    mainMenuInput(str);
                    break;
            }
        }
    }

    // EFFECTS: Ends the session for current week
    private void endCurrentWeek(Week week) {
        week.setActive(false);
        summarizeWeek(week);
        System.out.println("----------------------");
        printInstructions();
    }

    // EFFECTS: Takes user input to set current week's threshold
    private void setWeekThreshold(Week week) {
        System.out.println("Enter threshold: ");
        Scanner inputP = new Scanner(System.in);
        week.setThreshold(inputP.nextInt());
        weekMenu(week);
    }

    public void purchaseMenu(Week week) {
        Scanner inputP = new Scanner(System.in);
        System.out.println("Add item name");
        String name = inputP.nextLine();
        System.out.println("Add item category");
        String category = inputP.nextLine();
        System.out.println("Add item price");
        int price = inputP.nextInt();
        System.out.println("Enter purchase day");
        String day = inputP.next();
        Purchase purchase = new Purchase(price, day, name, category);
        week.addPurchase(purchase);
        System.out.println("Successfully added the purchase");
        System.out.println("-------------------------------");
        weekMenu(week);
    }

    public void summarizeWeek(Week week) {
        List<Purchase> purchases = week.getPurchases();
        if (purchases.size() != 0) {
            int total = week.getWeekTotal();
            boolean thresholdMet = week.thresholdMet();
            System.out.println("Items   |Price  |Day Bought");
            for (Purchase p : purchases) {
                int price = p.getPrice();
                String name = p.getItemName();
                String day = p.getPurchaseDay();
                System.out.println(name + "   " + "$" + price + "   " + day);
            }
            System.out.println();
            System.out.println("Total expenditure: " + "$" + total);
            if (thresholdMet) {
                System.out.println("Threshold was met in this week :)");
            } else {
                System.out.println("Threshold was not met in this week :(");
            }
            System.out.println("------------------------------");
            printInstructions();
        } else {
            System.out.println("No data available for this week");
        }

    }
}
