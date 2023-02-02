package lk.ijse.restaurant.to;

import java.sql.Blob;

public class User {
    private String userName;
    private String name;
    private String address;
    private int contact;
    private String nic;
    private String email;
    private String passWord;
    private String role;

    public User(String userName, String name, String address, int contact, String nic, String email, String  passWord, String role) {
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
        this.email = email;
        this.passWord = passWord;
        this.role = role;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", nic='" + nic + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
