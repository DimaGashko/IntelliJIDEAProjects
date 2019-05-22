package com;

import Global.Global;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import lib.Alerts.Alerts;
import lib.Component.Component;
import lib.Component.ComponentException;
import lib.Screen.Screen;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Bootstrap extends Screen {
    private Global global;

    private String currentScreen = "index";

    public Bootstrap() {
        super();
        global = new Global();
    }

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

        Pair<Parent, Component> componentPair;

        try {
            componentPair = loadComponent(path, params);
        } catch (ComponentException e) {
            alerts.showError(e);
            showScreen("index");
            return;
        }

        Parent root = componentPair.getKey();
        Screen screen = (Screen)componentPair.getValue();

        currentScreen = alias;

        screenSlot.getChildren().clear();
        screenSlot.getChildren().add(root);

        screen.showed();
    }

    private void showScreen(String alias) {
        showScreen(alias, null);
    }

    private boolean checkAuth() {
        return global.getAuth().isLoggedIn();
    }

    public Global getGlobal() {
        return global;
    }

}
