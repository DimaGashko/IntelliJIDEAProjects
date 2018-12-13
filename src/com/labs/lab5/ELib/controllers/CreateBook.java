package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
        setValidators();
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

    public void setValidators() {
        String mess = "Required field";

        fxName.getValidators().add(new RequiredFieldValidator(mess));

        fxName.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxName.validate();
        });
    }

    private void onCancel() {
        onCancelHandler.call();
    }

    private void onSave() {
        if (!isReady()) {
            alerts.show(alerts.getAlertWarn(), "Incorrect Data");
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
}

