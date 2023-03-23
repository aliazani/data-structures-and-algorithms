package io.github.aliazani.linear.stack;

import java.util.ArrayList;

public class MyArrayListStack<T extends Comparable<T>> implements MyStack<T> {
    private final ArrayList<T> array;
    private int count;

    public MyArrayListStack() {
        array = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        array.add(item);
        count++;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new IllegalStateException();

        T top = array.get(--count);
        array.remove(count);

        return top;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return array.get(count - 1);
    }

    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
