package lk.ijse.restaurant.to;

public class Supplier {
    private String supId;
    private String name;
    private int contact;
    private String email;
    private String company;
    private int noOfItems;
    private double payments;

    public Supplier(String supId, String name, int contact, String email, String company, int noOfItems, double payments) {
        this.supId = supId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.company = company;
        this.noOfItems = noOfItems;
        this.payments = payments;
    }

    public Supplier(String supId) {
        this.supId = supId;
    }


    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }
}
