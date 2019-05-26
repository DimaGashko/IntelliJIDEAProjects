package com.screens.TrainingScreen;

import com.components.TrainingMemorizeComponent.TrainingMemorizeComponent;
import com.components.TrainingRememberComponent.TrainingRememberComponent;
import com.components.TrainingSetupComponent.TrainingSetupComponent;
import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingService;
import com.services.TrainingService.WordsTrainingService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import lib.Alerts.Alerts;
import lib.Component.ComponentException;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrainingScreen extends Screen {

    @FXML private BorderPane fxLeftContainer;
    @FXML private BorderPane fxCenterContainer;

    private Parent setupComponentRoot;
    private Parent memorizeComponentRoot;
    private Parent rememberComponentRoot;

    private TrainingSetupComponent setupComponent;
    private TrainingMemorizeComponent memorizeComponent;
    private TrainingRememberComponent rememberComponent;

    private TrainingService trainingService;
    private ArrayList<String> trainingData;
    private String trainingType;
    private int dataCount;

    private boolean isTrainingInit = false;

    private ArrayList<Integer> timesToMemorize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChildComponents();
    }

    @Override
    public void showed() {
        runSetup();
    }

    private void runSetup() {
        fxLeftContainer.getChildren().clear();
        fxLeftContainer.setCenter(setupComponentRoot);

        setupComponent.run((trainingType, dataCount) -> {
            this.trainingType = trainingType;
            this.dataCount = dataCount;

            if (isTrainingInit) {
                var ans = alerts.ask("You're already training. Do you want restart it?");
                if (!ans) return;
            }

            isTrainingInit = true;

            //runMemorize();
            runRemember();
        });
    }

    private void runMemorize() {
        initTrainingService();

        trainingService.setUp(dataCount);
        trainingData = trainingService.start();

        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(memorizeComponentRoot);

        memorizeComponent.run(trainingData, trainingType, (timesToMemorize) -> {
           this.timesToMemorize = timesToMemorize;

           runRemember();
        });
    }

    private void runRemember() {
        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(rememberComponentRoot);

        rememberComponent.run(dataCount, (answers) -> {
            System.out.println(answers);
        });
    }

    private void initTrainingService() {
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
            System.exit(1);
        }

    }

    private void initChildComponents() {
        try {
            var setupPair = loadComponent("setup");
            var memorizePair = loadComponent("memorize");
            var rememberPair = loadComponent("remember");

            setupComponentRoot = setupPair.getKey();
            memorizeComponentRoot = memorizePair.getKey();
            rememberComponentRoot = rememberPair.getKey();

            setupComponent = (TrainingSetupComponent) setupPair.getValue();
            memorizeComponent = (TrainingMemorizeComponent) memorizePair.getValue();
            rememberComponent = (TrainingRememberComponent) rememberPair.getValue();

        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cant'l load Training Components");
            e.printStackTrace();
        }
    }

}