package model;

public class Purchase {
    private int price;
    private String purchaseDay;
    private String itemName;
    private String itemCategory;

    // EFFECTS: Constructs a purchase with given price,purchaseDay,itemName and itemCategory
    public Purchase(int price, String purchaseDay, String itemName, String itemCategory) {
        this.price = price;
        this.purchaseDay = purchaseDay;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
    }

    //setters
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setPrice(int price) {
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

    public int getPrice() {
        return price;
    }

    public String getPurchaseDay() {
        return purchaseDay;
    }

}
