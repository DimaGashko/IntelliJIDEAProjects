package com;

import com.Matrix.Matrix;
import com.Matrix.Vector;
import com.linearSystem.GaussianEliminationMethod;
import com.linearSystem.GetResidual;
import com.linearSystem.IterativeMethod;

import java.util.Scanner;

import static com.console.ConsolePrompt.promptInt;
import static com.console.ConsoleElements.hr;

public class Main {

    private double eps = 0.0000001;

    private GaussianEliminationMethod gaussMethod = new GaussianEliminationMethod();
    private IterativeMethod iterativeMethod = new IterativeMethod(eps);
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
        runGaussSeidel();
    }

    private void runGauss() {
        Vector X = gaussMethod.execute(new Matrix(A), new Vector(B));
        printResults("Gaussian Elimination Method:", A, X, B);
    }

    private void runIterative() {
        Vector X = iterativeMethod.executeIterative(new Matrix(A), new Vector(B));
        printResults("Iterative Method:", A, X, B);
        System.out.println("Number of Iteration: " + iterativeMethod.getIterCount());
    }

    private void runGaussSeidel() {
        Vector X = iterativeMethod.executeGaussSeidel(new Matrix(A), new Vector(B));
        printResults("Gauss-Seidel Method:", A, X, B);
        System.out.println("Number of Iteration: " + iterativeMethod.getIterCount());
    }

    private void printResults(String title, Matrix a, Vector x, Vector b) {
        var residual = getResidualCommand.execute(a, b, x);

        hr();
        System.out.println(title);
        System.out.println(x);
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
        System.out.println("Enter the equation (each line is a line of A and a B's item at the end)");
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

8 4 2 10
3 5 1 5
3 -2 10 4


27 0.9 -1.5 35
4.5 -28 6.7 26
5.1 3.7 -14 -14


500 1 2 3 4 5 6 7 8 9 100
1 200 9 8 7 6 5 4 3 2 200
2 3 300 1 3 5 4 5 6 2 300
3 5 4 250 1 2 4 5 5 5 400
4 5 2 5 350 4 5 7 8 8 500
5 1 2 3 5 880 1 2 5 5 600
6 8 9 7 1 2 250 7 8 8 700
7 1 2 3 5 4 8 390 1 2 800
8 8 5 1 3 5 4 2 790 1 900
9 7 8 9 8 1 5 3 1 990 100

*/