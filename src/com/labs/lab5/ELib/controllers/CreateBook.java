package com.labs.lab5.ELib.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.labs.lab3.part1.library.Book;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBook implements Initializable {
    private Stage window;

    @FXML private Label fxTitle;
    @FXML private JFXTextField fxName;
    @FXML private JFXTextField fxAuthor;
    @FXML private JFXTextField fxPublisher;
    @FXML private JFXTextField fxPrice;
    @FXML private JFXTextField fxPages;
    @FXML private JFXDatePicker fxDate;
    @FXML private JFXButton fxSave;
    @FXML private JFXButton sfCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

}
