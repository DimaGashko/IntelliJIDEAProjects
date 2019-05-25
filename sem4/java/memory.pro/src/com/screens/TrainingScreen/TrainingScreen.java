package com.screens.TrainingScreen;

import com.components.MemorizeComponent.MemorizeComponent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import lib.Alerts.Alerts;
import lib.Component.ComponentException;
import lib.Screen.Screen;
import lib.Validation.Validation;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TrainingScreen extends Screen {

    @FXML private VBox fxSetupRoot;
    @FXML private VBox fxMemorizeRoot;

    @FXML private JFXComboBox fxSelectTrainingType;
    @FXML private JFXTextField fxNumberOfData;

    boolean isMemorizeInited = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    @Override
    public void showed() {

    }

    private void initValidation() {
        Validation.initValidation(fxSelectTrainingType);
        Validation.initValidation(fxNumberOfData);
    }

    private void startTraining() {
        if (!isValid()) return;

        if (isMemorizeInited) {
            var ans = alerts.ask("You're already training. Do you want restart it?");
            if (!ans) return;
        }

        String trainingType = ((Label)fxSelectTrainingType.getValue()).getText();
        int dataCount = Integer.parseInt(fxNumberOfData.getText());

        if (dataCount < 1 || dataCount > 10000) {
            alerts.show(Alerts.alertErr, "Check your data");
            return;
        }

        runMemorizeComponent(trainingType, dataCount);
        isMemorizeInited = true;
    }

    private void runMemorizeComponent(String trainingType, int dataCount) {
        MemorizeComponent component;
        Parent root;

        try {
            var res = loadComponent("memorize");
            root = res.getKey();
            component = (MemorizeComponent) res.getValue();
        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cant'l load Memorize Component");
            e.printStackTrace();
            return;
        }

        fxMemorizeRoot.getChildren().clear();
        fxMemorizeRoot.getChildren().add(root);

        component.run(trainingType, dataCount);
    }

    private boolean isValid() {
        boolean type = fxSelectTrainingType.validate();
        boolean dataCount = fxNumberOfData.validate();

        return type && dataCount;
    }

    public void onStart() {
        startTraining();
    }
}