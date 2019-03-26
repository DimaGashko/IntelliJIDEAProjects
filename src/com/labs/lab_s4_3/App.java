package com.labs.lab_s4_3;

import com.labs.lab_s4_1.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.helpers.console.ConsoleElements.hr;
import static com.helpers.console.ConsolePrompt.*;
import static com.helpers.console.ConsolePrompt.promptInt;

public class App {
    Connection connection;

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
        printHelp();
        hr();

        while (true) {
            String command = promptLine("Enter command: ").trim();

            if (command.equalsIgnoreCase("exit")) {
                exitApp();
                break;

            } else {
                useCommand(command);

            }

            hr();
        }
    }

    private void useCommand(String command) {
        if (command.equalsIgnoreCase("add")) {
            addBook();

        } else if (command.equalsIgnoreCase("remove")) {
            removeBook();

        } else if (command.equalsIgnoreCase("showAll")) {
            showAllBooks();

        } else if (command.equalsIgnoreCase("filter")) {
            showBooksByFilter();

        } else if (command.equalsIgnoreCase("limit")) {
            setLimit();

        } else if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else {
            System.out.println("Command not found. Try again: ");
        }
    }

    private void addBook() {
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

    private void removeBook() {
        int bookId = promptInt("Enter the book id");

        try (Statement statement = connection.createStatement()) {
           statement.executeUpdate("DELETE FROM book WHERE id = " + bookId);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("Can't remove the book");
            e.printStackTrace();
        }
    }

    private void showAllBooks() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM book LIMIT " + limitToShow);
            var books = createBooksFromRs(rs);
            printBooks(books);
        } catch (SQLException e) {
            System.out.println("Can't load books");
            e.printStackTrace();
        }
    }

    private void showBooksByFilter() {
        printFiltersHelp();
        String filter = promptLine("Select Filter:");

        try {

            if (filter.equalsIgnoreCase("a")) {
                String author = promptLine("Author:");
                var rs = connection.createStatement().executeQuery(
                        "SELECT * FROM book WHERE author LIKE '%" + author + "%' " +
                                "ORDER BY publish_date LIMIT " + limitToShow
                );
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("b")) {
                String publisher = promptLine("Publisher:");
                var rs = connection.createStatement().executeQuery(
                        "SELECT * FROM book WHERE publisher LIKE '%" + publisher + "%' LIMIT " + limitToShow
                );
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("c")) {
                LocalDate date = promptDate("Publish Date:");
                System.out.println(date);
                var rs = connection.createStatement().executeQuery(
                        "SELECT * FROM book WHERE publish_date > '" + date + "' LIMIT " + limitToShow
                );
                printBooks(rs);

            } else if (filter.equalsIgnoreCase("d")) {


            } else if (filter.equalsIgnoreCase("e")) {


            } else if (filter.equalsIgnoreCase("f")) {


            } else {
                System.out.println("Can't find the filter");

            }

        } catch (SQLException e) {
            System.out.println("Filter error");
            e.printStackTrace();
        }
    }

    private void printFiltersHelp() {
        System.out.println("Filters:");
        System.out.println("> a #Список книг заданого автора в порядку зростання року видання");
        System.out.println("> b #Список книг, що видані заданим видавництвом");
        System.out.println("> c #Список книг, що випущені після заданої дати");
        System.out.println("> d #Список авторів в алфавітному порядку;");
        System.out.println("> e #Список видавництв, книги яких зареєстровані в системі без повторів");
        System.out.println("> f #Для кожного видавництва визначити список книг, виданих ним");
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("> add #Add new book");
        System.out.println("> remove #Remove the book by ID");
        System.out.println("> showAll #Show all books");
        System.out.println("> filter #Show books by filter");
        System.out.println();
        System.out.println("> limit #Set Limit to show");
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");
    }

    private void setLimit() {
        System.out.println("Current limit: " + limitToShow);
        limitToShow = promptInt("Enter new limit: ");
    }

    private Book createNewBook() {
        return new Book(
                0,
                promptLine("Name:"),
                promptLine("Author:"),
                promptLine("Publisher"),
                promptDate("Publish Date:"),
                promptInt("Pages:"),
                promptDouble("Price:")
        );
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

    private void printBooks(List<Book> books) {
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
                    "jdbc:mysql://localhost:3306/books_java_semester4_lab3",
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
}
