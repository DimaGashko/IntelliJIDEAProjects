package com.helpers.console;

import java.util.Scanner;

public class ConsolePrompt {
    static private Scanner scanner = new Scanner(System.in);

    static public int promptInt(String title) {
        System.out.println(title);
        return scanner.nextInt();
    }

    static public double promptDouble(String title) {
        System.out.println(title);
        return scanner.nextDouble();
    }

    static public String promptLine(String title) {
        System.out.println(title);
        String res = scanner.nextLine();

        if (res.isEmpty()) {
            res = scanner.nextLine();
        }

        return res;
    }

}
