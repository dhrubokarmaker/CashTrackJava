package ui;

import model.AllWeeks;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
// A Dashboard with a GUI for user to interact and give inputs

public class DashboardGUI {
    public static final String JSON_STORE = "./data/weekdata.json";
    public static final JsonWriter JSON_WRITER = new JsonWriter("./data/weekdata.json");
    public static final JsonReader JSON_READER = new JsonReader("./data/weekdata.json");

    private AllWeeks weeks;

    // EFFECTS: Constructs new Dashboard with a MainMenuFrame and initialize weeks.
    public DashboardGUI() {
        weeks = new AllWeeks();
        new MainMenuFrame(weeks);
    }

}
