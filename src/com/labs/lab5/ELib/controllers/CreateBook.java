package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.HandlerFunction;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateBook implements Initializable {
    @FXML private JFXTextField fxName;
    @FXML private JFXTextField fxAuthor;
    @FXML private JFXTextField fxPublisher;
    @FXML private JFXTextField fxPrice;
    @FXML private JFXTextField fxPages;
    @FXML private JFXDatePicker fxDate;

    @FXML
    public void fxOnSave() { onSave(); }

    @FXML
    public void fxOnCancel() {
        title.set(title.getValue() + "1 ");
        onCancel();
    }

    private HandlerFunction onCancel;
    private HandlerFunction onSave;

    private SimpleStringProperty title = new SimpleStringProperty("Create New Book");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setValidators();
    }

    public Book create() {
        String name = fxName.getText();
        String author = fxAuthor.getText();
        String publisher = fxPublisher.getText();
        System.out.println(fxPrice.getText());
        double price = Double.parseDouble(fxPrice.getText());
        int pages = Integer.parseInt(fxPages.getText());
        LocalDate date = fxDate.getValue();

        return new Book(name, author, publisher, date, pages, price);
    }

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
     * @return можноли ли формировать новую книгу из введенный в форме данных
     * TODO: реализовать этот метод
     */
    public boolean isReady() {
        return true;
    }

    public void setValidators() {
        String mess = "Required field";

        fxName.getValidators().add(new RequiredFieldValidator(mess));
        fxAuthor.getValidators().add(new RequiredFieldValidator(mess));
        fxPublisher.getValidators().add(new RequiredFieldValidator(mess));
        fxPrice.getValidators().add(new RequiredFieldValidator(mess));
        fxPages.getValidators().add(new RequiredFieldValidator(mess));
        fxDate.getValidators().add(new RequiredFieldValidator(mess));

        fxName.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxName.validate();
        });
        fxAuthor.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxAuthor.validate();
        });
        fxPublisher.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxPublisher.validate();
        });
        fxPrice.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxPrice.validate();
        });
        fxPages.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxPages.validate();
        });
        fxDate.focusedProperty().addListener((a, b, c) -> {
            if (!c) fxDate.validate();
        });
    }

    private void onCancel() {
        onCancel.call();
    }

    private void onSave() {
        onSave.call();
    }

    public void setOnCancel(HandlerFunction onCancel) {
        this.onCancel = onCancel;
    }

    public void setOnSave(HandlerFunction onSave) {
        this.onSave = onSave;
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

