package com.screens.TrainingScreen;

import com.components.TrainingMemorizeComponent.TrainingMemorizeComponent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingService;
import com.services.TrainingService.WordsTrainingService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lib.Alerts.Alerts;
import lib.Component.ComponentException;
import lib.Screen.Screen;
import lib.Validation.Validation;
import schemas.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrainingScreen extends Screen {

    @FXML private BorderPane fxMemorizeRoot;

    @FXML private JFXComboBox fxSelectTrainingType;
    @FXML private JFXTextField fxNumberOfData;

    private TrainingService trainingService;
    private ArrayList<String> trainingData;
    private String trainingType;
    private int dataCount;

    private boolean isTrainingInit = false;

    private ArrayList<Integer> timesToMemorize;

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

    private void runTraining() {
        if (!isValid()) return;

        if (isTrainingInit) {
            var ans = alerts.ask("You're already training. Do you want restart it?");
            if (!ans) return;
        }

        isTrainingInit = true;

        trainingType = ((Label)fxSelectTrainingType.getValue()).getText();
        dataCount = Integer.parseInt(fxNumberOfData.getText());

        if (dataCount < 1 || dataCount > 10000) {
            alerts.show(Alerts.alertErr, "Check your data");
            return;
        }

        initTrainingServices();
        trainingData = trainingService.start();

        runMemorize();
    }

    private void runMemorize() {
        TrainingMemorizeComponent component;
        Parent root;

        try {
            var res = loadComponent("memorize");
            root = res.getKey();
            component = (TrainingMemorizeComponent) res.getValue();
        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cant'l load Memorize Component");
            e.printStackTrace();
            return;
        }

        fxMemorizeRoot.getChildren().clear();
        fxMemorizeRoot.setCenter(root);

        component.run(trainingData, trainingType, (timesToMemorize) -> {
           this.timesToMemorize = timesToMemorize;

           runRemember();
        });
    }

    private void runRemember() {
        System.out.println(timesToMemorize);
    }

    private void initTrainingServices() {
        User user = getUser();

        if (user == null) {
            alerts.show(Alerts.alertErr, "Internal Error");
            return;
        }

        var em = common.getEm();

        if (trainingType.equals("Words")) {
            trainingService = new WordsTrainingService(user, em);

        } else if (trainingType.equals("Numbers")) {
            trainingService = new NumberTrainingService(user, em);

        } else {
            alerts.show(Alerts.alertErr, "Wrong Training Type");
            return;
        }

        trainingService.setUp(dataCount);
    }

    private boolean isValid() {
        boolean type = fxSelectTrainingType.validate();
        boolean dataCount = fxNumberOfData.validate();

        return type && dataCount;
    }

    public void onStart() {
        runTraining();
    }
}