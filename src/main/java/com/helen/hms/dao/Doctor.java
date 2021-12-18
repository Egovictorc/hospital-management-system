package com.helen.hms.dao;

import java.util.List;







public class Doctor {
    // Overview: manages doctor object
    private long id;
    private String username, password, firstName, lastName;
    private Qualification qualification;
    private long phone;
    private Gender gender;
    private Specialty specialty;

    public Doctor(long id, String username, String password, String firstName, String lastName, Qualification qualification, long phone, Gender gender, Specialty specialty) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.phone = phone;
        this.gender = gender;
        this.specialty = specialty;
    }

    public Doctor(String username, String password, String firstName, String lastName, Qualification qualification, long phone, Gender gender, Specialty specialty) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.phone = phone;
        this.gender = gender;
        this.specialty = specialty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", qualification=" + qualification +
                ", phone=" + phone +
                ", gender=" + gender +
                ", specialty=" + specialty +
                '}';
    }
}
