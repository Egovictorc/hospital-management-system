package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.DBPatient;
import com.helen.hms.dao.Doctor;
import com.helen.hms.dao.Patient;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

public class UpdatePatientController {

    @FXML
    VBox root;

    @FXML
    MFXTextField firstName, lastName, sickness, doctorId;
    @FXML
    ToggleGroup discharged;
    Patient p = PersonFactory.getSelectedPatient();

    @FXML
    public void initialize() throws IOException {

        if (p != null) {
            firstName.setText(p.getFirstName());
            lastName.setText(p.getLastName());
            sickness.setText(p.getSickness());
            doctorId.setText(String.valueOf(p.getDoctorId()));
        }
    }


    public void onMouseClicked(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
        System.out.println("btnText" + btnText);

        // updated fields
        String fname = firstName.getText();
        String lname = lastName.getText();
        String sickn = sickness.getText();
        // boolean disch = Boolean.parseBoolean(discharged.getText().toString());
        String docId = doctorId.getText();

        // check that all field values where supplied
        boolean isValid = UtilityClass.validateFields(fname, lname, sickn, docId);
        switch (btnText) {
            case "save" -> {
                if (isValid) {
                    Patient updatedPatient = new Patient(p.getId(), fname, lname, sickn, p.getDateAdmitted(), false, Integer.parseInt(docId));
                    // create a alert

                    DBPatient.connect();
                    DBPatient db = new DBPatient();
                    // update patient in database
                    db.updatePerson(updatedPatient);
                    try {
                        Main.setRoot("patient");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    UtilityClass.showAlert("Incomplete Fields", "Please provide all fields");
                }
            }
            case "sign out" -> {
                UtilityClass.changeTo("login");
            }

            case "add patient" -> {
                UtilityClass.changeTo("addPatient");
            }
            case "all patients" -> {
                UtilityClass.changeTo("patient");
            }
            default -> {

            }
        }


    }
}
