package com.labs.lab0_s4;

import java.util.ArrayList;

public class Benchmark {

    private int testLen = 100000;

    public static void main(String[] args) {
        var benchmark = new Benchmark();
        benchmark.run();
    }

    public void run() {
        double beginTime = testRemoveFromBegin();
        double middleTime = testRemoveFromMiddle();
        double endTime = testRemoveFromEnd();

        System.out.println("Remove item from the beginning: " + beginTime + "ns");
        System.out.println("Remove item from the middle: " + middleTime + "ns");
        System.out.println("Remove item from end: " + endTime + "ns");
    }

    private double testRemoveFromBegin() {
        var arr = getTestArray(testLen);
        long startTime = System.nanoTime();

        arr.remove(0);

        return (double)(System.nanoTime() - startTime);
    }

    private double testRemoveFromMiddle() {
        var arr = getTestArray(testLen);
        long startTime = System.nanoTime();

        arr.remove(arr.size() / 2);

        return (double)(System.nanoTime() - startTime);
    }

    private double testRemoveFromEnd() {
        var arr = getTestArray(testLen);
        long startTime = System.nanoTime();

        arr.remove(arr.size() - 1);

        return (double)(System.nanoTime() - startTime);
    }

    private ArrayList<Integer> getTestArray(int len) {
        ArrayList<Integer> arr = new ArrayList<>(len);

        arr.add((int)(Math.random() * 100));

        return arr;
    }

}
