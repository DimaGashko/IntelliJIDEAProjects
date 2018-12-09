package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML private Label fxFilterPagesFrom;
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

    @FXML
    void fxOnActionFilterAuthor(ActionEvent event) {

    }

    @FXML
    void fxOnActionFilterDateFrom(ActionEvent event) {

    }

    @FXML
    void fxOnActionFilterDateTo(ActionEvent event) {

    }

    @FXML
    void fxOnActionFilterName(ActionEvent event) {

    }

    @FXML
    void fxOnActionFilterPublisher(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuAbout(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuAddBook(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuAppExit(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuEditBook(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuHelp(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuRemoveBooks(ActionEvent event) {

    }

    @FXML
    void fxOnActionMenuResetFilters(ActionEvent event) {

    }

    @FXML
    void fxOnActionResetFilters(ActionEvent event) {

    }

    @FXML
    void fxOnActionToolAdd(ActionEvent event) {

    }

    @FXML
    void fxOnActionToolEdit(ActionEvent event) {

    }

    @FXML
    void fxOnActionToolRemove(ActionEvent event) {

    }


    private void initStorage() {
        storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
    }
}
