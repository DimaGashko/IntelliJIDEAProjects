package com.labs.lab3.part2;

import java.util.regex.Pattern;

public class Cleaner {
    private static Pattern WORD_PATTERN = Pattern.compile("(?i)[a-zа-яёіїє\\d\\-_']+");
    private static final String NO_COUNT = "[\\-_']"; //Символы слова, которые не входят в количетсво букв


    public String deleteFromWords(String text, String delete, int minLen) {
        return WORD_PATTERN.matcher(text).replaceAll((matchResult) -> {
            String word = matchResult.group();
            String cleanWord = word.replaceAll(NO_COUNT, "");

            if (cleanWord.length() >= minLen) {
                return word.replaceAll("(?i)" + delete, "");
            }

            return word;
        });
    }

}
