package io.github.aliazani.linear.queue.priority_queue.with_heap;

import io.github.aliazani.linear.queue.MyQueue;
import io.github.aliazani.nonlinear.heap.max_heap.MaxHeap;

public class PriorityQueueWithHeap<T extends Comparable<T>> implements MyQueue<T> {
    private final MaxHeap<T> maxHeap;

    public PriorityQueueWithHeap(int size) {
        maxHeap = new MaxHeap<>(size);
    }

    @Override
    public void enqueue(T item) {
        maxHeap.insert(item);
    }

    @Override
    public T dequeue() {
        return maxHeap.remove();
    }

    @Override
    public T peek() {
        return maxHeap.get(0);
    }

    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public String toString() {
        return maxHeap.toString();
    }
}