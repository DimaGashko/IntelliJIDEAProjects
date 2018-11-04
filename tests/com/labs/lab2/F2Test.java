package com.labs.lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    // - - - CalcY - - -
    @Test
    void testCalcYX1() {
        double expected = 7.20397873;
        double result = F2.calcY(0.2, 5);
        assertEquals(expected, result, 0.001, "По первому уравнению: caclY(0.2, 5)");
    }

    @Test
    void testCalcYX2() {
        double expected = -27.44;
        double result = F2.calcY(0.4, -5);
        assertEquals(expected, result, 0.001, "По второму уравнению: caclY(0.4, -5)");
    }

    @Test
    void testCalcYX3() {
        double expected = -0.855279258;
        double result = F2.calcY(3, 2);
        assertEquals(expected, result, 0.001, "По третьему уравнению: caclY(0.4, -5)");
    }

    @Test
    void testCalcYBorder1() {
        double expected = 1.369;
        double result = F2.calcY(0.3000000000001, 1);
        assertEquals(expected, result, 0.001, "x == 0.3000..001 должен считаться по первому уравнению");
    }

    @Test
    void testCalcYBorder2() {
        double expected = 6.09;
        double result = F2.calcY(2.300000000001, 1);
        assertEquals(expected, result, 0.001, "x == 2.300..01 должен считаться по второму уравнению");
    }

    // - - - TabulationSteps - - -
    @Test
    void testGetTabulationStepsInt() {
        var f2 = new F2(1, 10, 1, 0);
        double expected = 10;
        assertEquals(expected, f2.getStepsCount(), 0.001, "start: 1, end: 10, step: 1 -> 10");
    }

    @Test
    void testGetTabulationNegSteps() {
        var f2 = new F2(10, 1, 1, 0);
        double expected = 10;
        assertEquals(expected, f2.getStepsCount(), 0.001, "start: 1, end: 10, step: 1 -> 10");
    }

    @Test
    void testGetTabulationStepsFloat() {
        var f2 = new F2(0.1, 1.1, 0.1, 0);
        double expected = 11;
        assertEquals(expected, f2.getStepsCount(), 0.001, "start: 0.1, end: 1.1, step: 0.1 -> 11");
    }

    @Test
    void testGetTabulationStepsOut() {
        var f2 = new F2(1, 12, 2, 0);
        double expected = 6;
        assertEquals(expected, f2.getStepsCount(), 0.001, "start: 1, end: 12, step: 2 -> 6");
    }

    @Test
    void testGetTabulationStepsNegative() {
        var f2 = new F2(-10, -1, 2, 0);
        double expected = 5;
        assertEquals(expected, f2.getStepsCount(), 0.001, "start: -10, end: -1, step: 2 -> 5");
    }

    // - - - getX - - -
    @Test
    void testGetAllX() {
        var f2 = new F2(1, 5, 1, 0);
        double[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, f2.getAllX(), 0.001, "getAllX(1, 5, 1) -> {1,2,3,4,5}");
    }

    // - - - Tabulation - - -
    @Test
    void testGetAllY50() {
        var f2 = new F2(0.2, 2.8, 0.002, 2.3);
        double expected = 3.1487; //x = 0.3
        assertEquals(expected, f2.getAllY()[50], 0.001, "allY[50]");
    }

    @Test
    void testGetAllY1050() {
        var f2 = new F2(0.2, 2.8, 0.002, 2.3);
        assertEquals(13.89, f2.getAllY()[1050], 0.001, "allY[1050]");
    }

    @Test
    void testGetAllY1300() {
        var f2 = new F2(0.2, 2.8, 0.002, 2.3);
        assertEquals(-2.4532, f2.getAllY()[1300], 0.001, "allY[1300]");
    }

    // - - - getMinMax - - -
    @Test
    void testGetIndexOfMin() {
        var f2 = new F2();
        double[] arr = {1, -2, 2};

        double expected = 1;
        double result = f2.getIndexOfMin(arr);
        f2.getIndexOfMin(arr);
        assertEquals(expected, result, 0.001, "getIndexOfMin({1,-2,2}) -> 1");
    }

    @Test
    void testGetIndexOfMax() {
        var f2 = new F2();
        double[] arr = {1, 2, -2};

        double expected = 1;
        double result = f2.getIndexOfMax(arr);
        assertEquals(expected, result, 0.001, "getIndexOfMax({1,2,-2}) -> 1");
    }

    // - - - Average - - -
    @Test
    void testGetSumOfElements() {
        var f2 = new F2();
        double[] arr = {1,2,3,4};
        double expected = 10;
        double result = f2.getSumOfElements(arr);
        assertEquals(expected, result, 0.001, "getSumOfElements({1,2,3,4}) -> 10");
    }

    @Test
    void testGetAverage() {
        var f2 = new F2();
        double[] arr = {1,2,3,4};
        double expected = 2.5;
        double result = f2.getAverage(arr);
        assertEquals(expected, result, 0.001, "getAverage({1,2,3,4}) -> 2.5");
    }

}