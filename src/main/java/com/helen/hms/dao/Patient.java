package com.helen.hms.dao;

import java.time.LocalDate;

public class Patient {
    // Overview: manages Patient object
    private int id, doctorId;
    private String firstName, lastName, sickness;
    private LocalDate dateAdmitted;
    private boolean discharged;

    public Patient(String firstName, String lastName, String sickness, LocalDate dateAdmitted, boolean discharged, int doctorId) {
        // MODIFIES: this
        //EFFECTS: initializes object fields

        this.firstName = firstName;
        this.lastName = lastName;
        this.sickness = sickness;
        this.dateAdmitted = dateAdmitted;
        this.discharged = discharged;
        this.doctorId = doctorId;
    }

    public Patient(int id, String firstName, String lastName, LocalDate dateAdmitted, boolean discharged) {
        // MODIFIES: this
        //EFFECTS: initializes object fields

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateAdmitted = dateAdmitted;
        this.discharged = discharged;
    }

    public Patient(int id, String firstName, String lastName, String sickness, LocalDate dateAdmitted, boolean discharged, int doctorId) {
        // MODIFIES: this
        //EFFECTS: initializes object fields

        this.id = id;
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sickness = sickness;
        this.dateAdmitted = dateAdmitted;
        this.discharged = discharged;
    }

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public LocalDate getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(LocalDate dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sickness='" + sickness + '\'' +
                ", dateAdmitted=" + dateAdmitted +
                ", discharged=" + discharged +
                '}';
    }
}
