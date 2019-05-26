package com.components.TrainingSetupComponent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lib.Alerts.Alerts;
import lib.Component.Component;
import lib.Validation.Validation;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainingSetupComponent extends Component {

    @FXML private JFXComboBox fxSelectTrainingType;
    @FXML private JFXTextField fxNumberOfData;

    private OnDoneCallback onDoneCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    public void run(OnDoneCallback onDoneCallback) {
        this.onDoneCallback = onDoneCallback;
    }

    private void done() {
        if (!isValid()) return;

        String trainingType = ((Label)fxSelectTrainingType.getValue()).getText();
        int dataCount = Integer.parseInt(fxNumberOfData.getText());

        if (dataCount < 1 || dataCount > 2000) {
            alerts.show(Alerts.alertErr, "Check your data");
            return;
        }

        onDoneCallback.call(trainingType, dataCount);
    }

    private void initValidation() {
        Validation.initValidation(fxSelectTrainingType);
        Validation.initValidation(fxNumberOfData);
    }

    private boolean isValid() {
        boolean type = fxSelectTrainingType.validate();
        boolean dataCount = fxNumberOfData.validate();

        return type && dataCount;
    }

    @FXML public void onDone() {
        done();
    }

    @FunctionalInterface
    public interface OnDoneCallback {
        void call(String trainingType, int dataCount);
    }
}