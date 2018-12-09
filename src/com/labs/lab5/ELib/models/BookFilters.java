package com.labs.lab5.ELib.models;

import com.labs.lab3.part1.library.Book;
import java.time.LocalDate;

/**
 * Класс фильтров книг
 */
public class BookFilters {
    private String name;
    private String author;
    private String publisher;
    private double priceFrom;
    private double priceTo;
    private int pagesFrom;
    private int pagesTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public BookFilters() {

    }

    /**
     * Проверяет удовлетворяет ли переданная книга данному фильтру
     * @param book проверяемая книга
     * @return удовлетворяет ли книга фильтру
     */
    public boolean check(Book book) {

        return (
                   checkStringFilter(name, book.getName())
                && checkStringFilter(author, book.getAuthor())
                && checkStringFilter(publisher, book.getPublisher())

                && checkNumberFilter(priceFrom, priceTo, book.getPrice())
                && checkNumberFilter(pagesFrom, pagesTo, book.getPages())

                && checkDateFilter(dateFrom, dateTo, book.getDate())
        );

    }

    /**
     * Проверяет удовлетворяет ли строковый параметр фильтру
     * Если фильтр пустой, то считается, что паарметр может быть любым
     * @param filter фильтр
     * @param param проверяемое значение
     * @return удовлетворяет ли параметр фильтру
     */
    public boolean checkStringFilter(String filter, String param) {
        if (filter.isEmpty()) return true;

        return param.contains(filter);
    }

    /**
     * Проверяет удовлетворяет ли числовой параметр фильтру
     * Если и from и to равены 0, то считается, что параметр может быть любым
     * @param from минимальное значение
     * @param to максимальное значение
     * @param param проверяемое значение
     * @return удовлетворяет ли параметр фильтру
     */
    public boolean checkNumberFilter(Double from, Double to, Double param) {
        if (from == 0 && to == 0) return true;

        return (from <= param) && (to >= param);
    }

    /**
     * Проверяет удовлетворяет ли числовой параметр фильтру
     * Если и from и to равены 0, то считается, что параметр может быть любым
     * @param from минимальное значение
     * @param to максимальное значение
     * @param param проверяемое значение
     * @return удовлетворяет ли параметр фильтру
     */
    public boolean checkNumberFilter(int from, int to, int param) {
        if (from == 0 && to == 0) return true;

        return (from <= param) && (to >= param);
    }

    /**
     * Проверяет удовлетворяет ли фильтра-дата параметр фильтру
     * Если и from и to равены null, то считается, что параметр может быть любым
     * @param from минимальное значение
     * @param to максимальное значение
     * @param param проверяемое значение
     * @return удовлетворяет ли параметр фильтру
     */
    public boolean checkDateFilter(LocalDate from, LocalDate to, LocalDate param) {
        if (from == null && to == null) return true;

        return (from.compareTo(param) <= 0) && (to.compareTo(param) => 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public int getPagesFrom() {
        return pagesFrom;
    }

    public void setPagesFrom(int pagesFrom) {
        this.pagesFrom = pagesFrom;
    }

    public int getPagesTo() {
        return pagesTo;
    }

    public void setPagesTo(int pagesTo) {
        this.pagesTo = pagesTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
