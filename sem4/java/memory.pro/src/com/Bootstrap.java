package com;

import dao.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
import lib.Auth.Auth;
import lib.Auth.AuthException;
import lib.Screen.Screen;
import lib.Screen.ScreenException;
import schemas.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class Bootstrap extends Screen implements Initializable {
    UserDao userDao;
    User user;
    Auth auth;
    @FXML
    private VBox screenSlot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEntityManager();
        initRouter();
        initEvents();

        start();
    }

    protected void start() {
        router.setScreen("index");
    }

    private void initEvents() {
        router.getOnScreenChangeCallbacks().add(this::showScreen);
    }

    private void showScreen(String path, HashMap<String, String> params) {
        if (!checkAuth(path)) {
            return;
        }

        System.out.println(path);

        if (path == null || path.isEmpty()) {
            alerts.show(Alerts.alertErr, "Screen Not Found");
            return;
        }

        Parent root;

        try {
            root = loadScreen(path, params);
        } catch (ScreenException e) {
            alerts.showError("Screen Not Found", e);
            return;
        }
        //System.out.println(root);
        screenSlot.getChildren().clear();
        screenSlot.getChildren().add(root);
    }

    private boolean checkAuth(String path) {
        if (path.equals(router.getScreens().get("auth"))) {
            return true;
        }

        if (!auth.isLoggedIn()) {
            System.out.println("You're not logged in");
            router.setScreen("auth");
            return false;
        }

        Optional<User> userOpt;

        while (true) {
            userOpt = userDao.getUserByUsername(auth.getLoggedInUsername());
            if (userOpt.isPresent()) break;

            Optional<ButtonType> ans = alerts.show(Alerts.alertConfirm, "Connection error", "Repeat?");

            if (ans.isPresent() && ans.get() != ButtonType.OK) {
                // TODO: do real close
                System.exit(0);
            }

        }

        user = userOpt.get();

        System.out.println(user);
        return true;
    }

    private void initEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        auth = new Auth(userDao);
    }

    private void initRouter() {
        router.addScreen("index", "screens/index/IndexScreen.fxml");
        router.addScreen("auth", "screens/auth/AuthScreen.fxml");
        router.addScreen("profile", "screens/profile/ProfileScreen.fxml");
        router.addScreen("statistic", "screens/statistic/StatisticScreen.fxml");
        router.addScreen("training", "screens/training/TrainingScreen.fxml");

        router.addComponent("header", "components/header/HeaderComponent.fxml");
        router.addComponent("login", "components/login/LoginComponent.fxml");
        router.addComponent("signup", "components/signup/SignupComponent.fxml");
    }
}
