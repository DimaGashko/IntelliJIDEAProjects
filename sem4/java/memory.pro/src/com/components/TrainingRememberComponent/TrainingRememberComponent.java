package com.components.TrainingRememberComponent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import lib.Alerts.Alerts;
import lib.Component.Component;
import lib.Validation.Validation;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TrainingRememberComponent extends Component {

    @FXML private FlowPane fxInputsContainer;

    private OnDoneCallback onDoneCallback;

    private LocalDateTime startTime;

    private int dataCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    public void run(int dataCount, OnDoneCallback onDoneCallback) {
        this.onDoneCallback = onDoneCallback;
        this.dataCount = dataCount;

        this.startTime = LocalDateTime.now();

        renderInputs();
    }

    private void renderInputs() {
        ArrayList<JFXTextField> inputs = new ArrayList<>(dataCount);

        for (int i = 0; i < dataCount; i++) {
            JFXTextField input = new JFXTextField();
            input.setPromptText(Integer.toString(i + 1));

            inputs.add(input);
        }

        fxInputsContainer.getChildren().clear();
        fxInputsContainer.getChildren().addAll(inputs);
    }

    private ArrayList<String> getAnswers() {
        return this.fxInputsContainer.getChildren().stream()
                .map(input -> ((JFXTextField)input).getText())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void done() {
        var answers = getAnswers();
        int time = getTimeToRemember();

        onDoneCallback.call(time, answers);
    }

    private int getTimeToRemember() {
        LocalDateTime endTime = LocalDateTime.now();

        return (int)SECONDS.between(startTime, endTime);
    }

    private void initValidation() {

    }

    @FXML public void onDone() {
        done();
    }

    @FunctionalInterface
    public interface OnDoneCallback {
        void call(int timeToRemember, ArrayList<String> answers);
    }
}