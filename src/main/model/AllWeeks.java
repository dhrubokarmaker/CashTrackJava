package model;

import java.util.ArrayList;
import java.util.List;

public class AllWeeks {

    private List<Week> weeks;

    public AllWeeks() {
        weeks = new ArrayList<Week>();
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public Week addWeek() {
        Week week = new Week();
        weeks.add(week);
        return week;
    }

    public Week lookupWeek(int num) {
        for (Week week : weeks) {
            if (week.getWeekNum() == num) {
                return week;
            }
        }
        return null;
    }
}
