package model;

public class Purchase {
    private float price;
    private String purchaseDay;
    private String itemName;
    private String itemCategory;

    public Purchase(float price, String purchaseDay, String itemName, String itemCategory) {
        this.price = price;
        this.purchaseDay = purchaseDay;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
    }

    //setters
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPurchaseDay(String purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // getters
    public String getItemName() {
        return itemName;
    }


    public String getItemCategory() {
        return itemCategory;
    }


    public float getPrice() {
        return price;
    }

    public String getPurchaseDay() {
        return purchaseDay;
    }

}
