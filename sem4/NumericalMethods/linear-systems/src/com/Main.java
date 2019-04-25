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
        int size = promptInt("Enter the number of equations:");
        Matrix A = askForA();
        Vector B = askForB();


    }

    private Matrix askForA() {
        int n = promptInt("Enter the number of equations: ");
        System.out.println("Enter elements of the matrix: ");

        Matrix matrix = new Matrix(n, n);
        matrix.enter();

        return matrix;
    }

    private Vector askForB() {
        int size = promptInt("Enter vector size: ");
        System.out.println("Enter elements of a the vector: ");

        Vector vector = new Vector(size);
        vector.enter();

        return vector;
    }

}
