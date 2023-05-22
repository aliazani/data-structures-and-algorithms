package io.github.aliazani.nonlinear.heap.min_heap;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MinHeap<T extends Comparable<T>, E extends Comparable<E>> {
    private final MinHeapNode<T, E>[] items;
    private int size;

    public MinHeap(int size) {
        items = (MinHeapNode<T, E>[]) new MinHeapNode[size];
    }

    public void insert(T key, E value) {
        if (isFull()) throw new IllegalStateException("The heap is full!");
        items[size++] = new MinHeapNode<>(key, value);
        bubbleUp();
    }

    public boolean isFull() {
        return items.length == size;
    }

    private void bubbleUp() {
        int index = size - 1;

        while (index > 0
                && items[index].getKey()
                .compareTo(items[getParentIndex(index)].getKey()) < 0) {
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

    public MinHeapNode<T, E> remove() {
        if (isEmpty()) throw new IllegalStateException("Heap is Empty!");

        MinHeapNode<T, E> root = items[0];
        items[0] = items[size - 1];
        items[size - 1] = null;
        size--;

        bubbleDown();

        return root;
    }

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

        boolean isValid = items[index].getKey()
                .compareTo(items[getLeftChildIndex(index)].getKey()) <= 0;
        if (hasRightChild(index)) isValid &= items[index].getKey()
                .compareTo(items[getRightChildIndex(index)].getKey()) <= 0;

        return isValid;
    }

    private int getSmallerChild(int index) {
        if (!hasRightChild(index)) return getLeftChildIndex(index);
        else
            return (items[getLeftChildIndex(index)].getKey()
                    .compareTo(items[getRightChildIndex(index)].getKey()) < 0)
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

    public MinHeapNode<T, E> get(int index) {
        if (isEmpty()) throw new IllegalStateException();
        if (index >= size || index < 0) throw new IllegalArgumentException();

        return items[index];
    }

    public MinHeapNode<T, E> min() {
        if (isEmpty()) throw new IllegalStateException("Heap is Empty!");

        return items[0];
    }

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