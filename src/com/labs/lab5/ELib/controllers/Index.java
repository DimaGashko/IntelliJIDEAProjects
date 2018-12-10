package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.BookFilters;
import com.labs.lab5.ELib.models.BookRow;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Index implements Initializable {
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    private IStorage<Book> storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);
    private BookFilters filters = new BookFilters();

    private SimpleDoubleProperty minPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty maxPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty minPages = new SimpleIntegerProperty();
    private SimpleIntegerProperty maxPages = new SimpleIntegerProperty();

    // Массив книг на экране
    private ObservableList<BookRow> filteredBooks = FXCollections.observableArrayList();

    private CreateBook controllerCreateBook;

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
    @FXML private JFXTreeTableView fxBooksTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        updateFilterLimits();
        initBinds();
        resetFilters();
        runFilter();
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
        showFxCreateBook();
    }

    @FXML private void fxOnToolEdit() {
        System.out.println("Tool Edit");
    }

    @FXML private void fxOnToolRemove() {
        System.out.println("Tool Remove");
    }

    // Fx Filters Events
    @FXML private void fxOnRunFilter() {
        runFilter();
    }

    @FXML private void fxOnResetFilters() {
        resetFilters();
        runFilter();
    }

    // Other methods

    private void runFilter() {
        updateFilters();
        updateFilteredBooks();
    }

    /**
     * Инициализирует окно создания новой книги
     * TODO: Exception
     */
    private void showFxCreateBook() {
        //if (controllerCreateBook == null) {
            loadFxCreateBook();
        //}
    }

    private void loadFxCreateBook() {
        try {
            var loader = new FXMLLoader(getClass().getResource("../views/createBook.fxml"));
            Parent root = loader.load();
            controllerCreateBook = loader.getController();

            Stage window =  new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            //controllerCreateBook.setWindow(window);

            Scene scene = new Scene(root);
            window.setScene(scene);

            window.show();

        } catch (IOException err) {
            System.out.println(err);

        }
    }

    private void initTable() {
        var thName = new JFXTreeTableColumn<BookRow, String>("Name");
        var thAuthor = new JFXTreeTableColumn<BookRow, String>("Author");
        var thPublisher = new JFXTreeTableColumn<BookRow, String>("Publisher");
        var thPrice = new JFXTreeTableColumn<BookRow, Double>("Price");
        var thPages = new JFXTreeTableColumn<BookRow, Integer>("Pages");
        var thYear = new JFXTreeTableColumn<BookRow, Integer>("Year");

        thName.setCellValueFactory(value -> value.getValue().getValue().nameProperty());
        thAuthor.setCellValueFactory(value -> value.getValue().getValue().authorProperty());
        thPublisher.setCellValueFactory(value -> value.getValue().getValue().publisherProperty());
        thPrice.setCellValueFactory(value -> value.getValue().getValue().priceProperty().asObject());
        thPages.setCellValueFactory(value -> value.getValue().getValue().pagesProperty().asObject());
        thYear.setCellValueFactory(value -> value.getValue().getValue().yearProperty().asObject());

        final TreeItem<BookRow> root = new RecursiveTreeItem<>(filteredBooks, RecursiveTreeObject::getChildren);

        fxBooksTable.setRoot(root);
        fxBooksTable.getColumns().setAll(thName, thAuthor, thPublisher, thPrice, thPages, thYear);
        fxBooksTable.setShowRoot(false);
    }

    private void updateFilteredBooks() {
        var books = storage.getArrOfData(book -> filters.check(book));

        filteredBooks.clear();

        for (Book book : books) {
            filteredBooks.add(new BookRow(book));
        }
    }

    private void initBinds() {
        bindFilterLimits();
    }

    private void updateFilters() {
        filters.setNameFilter(fxFilterName.getText());
        filters.setAuthorFilter(fxFilterAuthor.getText());
        filters.setPublisherFilter(fxFilterPublisher.getText());

        filters.setPriceFromFilter(fxFilterPriceFrom.getValue());
        filters.setPriceToFilter(fxFilterPriceTo.getValue());

        filters.setPagesFromFilter((int)fxFilterPagesFrom.getValue());
        filters.setPagesToFilter((int)fxFilterPagesTo.getValue());

        filters.setDateFromFilter(fxFilterDateFrom.getValue());
        filters.setDateToFilter(fxFilterDateTo.getValue());
    }

    /**
     * Сбрасывает фильтры
     * Первый вызов должен быть после первого вызова метода initBinds()
     */
    private void resetFilters() {
        filters.reset();

        fxFilterName.clear();
        fxFilterAuthor.clear();
        fxFilterPublisher.clear();

        fxFilterPriceFrom.setValue(minPrice.getValue());
        fxFilterPriceTo.setValue(maxPrice.getValue());

        fxFilterPagesFrom.setValue(minPages.getValue());
        fxFilterPagesTo.setValue(maxPages.getValue());

        fxFilterDateFrom.setValue(null);
        fxFilterDateTo.setValue(null);
    }

    /**
     * Устанавливает привязку полей фильтров к граничным значениям
     */
    private void bindFilterLimits() {
        fxFilterPriceFrom.minProperty().bind(minPrice);
        fxFilterPriceFrom.maxProperty().bind(maxPrice);

        fxFilterPriceTo.minProperty().bind(minPrice);
        fxFilterPriceTo.maxProperty().bind(maxPrice);

        fxFilterPagesFrom.minProperty().bind(minPages);
        fxFilterPagesFrom.maxProperty().bind(maxPages);

        fxFilterPagesTo.minProperty().bind(minPages);
        fxFilterPagesTo.maxProperty().bind(maxPages);
    }

    /**
     * Вычисляет граници фильтров
     * (обновляет поля min/max price/pages и др)
     */
    private void updateFilterLimits() {
        var books = storage.getArrOfData();

        minPages.set(_getSuitable(books, (next, min) -> next.getPages() < min.getPages()).getPages());
        maxPages.set(_getSuitable(books, (next, max) -> next.getPages() > max.getPages()).getPages());

        minPrice.set(_getSuitable(books, (next, min) -> next.getPrice() < min.getPrice()).getPrice());
        maxPrice.set(_getSuitable(books, (next, max) -> next.getPrice() > max.getPrice()).getPrice());
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
