package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.HandlerFunction;
import com.labs.lab5.ELib.windows.Alerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateBook implements Initializable {
    @FXML private JFXTextField fxName;
    @FXML private JFXTextField fxAuthor;
    @FXML private JFXTextField fxPublisher;
    @FXML private JFXTextField fxPrice;
    @FXML private JFXTextField fxPages;
    @FXML private JFXDatePicker fxDate;

    @FXML public void fxOnSave() { onSave(); }
    @FXML public void fxOnCancel() { onCancel(); }

    private HandlerFunction onCancelHandler;
    private HandlerFunction onSaveHandler;

    private SimpleStringProperty title = new SimpleStringProperty("Create New Book");

    private Alerts alerts = new Alerts();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initValidators();
    }

    /**
     * Создает и возващает книгу на основании введенных пользователем данных
     * @return книга созданная на основании введенних данных
     */
    public Book create() {
        if (!isReady()) {
            return null;
        }

        String name = fxName.getText();
        String author = fxAuthor.getText();
        String publisher = fxPublisher.getText();
        System.out.println(fxPrice.getText());
        double price = Double.parseDouble(fxPrice.getText());
        int pages = Integer.parseInt(fxPages.getText());
        LocalDate date = fxDate.getValue();

        return new Book(name, author, publisher, date, pages, price);
    }

    /**
     * Устанавливает в поля формы данних переданной книги
     * @param book книга данные которой будут установлены в поля формы
     */
    public void setValuesBy(Book book) {
        fxName.setText(book.getName());
        fxAuthor.setText(book.getAuthor());
        fxPublisher.setText(book.getPublisher());
        fxPrice.setText(Double.toString(book.getPrice()));
        fxPages.setText(Integer.toString(book.getPages()));
        fxDate.setValue(book.getDate());
    }

    public void reset() {
        fxName.clear();
        fxAuthor.clear();
        fxPublisher.clear();
        fxPrice.clear();
        fxPages.clear();
        fxDate.setValue(null);
    }

    /**
     * @return можно ли ли формировать новую книгу из введенный в форме данных
     */
    public boolean isReady() {

        return fxName.validate()
                && fxAuthor.validate()
                && fxPublisher.validate()
                && fxPrice.validate()
                && fxPages.validate()
                && fxDate.validate();

    }

    private void initValidators() {
        var required = new RequiredFieldValidator("Required field");

        fxName.getValidators().add(required);
        fxAuthor.getValidators().add(required);
        fxPublisher.getValidators().add(required);
        fxPrice.getValidators().add(required);
        fxPages.getValidators().add(required);
        fxDate.getValidators().add(required);

        fxPrice.getValidators().add(new DoubleValidator("Not a number"));
        fxPages.getValidators().add(new IntegerValidator("Not an integer"));
    }

    private void onCancel() {
        onCancelHandler.call();
    }

    private void onSave() {
        if (!isReady()) {
            alerts.show(alerts.getAlertWarn(), "Check your inputs");
            return;
        }

        onSaveHandler.call();
    }

    public void setOnCancelHandler(HandlerFunction onCancelHandler) {
        this.onCancelHandler = onCancelHandler;
    }

    public void setOnSaveHandler(HandlerFunction onSaveHandler) {
        this.onSaveHandler = onSaveHandler;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setNameField(String text) {
        this.fxName.setText(text);
    }

    public void setAuthorField(String text) {
        this.fxAuthor.setText(text);
    }

    public void setPublisherField(String text) {
        this.fxPublisher.setText(text);
    }

    public void setPriceField(double val) {
        this.fxPrice.setText(Double.toString(val));
    }

    public void setPagesField(int val) {
        this.fxPages.setText(Integer.toString(val));
    }

    public void setDateField(LocalDate date) {
        this.fxDate.setValue(date);
    }
}

