package com.patika.cohort3.veterinaryapp.utilities;

import java.util.Locale;

public final class StringUtils {
    private static final Locale TURKISH = Locale.forLanguageTag("tr-TR");

    private StringUtils(){}


    public static String normalizeSpaces(String input) {
        if (input == null) {
            return null;
        }
        input = input.trim().toLowerCase(TURKISH);
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                int firstCharEnd = word.offsetByCodePoints(0, 1);
                result.append(word.substring(0, firstCharEnd).toUpperCase(TURKISH))
                        .append(word.substring(firstCharEnd))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String removeSpaces(String input) {
        if (input == null) {
            return null;
        }
        input = input.trim();
        String[] words = input.split("\\s+");
        return String.join("", words);
    }

}
