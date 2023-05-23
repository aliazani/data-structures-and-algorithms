package io.github.aliazani.nonlinear.trie.using_arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrieNodeUsingArrayTest {
    private TrieNodeUsingArray<Integer> trieNode;

    @BeforeEach
    void setUp() {
        trieNode = new TrieNodeUsingArray<>(null, 26);
    }

    @Test
    @DisplayName("addChild - " +
            "When adding a child to an empty node - " +
            "Should add the child")
    void addChild_addChildToEmptyNode_addsChild() {
        trieNode.addChild(5);

        assertTrue(trieNode.hasChild(5));
    }

    @Test
    @DisplayName("addChild - When adding a child with the same value - Should not add duplicate child")
    void addChild_addChildWithSameValue_doesNotAddDuplicateChild() {
        trieNode.addChild(5);
        trieNode.addChild(5);

        assertEquals("(value=null, " +
                "children=[null, null, null, null, null, " +
                "(value=5, children=[null, null, null, null, null, null, " +
                "null, null, null, null, null, null, null, null, null, null, " +
                "null, null, null, null, null, null, null, null, null, null])," +
                " null, null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null])", trieNode.toString());
    }

    @Test
    @DisplayName("removeChild - " +
            "When removing an existing child - " +
            "Should remove the child")
    void removeChild_removeExistingChild_removesChild() {
        trieNode.addChild(5);
        trieNode.removeChild(5);

        assertFalse(trieNode.hasChild(5));
    }

    @Test
    @DisplayName("removeChild - " +
            "When removing a non-existing child - " +
            "Should not modify the node")
    void removeChild_removeNonExistingChild_doesNotModifyNode() {
        trieNode.addChild(5);
        trieNode.removeChild(10);

        assertTrue(trieNode.hasChild(5));
    }

    @Test
    @DisplayName("getChild - " +
            "When getting an existing child - " +
            "Should return the child")
    void getChild_getExistingChild_returnsChild() {
        trieNode.addChild(5);
        trieNode.addChild(10);

        assertNotNull(trieNode.getChild(5));
        assertEquals("(value=5," +
                " children=[null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null])", trieNode.getChild(5).toString());
    }

    @Test
    @DisplayName("getChild - " +
            "When getting a non-existing child - " +
            "Should return null")
    void getChild_getNonExistingChild_returnsNull() {
        trieNode.addChild(5);
        trieNode.addChild(10);

        assertNull(trieNode.getChild(12));
    }

    @Test
    @DisplayName("hasChild - " +
            "When checking an existing child - " +
            "Should return true")
    void hasChild_checkExistingChild_returnsTrue() {
        trieNode.addChild(5);

        assertTrue(trieNode.hasChild(5));
    }

    @Test
    @DisplayName("hasChild - " +
            "When checking a non-existing child - " +
            "Should return false")
    void hasChild_checkNonExistingChild_returnsFalse() {
        assertFalse(trieNode.hasChild(10));
    }

    @Test
    @DisplayName("hasChildren - " +
            "When the node has children - " +
            "Should return true")
    void hasChildren_nodeHasChildren_returnsTrue() {
        trieNode.addChild(5);

        assertTrue(trieNode.hasChildren());
    }

    @Test
    @DisplayName("hasChildren - " +
            "When the node has no children - " +
            "Should return false")
    void hasChildren_nodeHasNoChildren_returnsFalse() {
        assertFalse(trieNode.hasChildren());
    }

    @Test
    @DisplayName("toString - " +
            "When the node has no children - " +
            "Should return string representation without children")
    void toString_nodeHasNoChildren_returnsStringWithoutChildren() {
        trieNode.setValue(5);

        String expected = "(value=5, children=[null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null, null, null, " +
                "null, null, null, null, null, null, null])";

        assertEquals(expected, trieNode.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the node has children - " +
            "Should return string representation with children")
    void toString_nodeHasChildren_returnsStringWithChildren() {
        trieNode.addChild(5);
        trieNode.addChild(10);
        trieNode.addChild(15);

        String expected = "(value=null," +
                " children=[null, null, null, null, null, " +
                "(value=5, children=[null, null, null, null, null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null, null, null]), null, null, null, null, " +
                "(value=10, children=[null, null, null, null, null, null, null, null, null, null, null, null, null, " +
                "null, null, null, null, null, null, null, null, null, null, null, null, null]), null, null, null, null" +
                ", (value=15, children=[null, null, null, null, null, null, null, null, null, null, null, null, null," +
                " null, null, null, null, null, null, null, null, null, null, null, null, null]), null, null, null, " +
                "null, null, null, null, null, null, null])";

        assertEquals(expected, trieNode.toString());
    }
}