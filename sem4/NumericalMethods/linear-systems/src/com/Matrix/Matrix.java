package com.Matrix;

import static com.console.ConsolePrompt.promptDouble;

public class Matrix {

    protected int n;
    protected int m;

    protected double[][] matrix;

    public Matrix() {

    }

    public Matrix(int n, int m) {
        setN(n);
        setM(m);

        init();
    }

    public Matrix(Matrix matrix) {
        setN(matrix.getN());
        setM(matrix.getM());

        init();

        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getM(); j++) {
                set(i, j, matrix.get(i, j));
            }
        }
    }

    public double get(int i, int j) {
        if (i >= n || i < 0) return 0;
        if (j >= m || j < 0) return 0;

        return matrix[i][j];
    }

    public void set(int i, int j, double val) {
        if (i >= n || i < 0) return;
        if (j >= m || j < 0) return;

        matrix[i][j] = val;
    }

    public void swapLines(int lineA, int lineB) {
        var _aLine = matrix[lineA];
        matrix[lineA] = matrix[lineB];
        matrix[lineB] = _aLine;
    }

    public double[][] getInternal() {
        return matrix;
    }

    protected void init() {
        matrix = new double[n][m];
    }

    public int getN() {
        return n;
    }

    protected void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    protected void setM(int m) {
        this.m = m;
    }

    public void enter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // TODO: use prompt double
                double next = promptDouble(String.format("[%d][%d]", i + 1, j + 1));
                set(i, j, next);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res.append(matrix[i][j]).append(" ");
            }

            res.append("\n");
        }

        return res.toString();
    }

    static public Vector mulMatToVec(Matrix mat, Vector vec) {
        Vector res = new Vector(vec.getN());

        for (int i = 0; i < vec.getSize(); i++) {
            double iRes = 0;

            for (int j = 0; j < mat.getM(); j++) {
                iRes += vec.get(j) * mat.get(i, j);
            }

            res.set(i, iRes);
        }

        return res;
    }

}

