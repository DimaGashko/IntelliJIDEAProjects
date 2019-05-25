package com.screens.TrainingScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lib.Alerts.Alerts;
import lib.Screen.Screen;
import lib.Validation.Validation;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainingScreen extends Screen {

    public JFXComboBox fxSelectTrainingType;
    public JFXTextField fxNumberOfData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    private void initValidation() {
        Validation.initValidation(fxSelectTrainingType);
        Validation.initValidation(fxNumberOfData);
    }

    private void startTraining() {
        if (!isValid()) return;

        String trainingType = ((Label)fxSelectTrainingType.getValue()).getText();
        int dataCount = Integer.parseInt(fxNumberOfData.getText());

        if (dataCount < 1 || dataCount > 10000) {
            alerts.show(Alerts.alertErr, "Check your data");
            return;
        }

        System.out.println(trainingType + " | " + dataCount);
    }

    private boolean isValid() {
        boolean type = fxSelectTrainingType.validate();
        boolean dataCount = fxNumberOfData.validate();

        return type && dataCount;
    }

    @Override
    public void showed() {

    }

    public void onStart() {
        startTraining();
    }
}