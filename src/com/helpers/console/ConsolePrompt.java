package com.helpers.console;

import java.util.Scanner;

public class ConsolePrompt {
    static private Scanner scanner = new Scanner(System.in);

    static public String promptLine(String title) {
        System.out.println(title);
        return scanner.nextLine();
    }

    static public int promptInt(String title) {
        System.out.println(title);
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }

    static public double promptDouble(String title) {
        System.out.println(title);
        double res = scanner.nextDouble();
        scanner.nextLine();
        return res;
    }

}
