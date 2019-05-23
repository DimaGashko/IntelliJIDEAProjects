package com.screens.auth;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import lib.Component.Component;
import lib.Component.ComponentException;
import lib.Screen.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthScreen extends Screen {
    private Parent loginRoot;
    private Parent signupRoot;

    @FXML private VBox authContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComponents();
        openLogin();
    }

    private void openLogin() {
        authContent.getChildren().clear();
        authContent.getChildren().add(loginRoot);
    }

    private void openSignup() {
        authContent.getChildren().clear();
        authContent.getChildren().add(signupRoot);
    }

    private void initComponents() {
        try {
            loginRoot = loadComponent(global.getComponents().get("login"), null).getKey();
            signupRoot = loadComponent(global.getComponents().get("signup"), null).getKey();

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
