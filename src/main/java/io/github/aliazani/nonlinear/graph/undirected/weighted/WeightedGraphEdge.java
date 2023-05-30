package io.github.aliazani.nonlinear.graph.undirected.weighted;

record WeightedGraphEdge<T extends Comparable<T>>(WeightedGraphNode<T> from, WeightedGraphNode<T> to, int weight) {
}