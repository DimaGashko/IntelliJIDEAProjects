package com.labs.lab4.view;

import com.labs.lab3.part2.Cleaner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
    private void onInputKeyPressed(Event event) {
        TextField input = (TextField)event.getTarget();
        double val = 0;

        try {
            val = Double.parseDouble(input.getText());
            input.getStyleClass().remove("f2__input-invalid");

        } catch (Exception err) {
            if ( !(err instanceof NumberFormatException) ) throw err;
            addClass(input, "f2__input-invalid");
            return;
        }

        if (input == aInput) a = val;
        else if (input == minXInput) minX = val;
        else if (input == maxXInput) maxX = val;
        else if (input == stepInput) step = val;

        update();
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