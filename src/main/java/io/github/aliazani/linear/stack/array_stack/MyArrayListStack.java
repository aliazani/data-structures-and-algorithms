package io.github.aliazani.linear.stack.array_stack;

import io.github.aliazani.linear.stack.MyStack;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A stack data structure implemented using an ArrayList to store the elements.
 *
 * @param <T> the type of elements stored in the stack, which must implement the Comparable interface
 */
public class MyArrayListStack<T extends Comparable<T>> implements MyStack<T> {
    private final ArrayList<T> array;

    /**
     * Creates a new instance of MyArrayListStack.
     */
    public MyArrayListStack() {
        array = new ArrayList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param item the element to add
     */
    @Override
    public void push(T item) {
        array.add(item);
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the element that was on top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();

        return array.remove(size() - 1);
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element that is on top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();

        return array.get(size() - 1);
    }

    /**
     * Returns whether the stack is empty or not.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return array.size();
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        return array.toString();
    }
}