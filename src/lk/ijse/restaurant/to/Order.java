package lk.ijse.restaurant.to;


import java.sql.Time;
import java.util.Date;

public class Order {
    private String oid;
    private String date;
    private String time;
    private int noOfItems;
    private int total;
    private String pid;
    private String cid;

    public Order(String oid, String date, String time, int noOfItems, int total, String pid, String cid) {
        this.oid = oid;
        this.date = date;
        this.time = time;
        this.noOfItems = noOfItems;
        this.total = total;
        this.pid = pid;
        this.cid = cid;
    }

    public Order(String oid) {
        this.oid = oid;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
