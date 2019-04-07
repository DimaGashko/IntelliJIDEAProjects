package com.labs.lab2;

import static java.lang.Math.*;

public class F2 {
    private double minX;
    private double maxX;
    private double step;
    private double a;

    private int stepsCount;
    private double[] allX;
    private double[] allY;

    static private double MAX_A = 1000;
    static private double MAX_X = 1000;
    static private double MIN_X = -1000;
    static private double MIN_STEP = 0.001;

    public F2() {
        setMinX(0);
        setMaxX(2.8);
        setStep(0.001);
        setA(2.3);

        update();
    }

    public F2(double minX, double maxX, double step, double a) {
        setMinX(minX);
        setMaxX(maxX);
        setStep(step);
        setA(a);

        update();
    }

    public void update() {
        updateTabulationStepsCount();
        updateAllX();
        updateAllY();
    }

    public double getMinY() {
        return allY[getIndexOfMin(allY)];
    }

    public double getMaxY() {
        return allY[getIndexOfMax(allY)];
    }

    public double getSumOfAllY() {
        return getSumOfElements(allY);
    }

    public double[] getAllX() {
        return allX;
    }

    public double[] getAllY() {
        return allY;
    }

    public double getAverageY() {
        return getAverage(allY);
    }

    private void updateAllX() {
        allX = new double[stepsCount];

        for (int i = 0; i < stepsCount; i++) {
            double res = minX + i * step;
            allX[i] = round(res * 10000.0) / 10000.0;
        }
    }

    private void updateAllY() {
        allY = new double[allX.length];

        for (int i = allX.length - 1; i >= 0; i--) {
            allY[i] = calcY(allX[i], a);
        }
    }

    private void updateTabulationStepsCount() {
        double realStep = (maxX > minX) ? step : -step;
        stepsCount = (int)abs(((maxX - minX) / realStep + 0.000001) + 1);
    }

    // - - -

    double getAverage(double[] arr) {
        return getSumOfElements(arr) / arr.length;
    }

    double getSumOfElements(double[] arr) {
        double res = 0;

        for (double item : arr) {
            res += item;
        }

        return res;
    }

    int getIndexOfMin(double[] arr) {
        double min = arr[0];
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }

        return index;
    }

    int getIndexOfMax(double[] arr) {
        double max = arr[0];
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        return index;
    }

    public static double calcY(double x, double a, double epsilon) {
        if (x - 0.3 <= epsilon) {
            return 1.5 * a * cos(x) * cos(x);

        } else if (x - 2.3 <= epsilon) {
            return (x - 2) * (x - 2) + (6 * a);

        } else {
            return 3 * a * tan(x);
        }
    }

    public static double calcY(double x, double a) {
        return calcY(x, a, 0.001);
    }

    // getters/setters

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        if (minX < MIN_X) minX = MIN_X;
        if (minX > MAX_X) minX = MAX_X;
        this.minX = minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        if (maxX < MIN_X) maxX = MIN_X;
        if (maxX > MAX_X) maxX = MAX_X;
        this.maxX = maxX;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (step <= MIN_STEP) step = MIN_STEP;
        this.step = step;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        if (a > MAX_A) a = MAX_A;
        this.a = a;
    }

    public double getStepsCount() {
        return stepsCount;
    }
}





