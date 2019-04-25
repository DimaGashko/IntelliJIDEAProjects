package com;

import com.console.Matrix.Matrix;
import com.console.Matrix.Vector;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    Matrix A;
    Vector B;
    int size;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        init();
        runCalc();
    }

    private void runCalc() {

    }

    private void init() {
        int size = promptInt("Enter the number of equations:");
        Matrix A = askForA();
        Vector B = askForB();
    }

    private Matrix askForA() {
        System.out.println("Enter elements of the A matrix: ");

        Matrix matrix = new Matrix(size, size);
        matrix.enter();

        return matrix;
    }

    private Vector askForB() {
        System.out.println("Enter elements of a B matrix: ");

        Vector vector = new Vector(size);
        vector.enter();

        return vector;
    }

}
