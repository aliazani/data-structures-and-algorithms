package io.github.aliazani.nonlinear.graph.undirected.weighted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WeightedGraphNode")
class WeightedGraphNodeTest {
    private WeightedGraphNode<Integer> node;
    private WeightedGraphNode<Integer> neighbor;
    private WeightedGraphEdge<Integer> edge;

    @BeforeEach
    void setUp() {
        node = new WeightedGraphNode<>(1);
        neighbor = new WeightedGraphNode<>(2);
        edge = new WeightedGraphEdge<>(node, neighbor, 5);
    }

    @DisplayName("addEdge - " +
            "When adding a new edge - " +
            "Should add edge to the edges map")
    @Test
    void addEdge_addNewEdge_addEdgeToEdgesMap() {
        node.addEdge(neighbor, 5);

        assertTrue(node.getEdges().containsKey(neighbor.getValue()));
        assertEquals(edge, node.getEdges().get(neighbor.getValue()));
    }

    @DisplayName("addEdge - " +
            "When adding an edge to an existing destination node - " +
            "Should update the existing edge")
    @Test
    void addEdge_updateExistingEdge_updateExistingEdgeInEdgesMap() {
        node.addEdge(neighbor, 5);
        node.addEdge(neighbor, 10);

        assertTrue(node.getEdges().containsKey(neighbor.getValue()));
        assertNotEquals(new WeightedGraphEdge<>(node, neighbor, 10), node.getEdges().get(neighbor.getValue()));
        assertNotEquals( 10, node.getEdges().get(neighbor.getValue()).weight());
    }

    @DisplayName("toString - " +
            "When called - " +
            "Should return the string representation of the node value")
    @Test
    void toString_returnStringRepresentationOfNodeValue() {
        WeightedGraphNode<Integer> node = new WeightedGraphNode<>(10);

        assertEquals("10", node.toString());
    }
}