package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Index implements Initializable {
    static final private String DB_URL = "com/labs/lab5/ELib/configs/books-db.txt";

    // Объект для хранения книг
    private IStorage<Book> storage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void initStorage() {
        storage = new TextStorage<>("", Book::toString, Book::parse, Book.class);
    }


    public IStorage<Book> getStorage() {
        return storage;
    }

    public void setStorage(IStorage<Book> storage) {
        this.storage = storage;
    }
}
