package com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
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
        router.setScreen("index");
    }

    private void initEvents() {
        router.getOnScreenChangeCallbacks().add(this::showScreen);
    }

    private void showScreen(String path) {
        System.out.println(path);

        if (path == null || path.isEmpty()) {
            alerts.show(Alerts.alertErr, "Screen Not Found");
            return;
        }

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
        router.addScreen("index", "screens/index/index.fxml");
        router.addScreen("auth", "screens/auth/auth.fxml");
        router.addScreen("profile", "screens/profile/profile.fxml");
        router.addScreen("statistic", "screens/statistic/statistic.fxml");
        router.addScreen("training", "screens/training/training.fxml");
    }
}
