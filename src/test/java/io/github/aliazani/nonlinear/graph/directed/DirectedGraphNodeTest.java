package io.github.aliazani.nonlinear.graph.directed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedGraphNodeTest {
    @DisplayName("toString - " +
            "When the value is a string - " +
            "Should return the correct string representation")
    @Test
    void toString_stringValue_returnsCorrectStringRepresentation() {
        DirectedGraphNode<String> node = new DirectedGraphNode<>("Hello");

        assertEquals("Hello", node.toString());
    }

    @DisplayName("toString - " +
            "When the value is an integer - " +
            "Should return the correct string representation")
    @Test
    void toString_integerValue_returnsCorrectStringRepresentation() {
        DirectedGraphNode<Integer> node = new DirectedGraphNode<>(42);

        assertEquals("42", node.toString());
    }
}