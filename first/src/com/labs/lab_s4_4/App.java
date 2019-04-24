package com.labs.lab_s4_4;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.helpers.console.ConsoleElements.hr;
import static com.helpers.console.ConsolePrompt.*;
import static com.helpers.console.ConsolePrompt.promptInt;

public class App {
    private Connection connection;

    private static final String sqlSelectAll = "SELECT book.id, book.name, author.name as author,\n" +
            "    publisher.name AS publisher, book.publis_date, book.pages, book.price\n" +
            "FROM book\n" +
            "LEFT JOIN book_author ON book.id = book_author.book_id\n" +
            "LEFT JOIN author ON author.id = book_author.author_id\n" +
            "LEFT JOIN publisher ON book.publisher_id = publisher.id\n ";

    private int limitToShow = 15;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        connect();
        runCli();
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

        exitApp();
    }

    private void useCommand(String command) {
        switch (command) {
            case "showall":
                cliShowAllBooks();
                break;
            case "remove":
                cliRemoveBook();
                break;
            case "filter":
                cliShowBooksByFilter();
                break;
            case "limit":
                cliSetLimit();
                break;
            case "help":
                cliPrintHelp();
                break;
            default:
                System.out.println("Command not found. Try again: ");
                break;
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
        try (var preparedSt = connection.prepareStatement(sqlSelectAll + "LIMIT ?")) {
            preparedSt.setInt(1, limitToShow);

            var rs = preparedSt.executeQuery();
            var books = createBooksFromRs(rs);
            printBooks(books);
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
                        sqlSelectAll + "WHERE author.name LIKE ? ORDER BY book.publish_date LIMIT ?"
                );

                prepareSt.setString(1, "%" + author + "%");
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("b")) {
                var prepareSt = connection.prepareStatement(sqlSelectAll + "WHERE publisher.name LIKE ? LIMIT ?");

                String publisher = promptLine("Publisher:");
                prepareSt.setString(1, "%" + publisher + "%");
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("c")) {
                var prepareSt = connection.prepareStatement(sqlSelectAll + "WHERE publish_date > ? LIMIT ?");

                LocalDate date = promptDate("Publish Date:");
                prepareSt.setString(1, date.toString());
                prepareSt.setInt(2, limitToShow);

                var rs = prepareSt.executeQuery();
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("d")) {
                var prepareSt = connection.prepareStatement(
                        "SELECT DISTINCT author.name AS author FROM book\n" +
                                "LEFT JOIN book_author ON book.id = book_author.book_id\n" +
                                "LEFT JOIN author ON author.id = book_author.author_id\n" +
                                "ORDER BY author.name LIMIT ?"
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
                        "SELECT DISTINCT publisher.name AS publisher FROM book\n" +
                                "INNER JOIN publisher ON book.publisher_id = publisher.id\n " +
                                "LIMIT ?"
                );

                prepareSt.setInt(1, limitToShow);
                var rs = prepareSt.executeQuery();

                List<String> publishers = new ArrayList<>();

                while (rs.next()) {
                    publishers.add(rs.getString("publisher"));
                }

                publishers.forEach(System.out::println);

            } else if (filter.equalsIgnoreCase("f")) {
                var prepareSt = connection.prepareStatement(sqlSelectAll + "LIMIT ?");

                prepareSt.setInt(1, limitToShow);
                var rs = prepareSt.executeQuery();

                var books = createBooksFromRs(rs);
                Map<String, HashSet<Book>> map = books.stream()
                        .collect(Collectors.groupingBy(Book::getPublisher, Collectors.toCollection(HashSet::new)));

                map.forEach((publisher, publishersBook) -> {
                    System.out.println(" - " + publisher);
                    printBooks(publishersBook);
                });

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
        String name = promptLine("Name:");
        String author = promptLine("Author:");
        String publisher = promptLine("Publisher");
        LocalDate publishDate = promptDate("Publish Date:");
        int pages = promptInt("Pages:");
        double price = promptDouble("Price:");

        return new Book(0, name, author, publisher, publishDate, pages, price);
    }

    private ArrayList<Book> createBooksFromRs(ResultSet rs) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            LocalDate publishDate = rs.getDate("publish_date").toLocalDate();
            int pages = rs.getInt("pages");
            double price = rs.getDouble("price");

            Book book = new Book(id, name, author, publisher, publishDate, pages, price);
            books.add(book);
        }

        return books;
    }

    private void printBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            System.out.println("There are no users");
            return;
        }

        books.forEach((item) -> System.out.println("\t" + item));
    }

    private void printBooks(ResultSet rs) throws SQLException {
        var books = createBooksFromRs(rs);
        printBooks(books);
    }

    private void exitApp() {
        disconnect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_sem4_lab4?serverTimezone=UTC",
                    "db_user", "qqqqqqqqww"
            );

        } catch (SQLException e) {
            System.out.println("Can't connect to DB");
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cliPrintHelp() {
        System.out.println("Commands:");
        System.out.println("> showAll #Show all books");
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
