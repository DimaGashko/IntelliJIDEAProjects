package com.labs.lab5.ELib.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.labs.lab3.part1.library.Book;
import javafx.beans.property.*;

/**
 * Класс представления книги в таблице
 */
public class BookRow extends RecursiveTreeObject<BookRow> {
    private Book book;

    private SimpleStringProperty name;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty pages;
    private SimpleIntegerProperty year;

    private BooleanProperty selected;

    BookRow(Book book) {
        setBook(book);
        setSelected(true);
    }

    public void setBook(Book book) {
        this.book = book;

        name = new SimpleStringProperty(book.getName());
        author = new SimpleStringProperty(book.getAuthor());
        publisher = new SimpleStringProperty(book.getPublisher());

        price = new SimpleDoubleProperty(book.getPrice());
        pages = new SimpleIntegerProperty(book.getPages());

        year = new SimpleIntegerProperty(book.getYear());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Book getBook() {
        return book;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getPages() {
        return pages.get();
    }

    public IntegerProperty pagesProperty() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages.set(pages);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty dateProperty() {
        return year;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public void setDate(int date) {
        this.year.set(date);
    }

}
