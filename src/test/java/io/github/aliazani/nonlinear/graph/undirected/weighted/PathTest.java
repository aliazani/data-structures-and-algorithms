package io.github.aliazani.nonlinear.graph.undirected.weighted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Path")
class PathTest {
    private Path<Integer> path;

    @BeforeEach
    void setUp() {
        path = new Path<>();
    }

    @DisplayName("addNode - " +
            "When adding nodes to the path - " +
            "Should store the nodes correctly")
    @Test
    void addNode_addingNodes_storeNodesCorrectly() {
        path.addNode(1);
        path.addNode(2);
        path.addNode(3);

        assertEquals("[1, 2, 3]", path.toString());
    }

    @DisplayName("addNode - " +
            "When adding a null node - " +
            "Should throw an IllegalArgumentException")
    @Test
    void addNode_addingNullNode_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> path.addNode(null));
    }

    @DisplayName("toString - " +
            "When converting the path to a string - " +
            "Should return the correct string representation")
    @Test
    void toString_convertToString_returnCorrectStringRepresentation() {
        path.addNode(1);
        path.addNode(2);
        path.addNode(3);

        assertEquals("[1, 2, 3]", path.toString());
    }

    @DisplayName("toString - " +
            "When converting an empty path to a string - " +
            "Should return an empty string representation")
    @Test
    void toString_convertEmptyPathToString_returnEmptyStringRepresentation() {
        assertEquals("[]", path.toString());
    }
}