package lk.ijse.restaurant.to;

public class Stock {
    private String sid;
    private String itemName;
    private String category;
    private int qtyOnHand;
    private int unitPrice;
    private String status;
    private String eid;

    public Stock(String sid, String itemName, String category, int qtyOnHand, int unitPrice, String status, String eid) {
        this.sid = sid;
        this.itemName = itemName;
        this.category = category;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
        this.status = status;
        this.eid = eid;
    }

    public Stock(String sid) {
        this.sid = sid;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
