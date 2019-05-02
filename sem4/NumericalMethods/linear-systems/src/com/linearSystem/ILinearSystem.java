package com.linearSystem;

import com.Matrix.Matrix;
import com.Matrix.Vector;

public interface ILinearSystem {
    Vector execute(Matrix A, Vector B);
}
