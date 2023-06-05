package io.github.aliazani.nonlinear.graph.directed;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a node in a directed graph.
 *
 * @param <T> the type of the node value.
 */
@Getter
@AllArgsConstructor
class DirectedGraphNode<T extends Comparable<T>> {
    private T value;

    /**
     * Returns a string representation of the node value.
     *
     * @return a string representation of the node value.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}