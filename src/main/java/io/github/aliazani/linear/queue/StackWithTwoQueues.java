package io.github.aliazani.linear.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A stack implementation using two queues. The last element added is considered the top of the stack.
 *
 * @param <T> the type of elements in this stack
 */
public class StackWithTwoQueues<T> {
    private Queue<T> queue1 = new ArrayDeque<>();
    private Queue<T> queue2 = new ArrayDeque<>();
    T top;

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack
     */
    public void push(T item) {
        queue1.add(item);
        top = item;
    }

    /**
     * Removes the top element from this stack and returns it.
     *
     * @return the top element of this stack
     * @throws IllegalStateException if the stack is empty
     */
    public T pop() {
        if (isEmpty())
            throw new IllegalStateException();

        while (queue1.size() > 1) {
            top = queue1.remove();
            queue2.add(top);
        }

        swapQueues();

        return queue2.remove();
    }

    /**
     * Swaps the two queues.
     */
    private void swapQueues() {
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * Checks if this stack is empty.
     *
     * @return true if this stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return queue1.size();
    }

    /**
     * Returns the top element of this stack without removing it.
     *
     * @return the top element of this stack
     * @throws IllegalStateException if the stack is empty
     */
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return top;
    }


    /**
     * Returns a string representation of this stack.
     *
     * @return a string representation of this stack
     */
    @Override
    public String toString() {
        return queue1.toString();
    }
}