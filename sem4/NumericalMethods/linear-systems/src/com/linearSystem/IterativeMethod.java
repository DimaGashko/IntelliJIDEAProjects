package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

public class IterativeMethod implements ILinearSystemSolver {
    private int size;

    @Override
    public Vector execute(Matrix A, Vector B) {
        size = B.getSize();

        Vector x = new Vector(B);
        Vector b = prepareB(A, B);
        Matrix a = prepareA(A, B);

        return B;
    }

    private Matrix prepareA(Matrix A, Vector B) {
        Matrix a = new Matrix(A);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double newVal = 0;

                if (i != j) {
                    System.out.println(A.get(i, j) + "\t" + A.get(i, i));
                    newVal = A.get(i, j) / A.get(i, i);
                    newVal = -newVal;
                }

                a.set(i, j, newVal);
            }
        }

        return a;
    }

    private Vector prepareB(Matrix A, Vector B) {
        Vector b = new Vector(size);

        for (int i = 0; i < size; i++) {
            b.set(i, B.get(i) / A.get(i, i));
        }

        return b;
    }

}
