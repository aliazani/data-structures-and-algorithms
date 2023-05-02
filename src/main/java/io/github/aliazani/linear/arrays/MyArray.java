package io.github.aliazani.linear.arrays;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArray<T extends Comparable<T>> implements Iterable<T> {
    private T[] elements;
    private int size;

    public MyArray(int length) {
        elements = (T[]) new Comparable[length];
    }

    public void insert(T item) {
        insertAt(size, item);
    }

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

    public int indexOf(T item) {
        for (int i = 0; i < size; i++)
            if (Objects.equals(elements[i], item)) return i;

        return -1;
    }

    public MyArray<T> reverse() {
        MyArray<T> reversed = new MyArray<>(size);

        for (T item : this) reversed.insertAt(0, item);

        return reversed;
    }

    public T max() {
        if (isEmpty())
            throw new NoSuchElementException("Array is empty");

        T maximumValue = elements[0];
        for (T item : this)
            if (maximumValue.compareTo(item) < 0) maximumValue = item;

        return maximumValue;
    }

    public MyArray<T> intersect(MyArray<T> other) {
        MyArray<T> intersection = new MyArray<>(elements.length);

        for (T item : this)
            if (other.indexOf(item) != -1 && intersection.indexOf(item) == -1) intersection.insert(item);

        return intersection;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds: " + index);

        return elements[index];
    }

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