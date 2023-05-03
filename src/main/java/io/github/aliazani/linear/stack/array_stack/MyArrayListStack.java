package io.github.aliazani.linear.stack.array_stack;

import io.github.aliazani.linear.stack.MyStack;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyArrayListStack<T extends Comparable<T>> implements MyStack<T> {
    private final ArrayList<T> array;
    private int size;

    public MyArrayListStack() {
        array = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        array.add(item);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();

        T top = array.get(--size);
        array.remove(size);

        return top;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();

        return array.get(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return array.toString();
    }
}