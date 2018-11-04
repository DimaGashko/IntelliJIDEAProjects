package com.labs.lab4.view;

import com.labs.lab2.F2;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class Index {
    private F2 f2 = new F2(0.1, 2.6, 0.01, 2.3);

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
        updateInputTexts();
        update();
    }

    @FXML
    private void onInputChange(Event event) {
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

        if (input == a) f2.setA(val);
        else if (input == minX) f2.setMinX(val);
        else if (input == maxX) f2.setMaxX(val);
        else if (input == step) f2.setStep(val);

        updateInputTexts();
        update();
    }

    private void update() {
        f2.update();

        minY.setText( Double.toString(f2.getMinY()) );
        maxY.setText( Double.toString(f2.getMaxY()) );
        average.setText( Double.toString( f2.getAverageY()) );
        sumOfElements.setText( Double.toString(f2.getSumOfAllY()) );
    }

    private void updateInputTexts() {
        minX.setText( Double.toString(f2.getMinX()) );
        maxX.setText( Double.toString(f2.getMaxX()) );
        step.setText( Double.toString(f2.getStep()) );
        a.setText( Double.toString(f2.getA()) );
    }

    //Добавляет класс переданному элементу
    //Не добавляет класс, если элемент уже содержит этот класс
    private void addClass(Node node, String className) {
        if (node.getStyleClass().contains(className)) return;
        node.getStyleClass().add(className);
    }
}