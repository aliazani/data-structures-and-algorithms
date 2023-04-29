package io.github.aliazani.linear.queue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A priority queue implementation based on a fixed-size array.
 * <p>
 * The priority of the elements is determined by their natural order, as defined by the Comparable interface.
 *
 * @param <T> the type of the elements stored in the priority queue.
 */
public class PriorityQueueWithArray<T extends Comparable<T>> {
    private final T[] elements;
    private int size;

    /**
     * Constructs a new priority queue with the specified capacity.
     *
     * @param typeOfClass the class object representing the type of the elements in the priority queue.
     * @param capacity    the capacity of the priority queue.
     */
    public PriorityQueueWithArray(Class<T> typeOfClass, int capacity) {
        elements = (T[]) Array.newInstance(typeOfClass, capacity);
    }

    /**
     * Adds an element to the priority queue.
     * <p>
     * The element is inserted in its proper position based on its priority.
     *
     * @param item the element to add.
     * @throws IllegalStateException if the priority queue is full.
     */
    public void add(T item) {
        if (isFull())
            throw new IllegalStateException();

        int properIndex = shiftItemsToInsert(item);
        elements[properIndex] = item;
        size++;
    }

    /**
     * Returns whether the priority queue is full.
     *
     * @return true if the priority queue is full, false otherwise.
     */
    public boolean isFull() {
        return size == elements.length;
    }

    /**
     * Shifts the elements of the priority queue to insert a new element in its proper position.
     *
     * @param item the element to insert.
     * @return the index where the new element should be inserted.
     */
    private int shiftItemsToInsert(T item) {
        int i;

        for (i = size - 1; i >= 0; i--)
            if (elements[i].compareTo(item) > 0)
                elements[i + 1] = elements[i];
            else
                break;

        return i + 1;
    }

    /**
     * Removes and returns the element with the highest priority from the priority queue.
     *
     * @return the element with the highest priority.
     * @throws IllegalStateException if the priority queue is empty.
     */
    public T remove() {
        if (isEmpty())
            throw new IllegalStateException();

        T tail = elements[--size];
        elements[size] = null;

        return tail;
    }

    /**
     * Returns whether the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the elements in the priority queue.
     *
     * @return a string representation of the elements in the priority queue.
     */
    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}