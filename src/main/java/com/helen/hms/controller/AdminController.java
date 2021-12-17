package com.helen.hms.controller;

import com.helen.hms.service.UtilityClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Locale;

public class AdminController {

    public void onMouseClicked(MouseEvent mouseEvent) {

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);

        System.out.println("btnText " + btnText);
        switch (btnText) {
            case "patient" -> UtilityClass.changeTo("patient");
            case "doctor" -> UtilityClass.changeTo("doctor");
            case "accountant" -> UtilityClass.changeTo("accountant");
            case "sign out" -> UtilityClass.changeTo("login");
            default -> UtilityClass.changeTo("patient");
        }
        UtilityClass.getHistory().add("admin");
    }


}
