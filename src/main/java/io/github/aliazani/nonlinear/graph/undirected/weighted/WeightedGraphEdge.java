package io.github.aliazani.nonlinear.graph.undirected.weighted;

/**
 * Represents a weighted edge in a graph.
 *
 * @param <T> the type of nodes in the edge.
 */
record WeightedGraphEdge<T extends Comparable<T>>(WeightedGraphNode<T> from, WeightedGraphNode<T> to, int weight) {
}