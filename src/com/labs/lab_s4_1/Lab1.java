package com.labs.lab_s4_1;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

import static com.helpers.console.ConsoleElements.hr;
import static com.helpers.console.ConsolePrompt.promptInt;
import static com.helpers.console.ConsolePrompt.promptLine;

public class Lab1 {
    private static final String USERS_STORAGE_URL = "src/com/labs/lab_s4_1/users";

    private HashSet<User> users = new HashSet<>();

    public static void main(String[] args) {
        var lab = new Lab1();
        lab.run();
    }

    public void run() {

        runCLI();
    }

    private void runCLI() {
        printHelp();

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
        if (command.equalsIgnoreCase("")) {
            String author = promptLine("Enter the author: ");
            //printBooks(library.getBooksByAuthor(author));

        } else if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else {
            System.out.println("Command not found. Try again: ");
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("> all #Print all users");
        System.out.println();
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");

        hr();
    }

    /**
     * Загружает данные users из файла
     * <b>users при этом перезаписывается данными из файла</b>
     */
    private void loadUsers() throws IOException, ClassNotFoundException {
        try(FileInputStream fis = new FileInputStream(USERS_STORAGE_URL);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            users = (HashSet<User>)ois.readObject();
        }
    }

    /**
     * Записывает коллекцию users в файл
     */
    private void saveUsers() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(USERS_STORAGE_URL);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(users);
        }
    }

    private void printUsers(Collection<User> users) {
        System.out.println("[");

        users.forEach((item) -> {
            System.out.println("\t" + item + ",");
        });

        System.out.println("[");
    }
}
