package persistence;

import model.AllWeeks;
import model.Purchase;
import model.Week;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads week data from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads data of week from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AllWeeks read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeeks(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private AllWeeks parseWeeks(JSONObject jsonObject) {
        AllWeeks allWeeks = new AllWeeks();
        JSONArray jsonArray = jsonObject.getJSONArray("weeks");
        for (Object json : jsonArray) {
            JSONObject nextWeek = (JSONObject) json;
            Week week = allWeeks.addWeek();
            getWeekDetails(nextWeek,week);
        }
        return allWeeks;
    }

    private void getWeekDetails(JSONObject nextWeek,Week week) {
        JSONArray jsonArray = nextWeek.getJSONArray("purchases");
        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            addPurchases(week, nextPurchase);
        }
    }

    private void addPurchases(Week week, JSONObject jsonObject) {
        Integer price = jsonObject.getInt("price");
        String name = jsonObject.getString("name");
        String purchaseDay = jsonObject.getString("purchaseDay");
        String category = jsonObject.getString("category");

        Purchase purchase = new Purchase(price,purchaseDay,name,category);

        week.addPurchase(purchase);
    }


}
