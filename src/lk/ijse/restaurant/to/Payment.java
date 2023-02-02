package lk.ijse.restaurant.to;

public class Payment {
    private String pid;
    private String date;
    private String time;

    private String type;
    private int total;

    public Payment(String pid, String date, String time,String type, int total) {
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.type=type;
        this.total = total;
    }

    public Payment(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
