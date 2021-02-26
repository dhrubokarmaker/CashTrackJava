package persistence;

import model.AllWeeks;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllWeeks weeks = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWeeks() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWeeks.json");
        try {
            AllWeeks weeks = reader.read();
            assertEquals(0, weeks.getWeeks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeeks() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWeeks.json");
        try {
            AllWeeks allWeeks = reader.read();
            List<Week> weeks = allWeeks.getWeeks();
            assertEquals(2, weeks.size());
            assertEquals(100,weeks.get(0).getThreshold());
            assertEquals(20,weeks.get(1).getThreshold());

            assertEquals(20,weeks.get(1).getThreshold());
            assertEquals(2,weeks.get(0).getPurchases().size());
            checkPurchase("Ford",240,"Sunday","Car",weeks.get(0).getPurchases().get(0));
            checkPurchase("Pencil",120,"Tuesday","Utensils",weeks.get(0).getPurchases().get(1));

            assertEquals(20,weeks.get(1).getThreshold());
            assertEquals(1,weeks.get(1).getPurchases().size());
            checkPurchase("Apples",500,"Monday","Food",weeks.get(1).getPurchases().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
