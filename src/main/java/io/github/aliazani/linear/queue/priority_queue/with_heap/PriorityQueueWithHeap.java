package io.github.aliazani.linear.queue.priority_queue.with_heap;

import io.github.aliazani.nonlinear.heap.MaxHeap;

public class PriorityQueueWithHeap<T extends Comparable<T>> {
    private final MaxHeap<T> maxHeap;

    public PriorityQueueWithHeap(int size) {
        maxHeap = new MaxHeap<>(size);
    }

    public void enqueue(T item) {
        maxHeap.insert(item);
    }

    public T dequeue() {
        return maxHeap.remove();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public String toString() {
        return maxHeap.toString();
    }
}
