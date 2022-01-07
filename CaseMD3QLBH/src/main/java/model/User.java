package model;

public class User {
    private int id;
    private String userName;
    private String pass;
    private String email;
    private String roll;
    private String address;
    private String phoneNumber;


    public User(int id, String userName, String pass, String email, String roll, String address, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.roll = roll;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public User(String userName, String pass, String email, String address, String phoneNumber) {
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
