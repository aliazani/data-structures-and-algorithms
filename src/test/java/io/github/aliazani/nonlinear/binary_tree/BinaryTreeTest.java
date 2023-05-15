package io.github.aliazani.nonlinear.binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("BinaryTree")
class BinaryTreeTest {
    BinaryTree<Integer> binaryTree;

    @BeforeEach
    void setUp() {
        binaryTree = new BinaryTree<>(20);
    }

    @DisplayName("insert - " +
            "When item is less than root - " +
            "Should insert that item as the left child")
    @Test
    void insert_itemIsLessThanRoot_insertAsLeftChild() {
        binaryTree.insert(10);

        assertEquals("(Value: 20, RightChild: null, LeftChild: (Value: 10, RightChild: null, LeftChild: null))",
                binaryTree.toString());
    }

    @DisplayName("insert - " +
            "When item is greater than root - " +
            "Should insert that item as the right child")
    @Test
    void insert_itemIsGreaterThanRoot_insertAsRightChild() {
        binaryTree.insert(30);

        assertEquals("(Value: 20, RightChild: (Value: 30, RightChild: null, LeftChild: null), LeftChild: null)",
                binaryTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting a duplicate item - " +
            "Should throw IllegalStateException")
    @Test
    void insert_duplicateItem_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> binaryTree.insert(20));
    }

    @DisplayName("insert - " +
            "When item is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void insert_itemIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> binaryTree.insert(null));
    }

    @DisplayName("contains - " +
            "When value is in the tree - " +
            "Should return true")
    @Test
    void contains_valueIsInTheTree_returnTrue() {
        binaryTree.insert(10);

       assertTrue(binaryTree.contains(20));
        assertTrue(binaryTree.contains(10));
    }

    @DisplayName("contains - " +
            "When value is not in the tree - " +
            "Should return false")
    @Test
    void contains_valueIsNotInTheTree_returnFalse() {
        assertFalse(binaryTree.contains(15));
    }

    @DisplayName("contains - " +
            "When value is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void contains_valueIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> binaryTree.contains(null));
    }

    @DisplayName("contains2 - " +
            "When value is in the tree - " +
            "Should return true")
    @Test
    void contains2_valueIsInTheTree_returnTrue() {
        binaryTree.insert(10);

        assertTrue(binaryTree.contains2(20));
        assertTrue(binaryTree.contains2(10));
    }

    @DisplayName("contains2 - " +
            "When value is not in the tree - " +
            "Should return false")
    @Test
    void contains2_valueIsNotInTheTree_returnFalse() {
        assertFalse(binaryTree.contains2(15));
    }

    @DisplayName("contains2 - " +
            "When value is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void contains2_valueIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> binaryTree.contains2(null));
    }

    @Test
    @DisplayName("traverseLevelOrder - " +
            "When tree is empty - " +
            "Should return an empty string")
    void traverseLevelOrder_emptyTree_returnsEmptyString() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>(null);

        assertEquals("", binaryTree.traverseLevelOrder());
    }

    @Test
    @DisplayName("traverseLevelOrder - " +
            "When tree has a single node - " +
            "Should return the value of the root node")
    void traverseLevelOrder_singleNodeTree_returnsRootValue() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>(20);

        assertEquals("20", binaryTree.traverseLevelOrder());
    }

    @Test
    @DisplayName("traverseLevelOrder - " +
            "When tree has multiple nodes - " +
            "Should return the level order traversal string")
    void traverseLevelOrder_multipleNodesTree_returnsLevelOrderTraversalString() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>(50);
        binaryTree.insert(20);
        binaryTree.insert(60);
        binaryTree.insert(5);
        binaryTree.insert(55);
        binaryTree.insert(15);
        binaryTree.insert(25);
        binaryTree.insert(70);

        assertEquals("50, 20, 60, 5, 25, 55, 70, 15", binaryTree.traverseLevelOrder());
    }

    @DisplayName("traversePreOrder - " +
            "When tree has one item - " +
            "Should return that Item")
    @Test
    void traversePreOrder_treeHasOneItem_returnThatItem() {
        assertEquals("20", binaryTree.traversePreOrder());
    }

    @DisplayName("traversePreOrder - " +
            "When tree has more  than one item - " +
            "Should return Items pre-order")
    @Test
    void traversePreOrder_treeMoreThanHasOneItem_returnItemsPreOrder() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(21);
        assertEquals("20, 10, 8, 12, 25, 21, 30", binaryTree.traversePreOrder());
    }

    @DisplayName("traversePostOrder - " +
            "When tree has one item - " +
            "Should return that Item")
    @Test
    void traversePostOrder_treeHasOneItem_returnThatItem() {
        assertEquals("20", binaryTree.traversePostOrder());
    }

    @DisplayName("traversePostOrder - " +
            "When tree has more  than one item - " +
            "Should return Items post-order")
    @Test
    void traversePostOrder_treeMoreThanHasOneItem_returnItemsPostOrder() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(21);
        assertEquals("8, 12, 10, 21, 30, 25, 20", binaryTree.traversePostOrder());
    }

    @DisplayName("traverseInOrder - " +
            "When tree has one item - " +
            "Should return that Item")
    @Test
    void traverseInOrder_treeHasOneItem_returnThatItem() {
        assertEquals("20", binaryTree.traverseInOrder());
    }

    @DisplayName("traverseIntOrder - " +
            "When tree has more  than one item - " +
            "Should return Items in-order")
    @Test
    void traverseInOrder_treeMoreThanHasOneItem_returnItemsInOrder() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(21);
        assertEquals("8, 10, 12, 20, 21, 25, 30", binaryTree.traverseInOrder());
    }

    @DisplayName("height - " +
            "When tree root is null - " +
            "Should return (-1)")
    @Test
    void height_rootIsNull_returnNegativeOne() {
        binaryTree = new BinaryTree<>(null);

        assertEquals(-1, binaryTree.height());
    }

    @DisplayName("height - " +
            "When tree has one item - " +
            "Should return 0")
    @Test
    void height_treeHasOneItem_returnZero() {
        assertEquals(0, binaryTree.height());
    }

    @DisplayName("height - " +
            "When tree has more than one item - " +
            "Should return height")
    @Test
    void height_treeHasMoreThanOneItem_returnHeight() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(4);
        binaryTree.insert(2);

        assertEquals(4, binaryTree.height());
    }
}