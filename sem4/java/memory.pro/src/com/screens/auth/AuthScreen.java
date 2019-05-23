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

    private Component logInController;
    private Component signUpController;

    @FXML
    private VBox authContent;

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

        Pair<Parent, Component> loginPair;
        Pair<Parent, Component> signupPair;

        try {
            loginPair = loadComponent(global.getComponents().get("login"), null);
            signupPair = loadComponent(global.getComponents().get("signup"), null);

        } catch (ComponentException e) {
            alerts.showError(e);
            System.exit(1);
            return;
        }

        loginRoot = loginPair.getKey();
        logInController = loginPair.getValue();

        signupRoot = signupPair.getKey();
        signUpController = signupPair.getValue();
    }

    @FXML
    void onLogIn() {
        System.out.println("login");
        openLogin();
    }

    @FXML
    void onSignUp() {
        System.out.println("signup");
        openSignup();
    }




}
