package com.components.signup;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import lib.Alerts.Alerts;
import lib.Auth.AuthException;
import lib.Component.Component;
import lib.Validation.Validation;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupComponent extends Component {

    @FXML private JFXTextField fxFirstName;
    @FXML private JFXTextField fxLastName;
    @FXML private JFXTextField fxUsername;
    @FXML private JFXTextField fxEmail;
    @FXML private JFXPasswordField fxPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    private void initValidation() {
        Validation.initValidation(fxFirstName);
        Validation.initValidation(fxLastName);
        Validation.initValidation(fxUsername);
        Validation.initValidation(fxPassword);
        Validation.initValidation(fxEmail);
    }

    private void signup() {
        User user = readUserData();

        try {
            global.getAuth().signupAndLogin(user);
        } catch (AuthException e) {
            alerts.show(Alerts.alertErr, e.getMessage());
            return;
        }

        global.setScreen("index");
    }

    private User readUserData() {
        User user = new User();

        user.setFirstName(fxFirstName.getText());
        user.setLastName(fxLastName.getText());
        user.setUsername(fxUsername.getText());
        user.setEmail(fxEmail.getText());
        user.setPassword(fxPassword.getText());

        return user;
    }

    public String getEmailRegExp() {
        return global.getEmailRegex();
    }

    @FXML void onSignup() {
        signup();
    }

}
