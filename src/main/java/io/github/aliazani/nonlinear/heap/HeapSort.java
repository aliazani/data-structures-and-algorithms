package io.github.aliazani.nonlinear.heap;

import io.github.aliazani.nonlinear.heap.max_heap.MaxHeap;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> {
    public void sort(T[] array) {
        MaxHeap<T> maxHeap = new MaxHeap<>(array.length);

        Arrays.stream(array).forEach(maxHeap::insert);
        for (int i = array.length - 1; i >= 0; i--)
            array[i] = maxHeap.remove();
    }
}