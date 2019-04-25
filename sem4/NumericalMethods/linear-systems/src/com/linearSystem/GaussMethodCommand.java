package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

import static java.lang.Math.abs;

public class GaussMethodCommand {
    private Matrix A;
    private Matrix B;
    private int n;

    public Vector execute(Matrix A, Vector B) {
        this.A = A;
        this.B = B;
        this.n = A.getN();



        return B;
    }

    private void selectMainElement(int step) {
        int maxV = abs(A.get(step, step));
        int maxIndex = step;

        for (int i = step + 1; i < n; i++) {
            int cur = abs(A.get(i, step));

            if (cur > maxV) {
                maxV = cur;
                maxIndex = i;
            }

        }

        A.swapLines(step, maxIndex);
        B.swapLines(step, maxIndex);
    }

}
