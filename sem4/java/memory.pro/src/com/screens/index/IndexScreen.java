package com.screens.index;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lib.Screen.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexScreen extends Screen implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("IndexScreen!!");

    }

    public void click(ActionEvent actionEvent) {
        global.setScreen("auth");
    }
}
