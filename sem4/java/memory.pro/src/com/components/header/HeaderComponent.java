package com.components.header;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import lib.Alerts.Alerts;
import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderComponent extends Component {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Header");
    }

    private void goHome() {
        global.setScreen("index");
    }

    private void goToTraining() {
        System.out.println("Training");
        //global.setScreen("training");
    }

    private void goToStatistic() {
        System.out.println("Statistic");
        //global.setScreen("statistic");
    }

    private void goToAuth() {
        global.setScreen("auth");
    }

    private void goToProfile() {
        System.out.println("Profile");
        //global.setScreen("profile");
    }

    private void logout() {
        var ans = alerts.show(Alerts.alertConfirm, "Logout?");

        if (ans.isPresent() && ans.get() != ButtonType.OK) {
            return;
        }

        global.getAuth().logout();
        global.setScreen("auth");
    }

    @FXML
    void onAuth() {
        goToAuth();
    }

    @FXML
    void onHome() {
        goHome();
    }

    @FXML
    void onProfile() {
        goToProfile();
    }

    @FXML
    void onStatistic() {
        goToStatistic();
    }

    @FXML
    void onTraining() {
        goToTraining();
    }

    @FXML
    void onLogout() {
        logout();
    }
}
