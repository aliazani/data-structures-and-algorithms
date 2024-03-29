package io.github.aliazani.linear.queue.stack_queue;

import io.github.aliazani.linear.queue.MyQueue;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A Queue implementation using two Stacks
 *
 * @param <T> the type of elements in the Queue
 */
public class StackQueue<T extends Comparable<T>> implements MyQueue<T> {
    private final Stack<T> reversedStack = new Stack<>();
    private final Stack<T> stack = new Stack<>();
    private int size;

    /**
     * Adds an element to the end of the queue.
     *
     * @param item the element to add
     */
    @Override
    public void enqueue(T item) {
        stack.push(item);

        size++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        if (reversedStack.isEmpty()) reverseTheStack();

        size--;

        return reversedStack.pop();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();

        if (reversedStack.isEmpty()) reverseTheStack();

        return reversedStack.peek();
    }

    private void reverseTheStack() {
        while (!stack.isEmpty()) reversedStack.push(stack.pop());
    }

    /**
     * Returns true if the queue contains no elements.
     *
     * @return true if the queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty() && reversedStack.isEmpty();
    }

    /**
     * Returns the size of the queue.
     *
     * @return the number of elements in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a String representation of the queue.
     *
     * @return a String representation of the queue
     */
    @Override
    public String toString() {
        if (!stack.isEmpty() && !reversedStack.isEmpty())
            return MessageFormat.format("{0}{1}",
                    reverse(reversedStack.toString()).replace("]", ", "),
                    stack.toString().replace("[", ""));
        else if (stack.isEmpty())
            return reverse(reversedStack.toString());
        else
            return stack.toString();
    }

    private String reverse(String str) {
        String[] parts = str.substring(1, str.length() - 1).split(", ");
        Collections.reverse(Arrays.asList(parts));

        return "[" + String.join(", ", parts) + "]";
    }
}