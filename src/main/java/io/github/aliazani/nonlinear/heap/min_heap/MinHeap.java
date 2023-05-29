package io.github.aliazani.nonlinear.heap.min_heap;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A class representing a Min Heap data structure.
 *
 * @param <T> the type of keys stored in the Min Heap, must implement Comparable interface
 * @param <E> the type of values associated with the keys, must implement Comparable interface
 */
public class MinHeap<T extends Comparable<T>, E extends Comparable<E>> {
    private final MinHeapNode<T, E>[] items;
    private int size;

    /**
     * Constructs a MinHeap with the specified size.
     *
     * @param size the maximum size of the Min Heap
     */
    public MinHeap(int size) {
        items = (MinHeapNode<T, E>[]) new MinHeapNode[size];
    }

    /**
     * Inserts a key-value pair into the Min Heap.
     *
     * @param key   the key to be inserted
     * @param value the value associated with the key
     * @throws IllegalStateException if the Min Heap is full
     */
    public void insert(T key, E value) {
        if (isFull()) throw new IllegalStateException("The heap is full!");
        items[size++] = new MinHeapNode<>(key, value);
        bubbleUp();
    }

    /**
     * Checks if the Min Heap is full.
     *
     * @return true if the Min Heap is full, false otherwise
     */
    public boolean isFull() {
        return items.length == size;
    }

    private void bubbleUp() {
        int index = size - 1;

        while (index > 0
                && items[index].key()
                .compareTo(items[getParentIndex(index)].key()) < 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void swap(int first, int second) {
        MinHeapNode<T, E> temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Removes and returns the root element of the Min Heap.
     *
     * @return the root element of the Min Heap
     * @throws IllegalStateException if the Min Heap is empty
     */
    public MinHeapNode<T, E> remove() {
        if (isEmpty()) throw new IllegalStateException("Heap is Empty!");

        MinHeapNode<T, E> root = items[0];
        items[0] = items[size - 1];
        items[size - 1] = null;
        size--;

        bubbleDown();

        return root;
    }

    /**
     * Checks if the Min Heap is empty.
     *
     * @return true if the Min Heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleDown() {
        int index = 0;

        while (index < size
                && !isValidParent(index)) {
            int smallerChildIndex = getSmallerChild(index);
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) return true;

        boolean isValid = items[index].key()
                .compareTo(items[getLeftChildIndex(index)].key()) <= 0;
        if (hasRightChild(index)) isValid &= items[index].key()
                .compareTo(items[getRightChildIndex(index)].key()) <= 0;

        return isValid;
    }

    private int getSmallerChild(int index) {
        if (!hasRightChild(index)) return getLeftChildIndex(index);
        else
            return (items[getLeftChildIndex(index)].key()
                    .compareTo(items[getRightChildIndex(index)].key()) < 0)
                    ? getLeftChildIndex(index)
                    : getRightChildIndex(index);
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * Returns the element at the given index in the Min Heap.
     *
     * @param index the index of the element
     * @return the element at the given index
     * @throws IllegalStateException    if the Min Heap is empty
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public MinHeapNode<T, E> get(int index) {
        if (isEmpty()) throw new IllegalStateException();
        if (index >= size || index < 0) throw new IllegalArgumentException();

        return items[index];
    }

    /**
     * Returns the minimum element (root) of the Min Heap.
     *
     * @return the minimum element of the Min Heap
     * @throws IllegalStateException if the Min Heap is empty
     */
    public MinHeapNode<T, E> min() {
        if (isEmpty()) throw new IllegalStateException("Heap is Empty!");

        return items[0];
    }

    /**
     * Returns the number of elements in the Min Heap.
     *
     * @return the number of elements in the Min Heap
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the Min Heap.
     *
     * @return a string representation of the Min Heap
     */
    @Override
    public String toString() {
        return Arrays.stream(items)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}