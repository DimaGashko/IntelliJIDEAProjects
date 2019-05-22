package com;

import com.sun.glass.ui.Screen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import lib.Router.Router;
import lib.Screen.ScreenController;

import javax.naming.spi.Resolver;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bootstrap implements Initializable {
    @FXML private VBox screenSlot;

    private Router router = new Router();
    private EntityManager em;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEntityManager();

        System.out.println("Bootstrap");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/screen1/screen1.fxml"));

            loader.setControllerFactory((ControllerClass) -> {
                try {
                    ScreenController screenController = (ScreenController) ControllerClass.getDeclaredConstructor().newInstance();
                    screenController.setEntityManager(em);
                    screenController.setRouter(router);

                    return screenController;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                    return null;
                }
            });

            Parent root = loader.load();

            screenSlot.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();
    }
}
