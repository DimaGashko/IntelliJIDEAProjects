package com.screens.ProfileScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lib.Alerts.Alerts;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileScreen extends Screen {

    @FXML private Label fxDataSideTitle;
    @FXML private Label fxInfoSideFullName;
    @FXML private Label fxInfoSideUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateUserData();
        openNumberStatistics();
    }

    @Override
    public void showed() {

    }

    private void updateUserData() {
        var userOpt = getUser();

        if (userOpt.isEmpty()) {
            common.setScreen("auth");
            return;
        }

        User user = userOpt.get();

        fxInfoSideFullName.setText(user.getFullName());
        fxInfoSideUsername.setText(user.getUsername());
    }

    protected void logout() {
        if (!common.getAuthService().isLoggedIn()) return;
        if (!alerts.ask("Logout?")) return;

        common.getAuthService().logout();
        common.setScreen("auth");
    }

    private void openEditProfile() {
        alerts.show(Alerts.alertInfo, "Edit profile", "This Feature Is Coming Soon (or not)", "");
    }

    private void openNumberStatistics() {
        fxDataSideTitle.setText("Statistics (numbers)");
    }

    private void openWordsStatistic() {
        fxDataSideTitle.setText("Statistics (words)");
    }

    private void goToTraining() {
        common.setScreen("training");
    }

    @FXML public void onEditProfile() {
        openEditProfile();
    }
    @FXML public void onNumberStatisticsOpen() {
        openNumberStatistics();
    }
    @FXML public void onWordsStatisticsOpen() {
        openWordsStatistic();
    }
    @FXML public void onGoToTraining() {
        goToTraining();
    }
    @FXML public void onLogout() {
        logout();
    }

}
