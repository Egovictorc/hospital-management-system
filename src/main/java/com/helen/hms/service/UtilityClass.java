package com.helen.hms.service;

import com.helen.hms.Main;
import com.helen.hms.dao.Staff;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.LinkedList;

public class UtilityClass {
    // Overview: manages error messages and history

    private static Alert alert;
    private static LinkedList<String> history = new LinkedList<>();

    public static boolean validateFields(String... fields) {
        // department is not required for login
        for (String field: fields) {
            if (field.isBlank()) {
                return false;
            }
        }
        return true;
    }

    public static void showAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.show();
    }

    public static void changeTo(String fxml) {
        try {
            Main.setRoot(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeToPrevious() {
        try {
            Main.setRoot(UtilityClass.getHistory().getLast());
            UtilityClass.getHistory().removeLast();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<String> getHistory() {
        return history;
    }

    public static void setHistory(LinkedList<String> history) {
        UtilityClass.history = history;
    }

    public static void updateHistory(String fxml) {
        if( !getHistory().contains(fxml)) {
            getHistory().add(fxml);
        }
    }
}
