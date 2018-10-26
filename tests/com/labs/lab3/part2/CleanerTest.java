package com.labs.lab3.part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CleanerTest {
    @BeforeEach
    void beforeEach() {
        var cleaner = new Cleaner();
    }

    @TestFactory
    public Collection<DynamicTest> dynamicTests() {
        String[] texts = {
                "Hello", "heLLO, World", "alL alllLLll", "", "it's my life ii", "mykp kprokpom",
                "сім'я", "abc-cba", "a-a-a-a", "a_a_a", "a'a'a'a"
        };

        String[] deletes = {"l", "L", "L", "l", "i", "kp", "'я", "c", "-", "_", "'"};
        int[] mins = {0,0,4,4,3,0,0,0,0,0,0};

        String[] results = {
                "Heo", "heO, Word", "alL a", "", "t's my lfe ii", "my room", "сім", "ab-ba",
                "aaaa", "aaa", "aaaa"
        };

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        var cleaner = new Cleaner();

        for (int i = 0; i < texts.length; i++) {
            var text = texts[i];
            var delete = deletes[i];
            var min = mins[i];
            var res = results[i];

            var actual = cleaner.deleteFromWords(text, delete, min);

            Executable exec = () -> assertEquals(res, actual);
            var name = text + "|" + delete + "|" + min + " -> " + res;

            var test = DynamicTest.dynamicTest(name, exec);
            dynamicTests.add(test);
        }

        return dynamicTests;
    }

    @Test
    void deleteFromWords() {

    }
}