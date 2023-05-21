package io.github.aliazani.nonlinear.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HeapSort")
class HeapSortTest {
    HeapSort<Integer> heapSort;

    @BeforeEach
    void setUp() {
        heapSort = new HeapSort<>();
    }

    @DisplayName("sort - " +
            "When the array is empty - " +
            "Should not modify the array")
    @Test
    void sort_emptyArray_noModification() {
        Integer[] array = new Integer[0];

        heapSort.sort(array);

        assertArrayEquals(new Integer[0], array);
    }

    @DisplayName("sort - " +
            "When the array contains a single element - " +
            "Should not modify the array")
    @Test
    void sort_singleElementArray_noModification() {
        Integer[] array = {10};

        heapSort.sort(array);

        assertArrayEquals(new Integer[]{10}, array);
    }

    @DisplayName("sort - " +
            "When the array is already sorted in ascending order - " +
            "Should not modify the array")
    @Test
    void sort_sortedArrayAscending_notModification() {
        Integer[] array = {1, 2, 3, 4, 5};

        heapSort.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @DisplayName("sort - " +
            "When the array is already sorted in descending order - " +
            "Should sort the array in ascending order")
    @Test
    void sort_sortedArrayDescending_sortArrayAscending() {
        Integer[] array = {5, 4, 3, 2, 1};

        heapSort.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @DisplayName("sort - " +
            "When the array is unsorted - " +
            "Should sort the array in ascending order")
    @Test
    void sort_unsortedArray_sortArrayAscending() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] array = {3, 1, 5, 2, 4};

        heapSort.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }
}