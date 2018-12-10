package com.labs.lab5.ELib.windows;

import com.labs.lab5.ELib.controllers.CreateBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WindowCreateBook {
    private Stage window;
    private CreateBook controller;

    public Stage getWindow() throws Exception  {
        if (window == null) init();
        return window;
    }

    public CreateBook getController() throws Exception {
        if (window == null) init();
        return controller;
    }

    private void init() throws Exception {
        var loader = new FXMLLoader(getClass().getResource("../views/createBook.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        window = new Stage();
        window.setScene(scene);

        window.setMinWidth(500);
        window.setMinHeight(620);
    }
}
