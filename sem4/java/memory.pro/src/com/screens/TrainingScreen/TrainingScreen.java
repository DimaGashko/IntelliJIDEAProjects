package com.screens.TrainingScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import lib.Screen.Screen;
import lib.Validation.Validation;

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
        System.out.println("Is Starting training");
    }

    @Override
    public void showed() {

    }

    public void onStart() {
        startTraining();
    }
}