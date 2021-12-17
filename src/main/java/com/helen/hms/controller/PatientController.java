package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.DBPatient;
import com.helen.hms.dao.Patient;
import com.helen.hms.dao.Staff;
import com.helen.hms.exceptions.NoSelectedRecordException;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class PatientController {

    @FXML
    TableView<Patient> tableView = new TableView<>();
    @FXML
    TableColumn<Patient, Integer> id, doctorId;
    @FXML
    TableColumn<Patient, String> firstName, lastName, sickness;
    @FXML
    TableColumn<Patient, Boolean> discharged;
    @FXML
    TableColumn<Patient, LocalDate> dateAdmitted;

    LocalDate ld = LocalDate.of(2021, 10, 15);
    ListIterator<Patient> patientListIterator;

    public void initialize() {
        // set columns factory
        setColumsValueFactory();
        // add patients
        addPatients();
    }

    private void addPatients() {
//        patient placeholder when there is no patient in db
        PersonFactory.getPatients().add(new Patient(1, "uche", "okafor", "Malaria", ld, false, 101));
        PersonFactory.getPatients().add(new Patient(2, "sandra", "doe", "typhoid", ld, false, 102));
        DBPatient.connect();
        DBPatient db = new DBPatient();
        ResultSet rs = db.getAll("patient");

        try {
            while (rs.next()) {
                Patient p = new Patient(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("sickness"), rs.getDate("date_admitted").toLocalDate(), rs.getBoolean("discharged"), rs.getInt("doctor_id"));
                // PatientFactory.patients.add(p);
                PersonFactory.getPatients().add(p);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        patientListIterator = PersonFactory.getPatients().listIterator();
        while(patientListIterator.hasNext()) {
            tableView.getItems().add(patientListIterator.next());
        }
    }

    private void setColumsValueFactory() {
        id.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        doctorId.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("doctorId"));
        firstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        sickness.setCellValueFactory(new PropertyValueFactory<Patient, String>("sickness"));
        dateAdmitted.setCellValueFactory(new PropertyValueFactory<Patient, LocalDate>("dateAdmitted"));
        discharged.setCellValueFactory(new PropertyValueFactory<Patient, Boolean>("discharged"));
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
        System.out.println("btnText" + btnText);

        switch (btnText) {
            case "add patient" -> {
                try {
                    Main.setRoot("addPatient");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // showFormWindow("addPatient", btnText);
            }
            case "update patient" -> {
                Patient p = tableView.getSelectionModel().getSelectedItem();
                if (p == null) {
                    UtilityClass.showAlert("No Selection", "Please select record to update");
                } else {
                    PersonFactory.setSelectedPatient(p);

                    try {
                        Main.setRoot("updatePatient");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "remove patient" -> {
                try {
                    Patient p = tableView.getSelectionModel().getSelectedItem();
                    if (p != null) {
                        DBPatient.connect();
                        DBPatient db = new DBPatient();
                        db.removePerson(p);
                        tableView.getItems().remove(p);
                    } else {
                        throw new NoSelectedRecordException("Please select patient to remove");
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
