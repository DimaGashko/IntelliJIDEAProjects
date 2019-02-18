package com.labs.lab_s4_1;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
            addUser();

        } else if (command.equalsIgnoreCase("remove")) {
            removeElement();

        } else if (command.equalsIgnoreCase("showAll")) {
            printUsers(users);

        } else if (command.equalsIgnoreCase("showByFilter")) {
            showUsersByFilter();

        } else if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else {
            System.out.println("Command not found. Try again: ");
        }
    }

    private void addUser() {
        var user = getNewUser();
        users.add(user);

        try {
            saveUsers();
            System.out.println(user);
        } catch (IOException err) {
            users.remove(user);
            System.out.println("Can't save users");
        }
    }

    private void removeElement() {
        String id = promptLine("Enter user id: ");

        var found = users.stream().filter((user -> user.getId().equals(id))).findFirst();

        if (!found.isPresent()) {
            System.out.println("No user have the id");
            return;
        }

        var user = found.get();

        boolean check = promptBool("Are you really want to remove user: \n" + user);
        if (!check) return;;

        users.removeIf((item) -> item.getId().equals(id));

        try {
            saveUsers();
            System.out.println("Successfully");
        } catch (IOException err) {
            System.out.println("Can't apply changes");
        }
    }

    private void showUsersByFilter() {
        printFiltersHelp();
        String filter = promptLine("Select Filter:");

        if (filter.equalsIgnoreCase("a")) {
            String country = promptLine("Country:");

            var result = users.stream().filter(user -> user.getCountry().equalsIgnoreCase(country))
                    .sorted(Comparator.comparing(User::getRegistered, LocalDate::compareTo))
                    .collect(Collectors.toCollection(ArrayList::new));

            printUsers(result);

        } else if (filter.equalsIgnoreCase("b")) {
            String name = promptLine("Name:");

            var result = users.stream().filter(user -> user.getFirstName().equalsIgnoreCase(name))
                    .collect(Collectors.toCollection(ArrayList::new));

            printUsers(result);

        } else if (filter.equalsIgnoreCase("c")) {
          int year = promptInt("Year:");

          var result = users.stream().filter(user -> user.getRegistered().getYear() > year)
                  .collect(Collectors.toCollection(ArrayList::new));

          printUsers(result);

        } else if (filter.equalsIgnoreCase("d")) {
            var result = users.stream().filter(User::isOnline)
                    .sorted(Comparator.comparing(User::getFullName, String::compareTo))
                    .collect(Collectors.toCollection(ArrayList::new));

            printUsers(result);

        } else if (filter.equalsIgnoreCase("e")) {
            var result = users.stream().map(User::getFirstName)
                    .collect(Collectors.toCollection(HashSet::new));

            System.out.println(result);

        } else {
            System.out.println("Can't find the filter");
            return;
        }
    }

    /**

     Comparator.comparing(User::getLastName, String::compareTo)
     .thenComparing(User::getFirstName, String::compareTo)
     .thenComparingInt(User::getAge).compare(this, o)

     */

    private void printFiltersHelp() {
        System.out.println("Filters:");
        System.out.println("> a #Список користувачів заданої країни в порядку зростання дати реєстрації");
        System.out.println("> b #Список користувачів з заданим ім'ям");
        System.out.println("> c #Список користувачів що зареєструвалися після вказаного року");
        System.out.println("> d #Список користувачів online в алфавітному порядку");
        System.out.println("> e #Список імен зареєстрованих користувачів без повторів");
        System.out.println("> f #Список країн і всіх користувачів, що проживають в цих країнах");
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("> add #Add new user");
        System.out.println("> remove #Remove the user by ID");
        System.out.println("> showAll #Show all users");
        System.out.println("> showByFilter #Show users by filter");
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
        if (users.isEmpty()) {
            System.out.println("There are no users");
            return;
        }

        users.forEach((item) -> System.out.println("\t" + item));
    }
}
