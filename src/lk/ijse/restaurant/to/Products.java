package lk.ijse.restaurant.to;

public class Products {
    private String fid;
    private String itemName;
    private String  category;
    private int itemPrice;
    private int qty;

    public Products(String fid, String itemName, String category, int itemPrice, int qty) {
        this.fid = fid;
        this.itemName = itemName;
        this.category = category;
        this.itemPrice = itemPrice;
        this.qty = qty;
    }

    public Products(String fid) {
        this.fid = fid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
