package io.github.aliazani.searching;

import java.util.stream.IntStream;

public class Search<T extends Comparable<T>> {
    public int linearSearch(T[] array, T item) {
        return IntStream.range(0, array.length)
                .filter(i -> array[i].compareTo(item) == 0)
                .findFirst()
                .orElse(-1);
    }
}
