package com.labs.lab3.part1.library;

import java.util.Arrays;

public class Library {
    private Book[] books;

    public Book[] getBooks() {
        return Arrays.copyOf(books, books.length);
    }

    /**
     * Добавляет переданные книги
     * @param books Книги, что нужно добавить
     */
    public void addBooks(Book[] books) {
        this.books = books;

        Book[] newBooks = Arrays.copyOf(this.books, this.books.length + books.length);
        System.arraycopy(books, 0, newBooks, this.books.length, books.length);
    }

    /**
     * Взвращает массив книг переданного автора
     * @param author Автор
     * @return массив книг переданного автора
     */
    public Book[] getBooksByAuthor(String author) {
        return Arrays.stream(books)
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toArray(Book[]::new);
    }
    /*

    * */

    /**
     * Взвращает массив книг переданного издательства
     * @param publisher Издательство
     * @return массив книг переданного издательства
     */
    public Book[] getBooksByPublisher(String publisher) {
        return Arrays.stream(books)
                .filter(book -> book.getPublisher().equalsIgnoreCase(publisher))
                .toArray(Book[]::new);
    }

    /**
     * Взвращает массив книг изданных после переданного года
     * @param year Год
     * @return массив книг изданных после переданного года
     */
    public Book[] getBooksAfterYear(int year) {
        return Arrays.stream(books)
                .filter(book -> book.getYear() > year)
                .toArray(Book[]::new);
    }

}
