package ui;

import model.AllWeeks;
import model.Purchase;
import model.Week;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// A Dashboard for user to interact and give inputs
public class Dashboard {
    private static final String JSON_STORE = "./data/weekdata.json";
    private static final String ADD_COMMAND = "ADD";
    private static final String GET_COMMAND = "GET";
    private static final String QUIT_COMMAND = "QUIT";
    private static final String SET_COMMAND = "SET";
    private static final String END_COMMAND = "END";
    private boolean runProgram = true;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner input;
    private AllWeeks weeks;

    //EFFECTS: Constructs new Dashboard with a Scanner and list of weeks
    public Dashboard() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Print command instructions
    public void printInstructions() {
        System.out.println("Enter one of the following");
        System.out.println("Enter " + ADD_COMMAND + " to add new week");
        System.out.println("Enter " + GET_COMMAND + " to retrieve data a previous week");
        System.out.println("Enter " + QUIT_COMMAND + " to quit the program");
    }

    //EFFECTS: parses user input until user quits and load week data
    public void handleUserInputAndLoad() {
        loadWeekData();
        printInstructions();
        String text;

        while (runProgram) {
            text = getUserInputString();
            mainMenuInput(text);
        }
    }

    // EFFECTS: Get inputs from user
    private String getUserInputString() {
        String text = "";
        if (input.hasNext()) {
            text = input.nextLine();
        }
        return text;
    }

    // MODIFIES: this
    // EFFECTS: prints menu options and info depending on input str
    private void mainMenuInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ADD_COMMAND:
                    Week week = weeks.addWeek();
                    week.setWeekNum(weeks.getWeeks().indexOf(week) + 1);
                    System.out.println("Successfully added new week.");
                    weekMenu(week);
                    break;
                case GET_COMMAND:
                    findWeekMenu();
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

    // EFFECTS: Displays week menu
    public void weekMenu(Week week) {
        System.out.println("WEEK " + week.getWeekNum());
        System.out.println("Threshold for this week: " + "$" + week.getThreshold());
        System.out.println("Total expenses for this week: " + "$" + week.produceWeekTotal());
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
                    weekMenu(week);
                    break;
            }
        }
    }

    // MODIFIES: week
    // EFFECTS: Ends the session for current week
    private void endCurrentWeek(Week week) {
        week.setActive(false);
        System.out.println("----EXPENSE SUMMARY----");
        summarizeWeek(week);
        System.out.println("----------------------");
        saveWeekData();
        printInstructions();
    }

    // MODIFIES: week
    // EFFECTS: Takes user input to set current week's threshold
    private void setWeekThreshold(Week week) {
        int threshold = 0;
        System.out.println("Enter threshold: ");
        Scanner inputP = new Scanner(System.in);
        threshold = takeIntegerInput(threshold, inputP, "Set threshold:", "Please enter integer dollars.");
        week.setThreshold(threshold);
        weekMenu(week);
    }

    // MODIFIES: week
    // EFFECTS: Displays purchase menu
    public void purchaseMenu(Week week) {
        int price = 0;
        Scanner inputP = new Scanner(System.in);
        System.out.println("Add item name");
        String name = inputP.nextLine();
        System.out.println("Add item category");
        String category = inputP.nextLine();
        price = takeIntegerInput(price, inputP, "Add item price", "Please enter integer dollars.");
        System.out.println("Enter purchase day");
        String day = inputP.next();
        Purchase purchase = new Purchase(price, day, name, category);
        week.addPurchase(purchase);
        System.out.println("Successfully added the purchase");
        System.out.println("-------------------------------");
        weekMenu(week);
    }

    // MODIFIES: price
    // EFFECTS: Takes integer input from user.
    private int takeIntegerInput(int price, Scanner inputP, String s, String s2) {
        boolean isInt;
        do {
            System.out.println(s);
            if (inputP.hasNextInt()) {
                price = inputP.nextInt();
                isInt = true;
            } else {
                System.out.println(s2);
                isInt = false;
                inputP.next();
            }
        } while (!isInt);
        return price;
    }

    // EFFECTS: Summarizes week with a table and give expense details
    public void summarizeWeek(Week week) {
        List<Purchase> purchases = week.getPurchases();
        if (purchases.size() != 0) {
            int total = week.produceWeekTotal();
            boolean thresholdMet = week.thresholdMet();
            System.out.printf("%-10s%-10s%-10s%10s\n", "Items", "Price", "Category", "Day Bought");
            for (Purchase p : purchases) {
                int price = p.getPrice();
                String name = p.getItemName();
                String day = p.getPurchaseDay();
                String category = p.getItemCategory();
                System.out.printf("%-10s%-10d%-10s%-10s\n", name, price, category, day);
            }
            System.out.println();
            System.out.println("Total expenditure: " + "$" + total);
            if (thresholdMet) {
                System.out.println("Threshold was met in this week :)");
            } else {
                System.out.println("Threshold was not met in this week :(");
            }
            System.out.println("------------------------------");
        } else {
            System.out.println("No data available for this week");
        }

    }

    // EFFECTS: Displays find week menu
    public void findWeekMenu() {
        int weekNum = 0;
        Scanner inputP = new Scanner(System.in);
        weekNum = takeIntegerInput(weekNum, inputP, "Enter week number", "Please enter a valid number.");
        System.out.println("Retrieving...");

        Week week = lookupWeek(weekNum);

        if (week != null) {
            summarizeWeek(week);
        } else {
            System.out.println("Sorry, no data was found.");
        }
        printInstructions();

    }

    // EFFECTS: saves weeks' data to file
    private void saveWeekData() {
        try {
            jsonWriter.open();
            jsonWriter.write(weeks);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: load weeks' data to file
    private void loadWeekData() {
        try {
            weeks = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: looks up week with desired weekNum,returns the week if found,null otherwise
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
