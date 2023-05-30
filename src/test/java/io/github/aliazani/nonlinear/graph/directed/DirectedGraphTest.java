package io.github.aliazani.nonlinear.graph.directed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Graph")
class DirectedGraphTest {
    private DirectedGraph<Integer> directedGraph;

    @BeforeEach
    void setUp() {
        directedGraph = new DirectedGraph<>();
    }

    @DisplayName("addVertex - " +
            "When adding a vertex to empty adjacency list - " +
            "Should add the vertex to the graph")
    @Test
    void addVertex_adjacencyListIsEmpty_addVertexToGraph() {
        directedGraph.addVertex(10);

        assertEquals("10: []", directedGraph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding a vertex to non-empty adjacency list - " +
            "Should add the vertex to the graph")
    @Test
    void addVertex_adjacencyListIsNotEmpty_addVertexToGraph() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);

        assertEquals("""
                10: []
                20: []""", directedGraph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding a null vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void addVertex_addingNullVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.addVertex(null));
    }

    @DisplayName("removeVertex - " +
            "When removing a vertex from adjacency list with one vertex - " +
            "Should remove the vertex from the graph")
    @Test
    void removeVertex_removingFromAdjacencyListWithOneVertex_removeVertexFromGraph() {
        directedGraph.addVertex(10);

        assertEquals(10, directedGraph.removeVertex(10));
        assertEquals("", directedGraph.toString());
    }

    @DisplayName("removeVertex - " +
            "When removing a vertex from adjacency list with more than one vertex - " +
            "Should remove the vertex from the graph")
    @Test
    void removeVertex_removingFromAdjacencyListWithMoreThanOneVertex_removeVertexFromGraph() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(20, 10);
        directedGraph.addEdge(30, 10);

        assertEquals(10, directedGraph.removeVertex(10));
        assertEquals("""
                20: []
                30: []""", directedGraph.toString());
    }

    @DisplayName("removeVertex - " +
            "When removing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void removeVertex_removingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.removeVertex(10));
    }

    @DisplayName("addEdge - " +
            "When adding an edge between two vertices - " +
            "Should add the edge to the graph")
    @Test
    void addEdge_isCalled_addEdgeToGraph() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addEdge(10, 20);

        assertEquals("""
                10: [20]
                20: []""", directedGraph.toString());
    }

    @DisplayName("addEdge - " +
            "When adding an edge with a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void addEdge_addingEdgeWithNonExistingVertex_throwIllegalArgumentException() {
        directedGraph.addVertex(10);

        assertThrows(IllegalArgumentException.class, () -> directedGraph.addEdge(10, 20));
        assertThrows(IllegalArgumentException.class, () -> directedGraph.addEdge(20, 10));
    }

    @DisplayName("removeEdge - " +
            "When removing an existing edge - " +
            "Should remove the edge from the graph")
    @Test
    void removeEdge_isCalled_removeExistingEdgeFromGraph() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 10);
        directedGraph.removeEdge(10, 20);

        assertEquals("""
                10: []
                20: [10]""", directedGraph.toString());
    }

    @DisplayName("removeEdge - " +
            "When removing a non-existing edge - " +
            "Should not modify the graph")
    @Test
    void removeEdge_removingNonExistingEdge_doNotModifyGraph() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);

        assertDoesNotThrow(() -> directedGraph.removeEdge(10, 20));
    }

    @DisplayName("findNeighbors - " +
            "When finding neighbors of a vertex - " +
            "Should return the correct list of neighbors")
    @Test
    void findNeighbors_vertexIsValid_returnCorrectListOfNeighbors() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(10, 30);

        assertEquals(List.of(20, 30), directedGraph.findNeighbors(10));
    }

    @DisplayName("findNeighbors - " +
            "When finding neighbors of a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void findNeighbors_findingNeighborsOfNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.findNeighbors(10));
    }

    @DisplayName("queryEdge - " +
            "When querying an existing edge - " +
            "Should return true")
    @Test
    void queryEdge_queriesExistingEdge_returnTrue() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addEdge(10, 20);

        assertTrue(directedGraph.queryEdge(10, 20));
    }

    @DisplayName("queryEdge - " +
            "When querying a non-existing edge - " +
            "Should return false")
    @Test
    void queryEdge_queriesNonExistingEdge_returnFalse() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);

        assertFalse(directedGraph.queryEdge(10, 20));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_singleVertexGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);

        assertEquals("10", directedGraph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_linearGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);

        assertEquals("10, 20, 30", directedGraph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_graphWithCycle_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);
        directedGraph.addEdge(30, 10);

        assertEquals("10, 20, 30", directedGraph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_disconnectedGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addVertex(40);

        assertEquals("10", directedGraph.traverseDepthFirst(10));
        assertEquals("20", directedGraph.traverseDepthFirst(20));
        assertEquals("30", directedGraph.traverseDepthFirst(30));
        assertEquals("40", directedGraph.traverseDepthFirst(40));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseDepthFirst_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.traverseDepthFirst(10));
    }


    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_singleVertexGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);

        assertEquals("10", directedGraph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_linearGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);

        assertEquals("10, 20, 30", directedGraph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_graphWithCycle_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);
        directedGraph.addEdge(30, 10);

        assertEquals("10, 20, 30", directedGraph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_disconnectedGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addVertex(40);

        assertEquals("10", directedGraph.traverseDepthFirst2(10));
        assertEquals("20", directedGraph.traverseDepthFirst2(20));
        assertEquals("30", directedGraph.traverseDepthFirst2(30));
        assertEquals("40", directedGraph.traverseDepthFirst2(40));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseDepthFirst2_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_singleVertexGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);

        assertEquals("10", directedGraph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_linearGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);

        assertEquals("10, 20, 30", directedGraph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_graphWithCycle_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addVertex(40);
        directedGraph.addVertex(50);
        directedGraph.addVertex(60);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(10, 30);
        directedGraph.addEdge(20, 60);
        directedGraph.addEdge(30, 40);
        directedGraph.addEdge(40, 50);
        directedGraph.addEdge(50, 10);

        assertEquals("10, 20, 30, 60, 40, 50", directedGraph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_disconnectedGraph_returnCorrectTraversalOrder() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addVertex(40);

        assertEquals("10", directedGraph.traverseBreadthFirst(10));
        assertEquals("20", directedGraph.traverseBreadthFirst(20));
        assertEquals("30", directedGraph.traverseBreadthFirst(30));
        assertEquals("40", directedGraph.traverseBreadthFirst(40));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseBreadthFirst_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> directedGraph.traverseBreadthFirst(10));
    }


    @DisplayName("topologicalSort - " +
            "When sorting a graph with no cycles - " +
            "Should return the correct topological order")
    @Test
    void topologicalSort_graphWithNoCycles_returnCorrectTopologicalOrder() {
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(1, 3);
        directedGraph.addEdge(2, 4);
        directedGraph.addEdge(3, 4);

        assertEquals(Arrays.asList(1, 3, 2, 4), directedGraph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting a graph with cycles - " +
            "Should throw IllegalStateException")
    @Test
    void topologicalSort_graphWithCycles_throwIllegalStateException() {
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 1);
        directedGraph.addEdge(4, 2);

        assertThrows(IllegalStateException.class, () -> directedGraph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting a graph with a single vertex - " +
            "Should return the single vertex as the result")
    @Test
    void topologicalSort_singleVertexGraph_returnSingleVertex() {
        directedGraph.addVertex(1);

        assertEquals(List.of(1), directedGraph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting an empty graph - " +
            "Should return an empty list")
    @Test
    void topologicalSort_emptyGraph_returnEmptyList() {
        assertEquals(List.of(), directedGraph.topologicalSort());
    }

    @DisplayName("hasCycle - " +
            "When graph has a cycle - " +
            "Should return true")
    @Test
    void hasCycle_graphWithCycle_returnTrue() {
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 1);

        assertTrue(directedGraph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph has no cycle - " +
            "Should return false")
    @Test
    void hasCycle_graphWithoutCycle_returnFalse() {
        directedGraph.addVertex(1);
        directedGraph.addVertex(2);
        directedGraph.addVertex(3);
        directedGraph.addVertex(4);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(1, 3);
        directedGraph.addEdge(2, 4);
        directedGraph.addEdge(3, 4);

        assertFalse(directedGraph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph is empty - " +
            "Should return false")
    @Test
    void hasCycle_emptyGraph_returnFalse() {
        assertFalse(directedGraph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph has a single vertex - " +
            "Should return false")
    @Test
    void hasCycle_singleVertexGraph_returnFalse() {
        directedGraph.addVertex(1);

        assertFalse(directedGraph.hasCycle());
    }

    @DisplayName("toString - " +
            "When the graph is empty - " +
            "Should return an empty string")
    @Test
    void toString_emptyGraph_returnEmptyString() {
        assertEquals("", directedGraph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has one vertex without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithOneVertexWithoutNeighbors_returnCorrectStringRepresentation() {
        directedGraph.addVertex(10);

        assertEquals("10: []", directedGraph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has multiple vertices without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithMultipleVerticesWithoutNeighbors_returnCorrectStringRepresentation() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);

        assertEquals("""
                10: []
                20: []
                30: []""", directedGraph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has vertices with neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithVerticesAndNeighbors_returnCorrectStringRepresentation() {
        directedGraph.addVertex(10);
        directedGraph.addVertex(20);
        directedGraph.addVertex(30);
        directedGraph.addEdge(10, 20);
        directedGraph.addEdge(20, 30);

        assertEquals("""
                10: [20]
                20: [30]
                30: []""", directedGraph.toString());
    }
}