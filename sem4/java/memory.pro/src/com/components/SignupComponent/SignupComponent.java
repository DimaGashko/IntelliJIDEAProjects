package com.components.SignupComponent;

import com.Common.Common;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import javafx.fxml.FXML;
import lib.Alerts.Alerts;
import com.services.AuthService.AuthServiceException;
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
    @FXML private RegexValidator fxEmailValidator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    private void initValidation() {
        fxEmailValidator.setRegexPattern(Common.EMAIL_REGEXP);

        Validation.initValidation(fxFirstName);
        Validation.initValidation(fxLastName);
        Validation.initValidation(fxUsername);
        Validation.initValidation(fxPassword);
        Validation.initValidation(fxEmail);
    }

    private void signup() {
        if (!isValid()) return;

        User user = readUserData();

        try {
            common.getAuthService().signupAndLogin(user);
        } catch (AuthServiceException e) {
            alerts.show(Alerts.alertErr, e.getMessage());
            return;
        }

        common.setScreen("index");
    }

    private User readUserData() {
        User user = new User();

        user.setFirstName(fxFirstName.getText());
        user.setLastName(fxLastName.getText());
        user.setUsername(fxUsername.getText());
        user.setPassword(fxPassword.getText());
        user.setEmail(fxEmail.getText());

        return user;
    }

    private boolean isValid() {
        boolean firstName = fxFirstName.validate();
        boolean lastName = fxLastName.validate();
        boolean username = fxUsername.validate();
        boolean password = fxPassword.validate();
        boolean email = fxEmail.validate();

        return firstName && lastName && username && password && email;
    }

    @FXML void onSignup() {
        signup();
    }

}
