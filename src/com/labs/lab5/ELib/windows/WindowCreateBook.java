package com.labs.lab5.ELib.windows;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.CreateBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCreateBook {
    private Stage window;
    private CreateBook controller;

    public WindowCreateBook() throws IOException {
        init();
    }

    public Stage getWindow() {
        return window;
    }

    public CreateBook getController() {
        return controller;
    }

    public void showLikeAdd() {
        setTitle("Add New Book");
        window.show();
    }

    public void showLikeEdit(Book book) {
        setTitle("Edit the Book");
        controller.setValueBy(book);
        window.show();
    }

    public void setTitle(String title) {
        controller.setTitle(title);
        window.setTitle(title);
    }

    private void init() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("../views/createBook.fxml"));

        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root);

        window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);

        Image icon = new Image(getClass().getResource("../img/icon.png").toString());
        window.getIcons().add(icon);

        window.setScene(scene);

        window.setMinWidth(500);
        window.setMinHeight(650);
    }
}
