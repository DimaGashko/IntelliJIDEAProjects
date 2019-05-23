package com.screens.IndexScreen;

import javafx.fxml.Initializable;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexScreen extends Screen implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void showed() {
        String username = common.getAuth().getLoggedInUsername();

        User user = common.getUserDao().getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cannot load user data"));

        System.out.println(user);
    }

    public void click() {
        common.setScreen("auth");
    }
}
