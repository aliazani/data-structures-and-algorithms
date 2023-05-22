package io.github.aliazani.nonlinear.heap.min_heap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public class MinHeapNode<T extends Comparable<T>, E extends Comparable<E>> {
    private final T key;
    private final E value;

    @Override
    public String toString() {
        return MessageFormat.format("(key={0}, value={1})", key, value);
    }
}