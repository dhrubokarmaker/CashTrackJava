package model;

import java.util.ArrayList;
import java.util.List;

public class AllWeeks {

    private List<Week> weeks;

    public AllWeeks() {
        weeks = new ArrayList<Week>();
    }

    public Week addWeek() {
        Week week = new Week();
        weeks.add(week);
        return week;
    }

    public boolean lookupWeek(int num) {
        return false;
    }
}
