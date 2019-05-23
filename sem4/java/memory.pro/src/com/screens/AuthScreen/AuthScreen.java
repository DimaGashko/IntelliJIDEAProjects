package com.screens.AuthScreen;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import lib.Component.ComponentException;
import lib.Screen.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthScreen extends Screen {
    private Parent loginRoot;
    private Parent signupRoot;

    @FXML private VBox fxAuthScreen;
    @FXML private VBox fxAuthScreenContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComponents();
        openLogin();
    }

    private void openLogin() {
        fxAuthScreenContent.getChildren().clear();
        fxAuthScreenContent.getChildren().add(loginRoot);

        fxAuthScreen.getStyleClass().removeIf(className -> className.equals("auth-screen--signup"));
        fxAuthScreen.getStyleClass().add("auth-screen--login");
    }

    private void openSignup() {
        fxAuthScreenContent.getChildren().clear();
        fxAuthScreenContent.getChildren().add(signupRoot);

        fxAuthScreen.getStyleClass().removeIf(className -> className.equals("auth-screen--login"));
        fxAuthScreen.getStyleClass().add("auth-screen--signup");
    }

    private void initComponents() {
        try {
            loginRoot = loadComponent("login").getKey();
            signupRoot = loadComponent("signup").getKey();

        } catch (ComponentException e) {
            alerts.showError(e);
            System.exit(1);
        }
    }

    @FXML void onLogIn() {
        openLogin();
    }
    @FXML void onSignUp() {
        openSignup();
    }



}
