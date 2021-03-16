package ui;

import model.AllWeeks;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

public class DashboardGUI {
    public static final String JSON_STORE = "./data/weekdata.json";
    public static final JsonWriter JSON_WRITER = new JsonWriter("./data/weekdata.json");
    public static final JsonReader JSON_READER = new JsonReader("./data/weekdata.json");

    private AllWeeks weeks;

    public DashboardGUI() {
        weeks = new AllWeeks();
        new MainMenuFrame(weeks);
    }

}
