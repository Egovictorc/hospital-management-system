package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.DBPatient;
import com.helen.hms.dao.Patient;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

public class AddPatientController {
    // Overview: controls the interactivity of addPatient fxml file(User interface)

    @FXML
    MFXTextField firstName, lastName, sickness, doctorId;

    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT).trim();
        System.out.println("btnText" + btnText);
        Scene s = btn.getScene();
        Stage stage = (Stage) s.getWindow();

        switch (btnText) {
            case "enrol patient" -> {
                String firstNameText = firstName.getText().toLowerCase().trim();
                String lastNameText = lastName.getText().toLowerCase().trim();
                String sicknessText = sickness.getText().toLowerCase().trim();
                String doctorIdText = doctorId.getText().toLowerCase().trim();
                // check that all field values where supplied
                boolean isValid = UtilityClass.validateFields(firstNameText, lastNameText, sicknessText, doctorIdText);

                if (isValid) {
                    Patient p = new Patient(firstNameText, lastNameText, sicknessText, LocalDate.now(), false, Integer.parseInt(doctorIdText));
                    // update patients table in database
                    DBPatient.connect();
                    DBPatient db = new DBPatient();
                    db.registerPerson(p);

//            update patient tableview
//            PatientController.tableView.getItems().add(p);
                } else {
                    UtilityClass.showAlert("Incomplete Fields", "Please provide all details");
                }
            }

            case "update patient" -> {
                try {
                    Main.setRoot("updatePatient");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "sign out" -> {
                UtilityClass.changeTo("login");
            }

            default -> {

            }
        }
    }
}
