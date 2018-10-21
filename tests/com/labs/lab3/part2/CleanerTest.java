package com.labs.lab3.part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleanerTest {

    @BeforeEach
    void beforeEach() {
        var cleaner = new Cleaner();
    }

    @TestFactory
    public Collection<DynamicTest> dynamicTests() {
        String[] texts = {"Hello", "Yes", "No"};
        String[] deletes = {"Hello", "Yes", "No"};
        int[] mins = {2,2,2};

        String[] results = {"Hello", "Yes", "No"};

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        var cleaner = new Cleaner();

        for (int i = 0; i < texts.length; i++) {
            var text = texts[i];
            var delete = deletes[i];
            var min = mins[i];

            var expected = results[i];
            var actual = cleaner.deleteFromWords(text, delete, min);

            Executable exec = () -> assertEquals(expected, actual);
            var name = text + "|" + delete + "|" + min;

            var test = DynamicTest.dynamicTest(name, exec);
            dynamicTests.add(test);
        }

        return dynamicTests;
    }

    @Test
    void deleteFromWords() {

    }
}