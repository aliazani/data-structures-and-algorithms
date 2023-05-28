package io.github.aliazani.nonlinear.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Graph")
class GraphTest {
    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @DisplayName("addVertex - " +
            "When adding a vertex to empty adjacency list - " +
            "Should add the vertex to the graph")
    @Test
    void addVertex_adjacencyListIsEmpty_addVertexToGraph() {
        graph.addVertex(10);

        assertEquals("10: []", graph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding a vertex to non-empty adjacency list - " +
            "Should add the vertex to the graph")
    @Test
    void addVertex_adjacencyListIsNotEmpty_addVertexToGraph() {
        graph.addVertex(10);
        graph.addVertex(20);

        assertEquals("""
                10: []
                20: []""", graph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding a null vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void addVertex_addingNullVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(null));
    }

    @DisplayName("removeVertex - " +
            "When removing a vertex from adjacency list with one vertex - " +
            "Should remove the vertex from the graph")
    @Test
    void removeVertex_removingFromAdjacencyListWithOneVertex_removeVertexFromGraph() {
        graph.addVertex(10);

        assertEquals(10, graph.removeVertex(10));
        assertEquals("", graph.toString());
    }

    @DisplayName("removeVertex - " +
            "When removing a vertex from adjacency list with more than one vertex - " +
            "Should remove the vertex from the graph")
    @Test
    void removeVertex_removingFromAdjacencyListWithMoreThanOneVertex_removeVertexFromGraph() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(20, 10);
        graph.addEdge(30, 10);

        assertEquals(10, graph.removeVertex(10));
        assertEquals("""
                20: []
                30: []""", graph.toString());
    }

    @DisplayName("removeVertex - " +
            "When removing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void removeVertex_removingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.removeVertex(10));
    }

    @DisplayName("addEdge - " +
            "When adding an edge between two vertices - " +
            "Should add the edge to the graph")
    @Test
    void addEdge_isCalled_addEdgeToGraph() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addEdge(10, 20);

        assertEquals("""
                10: [20]
                20: []""", graph.toString());
    }

    @DisplayName("addEdge - " +
            "When adding an edge with a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void addEdge_addingEdgeWithNonExistingVertex_throwIllegalArgumentException() {
        graph.addVertex(10);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(10, 20));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(20, 10));
    }

    @DisplayName("removeEdge - " +
            "When removing an existing edge - " +
            "Should remove the edge from the graph")
    @Test
    void removeEdge_isCalled_removeExistingEdgeFromGraph() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addEdge(10, 20);
        graph.addEdge(20, 10);
        graph.removeEdge(10, 20);

        assertEquals("""
                10: []
                20: [10]""", graph.toString());
    }

    @DisplayName("removeEdge - " +
            "When removing a non-existing edge - " +
            "Should not modify the graph")
    @Test
    void removeEdge_removingNonExistingEdge_doNotModifyGraph() {
        graph.addVertex(10);
        graph.addVertex(20);

        assertDoesNotThrow(() -> graph.removeEdge(10, 20));
    }

    @DisplayName("findNeighbors - " +
            "When finding neighbors of a vertex - " +
            "Should return the correct list of neighbors")
    @Test
    void findNeighbors_vertexIsValid_returnCorrectListOfNeighbors() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(10, 30);

        assertEquals(List.of(20, 30), graph.findNeighbors(10));
    }

    @DisplayName("findNeighbors - " +
            "When finding neighbors of a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void findNeighbors_findingNeighborsOfNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.findNeighbors(10));
    }

    @DisplayName("queryEdge - " +
            "When querying an existing edge - " +
            "Should return true")
    @Test
    void queryEdge_queriesExistingEdge_returnTrue() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addEdge(10, 20);

        assertTrue(graph.queryEdge(10, 20));
    }

    @DisplayName("queryEdge - " +
            "When querying a non-existing edge - " +
            "Should return false")
    @Test
    void queryEdge_queriesNonExistingEdge_returnFalse() {
        graph.addVertex(10);
        graph.addVertex(20);

        assertFalse(graph.queryEdge(10, 20));
    }

    @DisplayName("toString - " +
            "When the graph is empty - " +
            "Should return an empty string")
    @Test
    void toString_emptyGraph_returnsEmptyString() {
        assertEquals("", graph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has one vertex without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithOneVertexWithoutNeighbors_returnsCorrectStringRepresentation() {
        graph.addVertex(10);

        assertEquals("10: []", graph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has multiple vertices without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithMultipleVerticesWithoutNeighbors_returnsCorrectStringRepresentation() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);

        assertEquals("""
                10: []
                20: []
                30: []""", graph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has vertices with neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithVerticesAndNeighbors_returnsCorrectStringRepresentation() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);

        assertEquals("""
                10: [20]
                20: [30]
                30: []""", graph.toString());
    }
}