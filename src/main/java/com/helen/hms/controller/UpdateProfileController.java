package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.*;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class UpdateProfileController {

    @FXML
    MFXTextField firstName, lastName, email, id, phone, address;
    @FXML
    MFXComboBox<Gender> gender;
    @FXML
    MFXComboBox<Department> department;
    Staff s = PersonFactory.getSelectedStaff();

    @FXML
    private Label welcome;
    @FXML
    public void initialize() throws IOException {

        gender.getItems().addAll(Gender.Male, Gender.Female);
        department.getItems().addAll(Department.account, Department.admin, Department.doctor, Department.receptionist, Department.nurse);
        System.out.println(PersonFactory.getSelectedStaff());

        if (s != null) {
            welcome.setText("Welcome "+ s.getUsername()); //use active staff username

            firstName.setText(s.getFirstName());
            lastName.setText(s.getLastName());
            email.setText(s.getEmail());
            address.setText(s.getAddress());
            phone.setText(String.valueOf(s.getPhone()));
            id.setText(String.valueOf(s.getId()));
            //department.getSelectionModel().selectFirst();
            department.getSelectionModel().selectItem(s.getDepartment());
            gender.getSelectionModel().selectItem(s.getGender());
            // gender.getSelectionModel().selectFirst();
        }
    }


    public void onMouseClicked(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT).trim();
        System.out.println("btnText" + btnText);
        Scene s = btn.getScene();
        Stage stage = (Stage) s.getWindow();

        switch(btnText) {
            case "sign out" -> {
                UtilityClass.changeTo("home");
            }
            case "view profile" -> {
                UtilityClass.changeTo("dashboard");
            }
            case "save" -> {
                String firstNameText = firstName.getText();
                String lastNameText = lastName.getText();
                String emailText = email.getText();
                String idText = id.getText();
                String phoneText = phone.getText();
                String addressText = address.getText();
                String departmentText = Enum.valueOf(Department.class, department.getSelectedValue().toString()).toString();
                String genderText = Enum.valueOf(Gender.class, gender.getSelectedValue().toString()).toString();

                boolean isValid = UtilityClass.validateFields(addressText, firstNameText, lastNameText, emailText, idText, phoneText, departmentText, genderText);
                if (DBStaff.connect() == null) {
                    //show alert if app failed to connect to database
                    UtilityClass.showAlert("Error connecting to database", "Unable to connect to database instance");
                } else if (!isValid) {
                    // show alert if credentials are not complete
                    UtilityClass.showAlert("Incomplete credentials", "Please provide all fields");
                } else {
                    Staff selectedStaff = PersonFactory.getSelectedStaff();
                        Staff updatedStaff = new Staff(selectedStaff.getId(), Long.parseLong(phoneText), firstNameText, lastNameText, PersonFactory.getSelectedStaff().getUsername(), emailText, addressText, Enum.valueOf(Department.class, department.getSelectedValue().toString()), Enum.valueOf(Gender.class, gender.getSelectedValue().toString()));
                        DBStaff dbStaff = new DBStaff();
                        if (dbStaff.updatePerson(updatedStaff) == 1) {
                            PersonFactory.setSelectedStaff(updatedStaff);
                            UtilityClass.changeTo("dashboard");
                        };

                }
            }
            case "manage staff" -> {
                UtilityClass.changeTo("admin");
            }
            default -> {

            }
        }

    }
}
