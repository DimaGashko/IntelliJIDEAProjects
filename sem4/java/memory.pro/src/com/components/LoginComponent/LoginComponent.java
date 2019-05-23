package com.components.LoginComponent;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import lib.Alerts.Alerts;
import lib.Auth.AuthException;
import lib.Component.Component;
import lib.Validation.Validation;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginComponent extends Component {

    @FXML private JFXTextField fxUsername;
    @FXML private JFXPasswordField fxPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    private void initValidation() {
        Validation.initValidation(fxUsername);
        Validation.initValidation(fxPassword);
    }

    private void login() {
        if (!isValid()) return;

        String username = fxUsername.getText();
        String password = fxPassword.getText();

        try {
            common.getAuth().login(username, password);
        } catch (AuthException e) {
            alerts.show(Alerts.alertWarn, e.getMessage());
            return;
        }

        common.setScreen("index");
    }

    private boolean isValid() {
        boolean username = fxUsername.validate();
        boolean password = fxPassword.validate();

        return username && password;
    }

    @FXML void onLogin() {
        login();
    }

}
