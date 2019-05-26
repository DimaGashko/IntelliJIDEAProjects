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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrainingRememberComponent extends Component {

    @FXML private FlowPane fxInputsContainer;

    private OnDoneCallback onDoneCallback;

    private int dataCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    public void run(int dataCount, OnDoneCallback onDoneCallback) {
        this.onDoneCallback = onDoneCallback;
        this.dataCount = dataCount;

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

    private void done() {
        onDoneCallback.call();
    }

    private void initValidation() {

    }

    @FXML public void onDone() {
        done();
    }

    @FunctionalInterface
    public interface OnDoneCallback {
        void call();
    }
}