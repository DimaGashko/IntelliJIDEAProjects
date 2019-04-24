package com.console.Matrix;

import static com.console.ConsolePrompt.promptInt;

public class Vector extends Matrix {

    public Vector(int size) {
        super(1, size);
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
            int next = promptInt(String.format("[%d]", i));
            set(i, next);
        }

    }

}
