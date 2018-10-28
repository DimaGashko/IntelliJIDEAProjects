package com.labs.lab4.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

public class Index {
    private double a = 2.3;
    private double minX = 0.1;
    private double maxX = 8.8;
    private double step = 0.001;

    @FXML
    private TextField maxXInput;

    @FXML
    private TextField aInput;

    @FXML
    private TextField minXInput;

    @FXML
    private TextField stepInput;

    @FXML
    private void onUpdate(Event event) {
        update();
    }

    @FXML
    void onAInputChange(KeyEvent event) {
        double val = 0;

        try {
            val = Double.parseDouble(aInput.getText());
            System.out.println(val);
            aInput.getStyleClass().remove("f2__input-invalid");

        } catch (Exception err) {
            addClass(aInput, "f2__input-invalid");

        }
    }

    @FXML
    void onMinXInputChange(KeyEvent event) {

    }

    @FXML
    void onMaxXInputChange(KeyEvent event) {

    }

    @FXML
    void onStepChange(KeyEvent event) {

    }

    @FXML
    void initialize() {
        aInput.setText(Double.toString(a));
        minXInput.setText(Double.toString(minX));
        maxXInput.setText(Double.toString(maxX));
        stepInput.setText(Double.toString(step));
    }

    private void update() {

    }

    private void addClass(Node node, String className) {
        if (!node.getStyleClass().contains(className)) {
            node.getStyleClass().add(className);
        }
    }
}