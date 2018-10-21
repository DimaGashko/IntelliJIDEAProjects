package com.DG;

import com.labs.lab3.part1.Lab3Part1;
import com.labs.lab3.part2.Lab3Part2;

public class Main {
    public static void main(String[] args) {
       runLab3Part2();
    }

    private static void runLab3Part1() {
        var Lab3Part1 = new Lab3Part1();
        Lab3Part1.run();
    }

    private static void runLab3Part2() {
        var lab3Part2 = new Lab3Part2();
        lab3Part2.run();
    }
}
