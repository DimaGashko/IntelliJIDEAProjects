package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Index implements Initializable {
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Объект для хранения книг
    private IStorage<Book> storage;

    @FXML private MenuItem fxMenuAddBook;
    @FXML private MenuItem fxMenuResetFilters;
    @FXML private MenuItem fxMenuRemoveBooks;
    @FXML private MenuItem fxMenuAppExit;
    @FXML private MenuItem fxMenuEditBook;
    @FXML private MenuItem fxMenuRemoveBook;
    @FXML private MenuItem fxMenuHelp;
    @FXML private MenuItem fxMenuAbout;
    @FXML private JFXButton fxToolRemove;
    @FXML private JFXButton fxToolEdit;
    @FXML private JFXButton fxToolAdd;
    @FXML private JFXTextField fxFilterName;
    @FXML private JFXTextField fxFilterAuthor;
    @FXML private JFXTextField fxFilterPublisher;
    @FXML private JFXSlider fxFilterPriceFrom;
    @FXML private JFXSlider fxFilterPriceTo;
    @FXML private JFXSlider fxFilterPagesFrom;
    @FXML private JFXSlider fxFilterPagesTo;
    @FXML private JFXDatePicker fxFilterDateFrom;
    @FXML private JFXDatePicker fxFilterDateTo;
    @FXML private JFXButton fxResetFilters;
    @FXML private JFXTreeTableView<?> fxBooksTable;
    @FXML private Label fxNoBooks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStorage();
    }

    // Fx Menu Events
    @FXML private void fxOnMenuAbout() {
        System.out.println("Menu About");
    }

    @FXML private void fxOnMenuAddBook() {
        System.out.println("Menu Add");
    }

    @FXML private void fxOnMenuAppExit() {
        System.out.println("Menu Exit");
    }

    @FXML private void fxOnMenuEditBook() {
        System.out.println("Menu Edit");
    }

    @FXML private void fxOnMenuHelp() {
        System.out.println("Menu Help");
    }

    @FXML private void fxOnMenuRemoveBooks() {
        System.out.println("Menu Remove");
    }

    @FXML private void fxOnMenuResetFilters() {
        System.out.println("Menu Reset Filters");
    }

    // Fx Tools Events
    @FXML private void fxOnToolAdd() {
        System.out.println("Tool Add");
    }

    @FXML private void fxOnToolEdit() {
        System.out.println("Tool Edit");
    }

    @FXML private void fxOnToolRemove() {
        System.out.println("Tool Remove");
    }

    // Fx Filters Events
    @FXML private void fxOnFilterName() {
        System.out.println("Filter Name");
    }

    @FXML private void fxOnFilterAuthor() {
        System.out.println("Filter A");
    }

    @FXML private void fxOnFilterPublisher() {
        System.out.println("Filter Publisher");
    }

    @FXML private void fxOnPriceFrom() {
        System.out.println("Price From");
    }

    @FXML private void fxOnPriceTo() {
        System.out.println("Price To");
    }

    @FXML private void fxOnPagesFrom() {
        System.out.println("Pages From");
    }

    @FXML private void fxOnPagesTo() {
        System.out.println("Pages To");
    }

    @FXML private void fxOnFilterDateFrom() {
        System.out.println("Filter DateFrom");
    }

    @FXML private void fxOnFilterDateTo() {
        System.out.println("Filter DateTo");
    }

    @FXML private void fxOnResetFilters() {
        System.out.println("Reset Filters");
    }

    private void initStorage() {
        storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

    }
}
