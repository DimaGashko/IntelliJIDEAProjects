package com.helpers.console;

import java.util.InputMismatchException;
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
            System.out.println("Error, try again");
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

        boolean success = false;
        double res = 0;

        while (!success) try {
            res = scanner.nextDouble();
            success = true;
        } catch (InputMismatchException err) {

        }

        return res;
    }

    /**
     * Запрашивает у пользователя ответ типа Boolean
     *
     * @param title Текст, что будет показан пользователю в запрос
     */
    static public boolean promptBool(String title) {
        System.out.println(title + " (y / n)");
        String res = "";

        while (!res.equals("y") && !res.equals("n")) {
            res = scanner.nextLine();

            if (res.isEmpty()) {
                res = scanner.nextLine();
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

}
