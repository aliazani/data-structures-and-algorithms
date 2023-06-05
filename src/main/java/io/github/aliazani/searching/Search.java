package io.github.aliazani.searching;

import java.util.stream.IntStream;

/**
 * Utility class for performing various searching algorithms on arrays.
 *
 * @param <T> the type of elements in the array.
 */
public class Search<T extends Comparable<T>> {

    /**
     * Performs linear search on the array to find the target element.
     *
     * @param array  the array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
    public int linearSearch(T[] array, T target) {
        return IntStream.range(0, array.length)
                .filter(i -> array[i].compareTo(target) == 0)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Performs binary search on the sorted array to find the target element.
     *
     * @param array  the sorted array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
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

    /**
     * Performs binary search on the sorted array to find the target element (iterative approach).
     *
     * @param array  the sorted array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
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

    /**
     * Performs ternary search on the sorted array to find the target element.
     *
     * @param array  the sorted array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
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

    /**
     * Performs jump search on the sorted array to find the target element.
     *
     * @param array  the sorted array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
    public int jumpSearch(T[] array, T target) {
        int blockSize = (int) Math.sqrt(array.length);
        int start = 0;
        int next = blockSize;

        while (start < array.length
                &&
                target.compareTo(array[next - 1]) > 0) {
            start = next;
            next += blockSize;
            if (next > array.length) next = array.length;
        }

        return linearSearch(array, target, start, next);
    }

    private int linearSearch(T[] array, T target, int left, int right) {
        return IntStream.range(left, right)
                .filter(i -> array[i].compareTo(target) == 0)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Performs an exponential search on the sorted array to find the target element.
     *
     * @param array  the sorted array to search.
     * @param target the target element to find.
     * @return the index of the target element if found, or -1 if not found.
     */
    public int exponentialSearch(T[] array, T target) {
        int bound = 1;
        while (bound < array.length && target.compareTo(array[bound]) > 0) bound *= 2;

        int left = bound / 2;
        int right = Math.min(bound, array.length - 1);

        return binarySearch(array, target, left, right);
    }
}