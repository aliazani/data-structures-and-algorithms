package io.github.aliazani.nonlinear.graph.undirected.weighted;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
class WeightedGraphNode<T extends Comparable<T>> {
    private final T value;
    private final Map<T, WeightedGraphEdge<T>> edges;

    public WeightedGraphNode(T value) {
        this.value = value;
        edges = new HashMap<>();
    }

    public void addEdge(WeightedGraphNode<T> to, int weight) {
        edges.putIfAbsent(to.value, new WeightedGraphEdge<>(this, to, weight));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}