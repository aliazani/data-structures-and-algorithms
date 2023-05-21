package io.github.aliazani.nonlinear.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Heapify")
class HeapifyTest {
    Heapify<Integer> heapify;

    @BeforeEach
    void setUp() {
        heapify = new Heapify<>();
    }

    @DisplayName("heapify - " +
            "When array is already a max heap - " +
            "Should not modify the array")
    @Test
    void heapify_arrayIsMaxHeap_noModification() {
        Integer[] array = {10, 8, 5, 4, 6, 3};

        heapify.heapify(array);

        assertArrayEquals(new Integer[]{10, 8, 5, 4, 6, 3}, array);
    }

    @DisplayName("heapify - " +
            "When array is in random order - " +
            "Should transform the array into a max heap")
    @Test
    void heapify_arrayIsRandomOrder_transformToMaxHeap() {
        Integer[] array = {4, 8, 3, 2, 10, 9};

        heapify.heapify(array);

        assertArrayEquals(new Integer[]{10, 8, 9, 2, 4, 3}, array);
    }

    @DisplayName("kthLargestNode - " +
            "When k is within the valid range - " +
            "Should return the kth largest element")
    @Test
    void kthLargestNode_validK_returnKthLargestElement() {
        Integer[] array = {10, 8, 5, 4, 6, 7};

        Integer kthLargest = heapify.kthLargestNode(array, 2);

        assertEquals(8, kthLargest);
    }

    @DisplayName("kthLargestNode - " +
            "When k is smaller than 1 - " +
            "Should throw IllegalArgumentException")
    @Test
    void kthLargestNode_kIsSmallerThanOne_throwIllegalArgument() {
        Integer[] array = {10, 8, 5, 4, 6, 7};

        assertThrows(IllegalArgumentException.class, () -> heapify.kthLargestNode(array, 0));
    }

    @DisplayName("kthLargestNode - " +
            "When k is greater than the array length - " +
            "Should throw IllegalArgumentException")
    @Test
    void kthLargestNode_kIsGreaterThanArrayLength_throwIllegalArgument() {
        Integer[] array = {10, 8, 5, 4, 6, 7};

        assertThrows(IllegalArgumentException.class, () -> heapify.kthLargestNode(array, 7));
    }
}