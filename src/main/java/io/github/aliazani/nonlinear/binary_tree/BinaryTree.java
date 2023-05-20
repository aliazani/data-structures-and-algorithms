package io.github.aliazani.nonlinear.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A binary tree is a tree data structure in which each node has at most two children, referred to as the left child
 * and the right child.
 *
 * @param <T> the type of elements stored in the binary tree
 */
public class BinaryTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;
    private int size;

    /**
     * Constructs a binary tree with the specified root value.
     *
     * @param root the value of the root node
     */
    public BinaryTree(T root) {
        if (root != null) {
            this.root = new BinaryTreeNode<>(root, null);
            size++;
        }
    }

    /**
     * Inserts a value into the binary tree.
     *
     * @param value the value to be inserted
     * @throws IllegalArgumentException if the value is null
     * @throws IllegalStateException    if the binary tree already contains the value (duplicate values are not allowed)
     */
    public void insert(T value) {
        if (value == null) throw new IllegalArgumentException("Cannot assign null value to a node.");

        if (isEmpty(root)) {
            root = new BinaryTreeNode<>(value, null);
            size++;
            return;
        }

        BinaryTreeNode<T> current = root;
        while (true) {
            int comparison = value.compareTo(current.getValue());
            if (comparison == 0) throw new IllegalStateException("Binary Tree cannot have duplicate values.");

            BinaryTreeNode<T> nextChild = comparison < 0 ?
                    current.getLeftChild() :
                    current.getRightChild();
            if (isEmpty(nextChild)) {
                BinaryTreeNode<T> newBinaryTreeNode = new BinaryTreeNode<>(value, current);
                if (comparison < 0) current.setLeftChild(newBinaryTreeNode);
                else current.setRightChild(newBinaryTreeNode);
                size++;

                return;
            }
            current = nextChild;
        }
    }

    /**
     * Removes a value from the binary tree.
     *
     * @param value the value to be removed
     * @throws IllegalStateException if the binary tree does not contain the value
     */
    public void remove(T value) {
        root = removeRecursive(root, value);
        size--;
    }

    private BinaryTreeNode<T> removeRecursive(BinaryTreeNode<T> binaryTreeNode, T value) {
        if (isEmpty(binaryTreeNode)) throw new IllegalStateException();

        int comparison = value.compareTo(binaryTreeNode.getValue());
        if (comparison < 0) binaryTreeNode.setLeftChild(removeRecursive(binaryTreeNode.getLeftChild(), value));
        else if (comparison > 0) binaryTreeNode.setRightChild(removeRecursive(binaryTreeNode.getRightChild(), value));
        else {
            if (isLeaf(binaryTreeNode)) binaryTreeNode = null;
            else if (isEmpty(binaryTreeNode.getLeftChild())) binaryTreeNode = binaryTreeNode.getRightChild();
            else if (isEmpty(binaryTreeNode.getRightChild())) binaryTreeNode = binaryTreeNode.getLeftChild();
            else {
                BinaryTreeNode<T> successor = findSuccessor(binaryTreeNode.getRightChild());
                binaryTreeNode.setValue(successor.getValue());
                binaryTreeNode.setRightChild(removeRecursive(binaryTreeNode.getRightChild(), successor.getValue()));
            }
        }
        return binaryTreeNode;
    }

    private BinaryTreeNode<T> findSuccessor(BinaryTreeNode<T> binaryTreeNode) {
        while (!isEmpty(binaryTreeNode.getLeftChild())) binaryTreeNode = binaryTreeNode.getLeftChild();

        return binaryTreeNode;
    }

    /**
     * Checks if the binary tree contains the specified value.
     *
     * @param value the value to be checked
     * @return true if the value is found, false otherwise
     * @throws IllegalArgumentException if the value is null
     */
    public boolean contains(T value) {
        if (value == null) throw new IllegalArgumentException("value can not be null");

        BinaryTreeNode<T> current = root;
        while (!isEmpty(current)) {
            int comparison = value.compareTo(current.getValue());
            if (comparison < 0) current = current.getLeftChild();
            else if (comparison > 0) current = current.getRightChild();
            else return true;
        }
        return false;
    }

    /**
     * Checks if the binary tree contains the specified value recursively.
     *
     * @param value the value to be checked
     * @return true if the value is found, false otherwise
     * @throws IllegalArgumentException if the value is null
     */
    public boolean contains2(T value) {
        if (value == null) throw new IllegalArgumentException("value can not be null");

        return containsRecursive(root, value);
    }

    private boolean containsRecursive(BinaryTreeNode<T> binaryTreeNode, T value) {
        if (isEmpty(binaryTreeNode)) return false;

        int comparison = value.compareTo(binaryTreeNode.getValue());
        if (comparison == 0) return true;

        return containsRecursive(binaryTreeNode.getLeftChild(), value) || containsRecursive(root.getRightChild(), value);
    }

    /**
     * Traverses the binary tree in level order (breadth-first search) and returns the values of the nodes.
     *
     * @return a string representation of the values in level order
     */
    public String traverseLevelOrder() {
        if (isEmpty(root)) return "";

        StringBuilder strBuilder = new StringBuilder();
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> current = queue.poll();
            strBuilder.append(current.getValue()).append(", ");

            if (!isEmpty(current.getLeftChild())) queue.offer(current.getLeftChild());
            if (!isEmpty(current.getRightChild())) queue.offer(current.getRightChild());
        }

        strBuilder.setLength(strBuilder.length() - 2);
        return strBuilder.toString();
    }

    /**
     * Traverses the binary tree in pre-order and returns the values of the nodes.
     *
     * @return a string representation of the values in pre-order
     */
    public String traversePreOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traversePreOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traversePreOrder(BinaryTreeNode<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        strBuilder.append(root.getValue()).append(", ");
        traversePreOrder(root.getLeftChild(), strBuilder);
        traversePreOrder(root.getRightChild(), strBuilder);
    }

    /**
     * Traverses the binary tree in post-order and returns the values of the nodes.
     *
     * @return a string representation of the values in post-order
     */
    public String traversePostOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traversePostOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traversePostOrder(BinaryTreeNode<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        traversePostOrder(root.getLeftChild(), strBuilder);
        traversePostOrder(root.getRightChild(), strBuilder);
        strBuilder.append(root.getValue()).append(", ");
    }


    /**
     * Traverses the binary tree in in-order and returns the values of the nodes.
     *
     * @return a string representation of the values in in-order
     */
    public String traverseInOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traverseInOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traverseInOrder(BinaryTreeNode<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        traverseInOrder(root.getLeftChild(), strBuilder);
        strBuilder.append(root.getValue()).append(", ");
        traverseInOrder(root.getRightChild(), strBuilder);
    }

    /**
     * Calculates the height of the binary tree.
     *
     * @return the height of the binary tree
     */
    public int height() {
        return height(root);
    }

    private int height(BinaryTreeNode<T> root) {
        if (isEmpty(root)) return -1;
        if (isLeaf(root)) return 0;
        return 1 + Math.max(
                height(root.getLeftChild()),
                height(root.getRightChild()));
    }

    /**
     * Finds the minimum value in the binary tree.
     *
     * @return the minimum value in the binary tree
     * @throws IllegalStateException if the binary tree is empty
     */
    public T min() {
        if (isEmpty(root)) throw new IllegalStateException();
        return findMinimum(root);
    }

    private T findMinimum(BinaryTreeNode<T> binaryTreeNode) {
        if (isLeaf(binaryTreeNode)) return binaryTreeNode.getValue();

        T leftMin = (!isEmpty(binaryTreeNode.getLeftChild())) ? findMinimum(binaryTreeNode.getLeftChild()) : null;
        T rightMin = (!isEmpty(binaryTreeNode.getRightChild())) ? findMinimum(binaryTreeNode.getRightChild()) : null;

        T minValue = leftMin;
        if (rightMin != null &&
                (minValue == null || rightMin.compareTo(minValue) < 0)) minValue = rightMin;

        if (minValue == null
                || binaryTreeNode.getValue().compareTo(minValue) < 0) minValue = binaryTreeNode.getValue();

        return minValue;
    }

    /**
     * Finds the maximum value in the binary tree.
     *
     * @return the maximum value in the binary tree
     * @throws IllegalStateException if the binary tree is empty
     */
    public T max() {
        if (isEmpty(root)) throw new IllegalStateException();
        return findMaximum(root);
    }

    private T findMaximum(BinaryTreeNode<T> binaryTreeNode) {
        if (isLeaf(binaryTreeNode)) return binaryTreeNode.getValue();

        T leftMax = (!isEmpty(binaryTreeNode.getLeftChild())) ? findMaximum(binaryTreeNode.getLeftChild()) : null;
        T rightMax = (!isEmpty(binaryTreeNode.getRightChild())) ? findMaximum(binaryTreeNode.getRightChild()) : null;

        T maxValue = leftMax;
        if (rightMax != null &&
                (maxValue == null || rightMax.compareTo(maxValue) > 0)) maxValue = rightMax;

        if (maxValue == null
                || binaryTreeNode.getValue().compareTo(maxValue) > 0) maxValue = binaryTreeNode.getValue();

        return maxValue;
    }


    /**
     * Checks if the binary tree is equal to another binary tree.
     *
     * @param other the other binary tree to compare
     * @return true if the two binary trees are equal, false otherwise
     * @throws IllegalArgumentException if the other binary tree is null
     */
    public boolean equalsTree(BinaryTree<T> other) {
        if (other == null) throw new IllegalArgumentException();
        return equals(root, other.root);
    }

    private boolean equals(BinaryTreeNode<T> firstTreeRoot, BinaryTreeNode<T> secondTreeRoot) {
        if (isEmpty(firstTreeRoot) && isEmpty(secondTreeRoot)) return true;
        if (!isEmpty(firstTreeRoot) && !isEmpty(secondTreeRoot))
            return firstTreeRoot.getValue().equals(secondTreeRoot.getValue())
                    && equals(firstTreeRoot.getLeftChild(), secondTreeRoot.getLeftChild())
                    && equals(firstTreeRoot.getRightChild(), secondTreeRoot.getRightChild());

        return false;
    }

    /**
     * Checks if the binary tree is a binary search tree.
     *
     * @return true if the binary tree is a binary search tree, false otherwise
     */
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, null, null);
    }

    private boolean isBinarySearchTree(BinaryTreeNode<T> binaryTreeNode, T min, T max) {
        if (isEmpty(binaryTreeNode)) return true;
        if ((min != null && binaryTreeNode.getValue().compareTo(min) <= 0)
                || (max != null && binaryTreeNode.getValue().compareTo(max) >= 0)) return false;

        return isBinarySearchTree(binaryTreeNode.getLeftChild(), min, binaryTreeNode.getValue()) &&
                isBinarySearchTree(binaryTreeNode.getRightChild(), binaryTreeNode.getValue(), max);
    }

    /**
     * Swaps the left and right children of the root node in the binary tree.
     */
    public void swapRootChildren() {
        BinaryTreeNode<T> temp = root.getLeftChild();
        root.setLeftChild(root.getRightChild());
        root.setRightChild(temp);
    }

    /**
     * Returns the values of the nodes at a given distance from the root.
     *
     * @param distance the distance from the root
     * @return a string representation of the values at the given distance
     * @throws IllegalArgumentException if the distance is negative
     */
    public String nodesAtDistance(int distance) {
        if (distance < 0) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        nodesAtDistance(root, distance, strBuilder);
        if (strBuilder.length() >= 2)
            strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void nodesAtDistance(BinaryTreeNode<T> binaryTreeNode, int distance, StringBuilder strBuilder) {
        if (isEmpty(binaryTreeNode)) return;
        if (distance == 0) strBuilder.append(binaryTreeNode.getValue()).append(", ");
        else {
            nodesAtDistance(binaryTreeNode.getLeftChild(), distance - 1, strBuilder);
            nodesAtDistance(binaryTreeNode.getRightChild(), distance - 1, strBuilder);
        }
    }

    /**
     * Checks if the binary tree is balanced.
     *
     * @return true if the binary tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BinaryTreeNode<T> binaryTreeNode) {
        if (isEmpty(binaryTreeNode)) return true;

        int balanceFactor = height(binaryTreeNode.getLeftChild()) - height(binaryTreeNode.getRightChild());

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(binaryTreeNode.getLeftChild()) &&
                isBalanced(binaryTreeNode.getRightChild());
    }

    /**
     * Checks if the binary tree is perfect.
     *
     * @return true if the binary tree is perfect, false otherwise
     */
    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
    }

    /**
     * Returns the number of nodes in the binary tree.
     *
     * @return the number of nodes in the binary tree
     */
    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> binaryTreeNode) {
        if (isEmpty(binaryTreeNode)) return 0;

        if (isLeaf(binaryTreeNode)) return 1;

        return 1 + size(binaryTreeNode.getLeftChild()) + size(binaryTreeNode.getRightChild());
    }

    /**
     * Returns the number of nodes in the binary tree.
     *
     * @return the number of nodes in the binary tree
     */
    public int size2() {
        return size;
    }

    /**
     * Counts the number of leaf nodes in the binary tree.
     *
     * @return the number of leaf nodes in the binary tree
     */
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(BinaryTreeNode<T> binaryTreeNode) {
        if (isEmpty(binaryTreeNode)) return 0;

        if (isLeaf(binaryTreeNode)) return 1;

        return countLeaves(binaryTreeNode.getLeftChild()) + countLeaves(binaryTreeNode.getRightChild());
    }


    /**
     * Checks if two nodes in the binary tree are siblings.
     *
     * @param first  the value of the first node
     * @param second the value of the second node
     * @return true if the nodes are siblings, false otherwise
     */
    public boolean areSibling(T first, T second) {
        return areSibling(root, first, second);
    }

    private boolean areSibling(BinaryTreeNode<T> binaryTreeNode, T first, T second) {
        if (isEmpty(binaryTreeNode)) return false;

        boolean areSibling = false;
        if (!isEmpty(binaryTreeNode.getLeftChild()) && !isEmpty(binaryTreeNode.getRightChild()))
            areSibling = (binaryTreeNode.getLeftChild().getValue().equals(first) && binaryTreeNode.getRightChild().getValue().equals(second))
                    || (binaryTreeNode.getRightChild().getValue().equals(first) && binaryTreeNode.getLeftChild().getValue().equals(second));

        return areSibling ||
                areSibling(binaryTreeNode.getLeftChild(), first, second) ||
                areSibling(binaryTreeNode.getRightChild(), first, second);
    }

    /**
     * Returns a list of values representing the ancestors of a node in the binary tree.
     *
     * @param value the value of the node
     * @return a list of ancestor values
     */
    public List<T> getAncestors(T value) {
        List<T> ancestors = new ArrayList<>();
        getAncestors(root, value, ancestors);

        return ancestors;
    }

    private boolean getAncestors(BinaryTreeNode<T> binaryTreeNode, T value, List<T> list) {
        if (isEmpty(binaryTreeNode)) return false;

        if (binaryTreeNode.getValue().equals(value)) return true;

        if (getAncestors(binaryTreeNode.getLeftChild(), value, list) ||
                getAncestors(binaryTreeNode.getRightChild(), value, list)) {
            list.add(binaryTreeNode.getValue());
            return true;
        }

        return false;
    }

    private boolean isEmpty(BinaryTreeNode<T> binaryTreeNode) {
        return binaryTreeNode == null || binaryTreeNode.getValue() == null;
    }

    private boolean isLeaf(BinaryTreeNode<T> binaryTreeNode) {
        return binaryTreeNode.getRightChild() == null
                && binaryTreeNode.getLeftChild() == null;
    }

    /**
     * Returns a string representation of the binary tree.
     *
     * @return a string representation of the binary tree
     */
    public String representTree() {
        StringBuilder stringBuilder = new StringBuilder();
        buildTreeString(root, 0, stringBuilder);

        return stringBuilder.toString();
    }

    private void buildTreeString(BinaryTreeNode<T> binaryTreeNode, int currentIndentation, StringBuilder stringBuilder) {
        if (isEmpty(binaryTreeNode)) return;

        int childIndentation = currentIndentation + 5;

        buildTreeString(binaryTreeNode.getRightChild(), childIndentation, stringBuilder);

        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(Math.max(0, currentIndentation)));
        stringBuilder.append(binaryTreeNode.getValue());

        buildTreeString(binaryTreeNode.getLeftChild(), childIndentation, stringBuilder);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}