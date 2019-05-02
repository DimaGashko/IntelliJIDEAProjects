package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

public class IterativeMethod implements ILinearSystemSolver {

    @Override
    public Vector execute(Matrix A, Vector B) {

        return B;
    }

}
