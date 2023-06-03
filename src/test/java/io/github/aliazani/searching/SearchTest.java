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

    @Test
    @DisplayName("ternarySearch - " +
            "Element present in array - " +
            "Should return index")
    void ternarySearch_elementPresentInArray_returnIndex() {
        Integer[] array = {1, 3, 5, 7, 9};
        Integer target = 5;

        assertEquals(2, search.ternarySearch(array, target));
    }

    @Test
    @DisplayName("ternarySearch - " +
            "Element not present in array - " +
            "Should return -1")
    void ternarySearch_elementNotPresentInArray_returnMinusOne() {
        Integer[] array = {1, 3, 5, 7, 9};
        Integer target = 4;

        assertEquals(-1, search.ternarySearch(array, target));
    }

    @Test
    @DisplayName("ternarySearch - " +
            "Empty array - " +
            "Should return -1")
    void ternarySearch_emptyArray_returnsMinusOne() {
        Integer[] array = {};
        Integer target = 5;

        assertEquals(-1, search.ternarySearch(array, target));
    }

    @Test
    @DisplayName("ternarySearch - " +
            "Single-element array, element present - " +
            "Should return index 0")
    void ternarySearch_singleElementArray_elementPresent_returnIndex() {
        Integer[] array = {5};
        Integer target = 5;

        assertEquals(0, search.ternarySearch(array, target));
    }

    @Test
    @DisplayName("ternarySearch - " +
            "Single-element array, element not present - " +
            "Should return -1")
    void ternarySearch_singleElementArray_elementNotPresent_returnMinusOne() {
        Integer[] array = {7};
        Integer target = 5;
        assertEquals(-1, search.ternarySearch(array, target));
    }

    @DisplayName("jumpSearch - " +
            "When target exists in the array - " +
            "Should return the correct index")
    @Test
    void jumpSearch_targetExists_returnCorrectIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(4, search.jumpSearch(array, 9));
    }

    @DisplayName("jumpSearch - " +
            "When target does not exist in the array - " +
            "Should return -1")
    @Test
    void jumpSearch_targetDoesNotExist_returnMinusOne() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(-1, search.jumpSearch(array, 8));
    }

    @DisplayName("jumpSearch - " +
            "When target is the first element of the array - " +
            "Should return 0")
    @Test
    void jumpSearch_targetIsFirstElement_returnZero() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(0, search.jumpSearch(array, 1));
    }

    @DisplayName("jumpSearch - " +
            "When target is the last element of the array - " +
            "Should return the last index")
    @Test
    void jumpSearch_targetIsLastElement_returnLastIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(array.length - 1, search.jumpSearch(array, 19));
    }

    @DisplayName("jumpSearch - " +
            "When array is empty - " +
            "Should return -1")
    @Test
    void jumpSearch_emptyArray_returnMinusOne() {
        Integer[] array = {};
        assertEquals(-1, search.jumpSearch(array, 5));
    }

    @DisplayName("Exponential search - " +
            "When searching for an element in a sorted array - " +
            "Should return the correct index")
    @Test
    void exponentialSearch_searchElementInSortedArray_returnCorrectIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17};
        Integer target = 9;

        assertEquals(4, search.exponentialSearch(array, target));
    }

    @DisplayName("Exponential search - " +
            "When searching for an element not in a sorted array - " +
            "Should return -1")
    @Test
    void exponentialSearch_searchElementNotInSortedArray_returnMinusOne() {
        Integer[] array = {2, 4, 6, 8, 10, 12, 14, 16, 18};
        Integer target = 7;

        assertEquals(-1, search.exponentialSearch(array, target));
    }

    @DisplayName("exponentialSearch - " +
            "When target is the first element of the array - " +
            "Should return 0")
    @Test
    void exponentialSearch_targetIsFirstElement_returnZero() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(0, search.exponentialSearch(array, 1));
    }

    @DisplayName("exponentialSearch - " +
            "When target is the last element of the array - " +
            "Should return the last index")
    @Test
    void exponentialSearch_targetIsLastElement_returnLastIndex() {
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        assertEquals(array.length - 1, search.exponentialSearch(array, 19));
    }

    @DisplayName("exponentialSearch - " +
            "When array is empty - " +
            "Should return -1")
    @Test
    void exponentialSearch_emptyArray_returnMinusOne() {
        Integer[] array = {};
        assertEquals(-1, search.exponentialSearch(array, 5));
    }
}