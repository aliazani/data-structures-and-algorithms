package io.github.aliazani.linear.queue;

import java.util.Queue;
import java.util.Stack;

/**
 * The QueueReverser class is responsible for reversing a given number of elements in a Queue.
 *
 * @param <T> the type of elements in the Queue
 */
public class QueueReverser<T> {
    private final Stack<T> temporaryStack = new Stack<>();

    /**
     * Reverses the given number of elements in a Queue.
     *
     * @param queue the Queue to be reversed
     * @param n     the number of elements to be reversed
     * @throws IllegalArgumentException if n is less than 0 or greater than the size of the Queue
     */
    public void reverse(Queue<T> queue, int n) {
        if (n < 0 || n > queue.size()) throw new IllegalArgumentException();

        for (int i = 0; i < n; i++) temporaryStack.push(queue.remove());

        while (!temporaryStack.isEmpty()) queue.add(temporaryStack.pop());

        for (int i = 0; i < queue.size() - n; i++) queue.add(queue.remove());
    }
}