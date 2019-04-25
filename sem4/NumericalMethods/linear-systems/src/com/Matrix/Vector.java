package com.Matrix;

import static com.console.ConsolePrompt.promptInt;

public class Vector extends Matrix {

    public Vector(int size) {
        super(1, size);
    }

    public Vector(Vector matrix) {
        setN(matrix.getN());
        setM(matrix.getM());

        init();

        for (int i = 0; i < getM(); i++) {
            set(i, matrix.get(0, i));
        }

    }

    public void set(int index, int value) {
        super.set(0, index, value);
    }

    public void get(int index) {
        super.get(0, index);
    }

    @Override
    public void enter() {
        for (int i = 0; i < m; i++) {
            int next = promptInt(String.format("[%d]", i + 1));
            set(i, next);
        }

    }

}
