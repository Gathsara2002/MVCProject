package lk.ijse.restaurant.to;

public class Bill {
    private String billNo;
    private String date;
    private String time;
    private String desc;
    private String pid;

    public Bill(String billNo, String date, String time, String desc, String pid) {
        this.billNo = billNo;
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.pid = pid;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
