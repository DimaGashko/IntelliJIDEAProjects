package com.labs.lab5.ELib.windows;

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

    protected void init() throws IOException {
        load();
    }

    abstract protected void load() throws IOException;
}
