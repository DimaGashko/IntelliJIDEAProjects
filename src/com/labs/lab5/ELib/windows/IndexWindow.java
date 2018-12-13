package com.labs.lab5.ELib.windows;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.Index;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexWindow extends BaseWindow<Index> {

    // Путь к файлу в котором хранятся книги
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Хранилице книг - содежит все книги
    private IStorage<Book> storage;

    private Alerts alerts = new Alerts();

    private IndexWindow() throws IOException {

    }

    public IndexWindow(Stage window) throws IOException {
        super(window);
    }

    protected void load() throws IOException {
        initStorage();

        var loader = new FXMLLoader(getClass().getResource("../views/index.fxml"));
        loader.setControllerFactory(this::controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        controller = loader.getController();

        window.setScene(scene);

        window.setTitle("ELib - your world of books");

        Image icon = new Image(getClass().getResource("../img/icon.png").toString());
        window.getIcons().add(icon);

        window.setMinWidth(700);
        window.setMinHeight(400);

        window.setMaximized(true);
    }

    private Index controllerFactory(Class<?> type) {
        Index index = new Index(storage);
        index.setOnExit(this::onExit);

        return index;
    }

    private void onExit() {
        window.close();
        Platform.exit();
        System.exit(0);
    }

    private void initStorage() {
        if (storage != null) return;

        try {
            storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

        } catch (IOException err) {
            //Обработка ошибки происходит в Index.initialize

        }

    }

}
