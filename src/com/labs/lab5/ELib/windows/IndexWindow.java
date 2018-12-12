package com.labs.lab5.ELib.windows;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.Index;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;
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

    private Alerts alerts;

    private IndexWindow() throws IOException {
        super();
    }

    public IndexWindow(Stage window) throws IOException {
        super(window);
    }

    protected void load() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("../views/index.fxml"));
        loader.setControllerFactory(this::controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        controller = loader.getController();

        window.setScene(scene);

        window.setTitle("ELib - your world of books");

        Image icon = new Image(getClass().getResource("../img/icon.png").toString());
        window.getIcons().add(icon);


        window.setMaximized(true);
    }

    private Index controllerFactory(Class<?> type) {
        initStorage();

        Index index = new Index(storage);
        index.setOnExit(() -> window.close());

        return index;
    }

    private void initStorage() {
        if (storage != null) return;

        try {
            storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
            storage = null;

        } catch (IOException err) {
            alerts.show(alerts.getAlertErr(), "Can't load the data");

            var res = alerts.show(alerts.getAlertConfirm(), "Try again");

            if (alerts.getAnswer(res)) {
                initStorage();
            }
        }

    }

}
