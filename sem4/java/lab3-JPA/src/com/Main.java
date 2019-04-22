package com;

import dao.BookDao;
import schemas.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.console.ConsoleElements.hr;
import static com.console.ConsolePrompt.*;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        BookDao bookDao = new BookDao(em);

        var books = bookDao.findAll();
        books.forEach(System.out::println);

        System.out.println("Hello");
    }

}

class App {

    BookDao bookDao;

    Connection connection;

    private int limitToShow = 15;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        init();
        runCli();
    }

    private void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        bookDao = new BookDao(em);
    }

    private void runCli() {
        cliPrintHelp();
        hr();

        while (true) {
            String command = promptLine("Enter command: ").trim().toLowerCase();

            if (command.equals("exit")) break;
            else useCommand(command);

            hr();
        }

    }

    private void useCommand(String command) {
        switch (command) {
            case "showall":
                cliShowAllBooks(); break;
            case "add":
                cliAddBook(); break;
            case "remove":
                cliRemoveBook(); break;
            case "filter":
                cliShowBooksByFilter(); break;
            case "limit":
                cliSetLimit(); break;
            case "help":
                cliPrintHelp(); break;
            default:
                System.out.println("Command not found. Try again: ");
                break;
        }
    }

    private void cliAddBook() {
        Book book = createNewBook();

        try (var preparedSt = connection.prepareStatement("INSERT INTO book VALUES(NULL,?,?,?,?,?,?)")) {
            preparedSt.setString(1, book.getName());
            preparedSt.setString(2, book.getAuthor());
            preparedSt.setString(3, book.getPublisher());
            preparedSt.setDate(4, Date.valueOf(book.getPublishDate()));
            preparedSt.setInt(5, book.getPages());
            preparedSt.setDouble(6, book.getPrice());

            preparedSt.executeUpdate();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Can't add the book!");
            e.printStackTrace();
        }
    }

    private void cliRemoveBook() {
        int bookId = promptInt("Enter the book id");

        try (var preparedSt = connection.prepareStatement("DELETE FROM book WHERE id = ?")) {
            preparedSt.setInt(1, bookId);

            preparedSt.executeUpdate();
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("Can't remove the book");
            e.printStackTrace();
        }

    }

    private void cliShowAllBooks() {
        try (var preparedSt = connection.prepareStatement("SELECT * FROM book LIMIT ?")) {
            preparedSt.setInt(1, limitToShow);

            var rs = preparedSt.executeQuery();
            //var books = createBooksFromRs(rs);
            //printBooks(books);
        } catch (SQLException e) {
            System.out.println("Can't load books");
            e.printStackTrace();
        }
    }

    private void cliShowBooksByFilter() {
        printFiltersHelp();
        String filter = promptLine("Select Filter:");

        try {

            if (filter.equalsIgnoreCase("a")) {
                String author = promptLine("Author:");

                var prepareSt = connection.prepareStatement(
                        "SELECT * FROM book WHERE author LIKE ?" +
                                "ORDER BY publish_date LIMIT ?"
                );

                prepareSt.setString(1, "%" + author + "%");
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                //printBooks(rs);

            } else if (filter.equalsIgnoreCase("b")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT * FROM book WHERE publisher LIKE ? LIMIT ?"
                );

                String publisher = promptLine("Publisher:");
                prepareSt.setString(1, "%" + publisher + "%");
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                //printBooks(rs);

            } else if (filter.equalsIgnoreCase("c")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT * FROM book WHERE publish_date > ? LIMIT ?"
                );

                LocalDate date = promptDate("Publish Date:");
                prepareSt.setString(1, date.toString());
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                //printBooks(rs);

            } else if (filter.equalsIgnoreCase("d")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT DISTINCT author FROM book ORDER BY author LIMIT ?"
                );

                prepareSt.setInt(1, limitToShow);
                var rs = prepareSt.executeQuery();

                List<String> authors = new ArrayList<>();

                while (rs.next()) {
                    authors.add(rs.getString("author"));
                }

                authors.forEach(System.out::println);

            } else if (filter.equalsIgnoreCase("e")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT DISTINCT publisher FROM book LIMIT ?"
                );

                prepareSt.setInt(1, limitToShow);
                var rs = prepareSt.executeQuery();

                List<String> publishers = new ArrayList<>();

                while (rs.next()) {
                    publishers.add(rs.getString("publisher"));
                }

                publishers.forEach(System.out::println);

            } else if (filter.equalsIgnoreCase("f")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT * FROM book LIMIT  ?"
                );

                prepareSt.setInt(1, limitToShow);
                var rs = prepareSt.executeQuery();

                /*var books = createBooksFromRs(rs);
                Map<String, HashSet<Book>> map = books.stream()
                        .collect(Collectors.groupingBy(Book::getPublisher, Collectors.toCollection(HashSet::new)));

                map.forEach((publisher, publishersBook) -> {
                    System.out.println(" - " + publisher);
                    printBooks(publishersBook);
                });
*/
            } else {
                System.out.println("Can't find the filter");

            }

        } catch (SQLException e) {
            System.out.println("Filter error");
            e.printStackTrace();
        }
    }

    private void cliSetLimit() {
        System.out.println("Current limit: " + limitToShow);
        limitToShow = promptInt("Enter new limit: ");
    }

    private Book createNewBook() {
        Book book = new Book();

        book.setName(promptLine("Name:"));
        book.setAuthor(promptLine("Author:"));
        book.setPublisher(promptLine("Publisher"));
        book.setPublishDate(promptDate("Publish Date:"));
        book.setPages(promptInt("Pages:"));
        book.setPrice(promptDouble("Price:"));

        return book;
    }

    private void printBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            System.out.println("There are no users");
            return;
        }

        books.forEach((item) -> System.out.println("\t" + item));
    }

    private void cliPrintHelp() {
        System.out.println("Commands:");
        System.out.println("> showAll #Show all books");
        System.out.println("> add #Add new book");
        System.out.println("> remove #Remove the book by ID");
        System.out.println("> filter #Show books by filter");
        System.out.println();
        System.out.println("> limit #Set Limit to show");
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");
    }

    private void printFiltersHelp() {
        System.out.println("Filters:");
        System.out.println("> a #Список книг заданого автора в порядку зростання року видання");
        System.out.println("> b #Список книг, що видані заданим видавництвом");
        System.out.println("> c #Список книг, що випущені після заданої дати");
        System.out.println("> d #Список авторів в алфавітному порядку");
        System.out.println("> e #Список видавництв, книги яких зареєстровані в системі без повторів");
        System.out.println("> f #Для кожного видавництва визначити список книг, виданих ним");
    }

}
