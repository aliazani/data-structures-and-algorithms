package io.github.aliazani.nonlinear.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @DisplayName("traverseDepthFirst - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_singleVertexGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);

        assertEquals("10", graph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_linearGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);

        assertEquals("10, 20, 30", graph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_graphWithCycle_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);
        graph.addEdge(30, 10);

        assertEquals("10, 20, 30", graph.traverseDepthFirst(10));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst_disconnectedGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addVertex(40);

        assertEquals("10", graph.traverseDepthFirst(10));
        assertEquals("20", graph.traverseDepthFirst(20));
        assertEquals("30", graph.traverseDepthFirst(30));
        assertEquals("40", graph.traverseDepthFirst(40));
    }

    @DisplayName("traverseDepthFirst - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseDepthFirst_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.traverseDepthFirst(10));
    }


    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_singleVertexGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);

        assertEquals("10", graph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_linearGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);

        assertEquals("10, 20, 30", graph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_graphWithCycle_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);
        graph.addEdge(30, 10);

        assertEquals("10, 20, 30", graph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseDepthFirst2_disconnectedGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addVertex(40);

        assertEquals("10", graph.traverseDepthFirst2(10));
        assertEquals("20", graph.traverseDepthFirst2(20));
        assertEquals("30", graph.traverseDepthFirst2(30));
        assertEquals("40", graph.traverseDepthFirst2(40));
    }

    @DisplayName("traverseDepthFirst2 - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseDepthFirst2_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.traverseDepthFirst2(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a graph with a single vertex - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_singleVertexGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);

        assertEquals("10", graph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a linear graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_linearGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);

        assertEquals("10, 20, 30", graph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a graph with a cycle - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_graphWithCycle_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addVertex(40);
        graph.addVertex(50);
        graph.addVertex(60);
        graph.addEdge(10, 20);
        graph.addEdge(10, 30);
        graph.addEdge(20, 60);
        graph.addEdge(30, 40);
        graph.addEdge(40, 50);
        graph.addEdge(50, 10);

        assertEquals("10, 20, 30, 60, 40, 50", graph.traverseBreadthFirst(10));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a disconnected graph - " +
            "Should return the correct traversal order")
    @Test
    void traverseBreadthFirst_disconnectedGraph_returnCorrectTraversalOrder() {
        graph.addVertex(10);
        graph.addVertex(20);
        graph.addVertex(30);
        graph.addVertex(40);

        assertEquals("10", graph.traverseBreadthFirst(10));
        assertEquals("20", graph.traverseBreadthFirst(20));
        assertEquals("30", graph.traverseBreadthFirst(30));
        assertEquals("40", graph.traverseBreadthFirst(40));
    }

    @DisplayName("traverseBreadthFirst - " +
            "When traversing a non-existing vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void traverseBreadthFirst_traversingNonExistingVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.traverseBreadthFirst(10));
    }


    @DisplayName("topologicalSort - " +
            "When sorting a graph with no cycles - " +
            "Should return the correct topological order")
    @Test
    void topologicalSort_graphWithNoCycles_returnCorrectTopologicalOrder() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        assertEquals(Arrays.asList(1, 3, 2, 4), graph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting a graph with cycles - " +
            "Should throw IllegalStateException")
    @Test
    void topologicalSort_graphWithCycles_throwIllegalStateException() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 2);

        assertThrows(IllegalStateException.class, () -> graph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting a graph with a single vertex - " +
            "Should return the single vertex as the result")
    @Test
    void topologicalSort_singleVertexGraph_returnSingleVertex() {
        graph.addVertex(1);

        assertEquals(List.of(1), graph.topologicalSort());
    }

    @DisplayName("topologicalSort - " +
            "When sorting an empty graph - " +
            "Should return an empty list")
    @Test
    void topologicalSort_emptyGraph_returnEmptyList() {
        assertEquals(List.of(), graph.topologicalSort());
    }

    @DisplayName("hasCycle - " +
            "When graph has a cycle - " +
            "Should return true")
    @Test
    void hasCycle_graphWithCycle_returnTrue() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertTrue(graph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph has no cycle - " +
            "Should return false")
    @Test
    void hasCycle_graphWithoutCycle_returnFalse() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        assertFalse(graph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph is empty - " +
            "Should return false")
    @Test
    void hasCycle_emptyGraph_returnFalse() {
        assertFalse(graph.hasCycle());
    }

    @DisplayName("hasCycle - " +
            "When graph has a single vertex - " +
            "Should return false")
    @Test
    void hasCycle_singleVertexGraph_returnFalse() {
        graph.addVertex(1);

        assertFalse(graph.hasCycle());
    }

    @DisplayName("toString - " +
            "When the graph is empty - " +
            "Should return an empty string")
    @Test
    void toString_emptyGraph_returnEmptyString() {
        assertEquals("", graph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has one vertex without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithOneVertexWithoutNeighbors_returnCorrectStringRepresentation() {
        graph.addVertex(10);

        assertEquals("10: []", graph.toString());
    }

    @DisplayName("toString - " +
            "When the graph has multiple vertices without neighbors - " +
            "Should return the correct string representation")
    @Test
    void toString_graphWithMultipleVerticesWithoutNeighbors_returnCorrectStringRepresentation() {
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
    void toString_graphWithVerticesAndNeighbors_returnCorrectStringRepresentation() {
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