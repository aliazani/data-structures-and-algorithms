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

    public void selectionSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++)
                if (array[j].compareTo(array[minIndex]) < 0) minIndex = j;
            if (minIndex != i) swap(array, i, minIndex);
        }
    }

    public void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T current = array[i];
            int j = i - 1;
            while (j >= 0 && current.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    private void swap(T[] items, int firstIndex, int secondIndex) {
        T temp = items[firstIndex];
        items[firstIndex] = items[secondIndex];
        items[secondIndex] = temp;
    }
}