package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

import static java.lang.Math.abs;

public class IterativeMethod {
    private int size;
    private double eps = 0.00000001;

    private GetResidual getResidual = new GetResidual();

    public Vector executeIterative(Matrix A, Vector B, double eps) {
        size = B.getSize();
        this.eps = eps;

        Vector b = prepareB(A, B);
        Vector x = new Vector(b);
        Matrix a = prepareA(A, B);

        while (!isDone(A, x, B)) {
            x = nextIterativeStep(a, x, b);
        }

        return x;
    }

    public Vector executeGaussSeidel(Matrix A, Vector B, double eps) {
        size = B.getSize();
        this.eps = eps;

        Vector b = prepareB(A, B);
        Vector x = new Vector(b);
        Matrix a = prepareA(A, B);

        while (!isDone(A, x, B)) {
            x = nextGaussSeidelStep(a, x, b);
        }

        return x;
    }

    private Vector nextGaussSeidelStep(Matrix a, Vector x, Vector b) {
        Vector res = new Vector(x);

        for (int i = 0; i < size; i++) {
            double newVal = 0;

            for (int j = 0; j < size; j++) {
                newVal += res.get(j) * a.get(i, j);
            }

            res.set(i, newVal);
        }

        for (int i = 0; i < size; i++) {
            res.set(i, res.get(i) + b.get(i));
        }

        return res;
    }

    private Vector nextIterativeStep(Matrix a, Vector x, Vector b) {
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
                    //System.out.println(A.get(i, j) + " " +A.get(i, i) +  " " + A.get(i, j) / A.get(i, i));
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
