package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.BookFilters;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.windows.Alerts;
import com.labs.lab5.ELib.windows.WindowCreateBook;
import com.labs.lab5.ELib.models.storage.TextStorage;

import java.net.URL;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
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

    // Путь к файлу в котором хранятся книги
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";

    // Хранилице книг - содежит все книги
    private IStorage<Book> storage;

    // Массив книг удовлетворяющих фильтр (привязан к содержимому таблици)
    private ObservableList<Book> filteredBooks = FXCollections.observableArrayList();

    // Фильтры книг
    private BookFilters filters = new BookFilters();

    // Другие окна
    private WindowCreateBook windowAddBook;
    private WindowCreateBook windowEditBook;
    private Alerts alerts = new Alerts();

    // Граничные значение параметров книг
    // Привязываються к минимальны/максимальным значения фильтров (fxml-элементов)
    private final DoubleProperty minPrice = new SimpleDoubleProperty(20);
    private final DoubleProperty maxPrice = new SimpleDoubleProperty(100);
    private final IntegerProperty minPages = new SimpleIntegerProperty(20);
    private final IntegerProperty maxPages = new SimpleIntegerProperty(100);

    //Книга что редактируется в данный момент (проверка на null обязательна)
    private Book editingBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStorage();

        if (storage == null) {
            onExit();
            return;
        }

        initBinds();
        resetFilters();
        runFilter();
        initTable();
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
        if (books.length == 0) return;

        minPrice.set(_getSuitable(books, (next, min) -> next.getPrice() < min.getPrice()).getPrice());
        minPages.set(_getSuitable(books, (next, min) -> next.getPages() < min.getPages()).getPages());

        if (books.length == 1) {
            // FIXME: Когда в таблице одна книга, она не отображается
            maxPrice.set(minPages.get() + 10);
            maxPages.set(minPages.get() + 10);
            return;
        }

        maxPrice.set(_getSuitable(books, (next, max) -> next.getPrice() > max.getPrice()).getPrice());
        maxPages.set(_getSuitable(books, (next, max) -> next.getPages() > max.getPages()).getPages());
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
        Book selected = (Book)fxBooksTable.getSelectionModel().getSelectedItem();
        boolean alreadyEditing = false;

        if (editingBook != null && editingBook == selected) {
            alreadyEditing = true;

        } else  {
            editingBook = selected;
        }

        if (editingBook == null) {
            alerts.show(alerts.getAlertInfo(),  "No selected books");
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
            alerts.show(alerts.getAlertErr(), "Can't open the window");
            System.out.println(err.toString());
        }
    }

    private void initWindowEditBook() {
        try {
            windowEditBook = new WindowCreateBook("Edit The Book");
            windowEditBook.getController().getOnSaveListeners().add(this::editBook);

        } catch (IOException err) {
            alerts.show(alerts.getAlertErr(), "Can't open the window");
            System.out.println(err.toString());
        }
    }

    /**
     * Добавляет новую книгу в storage из windowAddBook
     */
    private void addNewBook() {
        if (!windowAddBook.getController().isReady()) {
            alerts.show(alerts.getAlertErr(), "Incorrect data");
        }

        try {
            storage.add(windowAddBook.getController().create());

        } catch ( IOException err) {
            alerts.show(alerts.getAlertErr(), "Something's wrong. Can't save the book");
            return;
        }

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
            alerts.show(alerts.getAlertErr(), "Something's wrong. Can't save the changes");
            return;
        }

        if (!windowEditBook.getController().isReady()) {
            alerts.show(alerts.getAlertErr(), "Incorrect data");
            return;
        }

        try {
            Book newBook = windowEditBook.getController().create();
            storage.replace(editingBook, newBook);

        } catch (IOException err) {
            alerts.show(alerts.getAlertErr(), "Something's wrong. Can't save the changes");
        }

        windowEditBook.getController().reset();
        windowEditBook.getWindow().close();

        editingBook = null;

        runFilter();
    }

    private void removeSelectedBook() {
        int selectedIndex = fxBooksTable.getSelectionModel().getSelectedIndex();

        Book selected = (Book)fxBooksTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            alerts.show(alerts.getAlertInfo(), "No selected books");
            return;
        }

        String mes = "Are you really want to remove '"+ selected.getName() +"'?";
        var res = (alerts.show(alerts.getAlertConfirm(), mes));

        if (!alerts.getAnswer(res)) {
            return;
        }

        try {
            storage.remove(selected);

        } catch (IOException err) {
            //TODO: Stack Trace
            alerts.show(alerts.getAlertErr(), "Something's wrong. Can't remove the book");
        }


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
        alerts.show(alerts.getAlertInfo(), "Only You Can Help yourself!");
    }

    private void onAbout() {
        alerts.show(alerts.getAlertInfo(), "ELib - Your World Of Books!");
    }

    private void onExit() {
        System.out.println("Exit");
    }

    private void onResetFilters() {
        resetFilters();
        runFilter();
    }

    private void initStorage() {
        try {
            storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

        } catch (IOException err) {
            alerts.show(alerts.getAlertErr(), "Can't load the data");

            var res = alerts.show(alerts.getAlertConfirm(), "Try again");

            if (alerts.getAnswer(res)) {
                initStorage();
            }
        }

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
        if (items.length == 0) return null;
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
