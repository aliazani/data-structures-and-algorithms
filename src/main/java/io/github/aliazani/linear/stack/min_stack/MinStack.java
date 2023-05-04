package io.github.aliazani.linear.stack.min_stack;

import io.github.aliazani.linear.stack.MyStack;

import java.text.MessageFormat;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A stack implementation that keeps track of the minimum value in the stack.
 * <p>
 * It uses two internal stacks - one to hold the actual elements and another to keep track of the minimum value.
 *
 * @param <T> the type of elements held in the stack, which must implement the Comparable interface.
 */
public class MinStack<T extends Comparable<T>> implements MyStack<T> {
    private final Stack<T> stack = new Stack<>();
    private final Stack<T> minTrackingStack = new Stack<>();
    private int size;

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack
     */
    @Override
    public void push(T item) {
        stack.push(item);

        if (minTrackingStack.isEmpty() || isLessThanOrEqualToMin(item))
            minTrackingStack.push(item);

        size++;
    }

    /**
     * Removes the item at the top of this stack and returns that item.
     *
     * @return the item at the top of this stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (stack.isEmpty()) throw new EmptyStackException();

        T top = stack.pop();

        if (minTrackingStack.peek().equals(top)) minTrackingStack.pop();

        size--;

        return top;
    }

    /**
     * Looks at the item at the top of this stack without removing it from the stack.
     *
     * @return the item at the top of this stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (stack.isEmpty()) throw new EmptyStackException();

        return stack.peek();
    }

    /**
     * Checks if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty() && minTrackingStack.isEmpty();
    }

    private boolean isLessThanOrEqualToMin(T item) {
        return item.compareTo(minTrackingStack.peek()) <= 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Returns the minimum element in the stack based on the elements' natural ordering.
     * Throws {@link EmptyStackException} if the stack is empty.
     *
     * @return the minimum element in the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T min() {
        if (stack.isEmpty()) throw new EmptyStackException();

        return minTrackingStack.peek();
    }

    @Override
    public String toString() {
        return MessageFormat.format("MinStack: {0}\nStack: {1}", minTrackingStack, stack);
    }
}