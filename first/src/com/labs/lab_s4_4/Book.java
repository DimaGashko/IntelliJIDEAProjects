package com.labs.lab_s4_4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book>, Serializable {
    private int id;
    private String name;
    private ArrayList<String> authors = new ArrayList<>();
    private String publisher;
    private LocalDate publishDate;
    private int pages;
    private double price;

    public Book(int id, String name, String author, String publisher, LocalDate publishDate, int pages, double price) {
        setId(id);
        setName(name);
        addAuthor(author);
        setPublisher(publisher);
        setPublishDate(publishDate);
        setPages(pages);
        setPrice(price);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", authors='" + authorsToString() + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + publishDate +
             ", pages=" + pages +
                ", price=" + price;
    }

    private String authorsToString() {
       return String.join(", ", authors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String author) {
        this.authors.add(author);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Book o) {
        return Comparator.comparing(Book::getId, Integer::compareTo).compare(this, o);
    }
}
