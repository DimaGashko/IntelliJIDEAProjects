package com.labs.lab3.part2;

import java.util.regex.Pattern;

public class Cleaner {
    public static Pattern WORD_PATTERN = Pattern.compile("(?i)[a-zа-яёіїє\\d\\-_']+");

    public String deleteFromWords(String text, String delete, int minLen) {
        return WORD_PATTERN.matcher(text).replaceAll((matchResult) -> {
            String match = matchResult.group();



            return match;
        });
    }

}
