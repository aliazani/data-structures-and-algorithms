package io.github.aliazani.linear.hashtables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapHelperTest {
    @Test
    @DisplayName("findFirstNonRepeatingCharacter - " +
            "When string is empty - " +
            "Should return Character.MIN_VALUE")
    void findFirstNonRepeatingCharacter_emptyString_returnCharacterMinValue() {
        char result = MapHelper.findFirstNonRepeatingCharacter("");

        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    @DisplayName("findFirstNonRepeatingCharacter - " +
            "When all characters are repeated - " +
            "Should return Character.MIN_VALUE")
    void findFirstNonRepeatingCharacter_allCharactersRepeated_returnCharacterMinValue() {
        char result = MapHelper.findFirstNonRepeatingCharacter("abccba");

        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    @DisplayName("findFirstNonRepeatingCharacter - " +
            "When only one non-repeating character is present - " +
            "Should return the non-repeating character")
    void findFirstNonRepeatingCharacter_oneNonRepeatingCharacter_returnThatCharacter() {
        char result = MapHelper.findFirstNonRepeatingCharacter("abcdef");

        assertEquals('a', result);
    }

    @Test
    @DisplayName("findFirstNonRepeatingCharacter - " +
            "When multiple non-repeating characters are present - " +
            "Should return the first non-repeating character")
    void findFirstNonRepeatingCharacter_multipleNonRepeatingCharacter_returnFirstNonRepeatingCharacter() {
        char result = MapHelper.findFirstNonRepeatingCharacter("leetcode");

        assertEquals('l', result);
    }

    @Test
    @DisplayName("findFirstRepeatedCharacter - " +
            "When string is empty - " +
            "Should return Character.MIN_VALUE")
    void findFirstRepeatedCharacter_emptyString_returnCharacterMinValue() {
        char result = MapHelper.findFirstRepeatedCharacter("");
        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    @DisplayName("findFirstRepeatedCharacter - " +
            "When no character is repeated - " +
            "Should return Character.MIN_VALUE")
    void findFirstRepeatedCharacter_noCharacterRepeated_returnCharacterMinValue() {
        char result = MapHelper.findFirstRepeatedCharacter("abcdef");
        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    @DisplayName("findFirstRepeatedCharacter - " +
            "When multiple characters are repeated - " +
            "Should return the first repeated character")
    void findFirstRepeatedCharacter_multipleCharactersRepeated_returnFirstRepeatedCharacter() {
        char result = MapHelper.findFirstRepeatedCharacter("leetcode");

        assertEquals('e', result);
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array is empty - " +
            "Should return 0")
    void mostFrequent_arrayIsEmpty_returnZero() {
        int[] numbers = {};

        assertEquals(0, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array has one number - " +
            "Should return the number")
    void mostFrequent_arrayHasOneNumber_returnTheNumber() {
        int[] numbers = {1};

        assertEquals(1, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array has two numbers and they are different - " +
            "Should return the number with a count of 1")
    void mostFrequent_arrayHasTwoNumbersAndTheyAreDifferent_returnTheNumberWithCountOfOne() {
        int[] numbers = {1, 2};

        assertEquals(1, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array has two numbers and they are the same - " +
            "Should return the number with a count of 2")
    void mostFrequent_arrayHasTwoNumbersAndTheyAreSame_returnTheNumberWithCountOfTwo() {
        int[] numbers = {1, 1};

        assertEquals(1, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array has more than two numbers and multiple numbers have the same count - " +
            "Should return the number that appears first")
    void mostFrequent_arrayHasMultipleNumbersWithSameCount_returnTheFirstNumber() {
        int[] numbers = {1, 2, 2, 3, 3};

        assertEquals(2, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("mostFrequent - " +
            "When the array has more than two numbers and one number has the highest count - " +
            "Should return the number with the highest count")
    void mostFrequent_arrayHasOneNumberWithHighestCount_returnTheNumberWithHighestCount() {
        int[] numbers = {1, 2, 2, 2, 3, 3};

        assertEquals(2, MapHelper.mostFrequent(numbers));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array is empty - " +
            "Should return 0")
    void countPairsWithDiff_arrayIsEmpty_returnZero() {
        int[] numbers = {};
        int difference = 1;

        assertEquals(0, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has one number - " +
            "Should return 0")
    void countPairsWithDiff_arrayHasOneNumber_returnZero() {
        int[] numbers = {1};
        int difference = 1;

        assertEquals(0, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has two numbers and their difference is equal to the given difference - " +
            "Should return 1")
    void countPairsWithDiff_arrayHasTwoNumbersWithDifferenceEqualToGivenDifference_returnOne() {
        int[] numbers = {1, 2};
        int difference = 1;

        assertEquals(1, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has two numbers and their difference is not equal to the given difference - " +
            "Should return 0")
    void countPairsWithDiff_arrayHasTwoNumbersWithDifferenceNotEqualToGivenDifference_returnZero() {
        int[] numbers = {1, 3};
        int difference = 3;

        assertEquals(0, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has more than two numbers and multiple pairs have the given difference - " +
            "Should return the correct number of pairs")
    void countPairsWithDiff_arrayHasMultiplePairsWithGivenDifference_returnCorrectCount() {
        int[] numbers = {1, 2, 3, 4, 5};
        int difference = 1;

        assertEquals(4, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has more than two numbers and no pairs have the given difference - " +
            "Should return 0")
    void countPairsWithDiff_arrayHasNoPairsWithGivenDifference_returnZero() {
        int[] numbers = {1, 2, 3, 4, 5};
        int difference = 10;

        assertEquals(0, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("countPairsWithDiff - " +
            "When the array has multiple pairs with the same difference - " +
            "Should count each pair only once")
    void countPairsWithDiff_arrayHasMultiplePairsWithSameDifference_countEachPairOnlyOnce() {
        int[] numbers = {1, 2, 2, 3, 3, 4, 0, -1, -1};
        int difference = 1;

        assertEquals(5, MapHelper.countPairsWithDiff(numbers, difference));
    }

    @Test
    @DisplayName("twoSum - " +
            "When the array is empty - " +
            "Should return null")
    void twoSum_arrayIsEmpty_returnNull() {
        int[] numbers = {};

        assertNull(MapHelper.twoSum(numbers, 0));
    }

    @Test
    @DisplayName("twoSum - " +
            "When no two numbers add up to the target - " +
            "Should return null")
    void twoSum_noNumbersAddUpToTarget_returnNull() {
        int[] numbers = {1, 2, 3, 4};
        int target = 10;

        assertNull(MapHelper.twoSum(numbers, target));
    }

    @Test
    @DisplayName("twoSum - " +
            "When two numbers add up to the target - " +
            "Should return their indices")
    void twoSum_twoNumbersAddUpToTarget_returnTheirIndices() {
        int[] numbers = {2, 5, 7, 11};
        int target = 12;

        int[] expected = {1, 2};
        assertArrayEquals(expected, MapHelper.twoSum(numbers, target));
    }

    @Test
    @DisplayName("twoSum - " +
            "When multiple pairs of numbers add up to the target - " +
            "Should return the indices of the first pair found")
    void twoSum_multiplePairsAddUpToTarget_returnIndicesOfFirstPairFound() {
        int[] numbers = {3, 2, 4, 2, 5, 6};
        int target = 7;

        int[] expected = {0, 2};
        assertArrayEquals(expected, MapHelper.twoSum(numbers, target));
    }

    @Test
    @DisplayName("twoSum - " +
            "When the same number is used twice to add up to the target - " +
            "Should return their indices")
    void twoSum_sameNumberUsedTwiceToAddUpToTarget_returnTheirIndices() {
        int[] numbers = {1, 3, 4, 5, 7};
        int target = 6;

        int[] expected = {1, 1};
        assertArrayEquals(expected, MapHelper.twoSum(numbers, target));
    }
}