package io.github.aliazani.nonlinear.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphNodeTest {

    @DisplayName("toString - " +
            "When the value is a string - " +
            "Should return the correct string representation")
    @Test
    void toString_stringValue_returnsCorrectStringRepresentation() {
        GraphNode<String> node = new GraphNode<>("Hello");

        assertEquals("Hello", node.toString());
    }

    @DisplayName("toString - " +
            "When the value is an integer - " +
            "Should return the correct string representation")
    @Test
    void toString_integerValue_returnsCorrectStringRepresentation() {
        GraphNode<Integer> node = new GraphNode<>(42);

        assertEquals("42", node.toString());
    }
}