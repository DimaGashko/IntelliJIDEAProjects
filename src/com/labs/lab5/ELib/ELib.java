package com.labs.lab5.ELib;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.windows.BaseWindow;
import com.labs.lab5.ELib.windows.IndexWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ELib extends Application {
    IndexWindow window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = new IndexWindow(primaryStage);
        window.getWindow().show();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> read;

        try(FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            //read = (ArrayList<Book>)ois.readObject();
            //System.out.println(read);
        }

        //launch(args);
    }
}
