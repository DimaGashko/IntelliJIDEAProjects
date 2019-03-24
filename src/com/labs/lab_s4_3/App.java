package com.labs.lab_s4_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.helpers.console.ConsoleElements.hr;
import static com.helpers.console.ConsolePrompt.promptLine;

public class App {
    Connection dbConnection;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        runCli();
    }

    private void runCli() {
        printHelp();
        hr();

        while (true) {
            String command = promptLine("Enter command: ").trim();

            if (command.equalsIgnoreCase("exit")) {
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

        } else if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else {
            System.out.println("Command not found. Try again: ");
        }
    }

    private void addBook() {

    }

    private void removeBook() {

    }

    private void showAllBooks() {

    }

    private void showBooksByFilter() {
        printFiltersHelp();
    }

    private void printFiltersHelp() {
        System.out.println("Filters:");
        System.out.println("> a #Список книг заданого автора в порядку зростання року видання;");
        System.out.println("> b #Список книг, що видані заданим видавництвом");
        System.out.println("> c #Список книг, що випущені після заданого року");
        System.out.println("> d #Список авторів в алфавітному порядку;");
        System.out.println("> e #Список видавництв, книги яких зареєстровані в системі без повторів;");
        System.out.println("> f #Для кожного видавництва визначити список книг, виданих ним");
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("> add #Add new book");
        System.out.println("> remove #Remove the book by ID");
        System.out.println("> showAll #Show all books");
        System.out.println("> filter #Show books by filter");
        System.out.println();
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");
    }

    private void connetectToDb() throws SQLException {
        dbConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/books_java_semester4_lab3",
                "db_user", "qqqqqqqqww");
    }
}
