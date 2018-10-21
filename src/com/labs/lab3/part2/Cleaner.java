package com.labs.lab3.part2;

import java.util.regex.Pattern;

public class Cleaner {
    private static Pattern WORD_PATTERN = Pattern.compile("(?i)[a-zа-яёіїє\\d\\-_']+");
    private static final String NO_COUNT = "[\\-_']"; //Символы слова, которые не входят в количетсво букв

    /**
     * Удалает из слов текста text длинной от minLen все вхождения подстроки delete
     * @param text Исходный текст
     * @param delete Подстрока, которую нужно удалить из слов
     * @param minLen Минимальная длина слова
     * @return строка образованная из входной путем удаления в словах указанной подстроки
     */
    public String deleteFromWords(String text, String delete, int minLen) {
        final String deletePattern = "(?i)" + delete;

        return WORD_PATTERN.matcher(text).replaceAll((matchResult) -> {
            String word = matchResult.group();
            String cleanWord = word.replaceAll(NO_COUNT, "");

            if (cleanWord.length() >= minLen) {
                return word.replaceAll(deletePattern, "");
            }

            return word;
        });
    }

}
