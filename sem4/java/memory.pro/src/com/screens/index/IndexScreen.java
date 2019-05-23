package com.screens.index;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexScreen extends Screen implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("IndexScreen!!");

    }

    @Override
    public void showed() {
        String username = global.getAuth().getLoggedInUsername();

        User user = global.getUserDao().getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cannot load user data"));

        System.out.println(user);
    }

    public void click() {
        global.setScreen("auth");
    }
}
