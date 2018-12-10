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

/**
 * Базовый класс окна
 * @param <T> Тип конструктора
 */
abstract public class BaseWindow<T> {
    final static private String ICON_URL = "../img/icon.png";
    final static private String FXML_URL = "";

    final static private int MIN_WIDTH = 600;
    final static private int MIN_HEIGHT = 400;

    protected Stage window;
    protected T controller;

    public BaseWindow() throws IOException {
        init();
    }

    public Stage getWindow() {
        return window;
    }

    public T getController() {
        return controller;
    }

    protected void init() throws IOException {
        load();
        initEvents();
    }

    protected void load() throws IOException {
        System.out.println(FXML_URL);
        var loader = new FXMLLoader(getClass().getResource(FXML_URL));

        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root);

        window = new Stage();

        Image icon = new Image(getClass().getResource(ICON_URL).toString());
        window.getIcons().add(icon);

        window.setScene(scene);

        window.setMinWidth(MIN_WIDTH);
        window.setMinHeight(MIN_HEIGHT);
    }

    protected void initEvents() {

    }
}
