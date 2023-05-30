package io.github.aliazani.nonlinear.graph.undirected.weighted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UndirectedWeightedGraph")
class UndirectedWeightedGraphTest {
    private UndirectedWeightedGraph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedWeightedGraph<>();
    }

    @DisplayName("addVertex - " +
            "When adding a vertex - " +
            "Should add the vertex to the graph")
    @Test
    void addVertex_emptyGraph_addVertexToGraph() {
        graph.addVertex(1);

        assertEquals("1: []", graph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding multiple vertices - " +
            "Should add all vertices to the graph")
    @Test
    void addVertex_nonEmptyGraph_addMultipleVerticesToGraph() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertEquals("""
                1: []
                2: []
                3: []""", graph.toString());
    }

    @DisplayName("addVertex - " +
            "When adding a null vertex - " +
            "Should throw IllegalArgumentException")
    @Test
    void addVertex_addNullVertex_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(null));
    }

    @DisplayName("addEdge - " +
            "When adding an edge between existing vertices - " +
            "Should add the edge to the adjacency list")
    @Test
    void addEdge_existingVertices_addEdgeToAdjacencyList() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 2);

        assertEquals("""
                1: [(2, 5), (3, 2)]
                2: [(1, 5)]
                3: [(1, 2)]""", graph.toString());
    }

    @DisplayName("addEdge - " +
            "When adding an edge between non-existing vertices - " +
            "Should throw IllegalArgumentException")
    @Test
    void addEdge_addEdgeWithNonExistingVertices_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2, 5));
    }

    @Test
    @DisplayName("getShortestPath - " +
            "When from and to nodes exist - " +
            "Should return the shortest path")
    void getShortestPath_fromAndToNodesExist_returnShortestPath() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 2);

        assertEquals("[1, 3, 4]", graph.getShortestPath(1, 4).toString());
    }

    @Test
    @DisplayName("getShortestPath - " +
            "When from node does not exist - " +
            "Should throw IllegalArgumentException")
    void getShortestPath_fromNodeDoesNotExist_throwsIllegalArgumentException() {
        graph.addVertex(2);
        assertThrows(IllegalArgumentException.class, () -> graph.getShortestPath(3, 2));
    }

    @Test
    @DisplayName("getShortestPath - " +
            "When to node does not exist - " +
            "Should throw IllegalArgumentException")
    void getShortestPath_toNodeDoesNotExist_throwIllegalArgumentException() {
        graph.addVertex(1);

        assertThrows(IllegalArgumentException.class, () -> graph.getShortestPath(1, 3));
    }

    @Test
    @DisplayName("getShortestPath - " +
            "When from and to nodes are the same - " +
            "Should return a path with a single node")
    void getShortestPath_fromAndToNodesAreSame_returnPathWithSingleNode() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 2);

        assertEquals("[]", graph.getShortestPath(2, 2).toString());
    }

    @Test
    @DisplayName("getShortestPath - " +
            "When there is no path between from and to nodes - " +
            "Should return an empty path")
    void getShortestPath_noPathBetweenFromAndToNodes_returnsEmptyPath() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 5);

        assertEquals("[]", graph.getShortestPath(1, 3).toString());
    }

    @Test
    @DisplayName("hasCycle - " +
            "When graph has a cycle - " +
            "Should return true")
    void hasCycle_graphWithCycle_returnTrue() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 10);
        graph.addEdge(1, 3, 15);

        assertTrue(graph.hasCycle());
    }

    @Test
    @DisplayName("hasCycle - " +
            "When graph has no cycle - " +
            "Should return false")
    void hasCycle_graphWithoutCycle_returnFalse() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 4, 15);

        assertFalse(graph.hasCycle());
    }

    @Test
    @DisplayName("hasCycle - " +
            "When graph is empty - " +
            "Should return false")
    void hasCycle_emptyGraph_returnFalse() {
        assertFalse(graph.hasCycle());
    }

    @Test
    @DisplayName("hasCycle - " +
            "When graph has a single vertex - " +
            "Should return false")
    void hasCycle_singleVertexGraph_returnFalse() {
        graph.addVertex(1);

        assertFalse(graph.hasCycle());
    }
}