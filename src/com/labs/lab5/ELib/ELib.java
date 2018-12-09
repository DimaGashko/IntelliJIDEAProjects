package com.labs.lab5.ELib;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.Index;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ELib extends Application {
    private final static String defTitle = "ELib - the world of books";
    private final static int minWidth = 600;
    private final static int minHeight = 400;

    @Override
    public void start(Stage mainWindow) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("views/index.fxml"));
        Parent root = loader.load();

        mainWindow.setTitle(defTitle);

        Image icon = new Image(getClass().getResource("img/icon.png").toString());
        mainWindow.getIcons().add(icon);

        Scene scene = new Scene(root);
        mainWindow.setScene(scene);

        mainWindow.setMinWidth(minWidth);
        mainWindow.setMinHeight(minHeight);

        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
