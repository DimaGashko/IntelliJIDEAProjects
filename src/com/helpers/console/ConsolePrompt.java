package com.helpers.console;

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
        return scanner.nextDouble();
    }

    /**
     * Запрашивает у пользователя ответ типа Boolean
     *
     * @param title Текст, что будет показан пользователю в запрос
     */
    static public boolean promptBool(String title) {
        System.out.println(title);
        return scanner.nextBoolean();
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
