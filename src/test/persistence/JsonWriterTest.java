package persistence;


import model.AllWeeks;
import model.Purchase;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            AllWeeks allWeeks = new AllWeeks();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWeeks() {
        try {
            AllWeeks weeks = new AllWeeks();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeeks.json");
            writer.open();
            writer.write(weeks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeeks.json");
            weeks = reader.read();
            assertEquals(0, weeks.getWeeks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeeks() {
        try {
            AllWeeks weeks = new AllWeeks();

            Week weekOne = weeks.addWeek();
            Week weekTwo = weeks.addWeek();
            Purchase purchaseOne = new Purchase(240, "Sunday", "Ford", "Car");
            Purchase purchaseTwo = new Purchase(120, "Tuesday", "Pencil", "Utensils");
            Purchase purchaseThree = new Purchase(500, "Monday", "Apples", "Food");

            weekOne.setThreshold(100);
            weekOne.addPurchase(purchaseOne);
            weekOne.addPurchase(purchaseTwo);

            weekTwo.setThreshold(20);
            weekTwo.addPurchase(purchaseThree);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWeeks.json");
            writer.open();
            writer.write(weeks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWeeks.json");
            AllWeeks allWeeks = reader.read();
            List<Week> weeksRead = allWeeks.getWeeks();
            assertEquals(2, weeksRead.size());
            assertEquals(100,weeksRead.get(0).getThreshold());
            assertEquals(20,weeksRead.get(1).getThreshold());

            assertEquals(20,weeksRead.get(1).getThreshold());
            assertEquals(2,weeksRead.get(0).getPurchases().size());
            checkPurchase("Ford",240,"Sunday","Car",weeksRead.get(0).getPurchases().get(0));
            checkPurchase("Pencil",120,"Tuesday","Utensils",weeksRead.get(0).getPurchases().get(1));

            assertEquals(20,weeksRead.get(1).getThreshold());
            assertEquals(1,weeksRead.get(1).getPurchases().size());
            checkPurchase("Apples",500,"Monday","Food",weeksRead.get(1).getPurchases().get(0));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
