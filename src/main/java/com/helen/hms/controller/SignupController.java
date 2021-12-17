package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.DBStaff;
import com.helen.hms.dao.Department;
import com.helen.hms.dao.Staff;
import com.helen.hms.exceptions.IncompleteCredentialsException;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Locale;

public class SignupController {
    // Overview: controls the interactivity of doctor fxml file(User interface)

    @FXML
    Button login;
    @FXML
    TextField username, password;
    @FXML
    ToggleGroup department;

    Alert alert;

    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
//        System.out.println("btnText" + btnText);

        // create a alert
        switch (btnText) {
            case "signup":
                try {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                RadioButton rd = (RadioButton) department.getSelectedToggle();

                String departmentText = "";
                if (rd != null) {
                    departmentText = rd.getText().toLowerCase(Locale.ROOT);
                }
                System.out.println("departmentText " + departmentText);

                boolean isValid = UtilityClass.validateFields(usernameText, passwordText, departmentText);

                if (DBStaff.connect() == null) {
                    //show alert if app failed to connect to database
                    UtilityClass.showAlert("Error connecting to database", "Unable to connect to database instance");
                } else if (!isValid) {
                    // show alert if credentials are not complete
                    throw new IncompleteCredentialsException("Please provide all fields");
                } else {
                    Staff s = new Staff(usernameText, passwordText, Enum.valueOf(Department.class, departmentText));

                    DBStaff st = new DBStaff();
                    if (st.registerPerson(s) == 1) { // if registration succeeds
                        //UtilityClass.setStaff(s); // set active staff
                         PersonFactory.setSelectedStaff(st.getByUsername(usernameText));
                        UtilityClass.changeTo("dashboard");
                    };
                }

        } catch (Exception e) {
            UtilityClass.showAlert(e.getClass().getSimpleName(), e.getMessage());
        }
                break;
            case "home":
                UtilityClass.changeTo("home");
                break;
            default:
                try {
                    Main.setRoot("login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        UtilityClass.updateHistory("signup");
    }
}
