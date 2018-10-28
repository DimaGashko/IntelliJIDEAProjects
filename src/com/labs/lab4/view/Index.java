package com.labs.lab4.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

public class Index {
    @FXML
    private TextField maxXInput;

    @FXML
    private TextField aInput;

    @FXML
    private TextField minXInput;

    @FXML
    private TextField stepInput;

    @FXML
    void onAInputChange(KeyEvent event) {
        var text = aInput.getText();
        var cleanedText = text.replaceAll("[^\\d\\.]+", "");

        var val = Double.parseDouble(aInput.getText());
        System.out.println(val);
    }

    @FXML
    void onMinXInputChange(KeyEvent event) {
        TextFormatter
    }

    @FXML
    void onMaxXInputChange(KeyEvent event) {

    }

    @FXML
    void onStepChange(KeyEvent event) {

    }

}