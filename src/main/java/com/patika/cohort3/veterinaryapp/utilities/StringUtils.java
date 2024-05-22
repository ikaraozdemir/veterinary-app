package com.patika.cohort3.veterinaryapp.utilities;

public final class StringUtils {
    private StringUtils(){}

    public static String normalizeSpaces(String input) {
        if (input == null) {
            return null;
        }
        input = input.trim();
        input.toLowerCase();
        String[] words = input.split("\\s+");
        return String.join(" ", words);
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
