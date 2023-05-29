package io.github.aliazani.linear.queue.priority_queue.min_with_heap;

import io.github.aliazani.nonlinear.heap.min_heap.MinHeap;

public class MinPriorityQueue<T extends Comparable<T>, E extends Comparable<E>> {
    private final MinHeap<T, E> minHeap;

    public MinPriorityQueue(int size) {
        minHeap = new MinHeap<>(size);
    }

    public void enqueue(T key, E value) {
        minHeap.insert(key, value);
    }

    public E dequeue() {
        return minHeap.remove().value();
    }

    public E peek() {
        return minHeap.get(0).value();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    public int size() {
        return minHeap.size();
    }

    @Override
    public String toString() {
        return minHeap.toString();
    }
}