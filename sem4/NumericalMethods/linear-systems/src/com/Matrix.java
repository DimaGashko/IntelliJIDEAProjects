package com;

public class Matrix {

    private int n;
    private int m;

    public Matrix(int n, int m) {
        setN(n);
        setM(m);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}
