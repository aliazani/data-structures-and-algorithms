package io.github.aliazani.linear.stack;

import java.util.Arrays;

public class TwoStacks<T extends Comparable<T>> {
    private final T[] items;
    private int top1Pointer;
    private int top2Pointer;

    public TwoStacks(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be 1 or greater.");

        items = (T[]) new Comparable[capacity];
        top1Pointer = -1;
        top2Pointer = capacity;
    }

    public void push1(T item) {
        if (isFull1())
            throw new IllegalStateException();

        items[++top1Pointer] = item;
    }

    public T pop1() {
        if (isEmpty1())
            throw new IllegalStateException();

        T top = items[top1Pointer];
        items[top1Pointer--] = null;
        return top;
    }

    public boolean isEmpty1() {
        return top1Pointer == -1;
    }

    public boolean isFull1() {
        return top1Pointer + 1 == top2Pointer;
    }

    public void push2(T item) {
        if (isFull2())
            throw new IllegalStateException();

        items[--top2Pointer] = item;
    }

    public T pop2() {
        if (isEmpty2())
            throw new IllegalStateException();

        T top = items[top1Pointer];
        items[top2Pointer++] = null;
        return top;
    }

    public boolean isEmpty2() {
        return top2Pointer == items.length;
    }

    public boolean isFull2() {
        return top2Pointer - 1 == top1Pointer;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}