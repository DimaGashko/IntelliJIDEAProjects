package com;

public class Matrix {

    private int n;
    private int m;

    private int[][] matrix;

    public Matrix(int n, int m) {
        setN(n);
        setM(m);

        init();
    }

    private int get(int i, int j) {
        if (i >= n || i < 0) return 0;
        if (j >= m || j < 0) return 0;

        return matrix[i][j];
    }

    public void set(int i, int j, int val) {
        if (i >= n || i < 0) return;
        if (j >= m || j < 0) return;

        matrix[i][j] = val;
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
}
