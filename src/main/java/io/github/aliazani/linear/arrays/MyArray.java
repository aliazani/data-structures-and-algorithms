package io.github.aliazani.linear.arrays;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a resizable array data structure.
 *
 * @param <T> the type of elements in the array, must extend Comparable interface.
 */
public class MyArray<T extends Comparable<T>> implements Iterable<T> {
    private T[] elements;
    private int size;

    /**
     * Constructs an empty array with the specified length.
     *
     * @param length the initial capacity of the array.
     */
    public MyArray(int length) {
        elements = (T[]) new Comparable[length];
    }

    /**
     * Inserts an element at the end of the array.
     *
     * @param item the element to be inserted.
     */
    public void insert(T item) {
        insertAt(size, item);
    }

    /**
     * Inserts an element at the specified index.
     *
     * @param index the index where the element is to be inserted.
     * @param item  the element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void insertAt(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException(MessageFormat
                .format("Index out of bounds: {0}", index));

        if (isFull()) resize(2 * elements.length);

        shiftOneItemToRight(index);
        elements[index] = item;

        size++;
    }

    private boolean isFull() {
        return size >= elements.length;
    }

    private void resize(int capacity) {
        elements = Arrays.copyOf(elements, capacity);
    }

    private void shiftOneItemToRight(int start) {
        System.arraycopy(elements, start, elements, start + 1, size - start);
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(MessageFormat.
                format("Index out of bounds: {0}", index));

        shiftOneItemToLeft(index);
        elements[size - 1] = null;

        size--;

        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
    }

    private void shiftOneItemToLeft(int start) {
        System.arraycopy(elements, start + 1, elements, start, size - start - 1);
    }

    /**
     * Returns the index of the first occurrence of the specified element in the array,
     * <p>
     * or -1 if the array does not contain the element.
     *
     * @param item the element to be searched for.
     * @return the index of the first occurrence of the element, or -1 if not found.
     */
    public int indexOf(T item) {
        for (int i = 0; i < size; i++)
            if (Objects.equals(elements[i], item)) return i;

        return -1;
    }

    /**
     * Returns a new MyArray instance that contains the elements of this array in reverse order.
     *
     * @return a new MyArray instance containing the elements of this array in reverse order.
     */
    public MyArray<T> reverse() {
        MyArray<T> reversed = new MyArray<>(size);

        for (T item : this) reversed.insertAt(0, item);

        return reversed;
    }

    /**
     * Returns the maximum element in the array.
     *
     * @return the maximum element in the array.
     * @throws NoSuchElementException if the array is empty.
     */
    public T max() {
        if (isEmpty())
            throw new NoSuchElementException("Array is empty");

        T maximumValue = elements[0];
        for (T item : this)
            if (maximumValue.compareTo(item) < 0) maximumValue = item;

        return maximumValue;
    }

    /**
     * Returns a new MyArray object containing the intersection of this MyArray object and another MyArray object.
     *
     * @param other The MyArray object to intersect with this MyArray object.
     * @return A new MyArray object containing the intersection of this MyArray object and another MyArray object.
     */

    public MyArray<T> intersect(MyArray<T> other) {
        MyArray<T> intersection = new MyArray<>(elements.length);

        for (T item : this)
            if (other.indexOf(item) != -1 && intersection.indexOf(item) == -1) intersection.insert(item);

        return intersection;
    }

    /**
     * Returns the number of elements in this MyArray object.
     *
     * @return The number of elements in this MyArray object.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified index in this MyArray object.
     *
     * @param index The index of the element to return.
     * @return The element at the specified index in this MyArray object.
     * @throws IndexOutOfBoundsException if the index is out of range (index {@literal <} 0 || index {@literal >}= size).
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds: " + index);

        return elements[index];
    }

    /**
     * Returns a string representation of this MyArray object.
     *
     * @return A string representation of this MyArray object.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        if (!isEmpty()) appendItems(stringBuilder);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void appendItems(StringBuilder stringBuilder) {
        for (int i = 0; i < size - 1; i++)
            if (isNotNull(elements[i])) stringBuilder.append(elements[i]).append(", ");

        stringBuilder.append(elements[size - 1]);
    }

    private boolean isNotNull(T item) {
        return item != null;
    }

    /**
     * Returns an iterator over the elements in this MyArray in proper sequence.
     *
     * @return An iterator over the elements in this MyArray in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayIterator();
    }

    private class MyArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            return elements[currentIndex++];
        }
    }
}