package io.github.aliazani.linear.stack;

public interface MyStack<T> {
    void push(T item);

    T pop();

    T peek();

    boolean isEmpty();

    int size();
}
