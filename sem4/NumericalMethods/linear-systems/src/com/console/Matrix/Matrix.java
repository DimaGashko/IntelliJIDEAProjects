package com.console.Matrix;

import static com.console.ConsolePrompt.promptInt;

public class Matrix {

    protected int n;
    protected int m;

    protected int[][] matrix;

    public Matrix(int n, int m) {
        setN(n);
        setM(m);

        init();
    }

    public int get(int i, int j) {
        if (i >= n || i < 0) return 0;
        if (j >= m || j < 0) return 0;

        return matrix[i][j];
    }

    public void set(int i, int j, int val) {
        if (i >= n || i < 0) return;
        if (j >= m || j < 0) return;

        matrix[i][j] = val;
    }

    public int[][] getInternal() {
        return matrix;
    }

    private void init() {
        matrix = new int[n][m];
    }

    public int getN() {
        return n;
    }

    private void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    private void setM(int m) {
        this.m = m;
    }

    public void enter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int next = promptInt(String.format("[%d][%d]", i, j));
                set(i, j, next);
            }
        }

    }
}
