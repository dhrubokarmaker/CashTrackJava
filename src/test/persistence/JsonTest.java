package persistence;

import model.Purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkPurchase(String name, Integer price, String purchaseDay, String category, Purchase purchase){
        assertEquals(purchase.getItemName(),name);
        assertEquals(purchase.getItemCategory(),category);
        assertEquals(purchase.getPrice(),price);
        assertEquals(purchase.getPurchaseDay(),purchaseDay);
    }
}
