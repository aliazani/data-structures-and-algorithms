package io.github.aliazani.nonlinear.heap;

import io.github.aliazani.nonlinear.heap.max_heap.MaxHeap;

import java.util.Arrays;

/**
 * A class providing a method for sorting an array using Heap Sort algorithm.
 *
 * @param <T> the type of elements in the array, must implement Comparable interface
 */
public class HeapSort<T extends Comparable<T>> {
    /**
     * Sorts the given array in ascending order using the Heap Sort algorithm.
     *
     * @param array the array to be sorted
     */
    public void sort(T[] array) {
        MaxHeap<T> maxHeap = new MaxHeap<>(array.length);

        Arrays.stream(array).forEach(maxHeap::insert);
        for (int i = array.length - 1; i >= 0; i--)
            array[i] = maxHeap.remove();
    }
}