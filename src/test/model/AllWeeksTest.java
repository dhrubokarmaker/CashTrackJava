package model;

import exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllWeeksTest {
    AllWeeks weeks;

    @BeforeEach
    public void runBefore() {
        weeks = new AllWeeks();
    }


    @Test
    public void addWeekTest() {
        assertEquals(0, weeks.getWeeks().size());
        Week week = weeks.addWeek();
        assertEquals(1, weeks.getWeeks().size());
        assertTrue(weeks.getWeeks().contains(week));
    }

    @Test
    public void lookupWeekListNotFoundEmptyListTest() {
        assertEquals(0, weeks.getWeeks().size());
        try{
            weeks.lookupWeek(1);
            fail();
        } catch (NotFoundException e) {
            //expected
        }
    }

    @Test
    public void lookupWeekNotFoundNonEmptyListTest() {
        Week weekA = weeks.addWeek();
        Week weekB = weeks.addWeek();
        assertEquals(2, weeks.getWeeks().size());
        weekA.setWeekNum(1);
        weekB.setWeekNum(2);
        try{
            weeks.lookupWeek(3);
            fail();
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void lookupWeekTestFound() {
        Week weekA = weeks.addWeek();
        Week weekB = weeks.addWeek();
        assertEquals(2, weeks.getWeeks().size());
        weekA.setWeekNum(1);
        weekB.setWeekNum(2);
        try{
            weeks.lookupWeek(2);
        } catch (NotFoundException e) {
            fail();
        }
        try{
            weeks.lookupWeek(1);
        } catch (NotFoundException e) {
            fail();
        }
    }
}
