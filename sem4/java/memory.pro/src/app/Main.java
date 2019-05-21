package app;

import java.util.Optional;

import static security.PasswordUtils.*;

public class Main {

    public static void main(String[] args) {
        String password = "Hello!!!!";
        String salt = generateSalt(512);
        String key = hashPassword(password, salt).get();

        String input = "asdfasdfsa";
        System.out.println(key);
        System.out.println(verifyPassword(input, key, salt) ? "Correct" : "Incorrect");
    }

}
