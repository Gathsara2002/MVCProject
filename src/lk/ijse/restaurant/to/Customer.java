package lk.ijse.restaurant.to;

public class Customer {
    private String cusId;
    private String name;
    private String address;
    private int contact;
    private String email;
    private String eid;

    public Customer(String cusId, String name, String address, int contact, String email, String eid) {
        this.cusId = cusId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.eid = eid;

    }

    public Customer(String cid) {
        this.cusId = cusId;
    }


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
