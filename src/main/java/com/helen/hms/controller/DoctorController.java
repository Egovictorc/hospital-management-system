package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.*;
import com.helen.hms.exceptions.NoSelectedRecordException;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ListIterator;
import java.util.Locale;

public class DoctorController {
    // Overview: controls the interactivity of doctor fxml file(User interface)

    @FXML
    TableView<Doctor> tableView = new TableView<>();
    @FXML
    TableColumn<Doctor, Integer> id;
    @FXML
    TableColumn<Doctor, Long> phone;
    @FXML
    TableColumn<Doctor, String> firstName, lastName;

    @FXML
    TableColumn<Doctor, Gender> gender;
    @FXML
    TableColumn<Doctor, Qualification> qualification;
    @FXML
    TableColumn<Doctor, Specialty> specialty;

    LocalDate ld = LocalDate.of(2021, 10, 15);
    ListIterator<Doctor> doctorListIterator;

    public void initialize() {
        // EFFECTS: initializes data members

        // set columns factory
        setColumsValueFactory();
        // initialize doctors collection
        PersonFactory.setDoctors(FXCollections.observableArrayList());
        // add doctors
        addDoctors();
    }

    private void addDoctors() {
        //MODIFIES: tableView
        // EFFECTS: populate tableView with doctors data from db

//        doctors placeholder when there is no doctor in db
        PersonFactory.getDoctors().add(new Doctor(1, "uche", "12345", "uche", "okafor", Qualification.BSC, 9012123123l, Gender.Male, Specialty.Gynecologist));
        PersonFactory.getDoctors().add(new Doctor(2, "sandra", "12345", "sandra", "doe", Qualification.MSC, 9012123123l, Gender.Female, Specialty.Cardiologist));

        DBDoctor.connect();
        DBDoctor db = new DBDoctor();
        ResultSet rs = db.getAll("doctor");

        try {
            while (rs.next()) {
                Doctor d = new Doctor(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), Enum.valueOf(Qualification.class, rs.getString("qualification")), rs.getLong("phone"), Enum.valueOf(Gender.class, rs.getString("gender")), Enum.valueOf(Specialty.class, rs.getString("specialty")));
                PersonFactory.getDoctors().add(d);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        doctorListIterator = PersonFactory.getDoctors().listIterator();
        while (doctorListIterator.hasNext()) {
            tableView.getItems().add(doctorListIterator.next());
        }
        //tableView.getItems().addAll(PersonFactory.getDoctors());
    }

    private void setColumsValueFactory() {
        // MODIFIES: data members
        // EFFECTS: sets cellvalueFactory on data memebers
        id.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastName"));
        qualification.setCellValueFactory(new PropertyValueFactory<Doctor, Qualification>("qualification"));
        gender.setCellValueFactory(new PropertyValueFactory<Doctor, Gender>("gender"));
        specialty.setCellValueFactory(new PropertyValueFactory<Doctor, Specialty>("specialty"));
        phone.setCellValueFactory(new PropertyValueFactory<Doctor, Long>("phone"));
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
        System.out.println("btnText" + btnText);

        switch (btnText) {
            case "add doctor" -> {
                try {
                    Main.setRoot("addDoctor");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // showFormWindow("addPatient", btnText);
            }
            case "update doctor" -> {
                Doctor d = tableView.getSelectionModel().getSelectedItem();
                if (d == null) {
                    UtilityClass.showAlert("No Selection", "Please select record to update");
                } else {
                    PersonFactory.setSelectedDoctor(d);
                    try {
                        Main.setRoot("updateDoctor");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "remove doctor" -> {
                Doctor d = tableView.getSelectionModel().getSelectedItem();
                try {
                    if (d != null) {
                        DBDoctor.connect();
                        DBDoctor db = new DBDoctor();
                        db.removePerson(d);
                        tableView.getItems().remove(d);
                    } else {
                        throw new NoSelectedRecordException("Please select doctor to remove");
                    }
                } catch (Exception e) {
                    UtilityClass.showAlert(e.getClass().getSimpleName(), e.getMessage());
                }
            }

            case "sign out" -> {
                UtilityClass.changeTo("login");
            }
            default -> {

            }
        }
        UtilityClass.getHistory().add("admin");
    }
}
