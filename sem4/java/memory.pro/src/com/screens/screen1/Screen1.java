package com.screens.screen1;

import javafx.fxml.Initializable;
import lib.Screen.ScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class Screen1 extends ScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Screen1!!");
        System.out.println(em);
        System.out.println(router);
        System.out.println("Screen1!!");
    }
}
