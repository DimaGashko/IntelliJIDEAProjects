package com.components.HeaderComponent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lib.Alerts.Alerts;
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

    private void openHelp() {
        String title = "Memory.pro - you can memorize everything!";

        String content = "Contacts: dimagashko@gmail.com\n" +
                "\n" +
                "You can fork this project:\n" +
                "https://github.com/DimaGashko/memory-pro\n" +
                "\n" +
                "Copyright \u00a9 2019 Dmitry Gashko\n";

        alerts.show(Alerts.alertInfo, "Memory.pro", title, content);
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
    @FXML void onHelp() {
        openHelp();
    }
}
