package com.screens.TrainingScreen;

import com.components.TrainingMemorizeComponent.TrainingMemorizeComponent;
import com.components.TrainingSetupComponent.TrainingSetupComponent;
import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingService;
import com.services.TrainingService.WordsTrainingService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lib.Alerts.Alerts;
import lib.Component.ComponentException;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrainingScreen extends Screen {

    @FXML private VBox fxSetupRoot;
    @FXML private BorderPane fxMemorizeRoot;

    private TrainingService trainingService;
    private ArrayList<String> trainingData;
    private String trainingType;
    private int dataCount;

    private boolean isTrainingInit = false;

    private ArrayList<Integer> timesToMemorize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSetup();
    }

    @Override
    public void showed() {

    }

    private void initSetup() {
        TrainingSetupComponent setupComponent;
        Parent setupRoot;

        try {
            var res = loadComponent("setup");
            setupRoot = res.getKey();
            setupComponent = (TrainingSetupComponent) res.getValue();
        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cant'l load Training Setup Component");
            e.printStackTrace();
            return;
        }

        fxSetupRoot.getChildren().clear();
        fxSetupRoot.getChildren().add(setupRoot);

        setupComponent.run((trainingType, dataCount) -> {
            if (isTrainingInit) {
                var ans = alerts.ask("You're already training. Do you want restart it?");
                if (!ans) return;
            }

            isTrainingInit = true;

            this.trainingType = trainingType;
            this.dataCount = dataCount;

            runMemorize();
        });

        runMemorize();
    }

    private void runMemorize() {
        initTrainingServices();
        trainingData = trainingService.start();

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

}