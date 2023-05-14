package io.github.aliazani.nonlinear.binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("BinaryTree")
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

    @DisplayName("traversePreOrder - " +
            "When tree has one item - " +
            "Should print that Item")
    @Test
    void traversePreOrder_treeHasOneItem_printThatItem() {
        assertEquals("20", bts.traversePreOrder());
    }

    @DisplayName("traversePreOrder - " +
            "When tree has more  than one item - " +
            "Should print Items pre-order")
    @Test
    void traversePreOrder_treeMoreThanHasOneItem_printItemsPreOrder() {
        bts.insert(10);
        bts.insert(12);
        bts.insert(8);
        bts.insert(25);
        bts.insert(30);
        bts.insert(21);
        assertEquals("20, 10, 8, 12, 25, 21, 30", bts.traversePreOrder());
    }

    @DisplayName("traversePostOrder - " +
            "When tree has one item - " +
            "Should print that Item")
    @Test
    void traversePostOrder_treeHasOneItem_printThatItem() {
        assertEquals("20", bts.traversePostOrder());
    }

    @DisplayName("traversePostOrder - " +
            "When tree has more  than one item - " +
            "Should print Items post-order")
    @Test
    void traversePostOrder_treeMoreThanHasOneItem_printItemsPostOrder() {
        bts.insert(10);
        bts.insert(12);
        bts.insert(8);
        bts.insert(25);
        bts.insert(30);
        bts.insert(21);
        assertEquals("8, 12, 10, 21, 30, 25, 20", bts.traversePostOrder());
    }

    @DisplayName("traverseInOrder - " +
            "When tree has one item - " +
            "Should print that Item")
    @Test
    void traverseInOrder_treeHasOneItem_printThatItem() {
        assertEquals("20", bts.traverseInOrder());
    }

    @DisplayName("traverseIntOrder - " +
            "When tree has more  than one item - " +
            "Should print Items in-order")
    @Test
    void traverseInOrder_treeMoreThanHasOneItem_printItemsInOrder() {
        bts.insert(10);
        bts.insert(12);
        bts.insert(8);
        bts.insert(25);
        bts.insert(30);
        bts.insert(21);
        assertEquals("8, 10, 12, 20, 21, 25, 30", bts.traverseInOrder());
    }
}