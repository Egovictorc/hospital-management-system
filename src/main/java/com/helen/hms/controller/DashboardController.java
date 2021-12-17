package com.helen.hms.controller;

import com.helen.hms.Main;
import com.helen.hms.dao.*;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class DashboardController {

    @FXML
    private Label welcome;
    @FXML
    private MFXLabel id, firstName, lastName, email, address, phone, gender, department;

    @FXML
    MFXButton manage;

    public void initialize() {
        //Staff s = DBStaff.getStaffByUsername(PersonFactory.getSelectedStaff().getUsername());
        Staff s = PersonFactory.getSelectedStaff();
        System.out.println("from dashboard "+s);

       if(s != null) {
           welcome.setText("Welcome "+ s.getUsername()); //use active staff username
           id.setText(String.valueOf(s.getId()));
           department.setText(String.valueOf(s.getDepartment()));
           firstName.setText(String.valueOf(s.getFirstName()));
           lastName.setText(String.valueOf(s.getLastName()));
           email.setText(String.valueOf(s.getEmail()));
           address.setText(String.valueOf(s.getAddress()));
           phone.setText(String.valueOf(s.getPhone()));
           gender.setText(String.valueOf(s.getGender()));
       }

        if (!s.getDepartment().toString().equalsIgnoreCase("admin") ) {
            manage.setVisible(false);
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
                UtilityClass.changeTo("login");
            }
            case "manage staff" -> {
                UtilityClass.changeTo("admin");
            }

            case "update profile" -> {
                UtilityClass.changeTo("updateProfile");
            }

            default -> {

            }
        }
    }


}
