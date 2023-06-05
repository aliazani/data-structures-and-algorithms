package io.github.aliazani.nonlinear.graph.undirected.weighted;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a path in a weighted undirected graph.
 *
 * @param <T> the type of nodes in the path.
 */
@Getter
public class Path<T extends Comparable<T>> {
    private final List<T> nodes;

    /**
     * Constructs an empty path.
     */
    public Path() {
        nodes = new ArrayList<>();
    }

    /**
     * Adds a node to the path.
     *
     * @param node the node to add.
     * @throws IllegalArgumentException if the node is null.
     */
    public void addNode(T node) {
        if (node == null)  throw new IllegalArgumentException();
        nodes.add(node);
    }


    /**
     * Returns a string representation of the path.
     *
     * @return a string representation of the path.
     */
    @Override
    public String toString() {
        return nodes.toString();
    }
}