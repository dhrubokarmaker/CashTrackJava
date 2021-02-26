package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Collection of weeks

public class AllWeeks implements Writable {

    private List<Week> weeks;

    // EFFECTS: Constructs AllWeeks with a list of weeks
    public AllWeeks() {
        weeks = new ArrayList<Week>();
    }

    // getter
    public List<Week> getWeeks() {
        return weeks;
    }

    // MODIFIES: this
    // EFFECTS: Adds new week to list of weeks
    public Week addWeek() {
        Week week = new Week();
        weeks.add(week);
        return week;
    }

    // EFFECTS: looks up week with desired weekNum,returns the week if found,null otherwise
    public Week lookupWeek(int weekNum) {
        for (Week week : weeks) {
            if (week.getWeekNum() == weekNum) {
                return week;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weeks",weeksToJson());
        return json;
    }

    // EFFECTS: returns weeks in AllWeeks as a JSON array
    private JSONArray weeksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Week w : weeks) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }

}
