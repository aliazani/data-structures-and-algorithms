package io.github.aliazani.string_manipulation;

import java.util.*;
import java.util.stream.Collectors;

public class StringUtils {
    public static int countVowels(String str) {
        Set<Character> vowels = Set.of('a', 'e', 'o', 'u', 'i');
        int count = 0;
        for (char ch : str.toLowerCase().toCharArray())
            if (vowels.contains(ch)) count++;

        return count;
    }

    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            reversed.append(str.charAt(i));

        return reversed.toString();
    }

    public static String reverseWords(String str) {
        String[] words = str.trim().split(" ");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }

    public static boolean areRotations(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        if (str1.isEmpty()) return true;
        int length = str1.length();
        int index = str1.concat(str1).indexOf(str2);
        return index != -1 && index < length;
    }

    public static String removeDuplicate(String str) {
        StringBuilder strBuilder = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        for (char ch : str.toLowerCase().toCharArray())
            if (seen.add(ch)) strBuilder.append(ch);

        return strBuilder.toString();
    }

    public static Character findMostRepeatedCharacter(String str) {
//        final int ASCII_SIZE = 256;
//        int[] frequencies = new int[ASCII_SIZE];
//        for (char ch : str.toLowerCase().toCharArray())
//            frequencies[ch]++;
        Map<Character, Integer> frequencies = new HashMap<>();

        for (char ch : str.toLowerCase().toCharArray())
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);

        return frequencies.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static String capitalize(String str) {
        if (str.trim().isEmpty()) return "";

        return Arrays.stream(str.trim().replaceAll("(\\s{2,})", " ").split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        char[] charArray1 = str1.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

}