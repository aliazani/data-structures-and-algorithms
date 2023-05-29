package io.github.aliazani.nonlinear.heap.min_heap;

import java.text.MessageFormat;

/**
 * A record representing a node in a Min Heap.
 *
 * @param <T> the type of the key stored in the node, must implement Comparable interface
 * @param <E> the type of the value stored in the node, must implement Comparable interface
 */
public record MinHeapNode<T extends Comparable<T>, E extends Comparable<E>>(T key, E value) {
    /**
     * Returns a string representation of the Min Heap node.
     *
     * @return a string representation of the Min Heap node
     */
    @Override
    public String toString() {
        return MessageFormat.format("(key={0}, value={1})", key, value);
    }
}