package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

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
    @FXML private void fxOnActionMenuAbout(ActionEvent event) {
        System.out.println("Menu About");
    }

    @FXML private void fxOnActionMenuAddBook(ActionEvent event) {
        System.out.println("Menu Add");
    }

    @FXML private void fxOnActionMenuAppExit(ActionEvent event) {
        System.out.println("Menu Exit");
    }

    @FXML private void fxOnActionMenuEditBook(ActionEvent event) {
        System.out.println("Menu Edit");
    }

    @FXML private void fxOnActionMenuHelp(ActionEvent event) {
        System.out.println("Menu Help");
    }

    @FXML private void fxOnActionMenuRemoveBooks(ActionEvent event) {
        System.out.println("Menu Remove");
    }

    @FXML private void fxOnActionMenuResetFilters(ActionEvent event) {
        System.out.println("Menu Reset Filters");
    }

    // Fx Tools Events
    @FXML private void fxOnActionToolAdd(ActionEvent event) {
        System.out.println("Tool Add");
    }

    @FXML private void fxOnActionToolEdit(ActionEvent event) {
        System.out.println("Tool Edit");
    }

    @FXML private void fxOnActionToolRemove(ActionEvent event) {
        System.out.println("Tool Remove");
    }

    // Fx Filters Events
    @FXML private void fxOnActionFilterName(ActionEvent event) {
        System.out.println("Filter Name");
    }

    @FXML public void fxOnKeyReleasedFilterName(KeyEvent keyEvent) {
        System.out.println("DateName key");
    }

    @FXML private void fxOnActionFilterAuthor(ActionEvent event) {
        System.out.println("Filter A");
    }

    @FXML public void fxOnKeyReleasedFilterAuthor(KeyEvent keyEvent) {
        System.out.println("Author key");
    }

    @FXML private void fxOnActionFilterPublisher(ActionEvent event) {
        System.out.println("Filter Publisher");
    }

    @FXML public void fxOnKeyReleasedFilterPublisher(KeyEvent keyEvent) {
        System.out.println("Publisher key");
    }

    @FXML private void fxOnActionFilterDateFrom(ActionEvent event) {
        System.out.println("Filter DateFrom");
    }

    @FXML public void fxOnKeyReleasedFilterDateFrom(KeyEvent keyEvent) {
        System.out.println("DateFrom key");
    }

    @FXML private void fxOnActionFilterDateTo(ActionEvent event) {
        System.out.println("Filter DateTo");
    }

    @FXML public void fxOnKeyReleasedFilterDateTo(KeyEvent keyEvent) {
        System.out.println("DateTo key");
    }

    @FXML private void fxOnActionResetFilters(ActionEvent event) {
        System.out.println("Reset Filters");
    }

    private void initStorage() {
        storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

        fxFilterPriceFrom
    }
}
