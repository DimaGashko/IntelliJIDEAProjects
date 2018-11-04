package com.labs.lab4.view;

import com.labs.lab2.F2;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Index {
    private F2 f2 = new F2();

    private double _a = 2.3;
    private double _minX = 0.1;
    private double _maxX = 8.8;
    private double _step = 0.001;

    @FXML private TextField maxX;
    @FXML private TextField minX;
    @FXML private TextField step;
    @FXML private TextField a;

    @FXML private Text minY;
    @FXML private Text maxY;
    @FXML private Text sumOfElements;
    @FXML private Text average;
    @FXML private Button showCoordinates;

    @FXML
    private void initialize() {
        update();
    }

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

        if (input == a) _a = val;
        else if (input == minX) _minX = val;
        else if (input == maxX) _maxX = val;
        else if (input == step) _step = val;

        update();
    }

    private void update() {
        var allY = f2.getAllY(f2.getAllX(_minX, _maxX, _step), _a);

        minY.setText(Double.toString(allY[f2.getIndexOfMin(allY)]));
        maxY.setText(Double.toString(allY[f2.getIndexOfMax(allY)]));
        sumOfElements.setText(Double.toString(f2.getSumOfElements(allY)));
        average.setText(Double.toString(f2.getAverage(allY)));
    }

    //Добавляет класс переданному элементу
    //Не добавляет класс, если элемент уже содержит этот класс
    private void addClass(Node node, String className) {
        if (node.getStyleClass().contains(className)) return;
        node.getStyleClass().add(className);
    }
}