package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeekTest {
    private Week week;
    private Purchase purchaseA;
    private Purchase purchaseB;

    @BeforeEach
    public void runBefore() {
        week = new Week();
        purchaseA = new Purchase(10, "Sunday", "Cola", "Food");
        purchaseB = new Purchase(50, "Monday", "Carrot", "Grocery");

    }

    @Test
    public void addPurchaseTest() {
        assertEquals(0, week.getPurchases().size());
        week.addPurchase(purchaseA);
        assertEquals(1, week.getPurchases().size());
        week.addPurchase(purchaseB);
        assertEquals(2, week.getPurchases().size());
    }

    @Test
    public void produceWeekTotalTest() {
        assertEquals(0, week.produceWeekTotal());
        week.addPurchase(purchaseA);
        assertEquals(10, week.produceWeekTotal());
        week.addPurchase(purchaseB);
        assertEquals(60, week.produceWeekTotal());
    }

    @Test
    public void thresholdMetTest() {
        week = new Week();
        week.setThreshold(10);
        assertEquals(10, week.getThreshold());
        week.setTotal(9);
        assertEquals(9, week.getTotal());
        assertTrue(week.thresholdMet());
        week.setTotal(10);
        assertTrue(week.thresholdMet());
        week.setTotal(11);
        assertFalse(week.thresholdMet());
    }

    @Test
    public void setActiveTest() {
        week = new Week();
        assertTrue(week.getActive());
        week.setActive(false);
        assertFalse(week.getActive());
    }

    @Test
    public void setterGetterTest() {
        week = new Week();
        week.setWeekNum(2);
        assertEquals(2, week.getWeekNum());
    }


}
