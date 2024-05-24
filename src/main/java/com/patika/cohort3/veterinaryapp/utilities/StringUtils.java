package com.patika.cohort3.veterinaryapp.utilities;

public final class StringUtils {
    private StringUtils(){}

//    public static String normalizeSpaces(String input) {
//        if (input == null) {
//            return null;
//        }
//        input = input.trim();
//        input.toLowerCase();
//        String[] words = input.split("\\s+");
//        return String.join(" ", words);
//    }

    public static String normalizeSpaces(String input) {
        if (input == null) {
            return null;
        }
        input = input.trim().toLowerCase();
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
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
