package com;

import com.Matrix.Matrix;
import com.Matrix.Vector;
import com.linearSystem.GaussMethodCommand;
import com.linearSystem.GetResidualCommand;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    private GaussMethodCommand GaussMethod = new GaussMethodCommand();
    private GetResidualCommand getResidualCommand = new GetResidualCommand();

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
        Vector X = GaussMethod.execute(new Matrix(A), new Vector(B));
        var residual = getResidualCommand.execute(A, X, B);

        System.out.println("Gauss Elimination Method: ");
        System.out.println(X);
        System.out.println("Residual:");
        System.out.println(residual);
    }

    protected void init() {
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

/*

1.16 1.3 1.14    0.83 -0.48 -2.44    2 -0.16 1.3    0.43 -0.15 1.5

*/