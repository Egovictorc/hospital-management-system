package com.helen.hms.dao;


public class Staff {
    // Overview: manages Staff objects

    private int id;
    private long phone;
    private String username, firstName, lastName, email, address;
    private String password;
    private int level;
    private Department department;
    private Gender gender;

    public Staff(int id, String username, String password) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        super();
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Staff(String username, String password, Department department) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        super();
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public Staff(int id, String username, String password, Department department) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public Staff(long phone, String firstName, String lastName, String username, String email, String address, Department department, Gender gender) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.department = department;
        this.gender = gender;
    }

    public Staff(int id, long phone, String firstName, String lastName, String username, String email, String address, Department department, Gender gender) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.department = department;
        this.gender = gender;
    }

    public Staff(int id, long phone, String firstName, String lastName, String username, String password, String email, String address, Department department, Gender gender) {
        // MODIFIES: this
        //EFFECTS: initializes object fields
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.department = department;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", phone=" + phone +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", department=" + department +
                ", gender=" + gender +
                '}';
    }
}
