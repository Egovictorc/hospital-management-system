package com.helen.hms.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Locale;

import com.helen.hms.Main;
import com.helen.hms.service.UtilityClass;

public class HomeController {
    // Overview: controls the interactivity of doctor fxml file(User interface)

    public void onMouseClicked(MouseEvent mouseEvent) {
        // EFFECTS: handles click event on the scene graph

        Button btn = (Button) mouseEvent.getSource();
        String btnText = btn.getText().toLowerCase(Locale.ROOT);

        System.out.println(btnText);
        try {
            switch (btnText) {
                case "don't have account? sign up":
                    Main.setRoot("signup");
                    break;
                default:
                    Main.setRoot("login");
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            UtilityClass.showAlert("Error ", e1.getMessage());
            e1.printStackTrace();
        }
        UtilityClass.getHistory().add("home");
    }


}
