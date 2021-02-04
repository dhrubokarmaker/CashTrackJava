package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PurchaseTest {
    private Purchase purchase;

    @BeforeEach
    public void runBefore(){
        purchase = new Purchase(100,"Sunday","Juice","Food");
    }

    @Test
    public void getterTest(){
        assertEquals(100,purchase.getPrice());
        assertEquals("Sunday",purchase.getPurchaseDay());
        assertEquals("Juice",purchase.getItemName());
        assertEquals("Food",purchase.getItemCategory());
    }

    @Test
    public void setterTest(){
        purchase.setPrice(200);
        assertEquals(200,purchase.getPrice());
        purchase.setItemName("Carrot");
        assertEquals("Carrot",purchase.getItemName());
        purchase.setPurchaseDay("Tuesday");
        assertEquals("Tuesday",purchase.getPurchaseDay());
        purchase.setItemCategory("Grocery");
        assertEquals("Grocery",purchase.getItemCategory());
    }

}