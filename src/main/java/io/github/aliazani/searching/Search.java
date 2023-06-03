package io.github.aliazani.searching;

import java.util.stream.IntStream;

public class Search<T extends Comparable<T>> {
    public int linearSearch(T[] array, T target) {
        return IntStream.range(0, array.length)
                .filter(i -> array[i].compareTo(target) == 0)
                .findFirst()
                .orElse(-1);
    }

    public int binarySearch(T[] array, T target) {
        return binarySearch(array, target, 0, array.length - 1);
    }

    private int binarySearch(T[] array, T target, int left, int right) {
        if (left > right) return -1;
        int middle = (left + right) / 2;

        if (target.compareTo(array[middle]) < 0)
            return binarySearch(array, target, left, middle - 1);
        else if (target.compareTo(array[middle]) > 0)
            return binarySearch(array, target, middle + 1, right);
        else return middle;
    }

    public int binarySearchIterative(T[] array, T target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (target.compareTo(array[middle]) < 0) right = middle - 1;
            else if (target.compareTo(array[middle]) > 0) left = middle + 1;
            else return middle;
        }
        return -1;
    }

    public int ternarySearch(T[] array, T target) {
        return ternarySearch(array, target, 0, array.length - 1);
    }

    private int ternarySearch(T[] array, T target, int left, int right) {
        if (left > right) return -1;
        int partitionSize = (right - left) / 3;
        int mid1 = left + partitionSize;
        int mid2 = right - partitionSize;

        if (target.compareTo(array[mid1]) < 0) return ternarySearch(array, target, left, mid1 - 1);
        else if (target.compareTo(array[mid1]) == 0) return mid1;
        else if (target.compareTo(array[mid1]) > 0
                &&
                target.compareTo(array[mid2]) < 0) return ternarySearch(array, target, mid1 + 1, mid2 - 1);
        else if (target.compareTo(array[mid2]) == 0) return mid2;
        else return ternarySearch(array, target, mid2 + 1, right);
    }
}