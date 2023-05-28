package io.github.aliazani.nonlinear.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GraphNode<T extends Comparable<T>> {
    private T value;

    @Override
    public String toString() {
        return value.toString();
    }
}