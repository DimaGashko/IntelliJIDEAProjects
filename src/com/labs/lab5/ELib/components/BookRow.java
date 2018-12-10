package com.labs.lab5.ELib.components;

import com.labs.lab3.part1.library.Book;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * TODO: Comments
 */
public class BookRow {
    private Book book;

    private HBox fxRoot;
    private Label fxLabel;

    public BookRow(Book book) {
        setBook(book);
        create();
    }

    private void create() {
        fxRoot = new HBox();
        fxRoot.getStyleClass().add("boorRow");

        fxLabel = new Label(book.toString());
        fxRoot.getChildren().add(fxLabel);
    }

    public Book getBook() {
        return book;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public HBox getFxRoot() {
        return fxRoot;
    }

    public void setFxRoot(HBox fxRoot) {
        this.fxRoot = fxRoot;
    }

    public Label getFxLabel() {
        return fxLabel;
    }

    public void setFxLabel(Label fxLabel) {
        this.fxLabel = fxLabel;
    }
}
