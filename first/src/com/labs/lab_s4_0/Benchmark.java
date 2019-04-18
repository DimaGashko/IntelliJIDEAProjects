package com.labs.lab_s4_0;

import java.util.ArrayList;
import java.util.List;

public class Benchmark {

    private int testLen;
    private int testRepeat;

    public static void main(String[] args) {
        var benchmark = new Benchmark(10000, 10);
        benchmark.run();
    }

    public Benchmark() {
        setTestLen(10000);
        setTestRepeat(5);
    }

    public Benchmark(int testLen, int testRepeat) {
        setTestLen(testLen);
        setTestRepeat(testRepeat);
    }

    public void run() {
        int endTime = test((arr) -> arr.remove(arr.size() - 1), "from end");
        int middleTime = test((arr) -> arr.remove(arr.size() / 2), "from the middle");
        int beginTime = test((arr) -> arr.remove(0), "from the beginning");

        System.out.println("Remove item from end: " + endTime + "ns");
        System.out.println("Remove item from the middle: " + middleTime + "ns");
        System.out.println("Remove item from the beginning: " + beginTime + "ns");
    }

    private int test(TestFunc<List<Integer>> testFunc, String testName) {
        System.out.println("Test: \"" + testName + "\"");
        int res = 0;

        for (int i = 0; i < testRepeat; i++) {
            System.out.println((i + 1) + " / " + testRepeat);

            var arr = getTestArray(testLen);
            int size = arr.size();

            long startTime = System.nanoTime();

            while (arr.size() > 0) {
                testFunc.apply(arr);
            }

            res += (System.nanoTime() - startTime) / size;
        }

        return res / testRepeat;
    }

    private ArrayList<Integer> getTestArray(int len) {
        ArrayList<Integer> arr = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            arr.add((int)(Math.random() * 100));
        }

        return arr;
    }

    public int getTestLen() {
        return testLen;
    }

    public void setTestLen(int testLen) {
        this.testLen = testLen;
    }

    public int getTestRepeat() {
        return testRepeat;
    }

    public void setTestRepeat(int testRepeat) {
        this.testRepeat = testRepeat;
    }

    @FunctionalInterface
    private interface TestFunc<T> {
        void apply(T t);
    }

}
