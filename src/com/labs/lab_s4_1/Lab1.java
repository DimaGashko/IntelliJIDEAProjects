package com.labs.lab_s4_1;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import static com.helpers.console.ConsoleElements.hr;
import static com.helpers.console.ConsolePrompt.*;

public class Lab1 {
    private static final String USERS_STORAGE_URL = "src/com/labs/lab_s4_1/users";

    private HashSet<User> users = new HashSet<>();

    public static void main(String[] args) {
        var lab = new Lab1();
        lab.run();
    }

    public void run() {

        try {
            loadUsers();
        } catch (IOException | ClassNotFoundException err) {
            System.out.println("Can't load users from file");
        }

        runCLI();
    }

    private void runCLI() {
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
            var user = getNewUser();
            users.add(user);

            try {
                saveUsers();
                System.out.println(user);
            } catch (IOException err) {
                users.remove(user);
                System.out.println("Can't save users");
            }

        } else if (command.equalsIgnoreCase("remove")) {
            removeElement();

        } else if (command.equalsIgnoreCase("showAll")) {
            printUsers(users);

        } else if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else {
            System.out.println("Command not found. Try again: ");
        }
    }

    private void removeElement() {
        String id = promptLine("Enter user id: ");

        users.removeIf((user) -> user.getId().equals(id));

        try {
            saveUsers();
            System.out.println("Successfully");
        } catch (IOException err) {
            System.out.println("Can't apply changes");
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("> add #Add new user");
        System.out.println("> remove #Remove the user by ID");
        System.out.println("> showAll #Show all users");
        System.out.println();
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");
    }

    private User getNewUser() {
        return new User(
                promptLine("Id:"),
                promptLine("First Name:"),
                promptLine("Last Name:"),
                promptLine("Country"),
                promptInt("Age:"),
                promptBool("Online:"),
                LocalDate.of(promptInt("Registered year:"), 1, 1)
        );
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
        users.forEach((item) -> System.out.println("\t" + item));
    }
}
