package io.github.aliazani.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Sorter")
class SorterTest {
    Sorter<Integer> sorter;

    @BeforeEach
    void setUp() {
        sorter = new Sorter<>();
    }

    @DisplayName("bubbleSort - " +
            "When sorting an array in ascending order - " +
            "Should return a sorted array")
    @Test
    void bubbleSort_sortArrayInAscendingOrder_returnSortedArray() {
        Integer[] array = {5, 2, 8, 1, 4};
        Integer[] expected = {1, 2, 4, 5, 8};

        sorter.bubbleSort(array);

        assertArrayEquals(expected, array);
    }

    @DisplayName("bubbleSort - " +
            "When sorting an array with duplicate elements - " +
            "Should return a sorted array with duplicate elements")
    @Test
    void bubbleSort_sortArrayWithDuplicateElements_returnSortedArrayWithDuplicateElements() {
        Integer[] array = {4, 2, 7, 3, 2, 6, 4};
        Integer[] expected = {2, 2, 3, 4, 4, 6, 7};

        sorter.bubbleSort(array);

        assertArrayEquals(expected, array);
    }

    @DisplayName("bubbleSort - " +
            "When sorting an empty array - " +
            "Should return an empty array")
    @Test
    void bubbleSort_sortEmptyArray_returnEmptyArray() {
        Integer[] array = {};

        sorter.bubbleSort(array);

        assertArrayEquals(new Integer[0], array);
    }

    @DisplayName("bubbleSort - " +
            "When sorting an array with a single element - " +
            "Should return the same array")
    @Test
    void bubbleSort_sortArrayWithSingleElement_returnSameArray() {
        Integer[] array = {5};

        sorter.bubbleSort(array);

        assertArrayEquals(new Integer[]{5}, array);
    }

    @Test
    @DisplayName("selectionSort - " +
            "When sorting array with random elements - " +
            "Should sort the array in ascending order")
    void selectionSort_randomElements_sortsArrayInAscendingOrder() {
        Integer[] array = {5, 2, 8, 1, 9, 3};
        Integer[] expected = {1, 2, 3, 5, 8, 9};

        sorter.selectionSort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("selectionSort - " +
            "When sorting array with duplicate elements - " +
            "Should sort the array with duplicates")
    void selectionSort_duplicateElements_sortArrayWithDuplicateElements() {
        Integer[] array = {3, 2, 1, 2, 3, 1};
        Integer[] expected = {1, 1, 2, 2, 3, 3};

        sorter.selectionSort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("selectionSort - " +
            "When sorting array with already sorted elements - " +
            "Should keep the elements in the same order")
    void selectionSort_sortedArray_sortArrayWithAlreadySortedElements() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer[] expected = {1, 2, 3, 4, 5};

        sorter.selectionSort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("selectionSort - " +
            "When sorting array with single element - " +
            "Should keep the element unchanged")
    void selectionSort_singleElement_sortArrayWithSingleElement() {
        Integer[] array = {5};
        Integer[] expected = {5};

        sorter.selectionSort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    @DisplayName("selectionSort - " +
            "When sorting empty array - " +
            "Should keep the array empty")
    void selectionSort_emptyArray_sortArrayWithNoElements() {
        Integer[] array = {};
        Integer[] expected = {};

        sorter.selectionSort(array);

        assertArrayEquals(expected, array);
    }

    @DisplayName("insertionSort - " +
            "When array is already sorted in ascending order - " +
            "Should keep the array unchanged")
    @Test
    void insertionSort_arraySortedAscending_noChange() {
        Integer[] array = {1, 2, 3, 4, 5};
        sorter.insertionSort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @DisplayName("insertionSort - " +
            "When array is already sorted in descending order - " +
            "Should sort the array in ascending order")
    @Test
    void insertionSort_arraySortedDescending_sortAscending() {
        Integer[] array = {5, 4, 3, 2, 1};
        sorter.insertionSort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @DisplayName("insertionSort - " +
            "When array has duplicate elements - " +
            "Should sort the array correctly")
    @Test
    void insertionSort_arrayWithDuplicateElements_sortCorrectly() {
        Integer[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        sorter.insertionSort(array);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 4, 5, 5, 6, 9}, array);
    }

    @DisplayName("insertionSort - " +
            "When array is empty - " +
            "Should keep the array unchanged")
    @Test
    void insertionSort_emptyArray_noChange() {
        Integer[] array = {};
        sorter.insertionSort(array);
        assertArrayEquals(new Integer[]{}, array);
    }

    @DisplayName("insertionSort - " +
            "When array has a single element - " +
            "Should keep the array unchanged")
    @Test
    void insertionSort_singleElementArray_noChange() {
        Integer[] array = {42};
        sorter.insertionSort(array);
        assertArrayEquals(new Integer[]{42}, array);
    }
}