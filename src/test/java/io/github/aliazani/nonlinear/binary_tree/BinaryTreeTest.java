package io.github.aliazani.nonlinear.binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BinaryTreeTest {
    BinaryTree<Integer> bts;

    @BeforeEach
    void setUp() {
        bts = new BinaryTree<>(20);
    }

    @DisplayName("insert - " +
            "When item is less than root - " +
            "Should insert that item as the left child")
    @Test
    void insert_itemIsLessThanRoot_insertAsLeftChild() {
        bts.insert(10);

        assertEquals("(Value: 20, RightChild: null, LeftChild: (Value: 10, RightChild: null, LeftChild: null))",
                bts.toString());
    }

    @DisplayName("insert - " +
            "When item is greater than root - " +
            "Should insert that item as the right child")
    @Test
    void insert_itemIsGreaterThanRoot_insertAsRightChild() {
        bts.insert(30);

        assertEquals("(Value: 20, RightChild: (Value: 30, RightChild: null, LeftChild: null), LeftChild: null)",
                bts.toString());
    }

    @DisplayName("insert - " +
            "When inserting a duplicate item - " +
            "Should throw IllegalStateException")
    @Test
    void insert_duplicateItem_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> bts.insert(20));
    }

    @DisplayName("insert - " +
            "When item is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void insert_itemIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> bts.insert(null));
    }

    @DisplayName("contains - " +
            "When value is in the tree - " +
            "Should return true")
    @Test
    void contains_valueIsInTheTree_returnTrue() {
        bts.insert(10);

       assertTrue(bts.contains(20));
        assertTrue(bts.contains(10));
    }

    @DisplayName("contains - " +
            "When value is not in the tree - " +
            "Should return false")
    @Test
    void contains_valueIsNotInTheTree_returnFalse() {
        assertFalse(bts.contains(15));
    }

    @DisplayName("contains - " +
            "When value is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void contains_valueIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> bts.contains(null));
    }

}