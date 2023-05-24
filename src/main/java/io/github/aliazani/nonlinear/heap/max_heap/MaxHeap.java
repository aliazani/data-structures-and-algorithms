package io.github.aliazani.nonlinear.heap.max_heap;

import io.github.aliazani.nonlinear.heap.MyHeap;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A class representing a Max Heap data structure.
 *
 * @param <T> the type of elements stored in the Max Heap, must implement Comparable interface
 */
public class MaxHeap<T extends Comparable<T>> implements MyHeap<T> {
    private final T[] items;
    private int size;

    /**
     * Constructs a MaxHeap with the specified size.
     *
     * @param size the maximum size of the Max Heap
     */
    public MaxHeap(int size) {
        items = (T[]) new Comparable[size];
    }

    /**
     * Inserts an element into the Max Heap.
     *
     * @param item the item to be inserted
     * @throws IllegalStateException if the Max Heap is full
     */
    @Override
    public void insert(T item) {
        if (isFull()) throw new IllegalStateException("Heap is full!!");

        items[size++] = item;
        bubbleUp();
    }

    /**
     * Checks if the Max Heap is full.
     *
     * @return true if the Max Heap is full, false otherwise
     */
    @Override
    public boolean isFull() {
        return items.length == size;
    }

    private void bubbleUp() {
        int index = size - 1;

        while (index > 0 && items[index].compareTo(items[parentIndexOf(index)]) > 0) {
            swap(index, parentIndexOf(index));
            index = parentIndexOf(index);
        }
    }

    private int parentIndexOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        T temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

    /**
     * Removes and returns the root element of the Max Heap.
     *
     * @return the root element of the Max Heap
     * @throws IllegalStateException if the Max Heap is empty
     */
    @Override
    public T remove() {
        if (isEmpty()) throw new IllegalStateException();

        T root = items[0];
        items[0] = items[size - 1];
        items[size - 1] = null;
        size--;
        bubbleDown();

        return root;
    }

    /**
     * Checks if the Max Heap is empty.
     *
     * @return true if the Max Heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void bubbleDown() {
        int index = 0;
        while (index <= size && !isValidParent(index)) {
            int largerChildIndex = largerChildIndexOf(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) return true;

        boolean isValid = items[index].compareTo(leftChild(index)) >= 0;
        if (hasRightChild(index)) isValid &= items[index].compareTo(rightChild(index)) >= 0;

        return isValid;
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

    private T leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private T rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private int largerChildIndexOf(int index) {
        if (!hasRightChild(index)) return getLeftChildIndex(index);
        else return (leftChild(index).compareTo(rightChild(index)) > 0)
                ? getLeftChildIndex(index)
                : getRightChildIndex(index);
    }

    /**
     * Returns the index-th element in the Max Heap.
     *
     * @param index the index of the element
     * @return the index-th element in the Max Heap
     * @throws IllegalStateException    if the Max Heap is empty
     * @throws IllegalArgumentException if the index is out of bounds
     */
    @Override
    public T get(int index) {
        if (isEmpty()) throw new IllegalStateException();
        if (index >= size || index < 0) throw new IllegalArgumentException();

        return items[index];
    }

    /**
     * Checks if the given array is a valid Max Heap.
     *
     * @param array the array to be checked
     * @return true if the array represents a valid Max Heap, false otherwise
     */
    public boolean isMaxHeap(T[] array) {
        int index = array.length - 1;

        while (index > 0) {
            if (array[index].compareTo(array[parentIndexOf(index)]) > 0) return false;
            index = parentIndexOf(index);
        }
        return true;
    }

    /**
     * Returns the maximum element (root) of the Max Heap.
     *
     * @return the maximum element of the Max Heap
     * @throws IllegalStateException if the Max Heap is empty
     */
    public T max() {
        if (isEmpty()) throw new IllegalStateException();

        return items[0];
    }

    /**
     * Returns the number of elements in the Max Heap.
     *
     * @return the number of elements in the Max Heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the Max Heap.
     *
     * @return a string representation of the Max Heap
     */
    @Override
    public String toString() {
        return Arrays.stream(items)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}