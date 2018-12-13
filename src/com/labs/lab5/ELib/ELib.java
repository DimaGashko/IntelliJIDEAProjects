package com.labs.lab5.ELib;

import com.labs.lab5.ELib.windows.IndexWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ELib extends Application {
    IndexWindow window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = new IndexWindow(primaryStage);
        window.getWindow().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
