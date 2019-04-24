package com;

import com.console.Matrix.Matrix;
import com.console.Matrix.Vector;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Matrix matrix = askForMatrix();
        Vector vector = askForVector();

    }

    private Matrix askForMatrix() {
        int m = promptInt("Enter the number of rows: ");
        int n = promptInt("Enter the number of columns: ");
        System.out.println("Enter elements of the matrix: ");

        Matrix matrix = new Matrix(m, n);
        matrix.enter();

        return matrix;
    }

    private Vector askForVector() {
        int size = promptInt("Enter vector size: ");
        System.out.println("Enter elements of a the vector: ");

        Vector vector = new Vector(size);
        vector.enter();

        return vector;
    }

}
