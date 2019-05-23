package com.components.header;

import javafx.fxml.FXML;
import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderComponent extends Component {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void goHome() {
        global.setScreen("index");
    }

    private void goToTraining() {
        global.setScreen("training");
    }

    private void goToStatistic() {
        global.setScreen("statistic");
    }

    private void goToProfile() {
        global.setScreen("profile");
    }

    private void logout() {
        if (!global.getAuth().isLoggedIn()) return;
        if (!alerts.ask("Logout?")) return;

        global.getAuth().logout();
        global.setScreen("auth");
    }

    @FXML void onHome() {
        goHome();
    }
    @FXML void onStatistic() {
        goToStatistic();
    }
    @FXML void onTraining() {
        goToTraining();
    }
    @FXML void onProfile() {
        goToProfile();
    }
    @FXML void onLogout() {
        logout();
    }
}
