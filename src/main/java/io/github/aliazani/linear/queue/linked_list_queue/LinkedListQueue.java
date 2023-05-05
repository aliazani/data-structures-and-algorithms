package io.github.aliazani.linear.queue.linked_list_queue;

import io.github.aliazani.linear.queue.MyQueue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * The LinkedListQueue class implements a queue using a linked list data structure.
 *
 * @param <T> the type of elements stored in the queue
 */
public class LinkedListQueue<T extends Comparable<T>> implements MyQueue<T> {
    private final LinkedList<T> linkedList = new LinkedList<>();
    private int size;

    /**
     * Adds the specified item to the end of the queue.
     *
     * @param item the item to be added
     */
    @Override
    public void enqueue(T item) {
        linkedList.addLast(item);

        size++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return the item at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        T head = linkedList.get(0);
        linkedList.removeFirst();

        size--;

        return head;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     *
     * @return the item at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();

        return linkedList.get(0);
    }

    /**
     * Returns the size of the queue.
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        return linkedList.toString();
    }
}