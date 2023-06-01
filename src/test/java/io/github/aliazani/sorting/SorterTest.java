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
}