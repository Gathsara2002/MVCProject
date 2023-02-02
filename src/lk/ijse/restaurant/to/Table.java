package lk.ijse.restaurant.to;

import java.sql.Time;
import java.util.Date;

public class Table {

    private String tid;
    private int capacity;
    private String description;
    private String booking_date;
    private String booking_time;
    private String availability;
    private String cusId;

    public Table(String tid, int capacity, String description, String  booking_date, String  booking_time, String availability, String cusId) {
        this.tid = tid;
        this.capacity = capacity;
        this.description = description;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
        this.availability = availability;
        this.cusId = cusId;
    }

    public Table(String tid) {
        this.tid = tid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }
}
