package lk.ijse.restaurant.to;

import java.sql.Blob;

public class Employee {
    private String eid;
    private String name;
    private String address;
    private int contact;
    private String nic;
    private double salary;
    private String email;
    private String passWord;
    private String designation;
    private int age;
    private String gender;
    private String userName;

    public Employee(String eid, String name, String address, int contact, String nic, double salary, String email, String passWord, String designation, int age, String gender, String userName) {
        this.eid = eid;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.nic=nic;
        this.salary = salary;
        this.email = email;
        this.passWord = passWord;
        this.designation = designation;
        this.age = age;
        this.gender = gender;
        this.userName = userName;
    }

    public Employee(String eid) {
        this.eid = eid;
    }

    public Employee() {

    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

}
