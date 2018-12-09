package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.fxml.Initializable;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Index implements Initializable {
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Объект для хранения книг
    private IStorage<Book> storage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStorage();

        storage.add(new Book());

        System.out.println(Arrays.toString(storage.getArrOfData()));
    }

    private void initStorage() {
        storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
    }
}
