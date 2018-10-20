package com.labs.lab3.part1;

import com.labs.lab3.part1.library.Book;
import com.labs.lab3.part1.library.Library;

import static com.helpers.console.ConsolePrompt.promptDouble;
import static com.helpers.console.ConsolePrompt.promptInt;
import static com.helpers.console.ConsolePrompt.promptLine;

public class Lab3Part1 {
    public static void main(String[] args) {

    }

    private Library library = new Library();

    public void run() {
        //var books = getBooksFromConsole();
        var books = getTestBooks();
        library.addBooks(books);

        printHr();

        String author = promptLine("Enter the author: ");
        printBooks(library.getBooksByAuthor(author));

        String publisher = promptLine("Enter the publisher: ");
        printBooks(library.getBooksByPublisher(publisher));

        int year = promptInt("Enter the year: ");
        printBooks(library.getBooksAfterYear(year));
    }

    private Book[] getBooksFromConsole() {
        var number = promptInt("Enter the number of books: ");
        Book[] books = new Book[number];

        printHr();

        for (int i = 0; i < books.length; i++) {
            String name = promptLine("Enter the name of the book: ");
            String author = promptLine("Enter the author: ");
            String publisher = promptLine("Enter the publisher: ");
            int year = promptInt("Enter the year: ");
            int pages = promptInt("Enter the number of pages: ");
            double price = promptDouble("Enter the price: ");

            books[i] = new Book(name, author, publisher, year, pages, price);

            printHr();
        }

        return books;
    }

    private Book[] getTestBooks() {
        Book[] books = new Book[7];

        books[0] = new Book("JavaScript: The Definitive Guide", "David Flanagan", "O'Reilly", 2006, 999, 50);
        books[1] = new Book("Java Programming", "Donald Bales", "O'Reilly", 2001, 450, 60);
        books[2] = new Book("Thinking in Java", "Bruce Eckel", "Oracle", 2002, 328, 50);
        books[3] = new Book("JS.Next", "Aaron Frost", "O'Reilly", 2015, 250, 50);
        books[4] = new Book("You don't know JS", "Simpson K.", "O'Reilly", 2015, 88, 30);
        books[5] = new Book("Angular", "David Flanagan", "O'Reilly", 2016, 396, 30);
        books[6] = new Book("Java in a Nutshell", "David Flanagan", "O'Reilly", 2014, 396, 60);

        return books;
    }

    private void printBooks(Book[] books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void printHr() {
        System.out.println("- - - - - - - - - - - - -");
    }

}
