package com;

import Global.Global;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
import lib.Component.Component;
import lib.Screen.Screen;
import lib.Screen.ScreenException;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class Bootstrap extends Screen {
    private Global global = new Global();

    private String currentScreen = "index";

    @FXML
    private VBox screenSlot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEvents();
        start();
    }

    private void start() {
        showScreen(currentScreen);
    }

    private void initEvents() {
        global.getOnScreenChangeCallbacks().add(this::showScreen);
    }

    private void showScreen(String alias, HashMap<String, String> params) {
        String path = global.getScreens().get(alias);

        if (path == null || path.isEmpty()) {
            alerts.show(Alerts.alertErr, "Screen Not Found");
            showScreen("index");
            return;
        }

        if (!alias.equals("auth") && !checkAuth()) {
            showScreen("auth");
            return;
        }

        Optional<Parent> parentOpt = loadComponent(path, params);

        if (parentOpt.isEmpty()) {
            showScreen("index");
            return;
        }

        Parent parent = parentOpt.get();

        currentScreen = alias;

        screenSlot.getChildren().clear();
        screenSlot.getChildren().add(parent);
    }

    private void showScreen(String alias) {
        showScreen(alias, null);
    }

    private boolean checkAuth() {
        return global.getAuth().isLoggedIn();
    }

}
