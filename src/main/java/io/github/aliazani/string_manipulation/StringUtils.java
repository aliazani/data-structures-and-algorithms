package io.github.aliazani.string_manipulation;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Utility class for various string manipulation operations.
 */
public class StringUtils {

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private StringUtils() {
    }

    /**
     * Counts the number of vowels in a given string.
     *
     * @param str the string to count vowels in.
     * @return the number of vowels in the string.
     */
    public static int countVowels(String str) {
        Set<Character> vowels = Set.of('a', 'e', 'o', 'u', 'i');
        int count = 0;
        for (char ch : str.toLowerCase().toCharArray())
            if (vowels.contains(ch)) count++;

        return count;
    }

    /**
     * Reverses the characters in a given string.
     *
     * @param str the string to be reversed.
     * @return the reversed string.
     */
    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            reversed.append(str.charAt(i));

        return reversed.toString();
    }

    /**
     * Reverses the words in a given string.
     *
     * @param str the string to reverse the words of.
     * @return the string with reversed words.
     */
    public static String reverseWords(String str) {
        String[] words = str.trim().split(" ");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }

    /**
     * Checks if two strings are rotations of each other.
     *
     * @param str1 the first string.
     * @param str2 the second string.
     * @return true if the strings are rotations of each other, false otherwise.
     */
    public static boolean areRotations(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        if (str1.isEmpty()) return true;
        int length = str1.length();
        int index = str1.concat(str1).indexOf(str2);
        return index != -1 && index < length;
    }

    /**
     * Removes duplicate characters from a given string.
     *
     * @param str the string to remove duplicates from.
     * @return the string with duplicate characters removed.
     */
    public static String removeDuplicate(String str) {
        StringBuilder strBuilder = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        for (char ch : str.toLowerCase().toCharArray())
            if (seen.add(ch)) strBuilder.append(ch);

        return strBuilder.toString();
    }

    /**
     * Finds the most repeated character in a given string.
     *
     * @param str the string to find the most repeated character in.
     * @return the most repeated character, or null if the string is empty.
     */
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

    /**
     * Capitalizes the first letter of each word in a given string.
     *
     * @param str the string to capitalize.
     * @return the string with capitalized words.
     */
    public static String capitalize(String str) {
        if (str.trim().isEmpty()) return "";

        return Arrays.stream(str.trim().replaceAll("(\\s{2,})", " ").split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    /**
     * Checks if two strings are anagrams of each other.
     *
     * @param str1 the first string.
     * @param str2 the second string.
     * @return true if the strings are anagrams, false otherwise.
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;

        char[] charArray1 = str1.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

    /**
     * Checks if two strings are anagrams of each other.
     *
     * @param str1 the first string.
     * @param str2 the second string.
     * @return true if the strings are anagrams, false otherwise.
     */
    public static boolean areAnagrams2(String str1, String str2) {
        if (str1.isEmpty() && str2.isEmpty()) return true;
        else if (str1.isEmpty() || str2.isEmpty()) return false;

        final int ENGLISH_ALPHABETS = 26;
        int[] frequencies = new int[ENGLISH_ALPHABETS];

        for (int i = 0; i < str1.length(); i++)
            frequencies[str1.charAt(i) - 'a']++;

        for (int i = 0; i < str2.length(); i++) {
            int index = str2.charAt(i) - 'a';
            if (frequencies[index] == 0) return false;

            frequencies[index]--;
        }

        return true;
    }

    /**
     * Checks if a given string is a palindrome.
     *
     * @param str the string to check.
     * @return true if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str) {
//        StringBuilder stringBuilder = new StringBuilder(str);
//        stringBuilder.reverse();
//        return stringBuilder.toString().equals(str);
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = cleanedStr.length() - 1;

        while (left < right)
            if (cleanedStr.charAt(left++) != cleanedStr.charAt(right--)) return false;

        return true;
    }
}