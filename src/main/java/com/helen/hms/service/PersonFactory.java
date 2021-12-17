package com.helen.hms.service;

import com.helen.hms.dao.Doctor;
import com.helen.hms.dao.Patient;
import com.helen.hms.dao.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonFactory {
    private static ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private static Doctor selectedDoctor;

    private static ObservableList<Patient> patients = FXCollections.observableArrayList();
    private static Patient selectedPatient;

    private static ObservableList<Staff> staff = FXCollections.observableArrayList();
    private static Staff selectedStaff;

    public static ObservableList<Patient> getPatients() {
        return patients;
    }

    public static void setPatients(ObservableList<Patient> patients) {
        PersonFactory.patients = patients;
    }

    public static Patient getSelectedPatient() {
        return selectedPatient;
    }

    public static void setSelectedPatient(Patient selectedPatient) {
        PersonFactory.selectedPatient = selectedPatient;
    }

    public static ObservableList<Staff> getStaff() {
        return staff;
    }

    public static void setStaff(ObservableList<Staff> staff) {
        PersonFactory.staff = staff;
    }

    public static Staff getSelectedStaff() {
        return selectedStaff;
    }

    public static void setSelectedStaff(Staff selectedStaff) {
        PersonFactory.selectedStaff = selectedStaff;
    }

    public static ObservableList<Doctor> getDoctors() {
        return doctors;
    }

    public static void setDoctors(ObservableList<Doctor> doctors) {
        PersonFactory.doctors = doctors;
    }

    public static Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public static void setSelectedDoctor(Doctor selectedDoctor) {
        PersonFactory.selectedDoctor = selectedDoctor;
    }
}
