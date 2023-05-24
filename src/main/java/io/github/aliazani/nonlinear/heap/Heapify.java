package io.github.aliazani.nonlinear.heap;

import io.github.aliazani.nonlinear.heap.max_heap.MaxHeap;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A class providing methods for heapifying an array and finding the kth largest element.
 *
 * @param <T> the type of elements in the array, must implement Comparable interface
 */
public class Heapify<T extends Comparable<T>> {
    // heapify: transforming an array into a heap in place

    /**
     * Transforms the given array into a heap in place using the heapify algorithm.
     *
     * @param array the array to be heapified
     */
    public void heapify(T[] array) {
        for (int i = getIndexOfLastParent(array); i >= 0; i--)
            heapify(array, i);
    }

    private int getIndexOfLastParent(T[] array) {
        return (array.length / 2) - 1;
    }

    private void heapify(T[] array, int currentIndex) {
        int indexOfLargerItem = currentIndex;

        int leftChildIndex = (currentIndex * 2) + 1;
        if (leftChildIndex < array.length // hasLeftChild
                && array[indexOfLargerItem].compareTo(array[leftChildIndex]) < 0) indexOfLargerItem = leftChildIndex;

        int rightChildIndex = (currentIndex * 2) + 2;
        if (rightChildIndex < array.length // hasRightChild
                && array[indexOfLargerItem].compareTo(array[rightChildIndex]) < 0) indexOfLargerItem = rightChildIndex;

        if (currentIndex == indexOfLargerItem) return;

        swap(array, currentIndex, indexOfLargerItem);
        heapify(array, indexOfLargerItem);
    }

    private void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    /**
     * Finds the kth largest element in the array.
     *
     * @param array the array
     * @param k     the value of k
     * @return the kth largest element in the array
     * @throws IllegalArgumentException if k is less than 1 or greater than the length of the array
     */
    public T kthLargestNode(T[] array, int k) {
        if (k < 1 || k > array.length) throw new IllegalArgumentException();

        MaxHeap<T> maxHeap = new MaxHeap<>(array.length);
        Arrays.stream(array).forEach(maxHeap::insert);

        IntStream.range(0, k - 1).forEach(i -> maxHeap.remove());

        return maxHeap.max();
    }
}