package io.github.aliazani.nonlinear.graph.undirected.weighted;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in a weighted undirected graph.
 *
 * @param <T> the type of the node value.
 */
@Getter
class WeightedGraphNode<T extends Comparable<T>> {
    private final T value;
    private final Map<T, WeightedGraphEdge<T>> edges;

    /**
     * Constructs a weighted graph node with the specified value.
     *
     * @param value the value of the node.
     */
    public WeightedGraphNode(T value) {
        this.value = value;
        edges = new HashMap<>();
    }

    /**
     * Adds an edge to another node with the specified weight.
     *
     * @param to     the node to connect to.
     * @param weight the weight of the edge.
     */
    public void addEdge(WeightedGraphNode<T> to, int weight) {
        edges.putIfAbsent(to.value, new WeightedGraphEdge<>(this, to, weight));
    }

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