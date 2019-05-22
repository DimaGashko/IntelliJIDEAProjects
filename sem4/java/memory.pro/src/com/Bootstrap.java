package com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bootstrap implements Initializable {
    @FXML private VBox screenSlot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Bootstrap");
        System.out.println(screenSlot.toString());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("screens/screen1/screen1.fxml"));

            screenSlot.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
