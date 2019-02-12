package com.labs.lab0_s4;

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
        System.out.println("Test: \"from end\"");
        int endTime = test((arr) -> arr.remove(arr.size() - 1));

        System.out.println("Test: \"from the middle\"");
        int middleTime = test((arr) -> arr.remove(arr.size() / 2));

        System.out.println("Test: \"from the beginning\"");
        int beginTime = test((arr) -> arr.remove(0));

        System.out.println("Remove item from end: " + endTime + "ns");
        System.out.println("Remove item from the middle: " + middleTime + "ns");
        System.out.println("Remove item from the beginning: " + beginTime + "ns");

    }

    private int test(TestFunc<List<Integer>> testFunc) {
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
    public interface TestFunc<T> {
        void apply(T t);
    }

}
