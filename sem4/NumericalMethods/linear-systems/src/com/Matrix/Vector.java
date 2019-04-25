package com.Matrix;

import static com.console.ConsolePrompt.promptDouble;
import static com.console.ConsolePrompt.promptInt;

public class Vector extends Matrix {
    private int size;

    public Vector(int size) {
        super(size, 1);
        this.size = size;
    }

    public Vector(Vector matrix) {
        setN(matrix.getN());
        setM(matrix.getM());
        size = matrix.getN();

        init();

        for (int i = 0; i < size; i++) {
            set(i, 0, matrix.get(i));
        }

    }

    public double get(int i) {
        return super.get(i, 0);
    }

    public void set(int i, double val) {
        super.set(i, 0, val);
    }

    @Override
    public void enter() {
        for (int i = 0; i < size; i++) {
            double next = promptDouble(String.format("[%d]", i + 1));
            set(i, next);
        }

    }

}
