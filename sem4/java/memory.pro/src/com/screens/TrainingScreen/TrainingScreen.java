package com.screens.TrainingScreen;

import com.components.ResultComponent.ResultComponent;
import com.components.TrainingMemorizeComponent.TrainingMemorizeComponent;
import com.components.TrainingRememberComponent.TrainingRememberComponent;
import com.components.TrainingSetupComponent.TrainingSetupComponent;
import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingResult;
import com.services.TrainingService.TrainingService;
import com.services.TrainingService.WordsTrainingService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

    private TrainingSetupComponent setupComponent;
    private TrainingMemorizeComponent memorizeComponent;
    private TrainingRememberComponent rememberComponent;
    private ResultComponent resultComponent;

    private Parent setupComponentRoot;
    private Parent memorizeComponentRoot;
    private Parent rememberComponentRoot;
    private Parent resultComponentRoot;

    private TrainingService trainingService;
    private ArrayList<String> trainingData;

    private boolean isTrainingInit = false;

    private ArrayList<Integer> timesToMemorize;
    private ArrayList<String> answers;
    private int timeToRemember;
    private String trainingType;
    private int dataCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChildComponents();
    }

    @Override
    public void showed() {
        //runSetup();
        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(resultComponentRoot);

        resultComponent.run("Numbers", 1);
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

            runMemorize();
        });
    }

    private void runMemorize() {
        initTrainingService();

        trainingService.setUp(dataCount);
        trainingData = trainingService.start();

        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(memorizeComponentRoot);

        System.out.println(trainingData);

        memorizeComponent.run(trainingData, trainingType, (timesToMemorize) -> {
           this.timesToMemorize = timesToMemorize;

           runRemember();
        });
    }

    private void runRemember() {
        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(rememberComponentRoot);

        rememberComponent.run(dataCount, (timeToRemember, answers) -> {
            this.timeToRemember = timeToRemember;
            this.answers = answers;

            finishTraining();
        });
    }

    private void finishTraining() {
        TrainingResult result = getResult();
        int resultId = trainingService.finish(result);

        fxCenterContainer.getChildren().clear();
        fxCenterContainer.setCenter(resultComponentRoot);

        resultComponent.run(trainingType, resultId);
    }

    private TrainingResult getResult() {
        TrainingResult result = new TrainingResult();

        result.setTrainingType(trainingType);
        result.setDataCount(dataCount);
        result.setTimesToMemorize(timesToMemorize);
        result.setTimeToRemember(timeToRemember);
        result.setAnswers(answers);

        return result;
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
            var resultPair = loadComponent("result");

            setupComponent = (TrainingSetupComponent) setupPair.getValue();
            memorizeComponent = (TrainingMemorizeComponent) memorizePair.getValue();
            rememberComponent = (TrainingRememberComponent) rememberPair.getValue();
            resultComponent = (ResultComponent) resultPair.getValue();

            setupComponentRoot = setupPair.getKey();
            memorizeComponentRoot = memorizePair.getKey();
            rememberComponentRoot = rememberPair.getKey();
            resultComponentRoot = resultPair.getKey();

        } catch (ComponentException e) {
            alerts.show(Alerts.alertErr, "Cant'l load Training Components");
            e.printStackTrace();
        }
    }

}