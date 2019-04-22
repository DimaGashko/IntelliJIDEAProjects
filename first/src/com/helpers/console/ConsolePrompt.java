package com.helpers.console;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Запрашивает у пользователя данные через консольный интерфейс
 */
public class ConsolePrompt {
    static private Scanner scanner = new Scanner(System.in);

    /**
     * Запрашивает у пользователя число типа int
     *
     * @param title Текст, что будет показан пользователю в запросе
     * @return число типа int
     */
    static public int promptInt(String title) {
        System.out.println(title);

        while (!scanner.hasNextInt()) {
            System.out.println("Error. Try again:");
            scanner.next();
        }

        return scanner.nextInt();
    }

    /**
     * Запрашивает у пользователя число типа double
     *
     * @param title Текст, что будет показан пользователю в запросе
     * @return число типа double
     */
    static public double promptDouble(String title) {
        System.out.println(title);

        while (!scanner.hasNextDouble()) {
            System.out.println("Error. Try again:");
            scanner.next();
        }

        return scanner.nextDouble();
    }

    /**
     * Запрашивает у пользователя ответ типа Boolean
     *
     * @param title Текст, что будет показан пользователю в запрос
     */
    static public boolean promptBool(String title) {
        System.out.println(title + " (y / n)");
        String res;

        while (true) {
            res = scanner.nextLine().trim();

            if (res.equals("y") || res.equals("n")) {
                break;
            }

            if (!res.isEmpty()) {
                System.out.println("Error. Try again:");
            }
        }

        return res.equals("y");
    }

    /**
     * Запрашивает у пользователя строку (с пробелмами)
     *
     * @param title Текст, что будет показан пользователю в запросе
     * @return строка введенная пользователем
     */
    static public String promptLine(String title) {
        System.out.println(title);
        String res = scanner.nextLine();

        if (res.isEmpty()) {
            res = scanner.nextLine();
        }

        return res;
    }

    /**
     * Запрашивает у пользователя дату
     *
     * @param title Текст, что будет показан пользователю в запросе
     * @return дата, введенная пользователем
     */
    static public LocalDate promptDate(String title) {
        System.out.println(title);
        System.out.println("yyyy-mm-dd");
        String strDate = scanner.nextLine();

        while (strDate.isEmpty()) {
            strDate = scanner.nextLine();
        }

        try {
            return LocalDate.parse(strDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date");
            return promptDate(title);
        }

    }

}
