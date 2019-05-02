package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

import static java.lang.Math.abs;

public class GaussianEliminationMethod {
    private Matrix A;
    private Vector B;
    private Vector X;
    private int n;

    public Vector execute(Matrix A, Vector B) {
        this.A = A;
        this.B = B;
        this.n = A.getN();

        forward();
        back();

        return X;
    }

    private void forward() {
        // Step
        for (int i = 0; i < n - 1; i++) {
            selectMainElement(i);
            double main = A.get(i, i);

            // Line
            for (int j = i + 1; j < n; j++) {
                double k = A.get(j, i) / main;

                // Element of A
                for (int t = i; t < n; t++) {
                    A.set(j, t, A.get(j, t) - A.get(i, t) * k);
                }

                // Element of B
                B.set(j, B.get(j, 0) - B.get(i) * k);
            }

        }
    }

    private void back() {
        Vector X = new Vector(n);

        for (int i = n - 1; i >= 0; i--) {
            double base = 0;


            for (int j = i + 1; j < n; j++) {
                base += A.get(i, j) * X.get(j);
            }

            double x = (B.get(i) - base) / A.get(i, i);
            X.set(i, x);
        }

        this.X = X;
    }

    private void selectMainElement(int step) {
        double maxV = abs(A.get(step, step));
        int maxIndex = step;

        for (int i = step + 1; i < n; i++) {
            double cur = abs(A.get(i, step));

            if (cur > maxV) {
                maxV = cur;
                maxIndex = i;
            }

        }

        A.swapLines(step, maxIndex);
        B.swapLines(step, maxIndex);
    }

}
