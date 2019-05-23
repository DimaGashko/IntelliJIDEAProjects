package com.components.HeaderComponent;

import javafx.fxml.FXML;
import lib.Component.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class HeaderComponent extends Component {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void goHome() {
        common.setScreen("index");
    }

    private void goToTraining() {
        common.setScreen("training");
    }

    private void goToStatistic() {
        common.setScreen("statistic");
    }

    private void goToProfile() {
        common.setScreen("profile");
    }

    private void logout() {
        if (!common.getAuth().isLoggedIn()) return;
        if (!alerts.ask("Logout?")) return;

        common.getAuth().logout();
        common.setScreen("auth");
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
