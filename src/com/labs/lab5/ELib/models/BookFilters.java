package com.labs.lab5.ELib.models;

import com.labs.lab3.part1.library.Book;
import java.time.LocalDate;

/**
 * Класс фильтров книг
 */
public class BookFilters {
    private String nameFilter = "";
    private String authorFilter = "";
    private String publisherFilter = "";
    private double priceFromFilter = 0;
    private double priceToFilter = 0;
    private int pagesFromFilter = 0;
    private int pagesToFilter = 0;
    private LocalDate dateFromFilter = null;
    private LocalDate dateToFilter = null;

    public BookFilters() {

    }

    /**
     * Проверяет удовлетворяет ли переданная книга данному фильтру
     * @param book проверяемая книга
     * @return удовлетворяет ли книга фильтру
     */
    public boolean check(Book book) {

        return (
                   checkStringFilter(nameFilter, book.getName())
                && checkStringFilter(authorFilter, book.getAuthor())
                && checkStringFilter(publisherFilter, book.getPublisher())

                && checkNumberFilter(priceFromFilter, priceToFilter, book.getPrice())
                && checkNumberFilter(pagesFromFilter, pagesToFilter, book.getPages())

                && checkDateFilter(dateFromFilter, dateToFilter, book.getDate())
        );

    }

    /**
     * Проверяет удовлетворяет ли строковый параметр фильтру
     * Если фильтр пустой, то считается, что паарметр может быть любым
     * @param filter фильтр
     * @param param проверяемое значение
     * @return удовлетворяет ли параметр фильтру
     */
    private boolean checkStringFilter(String filter, String param) {
        if (filter.isEmpty()) return true;

        filter = filter.toLowerCase().trim();
        param = param.toLowerCase().trim();

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
    private boolean checkNumberFilter(Double from, Double to, Double param) {
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
    private boolean checkNumberFilter(int from, int to, int param) {
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
    private boolean checkDateFilter(LocalDate from, LocalDate to, LocalDate param) {
        if (from == null && to == null) return true;

        return (from.compareTo(param) <= 0) && (to.compareTo(param) >= 0);
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getAuthorFilter() {
        return authorFilter;
    }

    public void setAuthorFilter(String authorFilter) {
        this.authorFilter = authorFilter;
    }

    public String getPublisherFilter() {
        return publisherFilter;
    }

    public void setPublisherFilter(String publisherFilter) {
        this.publisherFilter = publisherFilter;
    }

    public double getPriceFromFilter() {
        return priceFromFilter;
    }

    public void setPriceFromFilter(double priceFromFilter) {
        this.priceFromFilter = priceFromFilter;
    }

    public double getPriceToFilter() {
        return priceToFilter;
    }

    public void setPriceToFilter(double priceToFilter) {
        this.priceToFilter = priceToFilter;
    }

    public int getPagesFromFilter() {
        return pagesFromFilter;
    }

    public void setPagesFromFilter(int pagesFromFilter) {
        this.pagesFromFilter = pagesFromFilter;
    }

    public int getPagesToFilter() {
        return pagesToFilter;
    }

    public void setPagesToFilter(int pagesToFilter) {
        this.pagesToFilter = pagesToFilter;
    }

    public LocalDate getDateFromFilter() {
        return dateFromFilter;
    }

    public void setDateFromFilter(LocalDate dateFromFIlter) {
        this.dateFromFilter = dateFromFIlter;
    }

    public LocalDate getDateToFilter() {
        return dateToFilter;
    }

    public void setDateToFilter(LocalDate dateToFilter) {
        this.dateToFilter = dateToFilter;
    }
}
