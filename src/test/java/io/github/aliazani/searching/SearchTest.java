package io.github.aliazani.searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Search")
class SearchTest {
    Search<Integer> search;

    @BeforeEach
    void setUp() {
        search = new Search<>();
    }

    @DisplayName("linearSearch - " +
            "When item exists in the array - " +
            "Should return the correct index")
    @Test
    void linearSearch_itemExistsInArray_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int item = 3;

        int result = search.linearSearch(array, item);

        assertEquals(2, result);
    }

    @DisplayName("linearSearch - " +
            "When item does not exist in the array - " +
            "Should return -1")
    @Test
    void linearSearch_itemDoesNotExistInArray_returnNegativeOne() {
        Integer[] array = {1, 2, 3, 4, 5};
        int item = 6;

        assertEquals(-1, search.linearSearch(array, item));
    }

    @DisplayName("linearSearch - " +
            "When the array is empty - " +
            "Should return -1")
    @Test
    void linearSearch_emptyArray_returnNegativeOne() {
        Integer[] array = {};
        int item = 5;

        assertEquals(-1, search.linearSearch(array, item));
    }

    @DisplayName("linearSearch - " +
            "When there are multiple occurrences of the item - " +
            "Should return the first matching index")
    @Test
    void linearSearch_multipleOccurrencesOfItem_returnFirstMatchingIndex() {
        Integer[] array = {1, 2, 3, 4, 5, 3};
        int item = 3;

        assertEquals(2, search.linearSearch(array, item));
    }

    @DisplayName("binarySearch - " +
            "When searching for an existing target in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearch_existingTarget_returnCorrectIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13};
        int target = 7;

        assertEquals(3, search.binarySearch(array, target));
    }

    @DisplayName("binarySearch - " +
            "When searching for a non-existing target in a sorted array - " +
            "Should return -1")
    @Test
    void binarySearch_nonExistingTarget_returnMinusOne() {
        Integer[] array = {2, 4, 6, 8, 10};
        int target = 5;

        assertEquals(-1, search.binarySearchIterative(array, target));
    }

    @DisplayName("binarySearch - " +
            "When searching for the first element in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearch_firstElement_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int target = 1;

        assertEquals(0, search.binarySearch(array, target));
    }

    @DisplayName("binarySearch - " +
            "When searching for the last element in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearch_lastElement_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int target = 5;

        assertEquals(4, search.binarySearch(array, target));
    }

    @DisplayName("binarySearch - " +
            "When searching in an empty array - " +
            "Should return -1")
    @Test
    void binarySearch_emptyArray_returnMinusOne() {
        Integer[] array = {};
        int target = 10;

        assertEquals(-1, search.binarySearch(array, target));
    }

    @DisplayName("binarySearchIterative - " +
            "When searching for an existing target in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearchIterative_existingTarget_returnCorrectIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13};
        int target = 7;

        assertEquals(3, search.binarySearchIterative(array, target));
    }

    @DisplayName("binarySearchIterative - " +
            "When searching for a non-existing target in a sorted array - " +
            "Should return -1")
    @Test
    void binarySearchIterative_nonExistingTarget_returnMinusOne() {
        Integer[] array = {2, 4, 6, 8, 10};
        int target = 5;

        assertEquals(-1, search.binarySearchIterative(array, target));
    }

    @DisplayName("binarySearchIterative - " +
            "When searching for the first element in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearchIterative_firstElement_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int target = 1;

        assertEquals(0, search.binarySearchIterative(array, target));
    }

    @DisplayName("binarySearchIterative - " +
            "When searching for the last element in a sorted array - " +
            "Should return the correct index")
    @Test
    void binarySearchIterative_lastElement_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int target = 5;

        assertEquals(4, search.binarySearchIterative(array, target));
    }

    @DisplayName("binarySearchIterative - " +
            "When searching in an empty array - " +
            "Should return -1")
    @Test
    void binarySearchIterative_emptyArray_returnMinusOne() {
        Integer[] array = {};
        int target = 10;

        assertEquals(-1, search.binarySearchIterative(array, target));
    }
}