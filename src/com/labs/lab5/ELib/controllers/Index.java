package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.BookFilters;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;

import com.jfoenix.controls.*;

import com.labs.lab5.ELib.windows.WindowAddBook;
import com.labs.lab5.ELib.windows.WindowEditBook;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

public class Index implements Initializable {
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Хранилице книг - содежит все книги
    private IStorage<Book> storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

    // Массив книг удовлетворяющих фильтр (привязан к содержимому таблици)
    private ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

    // Фильтры книг
    private BookFilters filters = new BookFilters();

    // Другие окна
    private WindowAddBook windowAddBook;
    private WindowEditBook windowEditBook;

    // Граничные значение параметров книг
    // Привязываються к минимальны/максимальным значения
    // Fxml-элементов фильтров
    private SimpleDoubleProperty minPrice = new SimpleDoubleProperty();
    private SimpleDoubleProperty maxPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty minPages = new SimpleIntegerProperty();
    private SimpleIntegerProperty maxPages = new SimpleIntegerProperty();

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
    @FXML private TableView fxBooksTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBinds();
        resetFilters();
        runFilter();
        initTable();
    }

    private void initBinds() {
        bindFilterLimits();
    }

    private void runFilter() {
        updateFilters();
        updateFilteredBooks();
        updateFilterLimits();
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
     * TODO: Заменить for на forEach, после внедрени ArrayList
     */
    private void updateFilteredBooks() {
        var books = storage.getArrOfData(book -> filters.check(book));

        filteredBooks.clear();

        for (Book book : books) {
            filteredBooks.add(book);
        }
    }

    /**
     * Вычисляет граници фильтров
     * (обновляет поля min/max price/pages и др)
     */
    private void updateFilterLimits() {
        var books = storage.getArrOfData();

        minPrice.set(_getSuitable(books, (next, min) -> next.getPrice() < min.getPrice()).getPrice());
        maxPrice.set(_getSuitable(books, (next, max) -> next.getPrice() > max.getPrice()).getPrice());

        minPages.set(_getSuitable(books, (next, min) -> next.getPages() < min.getPages()).getPages());
        maxPages.set(_getSuitable(books, (next, max) -> next.getPages() > max.getPages()).getPages());
    }

    /**
     * Устанавливает привязку элементов фильтров к граничным значениям
     */
    private void bindFilterLimits() {
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
     * Сбрасывает фильтры (значения элементов fxml)
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
        if (windowEditBook == null) {
            initWindowEditBook();
        }

        windowEditBook.getWindow().show();
    }

    /**
     * Инициализирует окно создания новой книги
     * TODO: Exception
     */
    private void initWindowAddBook() {
        try {
            windowAddBook = new WindowAddBook();

            windowAddBook.getController().getOnSaveListeners().add(this::addNewBook);

        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    private void initWindowEditBook() {
        try {
            windowEditBook = new WindowEditBook();

            windowAddBook.getController().getOnSaveListeners().add(this::editBook);

        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    private void addNewBook() {
        storage.add(windowAddBook.getController().create());
        windowAddBook.getController().reset();
        windowAddBook.getWindow().close();
        runFilter();
    }

    private void editBook() {
        //storage.add(windowAddBook.getController().create());

        windowAddBook.getController().reset();
        windowAddBook.getWindow().close();
        runFilter();
    }

    private void initTable() {
        var thName = new TableColumn<Book, String>("Name");
        var thAuthor = new TableColumn<Book, String>("Author");
        var thPublisher = new TableColumn<Book, String>("Publisher");
        var thPrice = new TableColumn<Book, Double>("Price");
        var thPages = new TableColumn<Book, Integer>("Pages");
        var thDate = new TableColumn<Book, LocalDate>("Date");

        thName.setCellValueFactory(new PropertyValueFactory<>("name"));
        thAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        thPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        thPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        thPages.setCellValueFactory(new PropertyValueFactory<>("pages"));
        thDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        fxBooksTable.setItems(filteredBooks);
        fxBooksTable.getColumns().addAll(thName, thAuthor, thPublisher, thPrice, thPages, thDate);
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
        showWindowAddBook();
    }

    @FXML private void fxOnToolEdit() {
        showWindowEditBook();
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
