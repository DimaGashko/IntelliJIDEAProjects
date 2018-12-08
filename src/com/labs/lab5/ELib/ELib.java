package com.labs.lab5.ELib;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        var storage = new TextStorage<Book>(
                "data/test.txt",
                Book::toString,
                Book::parse,
                Book.class);

        /*
        storage.add(new Book("JavaScript: The Definitive Guide", "David Flanagan", "O'Reilly", 2006, 999, 50));
        storage.add(new Book("Java Programming", "Donald Bales", "O'Reilly", 2001, 450, 60));
        storage.add(new Book("Thinking in Java", "Bruce Eckel", "Oracle", 2002, 328, 50));
        storage.add(new Book("JS.Next", "Aaron Frost", "O'Reilly", 2015, 250, 50));
        storage.add(new Book("You don't know JS", "Simpson K.", "O'Reilly", 2015, 88, 30));
        storage.add(new Book("Angular", "David Flanagan", "O'Reilly", 2016, 396, 30));
        storage.add(new Book("Java in a Nutshell", "David Flanagan", "O'Reilly", 2014, 396, 60));
        */

        System.out.println(Arrays.toString(storage.getArrOfData()));

        storage.remove(item -> item.getYear() > 2005);

        System.out.println(Arrays.toString(storage.getArrOfData()));

        //launch(args);
    }
}
