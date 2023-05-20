package io.github.aliazani.nonlinear.avl_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AVLTree")
class AVLTreeTest {
    private AVLTree<Integer> avlTree;

    @BeforeEach
    void setUp() {
        avlTree = new AVLTree<>(10);
    }

    @DisplayName("insert - " +
            "When inserting the minimum value - " +
            "Should insert correctly")
    @Test
    void insert_minimumValue_insertCorrectly() {
        avlTree.insert(5);

        assertEquals("(value: 10, " +
                "[rightChild: null], " +
                "[leftChild: (value: 5, [rightChild: null], [leftChild: null])])", avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting the maximum value - " +
            "Should insert correctly")
    @Test
    void insert_maximumValue_insertCorrectly() {
        avlTree.insert(20);

        assertEquals("(value: 10, " +
                        "[rightChild: (value: 20, [rightChild: null], [leftChild: null])], " +
                        "[leftChild: null])"
                , avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting multiple values and imbalance is in right child, right subtree (right skewed) - " +
            "Should insert correctly and rotate left")
    @Test
    void insert_rightSkewed_insertCorrectlyAndRotateLeft() {
        avlTree.insert(5);
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(35);
        avlTree.insert(40);

        assertEquals("(value: 30, " +
                        "[rightChild: (value: 35, " +
                        "[rightChild: (value: 40, [rightChild: null], [leftChild: null])], [leftChild: null])], " +
                        "[leftChild: (value: 10, " +
                        "[rightChild: (value: 20, [rightChild: null], [leftChild: null])], " +
                        "[leftChild: (value: 5, [rightChild: null], [leftChild: null])])])",
                avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting multiple values and imbalance is in the left child, left subtree (left skewed) - " +
            "Should insert correctly and rotate right")
    @Test
    void insert_leftSkewed_insertCorrectlyAndRotateRight() {
        avlTree.insert(20);
        avlTree.insert(8);
        avlTree.insert(9);
        avlTree.insert(7);
        avlTree.insert(6);

        assertEquals("(value: 8, " +
                        "[rightChild: (value: 10, " +
                        "[rightChild: (value: 20, [rightChild: null], [leftChild: null])], " +
                        "[leftChild: (value: 9, [rightChild: null], [leftChild: null])])], " +
                        "[leftChild: (value: 7, [rightChild: null], " +
                        "[leftChild: (value: 6, [rightChild: null], [leftChild: null])])])",
                avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting multiple values and imbalance is in the left child, right subtree  - " +
            "Should insert correctly and rotate left following by rotate right")
    @Test
    void insert_leftChildRightSubtree_insertCorrectlyAndRotateLeftFollowingByRightRotation() {
        avlTree = new AVLTree<>(20);
        avlTree.insert(30);
        avlTree.insert(8);
        avlTree.insert(7);
        avlTree.insert(9);
        avlTree.insert(10);

        assertEquals("(value: 9, " +
                        "[rightChild: (value: 20, " +
                        "[rightChild: (value: 30, [rightChild: null], [leftChild: null])], " +
                        "[leftChild: (value: 10, [rightChild: null], [leftChild: null])])], " +
                        "[leftChild: (value: 8, [rightChild: null], " +
                        "[leftChild: (value: 7, [rightChild: null], [leftChild: null])])])",
                avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting multiple values and imbalance is in the right child, left subtree  - " +
            "Should insert correctly and rotate left following by rotate right")
    @Test
    void insert_rightChildLeftSubtree_insertCorrectlyAndRotateRightFollowingByLeftRotation() {
        avlTree = new AVLTree<>(20);
        avlTree.insert(10);
        avlTree.insert(30);
        avlTree.insert(25);
        avlTree.insert(35);
        avlTree.insert(23);

        assertEquals("(value: 25, " +
                        "[rightChild: (value: 30, " +
                        "[rightChild: (value: 35, [rightChild: null], [leftChild: null])], [leftChild: null])], " +
                        "[leftChild: (value: 20, " +
                        "[rightChild: (value: 23, [rightChild: null], [leftChild: null])], " +
                        "[leftChild: (value: 10, [rightChild: null], [leftChild: null])])])",
                avlTree.toString());
    }

    @DisplayName("insert - " +
            "When inserting duplicate value - " +
            "Should throw IllegalStateException")
    @Test
    void insert_duplicateValue_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> avlTree.insert(10));
    }

    @DisplayName("insert - " +
            "When inserting null value - " +
            "Should throw IllegalArgumentException")
    @Test
    void insert_nullValue_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> avlTree.insert(null));
    }

    @DisplayName("representTree - " +
            "When tree is empty - " +
            "Should return empty string")
    @Test
    void representTree_emptyTree_shouldReturnEmptyString() {
        avlTree = new AVLTree<>(null);

        assertEquals("", avlTree.representTree());
    }

    @DisplayName("representTree - " +
            "When tree has one node - " +
            "Should return single node string")
    @Test
    void representTree_singleNodeTree_shouldReturnSingleNodeString() {
        assertEquals("\n10", avlTree.representTree());
    }

    @DisplayName("representTree - " +
            "When tree has multiple nodes - " +
            "Should return representative string")
    @Test
    void representTree_multipleNodesTree_shouldReturnRepresentativeString() {
        avlTree = new AVLTree<>(20);
        avlTree.insert(10);
        avlTree.insert(30);
        avlTree.insert(25);
        avlTree.insert(35);
        avlTree.insert(23);

        assertEquals("""
                          
                          35
                     30
                25
                          23
                     20
                          10""", avlTree.representTree());
    }
}