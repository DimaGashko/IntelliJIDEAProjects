package com.labs.lab_s4_1;

import java.io.*;
import java.util.HashSet;

public class Lab1 {
    private static final String USERS_STORAGE_URL = "src/com/labs/lab_s4_1/users";

    private HashSet<User> users = new HashSet<>();

    public static void main(String[] args) {
        var lab = new Lab1();
        lab.run();
    }

    public void run() {

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

}
