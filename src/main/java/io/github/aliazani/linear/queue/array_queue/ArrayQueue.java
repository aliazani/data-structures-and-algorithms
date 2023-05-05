package io.github.aliazani.linear.queue.array_queue;

import io.github.aliazani.linear.queue.MyQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A simple implementation of a queue using an array.
 *
 * @param <T> the type of elements in the queue.
 */
public class ArrayQueue<T extends Comparable<T>> implements MyQueue<T> {
    private final T[] elements;
    private int size;
    private int rear;
    private int front;

    /**
     * Constructs an empty queue with the specified capacity.
     *
     * @param typeOfClass the type of elements in the queue.
     * @param capacity    the capacity of the queue.
     */
    public ArrayQueue(Class<T> typeOfClass, int capacity) {
        elements = (T[]) Array.newInstance(typeOfClass, capacity);
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param item the element to be added.
     * @throws IllegalStateException if the queue is full.
     */
    @Override
    public void enqueue(T item) {
        if (isFull()) throw new IllegalStateException();

        elements[rear] = item;
        rear = (rear + 1) % elements.length;

        size++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        T firstElement = elements[front];
        elements[front] = null;

        front = (front + 1) % elements.length;
        size--;

        return firstElement;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue.
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();

        return elements[front];
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns whether the queue is full.
     *
     * @return true if the queue is full, false otherwise.
     */
    private boolean isFull() {
        return size == elements.length;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        String closingBracket = "]";

        if (isEmpty()) stringBuilder.append(closingBracket);
        else if (rear <= front) {
            for (int i = front, count = 0; count < size; i = (i + 1) % elements.length, count++) {
                if (count > 0) stringBuilder.append(", ");
                stringBuilder.append(elements[i]);
            }
            stringBuilder.append(closingBracket);
        } else {
            for (int i = front; i < rear - 1; i++) stringBuilder.append(elements[i]).append(", ");
            stringBuilder.append(elements[rear - 1]).append(closingBracket);
        }

        return stringBuilder.toString();
    }
}