package com.screens.IndexScreen;

import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexScreen extends Screen {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void showed() {
        var userOpt = getUser();

        if (userOpt.isEmpty()) {
            common.setScreen("auth");
            return;
        }

        User user = userOpt.get();

        System.out.println(user);
    }

    public void click() {
        common.setScreen("auth");
    }
}
