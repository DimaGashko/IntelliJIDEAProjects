package com.labs.lab3.part2;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.models.storage.TextStorage;

import java.util.Arrays;

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
        int moreThen = promptInt("If the length more then: ");

        String cleaned = new Cleaner().deleteFromWords(text, sub, moreThen - 1);
        System.out.println(cleaned);
    }
}
