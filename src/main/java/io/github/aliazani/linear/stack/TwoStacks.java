package io.github.aliazani.linear.stack;

import java.util.Arrays;

/**
 * Represents a container that holds two stacks.
 *
 * @param <T> the type of elements stored in the stacks
 */
public class TwoStacks<T extends Comparable<T>> {
    private final T[] items;
    private int top1Pointer;
    private int top2Pointer;

    /**
     * Constructs a TwoStacks object with the specified capacity.
     *
     * @param capacity the capacity of the two stacks
     * @throws IllegalArgumentException if the capacity is less than or equal to 0
     */
    public TwoStacks(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be 1 or greater.");

        items = (T[]) new Comparable[capacity];
        top1Pointer = -1;
        top2Pointer = capacity;
    }

    /**
     * Pushes an element onto the first stack.
     *
     * @param item the element to be pushed onto the first stack
     * @throws IllegalStateException if the first stack is full
     */
    public void push1(T item) {
        if (isFull1()) throw new IllegalStateException();

        items[++top1Pointer] = item;
    }

    /**
     * Pops the top element from the first stack.
     *
     * @return the top element of the first stack
     * @throws IllegalStateException if the first stack is empty
     */
    public T pop1() {
        if (isEmpty1()) throw new IllegalStateException();

        T top = items[top1Pointer];
        items[top1Pointer--] = null;

        return top;
    }

    /**
     * Checks if the first stack is empty.
     *
     * @return true if the first stack is empty, false otherwise
     */
    public boolean isEmpty1() {
        return top1Pointer == -1;
    }


    /**
     * Checks if the first stack is full.
     *
     * @return true if the first stack is full, false otherwise
     */
    public boolean isFull1() {
        return top1Pointer + 1 == top2Pointer;
    }

    /**
     * Pushes an element onto the second stack.
     *
     * @param item the element to be pushed onto the second stack
     * @throws IllegalStateException if the second stack is full
     */
    public void push2(T item) {
        if (isFull2()) throw new IllegalStateException();

        items[--top2Pointer] = item;
    }

    /**
     * Pops the top element from the second stack.
     *
     * @return the top element of the second stack
     * @throws IllegalStateException if the second stack is empty
     */
    public T pop2() {
        if (isEmpty2()) throw new IllegalStateException();

        T top = items[top2Pointer];
        items[top2Pointer++] = null;

        return top;
    }

    /**
     * Checks if the second stack is empty.
     *
     * @return true if the second stack is empty, false otherwise
     */
    public boolean isEmpty2() {
        return top2Pointer == items.length;
    }

    /**
     * Checks if the second stack is full.
     *
     * @return true if the second stack is full, false otherwise
     */
    public boolean isFull2() {
        return top2Pointer - 1 == top1Pointer;
    }

    /**
     * Returns a string representation of the TwoStacks object.
     *
     * @return a string representation of the TwoStacks object
     */
    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}