package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.HandlerFunction;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateBook implements Initializable {
    private List<HandlerFunction> onCancelListeners = new ArrayList<>();
    private List<HandlerFunction> onSaveListeners = new ArrayList<>();

    @FXML private Label fxTitle;
    @FXML private JFXTextField fxName;
    @FXML private JFXTextField fxAuthor;
    @FXML private JFXTextField fxPublisher;
    @FXML private JFXTextField fxPrice;
    @FXML private JFXTextField fxPages;
    @FXML private JFXDatePicker fxDate;
    @FXML private JFXButton fxSave;
    @FXML private JFXButton fxCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValidators();
    }

    public Book create() {
        String name = fxName.getText();
        String author = fxAuthor.getText();
        String publisher = fxPublisher.getText();
        double price = Double.parseDouble(fxPrice.getText());
        int pages = Integer.parseInt(fxPages.getText());
        LocalDate date = fxDate.getValue();

        return new Book(name, author, publisher, date, pages, price);
    }

    public void setTitle(String title) {
        fxTitle.setText(title);
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

    private void setValidators() {
        String mess = "Required field";

        fxName.getValidators().add(new RequiredFieldValidator(mess));
        fxAuthor.getValidators().add(new RequiredFieldValidator(mess));
        fxPublisher.getValidators().add(new RequiredFieldValidator(mess));
        fxPrice.getValidators().add(new RequiredFieldValidator(mess));
        fxPages.getValidators().add(new RequiredFieldValidator(mess));
        fxDate.getValidators().add(new RequiredFieldValidator(mess));

        fxName.focusedProperty().addListener((a, b, c) -> {if (!c) fxName.validate();});
        fxAuthor.focusedProperty().addListener((a, b, c) -> {if (!c) fxAuthor.validate();});
        fxPublisher.focusedProperty().addListener((a, b, c) -> {if (!c) fxPublisher.validate();});
        fxPrice.focusedProperty().addListener((a, b, c) -> {if (!c) fxPrice.validate();});
        fxPages.focusedProperty().addListener((a, b, c) -> {if (!c) fxPages.validate();});
        fxDate.focusedProperty().addListener((a, b, c) -> {if (!c) fxDate.validate();});
    }

    private void onCancel() {
        onCancelListeners.forEach(HandlerFunction::call);
    }

    private void onSave() {
        onSaveListeners.forEach(HandlerFunction::call);
    }

    public List<HandlerFunction> getOnCancelListeners() {
        return onCancelListeners;
    }

    public List<HandlerFunction> getOnSaveListeners() {
        return onSaveListeners;
    }

    @FXML public void fxOnSave() {
        onSave();
    }

    @FXML public void fxOnCancel() {
        onCancel();
    }
}
