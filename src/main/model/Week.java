package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a single week.


public class Week implements Writable {
    private int weekNum;
    private List<Purchase> purchases = new ArrayList<Purchase>();
    private boolean active = true;
    private int threshold;
    private int total;

    // EFFECTS: Constructs a week
    public Week() {
    }

    //getters
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getTotal() {
        return total;
    }

    public boolean getActive() {
        return active;
    }

    public int getWeekNum() {
        return weekNum;
    }


    //setters
    public void setWeekNum(int num) {
        this.weekNum = num;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setActive(boolean state) {
        this.active = state;
    }


    // MODIFIES: this
    // EFFECTS Adds purchase to list of purchases
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    // EFFECTS: returns true if total <= threshold; false otherwise
    public boolean thresholdMet() {
        return (this.total <= this.threshold);
    }

    // MODIFIES: this
    // EFFECTS: find the total of all purchases in this week
    public int produceWeekTotal() {
        total = 0;
        for (Purchase p : purchases) {
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("purchases",purchasesToJson());
        json.put("threshold",threshold);
        return json;
    }

    // EFFECTS: returns purchases in Week as a JSON array
    private JSONArray purchasesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Purchase p : purchases) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }


}
