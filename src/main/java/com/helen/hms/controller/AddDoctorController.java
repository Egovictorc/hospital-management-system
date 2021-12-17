package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.*;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

public class AddDoctorController {

    @FXML
    MFXTextField username, password, firstName, lastName, phone;
    @FXML
    MFXComboBox<Gender> gender;
    @FXML
    MFXComboBox<Specialty> specialty;
    @FXML
    MFXComboBox<Qualification> qualification;


    public void initialize() {
        gender.getItems().addAll(Gender.Male, Gender.Female);
        gender.getSelectionModel().selectItem(Gender.Male);

        specialty.getItems().addAll(Specialty.Cardiologist, Specialty.Gynecologist, Specialty.Pediatrician, Specialty.Surgeon, Specialty.PsyChiatrist);
        specialty.getSelectionModel().selectFirst();

        qualification.getItems().addAll(Qualification.PHD, Qualification.MSC, Qualification.BSC);
        qualification.getSelectionModel().selectFirst();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT).trim();
        System.out.println("btnText" + btnText);
        Scene s = btn.getScene();
        Stage stage = (Stage) s.getWindow();

        switch (btnText) {
            case "add doctor" -> {
                String firstNameText = firstName.getText().toLowerCase().trim();
                String lastNameText = lastName.getText().toLowerCase().trim();
                String phoneNo = phone.getText().toLowerCase().trim();
                String user = username.getText().toLowerCase().trim();
                String pwd = password.getText().toLowerCase().trim();

                String specialization = specialty.getSelectionModel().getSelectedItem().toString();
                String quali = qualification.getSelectionModel().getSelectedItem().toString();
                String gend = gender.getSelectionModel().getSelectedItem().toString();
                // check that all field values where supplied
                // check that all field values where supplied
                boolean isValid = UtilityClass.validateFields(firstNameText, lastNameText, phoneNo, user, pwd, specialization, quali, gend);

                if (isValid) {
                    Doctor d = new Doctor(user, pwd, firstNameText, lastNameText, qualification.getSelectedValue(), Long.parseLong(phoneNo), gender.getSelectedValue(), specialty.getSelectedValue());
                    // update patients table in database
                    DBDoctor.connect();
                    DBDoctor db = new DBDoctor();
                    db.registerPerson(d);

                } else {
                    UtilityClass.showAlert("Incomplete Fields", "Please provide all details");
                }
            }

            case "update doctor" -> {
                try {
                    Main.setRoot("updateDoctor");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "all doctors" -> {
                try {
                    Main.setRoot("doctor");
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
