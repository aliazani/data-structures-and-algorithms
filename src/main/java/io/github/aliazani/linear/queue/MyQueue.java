package io.github.aliazani.linear.queue;

public interface MyQueue<T extends Comparable<T>> {
    void enqueue(T item);

    T dequeue();

    T peek();

    int size();

    boolean isEmpty();
}