package io.github.aliazani.linear.queue;

import io.github.aliazani.linear.linkedlist.MyLinkedList;

/**
 * The LinkedListQueue class implements a queue using a linked list data structure.
 *
 * @param <T> the type of elements stored in the queue
 */
public class LinkedListQueue<T extends Comparable<T>> {
    private final MyLinkedList<T> myLinkedList = new MyLinkedList<>();
    private int size;

    /**
     * Adds the specified item to the end of the queue.
     *
     * @param item the item to be added
     */
    public void enqueue(T item) {
        myLinkedList.addLast(item);

        size++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return the item at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        T head = myLinkedList.getNodeValue(0);
        myLinkedList.deleteFirst();
        size--;

        return head;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     *
     * @return the item at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return myLinkedList.getNodeValue(0);
    }

    /**
     * Returns the size of the queue.
     *
     * @return the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    public String toString() {
        return myLinkedList.toString();
    }
}