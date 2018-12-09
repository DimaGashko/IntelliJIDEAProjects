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

    // Fx Menu Events
    @FXML void fxOnActionMenuAbout(ActionEvent event) {
        System.out.println("Menu About");
    }

    @FXML void fxOnActionMenuAddBook(ActionEvent event) {
        System.out.println("Menu Add");
    }

    @FXML void fxOnActionMenuAppExit(ActionEvent event) {
        System.out.println("Menu Exit");
    }

    @FXML void fxOnActionMenuEditBook(ActionEvent event) {
        System.out.println("Menu Edit");
    }

    @FXML  void fxOnActionMenuHelp(ActionEvent event) {
        System.out.println("Menu Help");
    }

    @FXML void fxOnActionMenuRemoveBooks(ActionEvent event) {
        System.out.println("Menu Remove");
    }

    @FXML void fxOnActionMenuResetFilters(ActionEvent event) {
        System.out.println("Menu Reset Filters");
    }

    // Fx Tools Events
    @FXML void fxOnActionToolAdd(ActionEvent event) {
        System.out.println("Tool Add");
    }

    @FXML void fxOnActionToolEdit(ActionEvent event) {
        System.out.println("Tool Edit");
    }

    @FXML void fxOnActionToolRemove(ActionEvent event) {
        System.out.println("Tool Remove");
    }

    // Fx Filters Events
    @FXML void fxOnActionFilterName(ActionEvent event) {
        System.out.println("Filter Name");
    }

    @FXML void fxOnActionFilterAuthor(ActionEvent event) {
        System.out.println("Filter A");
    }

    @FXML void fxOnActionFilterPublisher(ActionEvent event) {
        System.out.println("Filter Publisher");
    }

    @FXML void fxOnActionFilterDateFrom(ActionEvent event) {
        System.out.println("Filter DateFrom");
    }

    @FXML void fxOnActionFilterDateTo(ActionEvent event) {
        System.out.println("Filter DateTo");
    }

    @FXML void fxOnActionResetFilters(ActionEvent event) {
        System.out.println("Reset Filters");
    }

    private void initStorage() {
        storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
    }
}
