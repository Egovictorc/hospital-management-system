package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.DBStaff;
import com.helen.hms.dao.Department;
import com.helen.hms.dao.Staff;
import com.helen.hms.exceptions.IncompleteCredentialsException;
import com.helen.hms.exceptions.UserNotFoundException;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Locale;

import static com.helen.hms.service.UtilityClass.changeToPrevious;

public class LoginController {
    // Overview: controls the interactivity of login fxml file(User interface)

    @FXML
    Button login;
    @FXML
    TextField username, password;
    Alert alert;

    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph
        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);
        System.out.println("btnText" + btnText);

        switch (btnText) {
            case "login":
                try {
                    String usernameText = username.getText().toString();
                    String passwordText = password.getText().toString();

                    boolean isValid = UtilityClass.validateFields(usernameText, passwordText);
                    if (DBStaff.connect() == null) {
                        //show alert if app failed to connect to database
                        UtilityClass.showAlert("Error connecting to database", "Unable to connect to database instance");
                    } else if (!isValid) {
                        // show alert if credentials are not complete
                        throw new IncompleteCredentialsException("Incomplete credentials provided");

                    } else {
                        DBStaff db = new DBStaff();
                        Staff s = db.getByUsername(usernameText); // find staff
                        if (s == null) {
                            throw new UserNotFoundException("User does not exist");
                        } else if (s.getPassword().equalsIgnoreCase(passwordText)) {
                            // validate credentials
                            PersonFactory.setSelectedStaff(s);
                            UtilityClass.changeTo("dashboard");
                        } else {
                            UtilityClass.showAlert("Username or Password Incorrect", "Please provide correct values");
                        }
                    }
                } catch (Exception e) {
                    UtilityClass.showAlert(e.getClass().getSimpleName(), e.getMessage());
                }
                break;
            case "home":
                UtilityClass.changeTo("home");
                break;
            default:
                UtilityClass.changeTo("signup");
        }
        UtilityClass.updateHistory("login");
    }
}
