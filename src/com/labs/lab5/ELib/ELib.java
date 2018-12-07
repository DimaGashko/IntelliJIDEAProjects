package com.labs.lab5.ELib;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;

public class ELib extends Application {
    private final static String iconSrc = "img/icon.jpg";
    private final static String title = "ELib - ";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
        primaryStage.setTitle(title);

        Image icon = new Image(getClass().getResource(iconSrc).toString());
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        var storage = new TextStorage<Integer>("data/test.txt", Integer.class);

        for (int i = 0; i < 20; i++) {
            storage.add(i);
        }

        System.out.println(Arrays.toString(storage.getArrOfData()));

        //launch(args);
    }
}