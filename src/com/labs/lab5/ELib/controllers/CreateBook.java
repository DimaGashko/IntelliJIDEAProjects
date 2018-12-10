package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.HandlerFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
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

    }

    @FXML public void fxOnSave() {
        onSave();
    }

    @FXML public void fxOnCancel() {
        onCancel();
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
        fxName.setText(null);
        fxAuthor.setText(null);
        fxPublisher.setText(null);
        fxPrice.setText(null);
        fxPages.setText(null);
        fxDate.setValue(null);
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
}
