package com;

import com.Matrix.Matrix;
import com.Matrix.Vector;
import com.linearSystem.GaussMethodCommand;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    private GaussMethodCommand GaussMethod = new GaussMethodCommand();

    private Matrix A;
    private Vector B;
    private int size;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        init();
        runCalc();
    }

    private void runCalc() {
       runGauss();
    }

    private void runGauss() {
        Vector x = GaussMethod.execute(A, B);

        System.out.println("Gauss Elimination Method: ");
        System.out.println(x);
    }

    private void init() {
        size = promptInt("Enter the number of equations:");
        A = askForA();
        B = askForB();
    }

    private Matrix askForA() {
        System.out.println("Enter elements of A matrix: ");

        Matrix matrix = new Matrix(size, size);
        matrix.enter();

        return matrix;
    }

    private Vector askForB() {
        System.out.println("Enter elements of B matrix: ");

        Vector vector = new Vector(size);
        vector.enter();

        return vector;
    }

}
