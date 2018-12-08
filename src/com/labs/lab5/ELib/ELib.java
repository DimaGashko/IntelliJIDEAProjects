package com.labs.lab5.ELib;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ELib extends Application {
    private final static String title = "ELib - the world of books";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
        primaryStage.setTitle(title);

        Image icon = new Image(getClass().getResource("img/icon.png").toString());
        primaryStage.getIcons().add(icon);

        JFXDecorator decorator = new JFXDecorator(primaryStage, root);
        Scene scene = new Scene(decorator, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
