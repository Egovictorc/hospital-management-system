package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.*;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Locale;

public class UpdateAccountantController {
// Overview: controls the interactivity of updateAccountant fxml file(User interface)

    @FXML
    MFXTextField username, password, firstName, lastName, phone;
    @FXML
    MFXComboBox<Gender> gender;
    @FXML
    MFXComboBox<Specialty> specialty;
    @FXML
    MFXComboBox<Qualification> qualification;

    Doctor d = PersonFactory.getSelectedDoctor();


    @FXML
    public void initialize() throws IOException {
        // EFFECTS: initializes data members

        gender.getItems().addAll(Gender.Male, Gender.Female);
        gender.getSelectionModel().selectItem(Gender.Male);

        specialty.getItems().addAll(Specialty.Cardiologist, Specialty.Gynecologist, Specialty.Pediatrician, Specialty.Surgeon, Specialty.PsyChiatrist);
        specialty.getSelectionModel().selectFirst();

        qualification.getItems().addAll(Qualification.PHD, Qualification.MSC, Qualification.BSC);
        qualification.getSelectionModel().selectFirst();

        if (d != null) {
            firstName.setText(d.getFirstName());
            lastName.setText(d.getLastName());
            username.setText(d.getLastName());
            password.setText(d.getLastName());
            phone.setText(String.valueOf(d.getPhone()));
            specialty.getSelectionModel().selectItem(d.getSpecialty());
            qualification.getSelectionModel().selectItem(d.getQualification());
            gender.getSelectionModel().selectItem(d.getGender());
        }
    }


    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
        System.out.println("btnText" + btnText);

        switch (btnText) {
            case "save" -> {
                // updated fields
                String fname = firstName.getText();
                String lname = lastName.getText();
                String phoneNo = phone.getText().toLowerCase().trim();
                String user = username.getText().toLowerCase().trim();
                String pwd = password.getText().toLowerCase().trim();
                String specialization = specialty.getSelectionModel().getSelectedItem().toString();
                String quali = qualification.getSelectionModel().getSelectedItem().toString();
                String gend = gender.getSelectionModel().getSelectedItem().toString();

                // check that all field values where supplied
                boolean isValid = UtilityClass.validateFields(fname, lname, phoneNo, user, pwd, specialization, quali, gend);
                if (isValid) {
                    Doctor updatedDoctor = new Doctor(d.getId(), user, pwd, fname, lname, Enum.valueOf(Qualification.class, quali), Long.parseLong(phoneNo), Enum.valueOf(Gender.class, gend), Enum.valueOf(Specialty.class, specialization));
                    // create a alert

                    DBDoctor.connect();
                    DBDoctor db = new DBDoctor();
                    // update patient in database
                    db.updatePerson(updatedDoctor);
                    try {
                        Main.setRoot("accountant");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    UtilityClass.showAlert("Incomplete Fields", "Please provide all fields");
                }
            }
            case "add accountant" -> {
                try {
                    Main.setRoot("addAccountant");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "all accountants" -> {
                try {
                    Main.setRoot("accountant");
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
