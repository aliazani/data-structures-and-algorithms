package io.github.aliazani.nonlinear.binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @DisplayName("remove - " +
            "When root is null - " +
            "Should IllegalStateException")
    @Test
    void remove_rootIsNull_throwIllegalState() {
        binaryTree = new BinaryTree<>(null);
        assertThrows(IllegalStateException.class, () -> binaryTree.remove(10));
    }

    @DisplayName("remove - " +
            "When removing leaf node - " +
            "Should remove leaf node")
    @Test
    void remove_leafNode_removeLeafNode() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(12);

        binaryTree.remove(5);

        assertEquals("(Value: 20," +
                        " RightChild: null," +
                        " LeftChild: (Value: 10, RightChild: (Value: 12, RightChild: null, LeftChild: null), LeftChild: null))"
                , binaryTree.toString());
    }

    @DisplayName("remove - " +
            "When removing node with one child - " +
            "Should hoist child node")
    @Test
    void remove_nodeWithOneChild_hoistChildNode() {
        binaryTree.insert(10);
        binaryTree.insert(12);

        binaryTree.remove(10);

        assertEquals("(Value: 20," +
                        " RightChild: null," +
                        " LeftChild: (Value: 12, RightChild: null, LeftChild: null))"
                , binaryTree.toString());
    }

    @DisplayName("remove - " +
            "When removing node with more than one child - " +
            "Should replace it with smallest value in the right subtree")
    @Test
    void remove_nodeWithMoreThanOneChild_replaceItWithSmallestValueInRightSubtree() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(5);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(22);
        binaryTree.insert(28);
        binaryTree.insert(26);

        binaryTree.remove(25);


        assertEquals("(Value: 20, " +
                        "RightChild: (Value: 30, RightChild: null, " +
                        "LeftChild: (Value: 26, RightChild: (Value: 28, RightChild: null, LeftChild: null), " +
                        "LeftChild: (Value: 22, RightChild: null, LeftChild: null))), " +
                        "LeftChild: (Value: 10, " +
                        "RightChild: (Value: 12, RightChild: null, LeftChild: null), " +
                        "LeftChild: (Value: 5, RightChild: null, LeftChild: null)))"
                , binaryTree.toString());
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

    @DisplayName("min - " +
            "When tree is empty - " +
            "Should throw IllegalStateException")
    @Test
    void min_treeIsEmpty_throwIllegalState() {
        binaryTree = new BinaryTree<>(null);

        assertThrows(IllegalStateException.class, () -> binaryTree.min());
    }

    @DisplayName("min - " +
            "When tree has one item - " +
            "Should return that item")
    @Test
    void min_treeHasOneItem_returnThatItem() {
        assertEquals(20, binaryTree.min());
    }

    @DisplayName("min - " +
            "When tree has more than one item - " +
            "Should return minimum item")
    @Test
    void min_treeHasMoreThanOneItem_returnMinItem() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(21);
        binaryTree.insert(0);
        binaryTree.insert(-1);

        assertEquals(-1, binaryTree.min());
    }

    @DisplayName("max - " +
            "When tree is empty - " +
            "Should throw IllegalStateException")
    @Test
    void max_treeIsEmpty_throwIllegalState() {
        binaryTree = new BinaryTree<>(null);

        assertThrows(IllegalStateException.class, () -> binaryTree.max());
    }

    @DisplayName("max - " +
            "When tree has one item - " +
            "Should return that item")
    @Test
    void max_treeHasOneItem_returnThatItem() {
        assertEquals(20, binaryTree.max());
    }

    @DisplayName("max - " +
            "When tree has more than one item - " +
            "Should return maximum item")
    @Test
    void max_treeHasMoreThanOneItem_returnMaxItem() {
        binaryTree.insert(10);
        binaryTree.insert(12);
        binaryTree.insert(8);
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(21);
        binaryTree.insert(0);
        binaryTree.insert(-1);

        assertEquals(30, binaryTree.max());
    }

    @DisplayName("equalsTree - " +
            "When other tree is null - " +
            "Should throw IllegalArgumentException")
    @Test
    void equalsTree_otherTreeIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> binaryTree.equalsTree(null));
    }

    @DisplayName("equalsTree - " +
            "When trees are empty - " +
            "Should return true")
    @Test
    void equalsTree_treesAreEmpty_returnTrue() {
        binaryTree = new BinaryTree<>(null);
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(null);

        assertTrue(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When one of the trees is empty - " +
            "Should return false")
    @Test
    void equalsTree_oneOfTheTreesIsEmpty_returnFalse() {
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(null);

        assertFalse(binaryTree.equalsTree(binaryTree2));

        binaryTree = new BinaryTree<>(null);
        binaryTree2 = new BinaryTree<>(20);
        assertFalse(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When two trees with one identical item - " +
            "Should return true")
    @Test
    void equalsTree_twoTreesWithOneIdenticalItem_returnTrue() {
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(20);

        assertTrue(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When two trees with one non-identical item - " +
            "Should return false")
    @Test
    void equalsTree_twoTreesWithOneNonIdenticalItem_returnFalse() {
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(30);

        assertFalse(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When comparing two identical trees - " +
            "Should return true")
    @Test
    void equalsTree_twoIdenticalTrees_returnTrue() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(20);
        binaryTree2.insert(10);
        binaryTree2.insert(8);
        binaryTree2.insert(12);
        binaryTree2.insert(30);
        binaryTree2.insert(28);
        binaryTree2.insert(32);

        assertTrue(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When comparing two trees with different structures - " +
            "Should return false")
    @Test
    void equalsTree_twoTreesWithDifferentStructures_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(20);
        binaryTree2.insert(8);
        binaryTree2.insert(10);
        binaryTree2.insert(12);
        binaryTree2.insert(30);
        binaryTree2.insert(28);
        binaryTree2.insert(32);

        assertFalse(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("equalsTree - " +
            "When comparing two trees with different values - " +
            "Should return false")
    @Test
    void equalsTree_twoTreesWithDifferentValues_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(20);
        binaryTree2.insert(9);
        binaryTree2.insert(8);
        binaryTree2.insert(12);
        binaryTree2.insert(30);
        binaryTree2.insert(28);
        binaryTree2.insert(32);

        assertFalse(binaryTree.equalsTree(binaryTree2));
    }

    @DisplayName("isBinarySearchTree - " +
            "When root is null - " +
            "Should return true")
    @Test
    void isBinarySearchTree_rootIsNull_returnTrue() {
        binaryTree = new BinaryTree<>(null);

        assertTrue(binaryTree.isBinarySearchTree());
    }

    @DisplayName("isBinarySearchTree - " +
            "When tree has one item - " +
            "Should return true")
    @Test
    void isBinarySearchTree_treeHasOneItem_returnTrue() {
        assertTrue(binaryTree.isBinarySearchTree());
    }

    @DisplayName("isBinarySearchTree - " +
            "When tree with valid order of items - " +
            "Should return true")
    @Test
    void isBinarySearchTree_validOrder_returnTrue() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);

        assertTrue(binaryTree.isBinarySearchTree());
    }

    @DisplayName("isBinarySearchTree - " +
            "When tree items in ascending order - " +
            "Should return true")
    @Test
    void isBinarySearchTree_itemsWithAscendingOrder_returnTrue() {
        binaryTree.insert(30);
        binaryTree.insert(35);
        binaryTree.insert(40);
        binaryTree.insert(45);
        binaryTree.insert(50);

        assertTrue(binaryTree.isBinarySearchTree());
    }

    @DisplayName("isBinarySearchTree - " +
            "When tree with invalid order of items - " +
            "Should return false")
    @Test
    void isBinarySearchTree_invalidOrder_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);
        binaryTree.swapRootChildren();

        assertFalse(binaryTree.isBinarySearchTree());
    }

    @DisplayName("nodesAtDistance - " +
            "When tree empty - " +
            "Should return empty string")
    @Test
    void nodesAtDistance_treeIsEmpty_returnEmptyString() {
        binaryTree = new BinaryTree<>(null);

        assertEquals("", binaryTree.nodesAtDistance(0));
    }

    @DisplayName("nodesAtDistance - " +
            "When tree has one item - " +
            "Should return that item at distance 0")
    @Test
    void nodesAtDistance_treeHasOneItem_returnThatItemAtDistanceZero() {
        assertEquals("20", binaryTree.nodesAtDistance(0));
        assertEquals("", binaryTree.nodesAtDistance(1));
    }

    @DisplayName("nodesAtDistance - " +
            "When tree has more than one item - " +
            "Should return items at the given distance")
    @Test
    void nodesAtDistance_treeHasMoreThanOneItem_returnItemsAtTheGivenDistance() {
        binaryTree.insert(10);
        binaryTree.insert(8);
        binaryTree.insert(12);
        binaryTree.insert(30);
        binaryTree.insert(28);
        binaryTree.insert(32);

        assertEquals("20", binaryTree.nodesAtDistance(0));
        assertEquals("10, 30", binaryTree.nodesAtDistance(1));
        assertEquals("8, 12, 28, 32", binaryTree.nodesAtDistance(2));
    }

    @DisplayName("nodesAtDistance - " +
            "When distance is invalid - " +
            "Should throw IllegalArgumentException")
    @Test
    void nodesAtDistance_distanceIsInvalid_trowIllegalArgument() {
        binaryTree.insert(10);
        binaryTree.insert(30);

        assertThrows(IllegalArgumentException.class, () -> binaryTree.nodesAtDistance(-1));
    }

    @DisplayName("isBalanced - " +
            "When tree is empty - " +
            "Should return true")
    @Test
    void isBalanced_treeIsEmpty_returnTrue() {
        binaryTree = new BinaryTree<>(null);

        assertTrue(binaryTree.isBalanced());
    }

    @DisplayName("isBalanced - " +
            "When tree has a single node - " +
            "Should return true")
    @Test
    void isBalanced_treeHasSingleNode_returnTrue() {
        assertTrue(binaryTree.isBalanced());
    }

    @DisplayName("isBalanced - " +
            "When tree is balanced - " +
            "Should return true")
    @Test
    void isBalanced_treeIsBalanced_returnTrue() {
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(35);

        assertTrue(binaryTree.isBalanced());
    }

    @DisplayName("isBalanced - When tree is not balanced - Should return false")
    @Test
    void isBalanced_treeIsNotBalanced_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);
        binaryTree.insert(18);

        assertFalse(binaryTree.isBalanced());
    }

    @DisplayName("isBalanced - " +
            "When tree has only left subtree - " +
            "Should return false")
    @Test
    void isBalanced_treeHasOnlyLeftSubtree_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(2);

        assertFalse(binaryTree.isBalanced());
    }

    @DisplayName("isBalanced - " +
            "When tree has only right subtree - " +
            "Should return false")
    @Test
    void isBalanced_treeHasOnlyRightSubtree_returnFalse() {
        binaryTree.insert(25);
        binaryTree.insert(30);
        binaryTree.insert(35);
        binaryTree.insert(40);

        assertFalse(binaryTree.isBalanced());
    }

    @DisplayName("isPerfect - " +
            "When tree is empty - " +
            "Should return true")
    @Test
    void isPerfect_treeIsEmpty_returnTrue() {
        binaryTree = new BinaryTree<>(null);

        assertTrue(binaryTree.isPerfect());
    }

    @DisplayName("isPerfect - " +
            "When tree has a single node - " +
            "Should return true")
    @Test
    void isPerfect_treeHasSingleNode_returnTrue() {
        assertTrue(binaryTree.isPerfect());
    }

    @DisplayName("isPerfect - " +
            "When tree is perfect - " +
            "Should return true")
    @Test
    void isPerfect_treeIsPerfect_returnTrue() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);
        binaryTree.insert(18);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(22);
        binaryTree.insert(26);
        binaryTree.insert(35);
        binaryTree.insert(34);
        binaryTree.insert(36);

        assertTrue(binaryTree.isPerfect());
    }

    @DisplayName("isPerfect - " +
            "When tree is not perfect - " +
            "Should return false")
    @Test
    void isPerfect_treeIsNotPerfect_returnFalse() {
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);

        assertFalse(binaryTree.isPerfect());
    }

    @DisplayName("isPerfect - " +
            "When tree has only left subtree - " +
            "Should return false")
    @Test
    void isPerfect_treeHasOnlyLeftSubtree_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(3);

        assertFalse(binaryTree.isPerfect());
    }

    @DisplayName("isPerfect - " +
            "When tree has only right subtree - " +
            "Should return false")
    @Test
    void isPerfect_treeHasOnlyRightSubtree_returnFalse() {
        binaryTree.insert(30);
        binaryTree.insert(35);
        binaryTree.insert(40);

        assertFalse(binaryTree.isPerfect());
    }

    @DisplayName("size - " +
            "When tree is empty - " +
            "Should return 0")
    @Test
    void size_treeIsEmpty_returnZero() {
        binaryTree = new BinaryTree<>(null);

        assertEquals(0, binaryTree.size());
    }

    @DisplayName("size - " +
            "When tree has a single node - " +
            "Should return 1")
    @Test
    void size_treeHasSingleNode_returnOne() {
        assertEquals(1, binaryTree.size());
    }

    @DisplayName("size - " +
            "When tree has multiple nodes - " +
            "Should return the correct size")
    @Test
    void size_treeHasMultipleNodes_returnCorrectSize() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);
        binaryTree.insert(18);

        binaryTree.remove(10);
        binaryTree.remove(3);

        binaryTree.insert(1);

        assertEquals(7, binaryTree.size());
    }


    @DisplayName("size2 - " +
            "When tree is empty - " +
            "Should return 0")
    @Test
    void size2_treeIsEmpty_returnZero() {
        binaryTree = new BinaryTree<>(null);

        assertEquals(0, binaryTree.size2());
    }

    @DisplayName("size2 - " +
            "When tree has a single node - " +
            "Should return 1")
    @Test
    void size2_treeHasSingleNode_returnOne() {
        assertEquals(1, binaryTree.size2());
    }

    @DisplayName("size2 - " +
            "When tree has multiple nodes - " +
            "Should return the correct size")
    @Test
    void size2_treeHasMultipleNodes_returnCorrectSize() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);
        binaryTree.insert(18);

        binaryTree.remove(10);
        binaryTree.remove(3);

        binaryTree.insert(1);

        assertEquals(7, binaryTree.size2());
    }

    @DisplayName("countLeaves - " +
            "When tree is empty - " +
            "Should return 0")
    @Test
    void countLeaves_treeIsEmpty_returnZero() {
        binaryTree = new BinaryTree<>(null);

        assertEquals(0, binaryTree.countLeaves());
    }

    @DisplayName("countLeaves - " +
            "When tree has a single leaf node - " +
            "Should return 1")
    @Test
    void countLeaves_treeHasSingleLeafNode_returnOne() {
        assertEquals(1, binaryTree.countLeaves());
    }

    @DisplayName("countLeaves - " +
            "When tree has multiple leaf nodes - " +
            "Should return the correct count")
    @Test
    void countLeaves_treeHasMultipleLeafNodes_returnCorrectCount() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(12);
        binaryTree.insert(18);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(35);

        assertEquals(6, binaryTree.countLeaves());
    }

    @DisplayName("areSibling - " +
            "When siblings exist - " +
            "Should return true")
    @Test
    void areSibling_siblingsExist_returnTrue() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);

        assertTrue(binaryTree.areSibling(15, 5));
        assertTrue(binaryTree.areSibling(5, 15));
    }

    @DisplayName("areSibling - " +
            "When siblings don't exist - " +
            "Should return false")
    @Test
    void areSibling_siblingsDoNotExist_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);

        assertFalse(binaryTree.areSibling(5, 3));
        assertFalse(binaryTree.areSibling(15, 7));
    }

    @DisplayName("areSibling - " +
            "When one or both values don't exist in the tree - " +
            "Should return false")
    @Test
    void areSibling_valuesDoNotExist_returnFalse() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);

        assertFalse(binaryTree.areSibling(5, 25));
        assertFalse(binaryTree.areSibling(25, 5));
        assertFalse(binaryTree.areSibling(25, 30));
    }

    @DisplayName("areSibling - " +
            "When tree is empty - " +
            "Should return false")
    @Test
    void areSibling_treeIsEmpty_returnFalse() {
        binaryTree = new BinaryTree<>(null);

        assertFalse(binaryTree.areSibling(5, 10));
    }

    @DisplayName("getAncestors - " +
            "When tree is empty - " +
            "Should return an empty list")
    @Test
    void getAncestors_emptyTree_shouldReturnEmptyList() {
        binaryTree = new BinaryTree<>(null);
        List<Integer> ancestors = binaryTree.getAncestors(10);

        assertTrue(ancestors.isEmpty());
    }

    @DisplayName("getAncestors - " +
            "When tree has a single node - " +
            "Should return an empty list")
    @Test
    void getAncestors_singleNodeTree_shouldReturnEmptyList() {
        List<Integer> ancestors = binaryTree.getAncestors(20);

        assertTrue(ancestors.isEmpty());
    }

    @DisplayName("getAncestors - " +
            "When tree has multiple nodes - " +
            "Should return ancestors")
    @Test
    void getAncestors_multipleNodesTree_shouldReturnAncestors() {
        binaryTree.insert(15);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(25);
        binaryTree.insert(23);

        List<Integer> ancestors = binaryTree.getAncestors(7);
        List<Integer> expectedAncestors = Arrays.asList(3, 15, 20);
        assertEquals(expectedAncestors, ancestors);
    }

    @DisplayName("getAncestors - " +
            "When value is not present in the tree - " +
            "Should return an empty list")
    @Test
    void getAncestors_valueNotPresent_shouldReturnEmptyList() {
        binaryTree.insert(5);
        binaryTree.insert(15);

        List<Integer> ancestors = binaryTree.getAncestors(8);
        assertTrue(ancestors.isEmpty());
    }

    @DisplayName("representTree - " +
            "When tree is empty - " +
            "Should return empty string")
    @Test
    void representTree_emptyTree_shouldReturnEmptyString() {
        binaryTree = new BinaryTree<>(null);

        assertEquals("", binaryTree.representTree());
    }

    @DisplayName("representTree - " +
            "When tree has one node - " +
            "Should return single node string")
    @Test
    void representTree_singleNodeTree_shouldReturnSingleNodeString() {
        assertEquals("\n20", binaryTree.representTree());
    }

    @DisplayName("representTree - " +
            "When tree has multiple nodes - " +
            "Should return representative string")
    @Test
    void representTree_multipleNodesTree_shouldReturnRepresentativeString() {
        binaryTree.insert(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(35);

        assertEquals("""
                          
                          35
                     30
                          25
                20
                          15
                     10
                          5""", binaryTree.representTree());
    }

}