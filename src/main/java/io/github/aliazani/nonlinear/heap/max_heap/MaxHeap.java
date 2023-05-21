package io.github.aliazani.nonlinear.heap.max_heap;

import io.github.aliazani.nonlinear.heap.MyHeap;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MaxHeap<T extends Comparable<T>> implements MyHeap<T> {
    private final T[] items;
    private int size;

    public MaxHeap(int size) {
        items = (T[]) new Comparable[size];
    }

    @Override
    public void insert(T item) {
        if (isFull()) throw new IllegalStateException("Heap is full!!");

        items[size++] = item;
        bubbleUp();
    }

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

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void bubbleDown() {
        int index = 0;
        while (index <= size && !isValidParent(index)) {
            int largerChildIndex = largerChildIndexOf(index);
            swap(index, largerChildIndexOf(index));
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

    @Override
    public T get(int index) {
        if (isEmpty()) throw new IllegalStateException();
        if (index >= size || index < 0) throw new IllegalArgumentException();

        return items[index];
    }

    public boolean isMaxHeap(T[] array) {
        int index = array.length - 1;

        while (index > 0) {
            if (array[index].compareTo(array[parentIndexOf(index)]) > 0) return false;
            index = parentIndexOf(index);
        }
        return true;
    }

    public T max() {
        if (isEmpty()) throw new IllegalStateException();

        return items[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.stream(items)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}