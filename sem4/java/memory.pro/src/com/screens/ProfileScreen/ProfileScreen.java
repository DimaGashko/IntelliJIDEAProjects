package com.screens.ProfileScreen;

import lib.Screen.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileScreen extends Screen {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    protected void logout() {
        if (!common.getAuth().isLoggedIn()) return;
        if (!alerts.ask("Logout?")) return;

        common.getAuth().logout();
        common.setScreen("auth");
    }

}
