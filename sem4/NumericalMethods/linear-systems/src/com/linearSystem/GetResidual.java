package com.linearSystem;

import static java.lang.Math.abs;

import com.Matrix.Matrix;
import com.Matrix.Vector;

public class GetResidual {

    public Vector execute(Matrix A, Vector X, Vector B) {
        Vector XA = Matrix.mulMatToVec(A, X);
        Vector residual = new Vector(B.getSize());

        for (int i = 0; i < B.getSize(); i++) {
            residual.set(i, abs(XA.get(i) - B.get(i)));
        }

        return residual;
    }

}
