package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.BookFilters;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.windows.WindowCreateBook;
import com.labs.lab5.ELib.models.storage.TextStorage;

import java.net.URL;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

import com.jfoenix.controls.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;

public class Index implements Initializable {
    @FXML private JFXTextField fxFilterName;
    @FXML private JFXTextField fxFilterAuthor;
    @FXML private JFXTextField fxFilterPublisher;
    @FXML private JFXSlider fxFilterPriceFrom;
    @FXML private JFXSlider fxFilterPriceTo;
    @FXML private JFXSlider fxFilterPagesFrom;
    @FXML private JFXSlider fxFilterPagesTo;
    @FXML private JFXDatePicker fxFilterDateFrom;
    @FXML private JFXDatePicker fxFilterDateTo;
    @FXML private TableView fxBooksTable;
    @FXML private TableColumn fxBooksTableColumnName;
    @FXML private TableColumn fxBooksTableColumnAuthor;
    @FXML private TableColumn fxBooksTableColumnPublisher;
    @FXML private TableColumn fxBooksTableColumnPrice;
    @FXML private TableColumn fxBooksTableColumnPages;
    @FXML private TableColumn fxBooksTableColumnDate;

    // Menu Events
    @FXML private void fxOnMenuAbout() { onAbout(); }
    @FXML private void fxOnMenuAddBook() { onAddBook(); }
    @FXML private void fxOnMenuAppExit() { onExit(); }
    @FXML private void fxOnMenuEditBook() { onEditBook(); }
    @FXML private void fxOnMenuHelp() { onHelp(); }
    @FXML private void fxOnMenuRemoveBooks() { onRemoveBook(); }
    @FXML private void fxOnMenuResetFilters() { onResetFilters(); }

    // Tools Events
    @FXML private void fxOnToolAdd() { onAddBook(); }
    @FXML private void fxOnToolEdit() { onEditBook(); }
    @FXML private void fxOnToolRemove() { onRemoveBook(); }

    // Filters Events
    @FXML private void fxOnRunFilter() { onRunFilter(); }
    @FXML private void fxOnResetFilters() { onResetFilters(); }

    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Хранилице книг - содежит все книги
    private IStorage<Book> storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

    // Массив книг удовлетворяющих фильтр (привязан к содержимому таблици)
    private ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

    // Фильтры книг
    private BookFilters filters = new BookFilters();

    // Другие окна
    private WindowCreateBook windowAddBook;
    private WindowCreateBook windowEditBook;

    // Граничные значение параметров книг
    // Привязываються к минимальны/максимальным значения фильтров (fxml-элементов)
    private final DoubleProperty minPrice = new SimpleDoubleProperty();
    private final DoubleProperty maxPrice = new SimpleDoubleProperty();
    private final IntegerProperty minPages = new SimpleIntegerProperty();
    private final IntegerProperty maxPages = new SimpleIntegerProperty();

    //Книга что редактируется в данный момент (проверка на null обязательна)
    private Book editingBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBinds();
        resetFilters();
        runFilter();
        initTable();
    }

    private void runFilter() {
        updateFilterLimits();
        updateFilters();
        updateFilteredBooks();
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

    private void updateFilteredBooks() {
        filteredBooks.clear();
        filteredBooks.addAll(storage.getArrOfData(book -> filters.check(book)));
    }

    private void updateFilterLimits() {
        var books = storage.getArrOfData();

        minPrice.set(_getSuitable(books, (next, min) -> next.getPrice() < min.getPrice()).getPrice());
        maxPrice.set(_getSuitable(books, (next, max) -> next.getPrice() > max.getPrice()).getPrice());

        minPages.set(_getSuitable(books, (next, min) -> next.getPages() < min.getPages()).getPages());
        maxPages.set(_getSuitable(books, (next, max) -> next.getPages() > max.getPages()).getPages());

        System.out.println(minPrice.getValue());
        System.out.println(maxPrice.getValue());
        System.out.println(minPages.getValue());
        System.out.println(maxPages.getValue());
    }

    private void initBinds() {
        updateFilterLimits();

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
     * Сбрасывает фильтры (значения полей fxml)
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

    private void showWindowAddBook() {
        if (windowAddBook == null) {
            initWindowAddBook();
        }

        windowAddBook.getWindow().show();
    }

    private void showWindowEditBook() {
        // Редактируется ли уже какая-то книга
        boolean alreadyEditing = (editingBook != null);

        if (!alreadyEditing) {
            editingBook = (Book)fxBooksTable.getSelectionModel().getSelectedItem();
        }

        if (editingBook == null) {
            //TODO: Alert - No selected books
            return;
        }

        if (windowEditBook == null) {
            initWindowEditBook();
        }

        if (!alreadyEditing) {
            windowEditBook.getController().setValuesBy(editingBook);
        }

        windowEditBook.getWindow().show();
    }

    private void initWindowAddBook() {
        try {
            windowAddBook = new WindowCreateBook("Add New Book");
            windowAddBook.getController().getOnSaveListeners().add(this::addNewBook);

        } catch (IOException err) {
            //TODO: Alert - Can't open the window
            System.out.println(err.toString());
        }
    }

    private void initWindowEditBook() {
        try {
            windowEditBook = new WindowCreateBook("Edit The Book");
            windowEditBook.getController().getOnSaveListeners().add(this::editBook);

        } catch (IOException err) {
            //TODO: Alert - Can't open the window
            System.out.println(err.toString());
        }
    }

    /**
     * Добавляет новую книгу в storage из windowAddBook
     */
    private void addNewBook() {
        if (!windowAddBook.getController().isReady()) {
            //TODO: Alert - Incorrect data
        }

        storage.add(windowAddBook.getController().create());

        windowAddBook.getController().reset();
        windowAddBook.getWindow().close();
        runFilter();
    }

    /**
     * Редактирует текущюю вибраную книгу
     * (на деле заменяет текущую выбранную книну на книгу созаную пользователем в windowEditBook)
     * Для правильной работы нужно убедиться, что за время, пока пользователь
     * Вводил данные книги выбранная книга в таблице не поменялась
     * (Например, окно windowEditBook может быть APPLICATION_MODAL)
     */
    private void editBook() {
        if (editingBook == null) {
            //TODO: Alert - Failed to save changes
            return;
        }

        if (!windowEditBook.getController().isReady()) {
            //TODO: Alert - Incorrect data
            return;
        }

        storage.remove(editingBook);
        storage.add(windowEditBook.getController().create());

        windowEditBook.getController().reset();
        windowEditBook.getWindow().close();

        editingBook = null;

        runFilter();
    }

    private void removeSelectedBook() {
        int selectedIndex = fxBooksTable.getSelectionModel().getSelectedIndex();

        Book selected = (Book)fxBooksTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            //TODO: Alert - No selected books
            return;
        }

        //TODO: Alert - Are you sure?

        storage.remove(selected);

        runFilter();

        // Восстановить выделение книги в таблице (обязательно после runFilter)
        fxBooksTable.getSelectionModel().select(selectedIndex);
    }

    private void initTable() {
        fxBooksTableColumnName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        fxBooksTableColumnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        fxBooksTableColumnPublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        fxBooksTableColumnPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        fxBooksTableColumnPages.setCellValueFactory(new PropertyValueFactory<Book, Package>("pages"));
        fxBooksTableColumnDate.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("date"));

        fxBooksTable.setItems(filteredBooks);
    }

    private void onAddBook() {
        showWindowAddBook();
    }

    private void onEditBook() {
        showWindowEditBook();
    }

    private void onRunFilter() {
        runFilter();
    }

    private void onRemoveBook() {
        removeSelectedBook();
    }

    private void onHelp() {
        System.out.println("Help");
    }

    private void onAbout() {
        System.out.println("About");
    }

    private void onExit() {
        System.out.println("Exit");
    }

    private void onResetFilters() {
        resetFilters();
        runFilter();
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

    public double getMinPrice() {
        return minPrice.get();
    }

    private DoubleProperty minPriceProperty() {
        return minPrice;
    }

    private void setMinPrice(double minPrice) {
        this.minPrice.set(minPrice);
    }

    public double getMaxPrice() {
        return maxPrice.get();
    }

    private DoubleProperty maxPriceProperty() {
        return maxPrice;
    }

    private void setMaxPrice(double maxPrice) {
        this.maxPrice.set(maxPrice);
    }

    public int getMinPages() {
        return minPages.get();
    }

    private IntegerProperty minPagesProperty() {
        return minPages;
    }

    private void setMinPages(int minPages) {
        this.minPages.set(minPages);
    }

    public int getMaxPages() {
        return maxPages.get();
    }

    private IntegerProperty maxPagesProperty() {
        return maxPages;
    }

    private void setMaxPages(int maxPages) {
        this.maxPages.set(maxPages);
    }
}
