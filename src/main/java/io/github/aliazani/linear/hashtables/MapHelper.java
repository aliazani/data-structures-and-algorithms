package io.github.aliazani.linear.hashtables;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Helper class for common operations on maps and collections.
 */
@Slf4j
public class MapHelper {
    private MapHelper() {
    }

    /**
     * Finds the first non-repeating character in the given string.
     *
     * @param str the input string
     * @return the first non-repeating character, or {@link Character#MIN_VALUE} if no such character exists
     */
    public static char findFirstNonRepeatingCharacter(String str) {
        Map<Character, Integer> alphabets = new HashMap<>();
        char[] charArray = str.toLowerCase().toCharArray();

        for (char ch : charArray) alphabets.put(ch, alphabets.getOrDefault(ch, 0) + 1);

        for (char ch : charArray)
            if (alphabets.get(ch) == 1) return ch;

        return Character.MIN_VALUE;
    }

    /**
     * Finds the first repeated character in the given string.
     *
     * @param str the input string
     * @return the first repeated character, or {@link Character#MIN_VALUE} if no such character exists
     */
    public static char findFirstRepeatedCharacter(String str) {
        Set<Character> alphabets = new HashSet<>();
        char[] chars = str.toLowerCase().toCharArray();

        for (char ch : chars)
            if (!alphabets.add(ch)) return ch;

        return Character.MIN_VALUE;
    }

    /**
     * Finds the most frequent number in the given array.
     *
     * @param numbers the input array of numbers
     * @return the most frequent number
     */
    public static int mostFrequent(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int number : numbers)
            map.put(number, map.getOrDefault(number, 0) + 1);

        int frequent = -1;
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (entry.getValue() > frequent) {
                frequent = entry.getValue();
                result = entry.getKey();
            }

        return result;
    }

    /**
     * Counts the number of pairs in the given array with the specified difference.
     *
     * @param numbers    the input array of numbers
     * @param difference the target difference
     * @return the number of pairs with the specified difference
     */
    public static int countPairsWithDiff(int[] numbers, int difference) {
        Set<Integer> uniqueNumbers = Arrays.stream(numbers).boxed().collect(Collectors.toSet());

        int count = 0;
        for (int number : uniqueNumbers)
            if (uniqueNumbers.contains(number + difference)) count++;
        return count;
    }

    /**
     * Finds two numbers in the given array that add up to the target value.
     *
     * @param numbers the input array of numbers
     * @param target  the target value
     * @return an array containing the indices of the two numbers, or null if no such numbers exist
     */
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (Objects.equals(numbers[i] * 2, target)) return new int[]{i, i};
            int complement = target - numbers[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(numbers[i], i);
        }

        return null;
    }
}