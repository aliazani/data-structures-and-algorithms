package io.github.aliazani.linear.arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic class that represents an array of comparable elements.
 * Provides methods for inserting, removing, and finding elements in the array,
 * as well as iterating over its contents.
 *
 * @param <T> The type of elements in the array. Must extend the Comparable interface.
 */
public class MyArray<T extends Comparable<T>> implements Iterable<T> {
    private T[] elements;
    private MyArray<T> arrayWithNewLength;
    private int size;

    public MyArray(int length) {
        elements = (T[]) new Comparable[length];
    }

    /**
     * Inserts a new element at the end of the array.
     *
     * @param item The element to insert.
     */
    public void insert(T item) {
        insertAt(size, item);
    }

    /**
     * Inserts a new element at a specified index in the array.
     *
     * @param index The index at which to insert the element.
     * @param item  The element to insert.
     */
    public void insertAt(int index, T item) {
        createNewArray();
        insertItemAtVariousPlacesOfArray(item, index);
        replaceOldArrayWithResizedOne();
        size++;
    }

    /**
     * Creates a new array with length increased by one if the current array is full.
     */
    private void createNewArray() {
        if (isArrayFull()) arrayWithNewLength = new MyArray<>(elements.length + 1);
        else arrayWithNewLength = new MyArray<>(elements.length);
    }

    /**
     * Returns true if the array is full.
     *
     * @return true if the array is full, false otherwise.
     */
    private boolean isArrayFull() {
        return size >= elements.length;
    }

    /**
     * Inserts an element at various places in the array depending on the index.
     *
     * @param item  The element to insert.
     * @param index The index at which to insert the element.
     */
    private void insertItemAtVariousPlacesOfArray(T item, int index) {
        if (isInsertionAtTheEndOfArray(index)) insertAtTheEndOfArray(item);
        else if (isInsertionAtTheBeginningOfArray(index)) insertAtTheBeginningOfArray(item);
        else insertInTheMiddleOfArray(item, index);
    }

    /**
     * Returns true if the insertion is at the end of the array.
     *
     * @param index The index at which to insert the element.
     * @return true if the insertion is at the end of the array, false otherwise.
     */
    private boolean isInsertionAtTheEndOfArray(int index) {
        return index == arrayWithNewLength.elements.length - 1;
    }

    /**
     * Inserts an item at the end of the array.
     *
     * @param item the item to be inserted
     */
    private void insertAtTheEndOfArray(T item) {
        copyItemsBeforeInsertNewItemAtIndex(arrayWithNewLength.elements.length - 1);
        arrayWithNewLength.elements[arrayWithNewLength.elements.length - 1] = item;
    }

    /**
     * Checks if the insertion is at the beginning of the array.
     *
     * @param index the index at which to check for insertion
     * @return true if the index is 0, false otherwise
     */
    private boolean isInsertionAtTheBeginningOfArray(int index) {
        return index == 0;
    }

    /**
     * Inserts an item at the beginning of the array.
     *
     * @param item the item to be inserted
     */
    private void insertAtTheBeginningOfArray(T item) {
        arrayWithNewLength.elements[0] = item;
        shiftOneItemToRight(0);
    }

    /**
     * Inserts an item in the middle of the array.
     *
     * @param item  the item to be inserted
     * @param index the index at which to insert the item
     */
    private void insertInTheMiddleOfArray(T item, int index) {
        copyItemsBeforeInsertNewItemAtIndex(index);
        arrayWithNewLength.elements[index] = item;
        shiftOneItemToRight(index);
    }

    /**
     * Copies the items before the index where the new item is to be inserted.
     *
     * @param index the index before which to copy the items
     */
    private void copyItemsBeforeInsertNewItemAtIndex(int index) {
        for (int i = 0; i < index; i++)
            if (isNotNull(elements[i])) arrayWithNewLength.elements[i] = elements[i];
    }

    /**
     * Shifts one item to the right, starting from the specified index.
     *
     * @param start the index from which to start shifting
     */
    private void shiftOneItemToRight(int start) {
        for (int i = start; i < elements.length; i++)
            if (isNotNull(elements[i])) arrayWithNewLength.elements[i + 1] = elements[i];
    }

    /**
     * Removes the item at the specified index.
     *
     * @param index the index of the item to be removed
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        elements[index] = null;
        arrayWithNewLength = new MyArray<>(--size);
        copyItemsToResizedArray();
        replaceOldArrayWithResizedOne();
    }

    /**
     * Copies the items from the old array to the new resized array.
     */
    private void copyItemsToResizedArray() {
        for (T item : elements)
            if (isNotNull(item)) arrayWithNewLength.insert(item);
    }

    /**
     * Checks if the item is not null.
     *
     * @param item the item to check
     * @return true if the item is not null, false otherwise
     */
    private boolean isNotNull(T item) {
        return item != null;
    }

    /**
     * Replaces the old array with the newly resized array.
     */
    private void replaceOldArrayWithResizedOne() {
        elements = arrayWithNewLength.elements;
        arrayWithNewLength = null;
    }

    /**
     * Returns the index of the specified item in the array.
     *
     * @param item the item to search for
     * @return the index of the item, or -1 if the item is not found
     */
    public int indexOf(T item) {
        for (int i = 0; i < elements.length; i++)
            if (elements[i].equals(item)) return i;

        return -1;
    }


    /**
     * Reverses the array and returns a new array.
     *
     * @return a new array with the elements in reverse order
     */
    public MyArray<T> reverse() {
        MyArray<T> reversedArray = new MyArray<>(elements.length);

        for (int i = elements.length - 1; i >= 0; i--) reversedArray.insert(elements[i]);

        return reversedArray;
    }

    /**
     * Returns the maximum value in the array.
     *
     * @return the maximum value in the array
     */
    public T max() {
        T maximumValue = elements[0];
        for (int i = 1; i < elements.length; i++)
            if (maximumValue.compareTo(elements[i]) < 0) maximumValue = elements[i];

        return maximumValue;
    }

    /**
     * Returns a new array containing the common items between this array and another array.
     *
     * @param other the other array to compare with
     * @return a new array containing the common items
     */
    public MyArray<T> intersect(MyArray<T> other) {
        int lengthOfFirstArray = elements.length;
        MyArray<T> intersectedArray = new MyArray<>(lengthOfFirstArray);

        insertCommonItemsToNewArray(other, intersectedArray);

        return intersectedArray;
    }

    /**
     * Inserts common elements from another MyArray object to the current MyArray object
     * and stores the common elements in a new MyArray object.
     *
     * @param other            the MyArray object to compare against the current MyArray object
     * @param intersectedArray the MyArray object to store the common elements in
     */
    private void insertCommonItemsToNewArray(MyArray<T> other, MyArray<T> intersectedArray) {
        for (T element : elements)
            for (int j = 0; j < other.elements.length; j++)
                if (element.equals(other.elements[j])) intersectedArray.insert(element);
    }

    /**
     * Returns the size of the array.
     *
     * @return the size of the array
     */
    public int size() {
        return size;
    }

    /**
     * Returns the item at the specified index.
     *
     * @param i the index of the item to retrieve
     * @return the item at the specified index
     */
    public T getItemAtIndex(int i) {
        return elements[i];
    }

    /**
     * Returns a string representation of the array.
     *
     * @return a string representation of the array
     */
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