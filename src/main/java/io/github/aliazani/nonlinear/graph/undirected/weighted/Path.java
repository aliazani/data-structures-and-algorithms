package io.github.aliazani.nonlinear.graph.undirected.weighted;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Path<T extends Comparable<T>> {
    private final List<T> nodes;

    public Path() {
        nodes = new ArrayList<>();
    }

    public void addNode(T node) {
        if (node == null)  throw new IllegalArgumentException();
        nodes.add(node);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}