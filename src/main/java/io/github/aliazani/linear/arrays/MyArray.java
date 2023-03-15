package io.github.aliazani.linear.arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArray<T extends Comparable<T>> implements Iterable<T> {
    private T[] elements;
    private MyArray<T> arrayWithNewLength;
    private int size;

    public MyArray(int length) {
        elements = (T[]) new Comparable[length];
    }

    public void insert(T item) {
        insertAt(size, item);
    }

    public void insertAt(int index, T item) {
        createNewArray();
        insertItemAtVariousPlacesOfArray(item, index);
        replaceOldArrayWithResizedOne();
        size++;
    }

    private void createNewArray() {
        if (isArrayFull()) arrayWithNewLength = new MyArray<>(elements.length + 1);
        else arrayWithNewLength = new MyArray<>(elements.length);
    }

    private boolean isArrayFull() {
        return size >= elements.length;
    }

    private void insertItemAtVariousPlacesOfArray(T item, int index) {
        if (isInsertionAtTheEndOfArray(index)) insertAtTheEndOfArray(item, index);
        else if (isInsertionAtTheBeginningOfArray(index)) insertAtTheBeginningOfArray(item, index);
        else insertInTheMiddleOfArray(item, index);
    }

    private boolean isInsertionAtTheEndOfArray(int index) {
        return index == arrayWithNewLength.elements.length - 1;
    }

    private void insertAtTheEndOfArray(T item, int index) {
        copyItemsBeforeInsertNewItemAtIndex(index);
        arrayWithNewLength.elements[index] = item;
    }

    private boolean isInsertionAtTheBeginningOfArray(int index) {
        return index == 0;
    }

    private void insertAtTheBeginningOfArray(T item, int index) {
        arrayWithNewLength.elements[index] = item;
        shiftOneItemToRight(0);
    }

    private void insertInTheMiddleOfArray(T item, int index) {
        copyItemsBeforeInsertNewItemAtIndex(index);
        arrayWithNewLength.elements[index] = item;
        shiftOneItemToRight(index);
    }

    private void copyItemsBeforeInsertNewItemAtIndex(int index) {
        for (int i = 0; i < index; i++)
            if (isNotNull(elements[i])) arrayWithNewLength.elements[i] = elements[i];
    }

    private void shiftOneItemToRight(int start) {
        for (int i = start; i < elements.length; i++)
            if (isNotNull(elements[i])) arrayWithNewLength.elements[i + 1] = elements[i];
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        elements[index] = null;
        arrayWithNewLength = new MyArray<>(--size);
        copyItemsToResizedArray();
        replaceOldArrayWithResizedOne();
    }

    private void copyItemsToResizedArray() {
        for (T item : elements)
            if (isNotNull(item)) arrayWithNewLength.insert(item);
    }

    private boolean isNotNull(T item) {
        return item != null;
    }

    private void replaceOldArrayWithResizedOne() {
        elements = arrayWithNewLength.elements;
        arrayWithNewLength = null;
    }

    public int indexOf(T item) {
        for (int i = 0; i < elements.length; i++)
            if (elements[i].equals(item)) return i;

        return -1;
    }

    public MyArray<T> reverse() {
        MyArray<T> reversedArray = new MyArray<>(elements.length);

        for (int i = elements.length - 1; i >= 0; i--) reversedArray.insert(elements[i]);

        return reversedArray;
    }

    public T max() {
        T maximumValue = elements[0];
        for (int i = 1; i < elements.length; i++)
            if (maximumValue.compareTo(elements[i]) < 0) maximumValue = elements[i];

        return maximumValue;
    }

    public MyArray<T> intersect(MyArray<T> other) {
        int lengthOfFirstArray = elements.length;
        MyArray<T> intersectedArray = new MyArray<>(lengthOfFirstArray);

        insertCommonItemsToNewArray(other, intersectedArray);

        return intersectedArray;
    }

    private void insertCommonItemsToNewArray(MyArray<T> other, MyArray<T> intersectedArray) {
        for (T element : elements)
            for (int j = 0; j < other.elements.length; j++)
                if (element.equals(other.elements[j])) intersectedArray.insert(element);
    }

    public int size() {
        return size;
    }

    public T getItemAtIndex(int i) {
        return elements[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayIterator(this);
    }

    private class MyArrayIterator implements Iterator<T> {
        private final MyArray<T> array;
        private int index;

        public MyArrayIterator(MyArray<T> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.elements.length;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            return array.elements[index++];
        }
    }
}