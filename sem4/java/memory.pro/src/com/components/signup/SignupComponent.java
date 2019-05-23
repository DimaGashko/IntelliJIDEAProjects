package com.components.signup;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import lib.Alerts.Alerts;
import lib.Auth.AuthException;
import lib.Component.Component;
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

    }

    private void signup() {
        String firstName = fxFirstName.getText();
        String lastName = fxLastName.getText();
        String username = fxUsername.getText();
        String email = fxEmail.getText();
        String password = fxPassword.getText();

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        try {
            global.getAuth().signupAndLogin(user);
        } catch (AuthException e) {
            alerts.show(Alerts.alertErr, e.getMessage());
            return;
        }

        global.setScreen("index");
    }

    @FXML void onSignup() {
        signup();
    }

}
