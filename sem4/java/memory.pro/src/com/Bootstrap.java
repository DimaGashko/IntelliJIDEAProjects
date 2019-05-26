package com;

import com.Common.Common;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import lib.Alerts.Alerts;
import lib.Component.ComponentException;
import lib.Screen.Screen;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Bootstrap extends Screen {
    private Common common;
    private String currentScreen;

    @FXML
    private VBox headerSlot;

    @FXML
    private StackPane screenSlot;

    public Bootstrap() {
        super();
        common = new Common();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEvents();
        start();
    }

    private void start() {
        renderHeader();
        showScreen("index");
    }

    private void initEvents() {
        common.getOnScreenChangeCallbacks().add(this::showScreen);
    }

    private void showScreen(String alias, HashMap<String, String> params) {
        if (alias.equals(currentScreen)) return;

        String path = common.getScreens().get(alias);

        if (path == null || path.isEmpty()) {
            alerts.show(Alerts.alertErr, "Screen Not Found");
            showScreen("index");
            return;
        }

        if (!alias.equals("auth") && !checkAuth()) {
            showScreen("auth");
            return;
        }

        Pair<Parent, Screen> componentPair;

        try {
            componentPair = loadScreen(alias, params);
        } catch (ComponentException e) {
            alerts.showError(e);
            return;
        }

        Parent root = componentPair.getKey();
        Screen screen = componentPair.getValue();

        currentScreen = alias;

        screenSlot.getChildren().clear();
        screenSlot.getChildren().add(root);

        screen.showed();
    }

    private void renderHeader() {
        Parent header = null;

        try {
            header = loadComponent("header").getKey();
        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cannot render Header");
            e.printStackTrace();
        }

        headerSlot.getChildren().clear();
        headerSlot.getChildren().add(header);
    }

    private void showScreen(String alias) {
        showScreen(alias, null);
    }

    private boolean checkAuth() {
        return common.getAuthService().isLoggedIn();
    }

    public Common getCommon() {
        return common;
    }

    @Override
    public void showed() {

    }
}
