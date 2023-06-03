package io.github.aliazani.sorting;

import java.util.*;

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

    public void mergeSort(T[] array) {
        if (array == null || array.length <= 1) return;

        int mid = array.length / 2;

        T[] left = (T[]) new Comparable[mid];
        System.arraycopy(array, 0, left, 0, mid);

        T[] right = (T[]) new Comparable[array.length - mid];
        System.arraycopy(array, mid, right, 0, array.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private void merge(T[] array, T[] left, T[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0)
                array[k++] = left[i++];
            else array[k++] = right[j++];
        }

        while (i < left.length) array[k++] = left[i++];
        while (j < right.length) array[k++] = right[j++];
    }

    public void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int start, int end) {
        if (start >= end) return;
        int boundary = partition(array, start, end);
        quickSort(array, start, boundary - 1);
        quickSort(array, boundary + 1, end);
    }

    private int partition(T[] array, int start, int end) {
        T pivot = array[end];
        int boundary = start - 1;

        for (int i = start; i <= end; i++)
            if (array[i].compareTo(pivot) <= 0)
                swap(array, i, ++boundary);

        return boundary;
    }

    public void countingSort(int[] array, int max) {
        int[] counts = new int[max + 1];

        for (int item : array)
            counts[item]++;

        int k = 0;
        for (int i = 0; i < counts.length; i++)
            for (int j = 0; j < counts[i]; j++) array[k++] = i;
    }

    public void bucketSort(T[] array, int numberOfBuckets) {
        int i = 0;
        for (List<T> bucket : createBuckets(array, numberOfBuckets)) {
            Collections.sort(bucket);
            for (T item : bucket)
                array[i++] = item;
        }
    }

    private List<List<T>> createBuckets(T[] array, int numberOfBuckets) {
        List<List<T>> buckets = new ArrayList<>();

        for (var i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (T item : array)
            buckets.get(item.hashCode() / numberOfBuckets).add(item);

        return buckets;
    }
}