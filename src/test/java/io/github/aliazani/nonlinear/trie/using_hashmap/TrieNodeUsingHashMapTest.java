package io.github.aliazani.nonlinear.trie.using_hashmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieNodeUsingHashMapTest {
    private TrieNodeUsingHashMap<Integer> trieNode;

    @BeforeEach
    void setUp() {
        trieNode = new TrieNodeUsingHashMap<>(null);
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

        assertEquals("(value=null, children={5=(value=5, children={})})", trieNode.toString());
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
        assertEquals("(value=5, children={})" , trieNode.getChild(5).toString());
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

}