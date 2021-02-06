package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.awt.windows.WEmbeddedFrame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllWeeksTest {
    AllWeeks weeks;

    @BeforeEach
    public void runBefore(){
       weeks  = new AllWeeks();
    }

    @Test
    public void addWeekTest(){
        assertEquals(0,weeks.getWeeks().size());
        Week week = weeks.addWeek();
        assertEquals(1,weeks.getWeeks().size());
        assertTrue(weeks.getWeeks().contains(week));
    }
    @Test
    public void lookupWeekListNotFoundEmptyListTest(){
        assertEquals(0,weeks.getWeeks().size());
        assertEquals(null,weeks.lookupWeek(1));
    }
    @Test
    public void lookupWeekNotFoundNonEmptyListTest(){
        Week weekA = weeks.addWeek();
        Week weekB = weeks.addWeek();
        assertEquals(2,weeks.getWeeks().size());
        weekA.setWeekNum(1);
        weekB.setWeekNum(2);
        assertEquals(null,weeks.lookupWeek(3));
    }
    @Test
    public void lookupWeekTestFound(){
        Week weekA = weeks.addWeek();
        Week weekB = weeks.addWeek();
        assertEquals(2,weeks.getWeeks().size());
        weekA.setWeekNum(1);
        weekB.setWeekNum(2);
        assertEquals(weekB,weeks.lookupWeek(2));
        assertEquals(weekA,weeks.lookupWeek(1));
    }
}
