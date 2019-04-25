package com.Matrix;

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
            set(i, matrix.get(i, 0));
        }

    }

    public void set(int index, int value) {
        super.set(index, 0, value);
    }

    public void get(int index) {
        super.get(index, 0);
    }

    @Override
    public void enter() {
        for (int i = 0; i < size; i++) {
            int next = promptInt(String.format("[%d]", i + 1));
            set(i, next);
        }

    }

}
