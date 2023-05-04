package io.github.aliazani.linear.stack.linked_list_stack;

import io.github.aliazani.linear.stack.MyStack;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * A stack data structure implemented using an LinkedList to store the elements.
 *
 * @param <E> the type of elements stored in the stack, which must implement the Comparable interface
 */
public class MyLinkedListStack<E extends Comparable<E>> implements MyStack<E> {
    private final LinkedList<E> linkedList;

    /**
     * Creates a new instance of MyLinkedListStack.
     */
    public MyLinkedListStack() {
        linkedList = new LinkedList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param item the element to add
     */
    @Override
    public void push(E item) {
        linkedList.add(item);
    }


    /**
     * Removes and returns the top element of the stack.
     *
     * @return the element that was on top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (isEmpty()) throw new EmptyStackException();

        return linkedList.removeLast();
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element that is on top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) throw new EmptyStackException();

        return linkedList.getLast();
    }

    /**
     * Returns whether the stack is empty or not.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return linkedList.size();
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        return linkedList.toString();
    }
}