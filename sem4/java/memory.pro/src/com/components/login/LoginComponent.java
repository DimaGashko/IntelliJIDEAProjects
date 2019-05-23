package com.components.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import lib.Alerts.Alerts;
import lib.Auth.AuthException;
import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginComponent extends Component {

    @FXML
    private JFXTextField fxUsername;

    @FXML
    private JFXPasswordField fxPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void login() {
        String username = fxUsername.getText();
        String password = fxPassword.getText();

        try {
            global.getAuth().login(username, password);
        } catch (AuthException e) {
            alerts.show(Alerts.alertWarn, "Wrong username of password");
            return;
        }

        global.setScreen("index");
    }

    @FXML
    void onLogin() {
        login();
    }

}
