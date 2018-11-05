package com.labs.lab4.controllers;

import com.labs.lab2.F2;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

import java.util.Locale;

public class Index {
    public ScrollPane coordinatesRoot;
    private F2 f2 = new F2(0.1, 2.6, 0.01, 2.3);

    @FXML private TextField maxX;
    @FXML private TextField minX;
    @FXML private TextField step;
    @FXML private TextField a;

    @FXML private Text minY;
    @FXML private Text maxY;
    @FXML private Text sumOfElements;
    @FXML private Text average;
    @FXML public CheckBox coordinatesVisible;
    @FXML public Text coordinates;

    @FXML
    private void initialize() {
        initBinds();
        updateInputTexts();
        update();
    }

    @FXML
    private void onInputChange(Event event) {
        TextField input = (TextField) event.getTarget();
        double val = 0;

        try {
            val = Double.parseDouble(input.getText());
            input.getStyleClass().remove("f2__input-invalid");

        } catch (Exception err) {
            if (!(err instanceof NumberFormatException)) throw err;
            addClass(input, "f2__input-invalid");
            return;
        }

        if (input == a) f2.setA(val);
        else if (input == minX) f2.setMinX(val);
        else if (input == maxX) f2.setMaxX(val);
        else if (input == step) f2.setStep(val);

        update();
    }

    private void update() {
        f2.update();

        minY.setText( toText(f2.getMinY()) );
        maxY.setText( toText(f2.getMaxY()) );
        average.setText( toText( f2.getAverageY()) );
        sumOfElements.setText( toText(f2.getSumOfAllY()) );
    }

    private void updateInputTexts() {
        minX.setText( toText(f2.getMinX()) );
        minX.setText( toText(f2.getMinX()) );
        maxX.setText( toText(f2.getMaxX()) );
        step.setText( toText(f2.getStep()) );
        a.setText( toText(f2.getA()) );
    }

    private String toText(double val) {
        return String.format(Locale.ENGLISH, "%.3f", val);
    }

    private void initBinds() {
        //coordinatesRoot.visibleProperty().bind(coordinatesVisible.selectedProperty());
        coordinatesRoot.setVisible(false);
    }

    //Добавляет класс переданному элементу
    //Не добавляет класс, если элемент уже содержит этот класс
    private void addClass(Node node, String className) {
        if (node.getStyleClass().contains(className)) return;
        node.getStyleClass().add(className);
    }
}