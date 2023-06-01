package io.github.aliazani.sorting;

public class Sorter<T extends Comparable<T>> {
    public void bubbleSort(T[] array) {
        boolean isSorted;
        for (int i = 0; i < array.length; i++) {
            isSorted = true;
            for (int j = 1; j < array.length - i; j++)
                if (array[j - 1].compareTo(array[j]) > 0) {
                    swap(array, j - 1, j);
                    isSorted = false;
                }
            if (isSorted) return;
        }
    }

    private void swap(T[] items, int firstIndex, int secondIndex) {
        T temp = items[firstIndex];
        items[firstIndex] = items[secondIndex];
        items[secondIndex] = temp;
    }
}