package model;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private int weekNum;
    private List<Purchase> purchases = new ArrayList<Purchase>();
    private boolean active = true;
    private float threshold;

    //getters
    public float getTotal() {
        return 0;
    }

    //setters
    public void setWeekNum() {

    }

    public void setThreshold() {

    }

    // MODIFIES: this
    // EFFECTS Adds purchase to list of purchases
    public void addPurchase(Purchase purchase) {

    }

    // EFFECTS: Summarizes expense for week
    public void summarizeWeek() {

    }


}
