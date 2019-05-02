package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

import static java.lang.Math.abs;

public class IterativeMethod implements ILinearSystemSolver {
    private int size;
    private double eps = 0.00000001;

    private GetResidual getResidual = new GetResidual();

    @Override
    public Vector execute(Matrix A, Vector B) {
        size = B.getSize();

        Vector b = prepareB(A, B);
        Vector x = new Vector(b);
        Matrix a = prepareA(A, B);

        while (!isDone(A, x, B)) {
            x = nextStep(a, x, b);
        }

        return x;
    }

    private Vector nextStep(Matrix a, Vector x, Vector b) {
        Vector res = Matrix.mulMatToVec(a, x);

        for (int i = 0; i < size; i++) {
            res.set(i, res.get(i) + b.get(i));
        }

        return res;
    }

    private boolean isDone(Matrix A, Vector X, Vector B) {
        var residual = getResidual.execute(A, X, B);

        double max = abs(residual.get(0));

        for (int i = 1; i < size; i++) {
            double cur = abs(residual.get(i));

            if (cur > max) {
                max = cur;
            }
        }

        return max < eps;
    }

    private Matrix prepareA(Matrix A, Vector B) {
        Matrix a = new Matrix(A);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double newVal = 0;

                if (i != j) {
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

    public void setEps(double eps) {
        this.eps = eps;
    }

}
