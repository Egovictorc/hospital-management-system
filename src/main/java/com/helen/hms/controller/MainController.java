package com.helen.hms.controller;

import io.github.palexdev.materialfx.controls.MFXLabel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {

    @FXML
    Label title;
    public void initialize() {
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
//                                System.out.println("this is called every 5 seconds on UI thread");

                                // get screen bounds
                                Rectangle2D bounds = Screen.getPrimary().getBounds();
                            /*    System.out.println("height "+ bounds.getHeight());
                                System.out.println("width "+ bounds.getWidth());*/

                                Scene scene = title.getScene();
                                Stage stage = (Stage)scene.getWindow();
                                try {
                                    // change view to home view
                                    FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/com/helen/hms/fxml/home.fxml"));
                                    scene.setRoot(fxmlLoader.load());
                                    // use screen width and height
                                    stage.setX(0.0);
                                    stage.setY(0.0);
                                    stage.setWidth(bounds.getWidth());
                                    stage.setHeight(bounds.getHeight());
                                    System.out.println("x "+ scene.getX());
                                    System.out.println("y " +scene.getY());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //event.getTarget()
                            }
                        }));
        fiveSecondsWonder.setCycleCount(1);

        //fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
}
