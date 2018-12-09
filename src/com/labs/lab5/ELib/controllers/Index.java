package com.labs.lab5.ELib.controllers;

import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.ValidationFacade;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.BookFilters;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.fxml.FXML;

public class Index implements Initializable {
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    private IStorage<Book> storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
    private BookFilters filters = new BookFilters();

    private double minPrice;
    private double maxPrice;
    private int minPages;
    private int maxPages;

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
        findFilterLimits();
        updateFilterLimits();
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
        System.out.println(fxFilterDateFrom.getValue().getClass());
    }

    @FXML private void fxOnFilterDateTo() {
        System.out.println("Filter DateTo");
    }

    @FXML private void fxOnResetFilters() {
        System.out.println("Reset Filters");
    }

    // Other methods

    /**
     * Вычисляет граници фильтров
     * (обновляет поля min/max price/pages и др)
     */
    private void findFilterLimits() {
        var books = storage.getArrOfData();

        minPages = _getSuitable(books, (next, min) -> (next.getPages() < min.getPages())).getPages();
        maxPages = _getSuitable(books, (next, max) -> (next.getPages() > max.getPages())).getPages();

        minPrice = _getSuitable(books, (next, min) -> (next.getPrice() < min.getPrice())).getPrice();
        maxPrice = _getSuitable(books, (next, max) -> (next.getPrice() > max.getPrice())).getPrice();

        System.out.println(minPages);
        System.out.println(maxPages);
        System.out.println(minPrice);
        System.out.println(maxPrice);
    }

    private void updateFilterLimits() {

    }

    /**
     * Возвращает наиболее подходящий элемент по переданным правилам сравнения
     * Можно использовать для нахождения минимального/максимального
     * Элемента массива по необходимому полю
     * @param items массив
     * @param compare функция сравнения
     * @param <T> тип обрабатываемых элементов
     * @return наиболее подходящий элемент
     */
    private <T> T _getSuitable(T[] items, BiFunction<T, T, Boolean> compare) {
        T res = items[0];

        for (int i = 1; i < items.length; i++) {
            if (compare.apply(items[i], res)) res = items[i];
        }

        return res;
    }

}
