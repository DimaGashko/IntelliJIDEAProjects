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
    protected Stage window;
    protected T controller;

    BaseWindow() throws IOException {
        init();
    }

    public Stage getWindow() {
        return window;
    }

    public T getController() {
        return controller;
    }

    private void init() throws IOException {
        load();
        initEvents();
    }

    abstract protected void load() throws IOException;

    protected void initEvents() {

    }


}
