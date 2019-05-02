package com;

import com.Matrix.Matrix;
import com.Matrix.Vector;
import com.linearSystem.GaussianEliminationMethod;
import com.linearSystem.GetResidual;
import com.linearSystem.IterativeMethod;

import java.util.Scanner;

import static com.console.ConsolePrompt.promptInt;

public class Main {

    private GaussianEliminationMethod gaussMethod = new GaussianEliminationMethod();
    private IterativeMethod iterativeMethod = new IterativeMethod();
    private GetResidual getResidualCommand = new GetResidual();

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
       runIterative();

    }

    private void runIterative() {
        Vector X = iterativeMethod.execute(new Matrix(A), new Vector(B));
        var residual = getResidualCommand.execute(A, X, B);

        System.out.println("Iterative Method: ");
        System.out.println(X);
        System.out.println("Residual:");
        System.out.println(residual);
    }

    private void runGauss() {
        Vector X = gaussMethod.execute(new Matrix(A), new Vector(B));
        var residual = getResidualCommand.execute(A, X, B);

        System.out.println("Gaussian Elimination Method: ");
        System.out.println(X);
        System.out.println("Residual:");
        System.out.println(residual);
    }

    protected void init() {
        size = promptInt("Enter the number of equations:");
        A = new Matrix(size, size);
        B = new Vector(size);

        enterEquation();
    }

    private void enterEquation() {
        System.out.println("Enter the equation (each row is a A's number and a B's number");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A.set(i, j, scanner.nextDouble());
            }

            B.set(i, scanner.nextDouble());
        }
    }

}

/*



1.16 1.3 1.14       0.43
0.83 -0.48 -2.44    -0.15
2 -0.16 1.3         1.5

0.512823173185892	-0.403279486381378	0.315253027543997

- - - - - - - - -

1 5 3 2 1   2
1 2 4 2 5   2
2 5 1 5 4   5
4 5 1 2 5   1
-1 5 4 2 4  5

-1.0660660660661	0.4204204204204	-0.3123123123123	0.7537537537538	0.3933933933934

- - - -
4 0.24 -0.08 8
0.09 3 -0.15 9
0.04 0.08 -4 20

*/