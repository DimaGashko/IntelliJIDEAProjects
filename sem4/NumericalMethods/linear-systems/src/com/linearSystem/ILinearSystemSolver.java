package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

public interface ILinearSystemSolver {
    Vector execute(Matrix A, Vector B);
}
