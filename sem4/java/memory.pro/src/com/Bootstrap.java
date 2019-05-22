package com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
import lib.Router.Router;
import lib.Screen.ScreenController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bootstrap implements Initializable {
    @FXML
    private VBox screenSlot;

    private Router router = new Router();
    private EntityManager em;

    private Alerts alerts = new Alerts();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        loader.setControllerFactory((ControllerClass) -> {
            try {
                ScreenController screenController = (ScreenController) ControllerClass.getDeclaredConstructor().newInstance();
                screenController.setEntityManager(em);
                screenController.setRouter(router);

                return screenController;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                alerts.showError("Screen Not Found", e);
                return null;
            }
        });

        try {
            root = loader.load();
        } catch (Exception e) {
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
