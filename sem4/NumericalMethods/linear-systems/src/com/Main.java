package com;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Matrix matrix = askForMatrix();

    }

    private Matrix askForMatrix() {
        int m = promptInt("Enter the number of rows: ");
        int n = promptInt("Enter the number of columns: ");
        System.out.println("Enter elements of matrix: ");

        Matrix matrix = new Matrix(m, n);
        Matrix.enterMatrix(matrix);

        return matrix;
    }

}
