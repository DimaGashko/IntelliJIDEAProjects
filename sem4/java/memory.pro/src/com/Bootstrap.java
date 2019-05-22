package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lib.Screen.Screen;
import lib.Screen.ScreenException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ResourceBundle;

public class Bootstrap extends Screen implements Initializable {
    @FXML
    private VBox screenSlot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEntityManager();
        initRouter();
        initEvents();
        start();
    }

    private void start() {
        router.setRoute("index");
    }

    private void initEvents() {
        router.getOnScreenChangeCallbacks().add((route) -> {
            showScreen(route.getValue());
        });
    }

    private void showScreen(String path) {
        Parent root;

        try {
            root = loadScreen(path);
        } catch (ScreenException e) {
            alerts.showError("Screen Not Found", e);
            return;
        }

        screenSlot.getChildren().clear();
        screenSlot.getChildren().add(root);
    }

    private void initEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();
    }

    private void initRouter() {
        router.addRoute("index", "screens/index/index.fxml");
        router.addRoute("auth", "screens/auth/auth.fxml");
        router.addRoute("screen1", "screens/screen1/screen1.fxml");
    }
}
