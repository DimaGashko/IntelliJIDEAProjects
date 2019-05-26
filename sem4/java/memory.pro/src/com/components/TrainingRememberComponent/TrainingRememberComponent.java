package com.components.TrainingRememberComponent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lib.Alerts.Alerts;
import lib.Component.Component;
import lib.Validation.Validation;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainingRememberComponent extends Component {

    private OnDoneCallback onDoneCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initValidation();
    }

    public void run(OnDoneCallback onDoneCallback) {
        this.onDoneCallback = onDoneCallback;
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