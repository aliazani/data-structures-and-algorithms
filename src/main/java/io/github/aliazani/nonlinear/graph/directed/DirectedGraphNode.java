package io.github.aliazani.nonlinear.graph.directed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class DirectedGraphNode<T extends Comparable<T>> {
    private T value;

    @Override
    public String toString() {
        return value.toString();
    }
}