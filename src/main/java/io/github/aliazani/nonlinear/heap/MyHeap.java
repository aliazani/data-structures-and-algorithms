package io.github.aliazani.nonlinear.heap;

public interface MyHeap<T extends Comparable<T>> {
    void insert(T item);

    T remove();

    T get(int index);

    boolean isFull();

    boolean isEmpty();

    int size();
}