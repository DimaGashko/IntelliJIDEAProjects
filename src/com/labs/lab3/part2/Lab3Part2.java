package com.labs.lab3.part2;

import static com.helpers.console.ConsolePrompt.promptInt;
import static com.helpers.console.ConsolePrompt.promptLine;

public class Lab3Part2 {
    public static void main(String[] args) {
        var app = new Lab3Part2();
        app.run();
    }

    public void run() {
        String text = promptLine("Input the text: ");
        String sub = promptLine("Need delete: ");
        int minLen = promptInt("If the length more then: ");

        var Cleaner = new Cleaner();

        String cleaned = Cleaner.deleteFromWords(text, sub, minLen);
        System.out.println(cleaned);
    }


}
