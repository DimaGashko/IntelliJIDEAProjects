package com.labs.lab4;

import com.labs.lab2.F2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.stage.Stage;

import java.beans.Expression;

public class Main extends Application {
    private F2 f2 = new F2();
    private Stage window;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initView(primaryStage);

    }

    private void initView(Stage window) throws Exception {
        setWindow(window);
        window.setTitle("F2");

        Parent root = FXMLLoader.load(getClass().getResource("view/index.fxml"));
        scene = new Scene(root, 500, 350);

        window.setScene(scene);
        window.show();
    }

    private void setWindow(Stage window) {
        window = window;
    }
}
