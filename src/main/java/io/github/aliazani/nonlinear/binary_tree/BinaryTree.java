package io.github.aliazani.nonlinear.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public BinaryTree(T root) {
        if (root != null) {
            this.root = new Node<>(root, null);
            size++;
        }
    }

    public void insert(T value) {
        if (value == null) throw new IllegalArgumentException("Cannot assign null value to a node.");

        if (isEmpty(root)) {
            root = new Node<>(value, null);
            size++;
            return;
        }

        Node<T> current = root;
        while (true) {
            int comparison = value.compareTo(current.getValue());
            if (comparison == 0) throw new IllegalStateException("Binary Tree cannot have duplicate values.");

            Node<T> nextChild = comparison < 0 ?
                    current.getLeftChild() :
                    current.getRightChild();
            if (isEmpty(nextChild)) {
                Node<T> newNode = new Node<>(value, current);
                if (comparison < 0) current.setLeftChild(newNode);
                else current.setRightChild(newNode);
                size++;

                return;
            }
            current = nextChild;
        }
    }

    public void remove(T value) {
        root = removeRecursive(root, value);
        size--;
    }

    private Node<T> removeRecursive(Node<T> node, T value) {
        if (isEmpty(node)) throw new IllegalStateException();

        int comparison = value.compareTo(node.getValue());
        if (comparison < 0) node.setLeftChild(removeRecursive(node.getLeftChild(), value));
        else if (comparison > 0) node.setRightChild(removeRecursive(node.getRightChild(), value));
        else {
            if (isLeaf(node)) node = null;
            else if (isEmpty(node.getLeftChild())) node = node.getRightChild();
            else if (isEmpty(node.getRightChild())) node = node.getLeftChild();
            else {
                Node<T> successor = findSuccessor(node.getRightChild());
                node.setValue(successor.getValue());
                node.setRightChild(removeRecursive(node.getRightChild(), successor.getValue()));
            }
        }
        return node;
    }

    private Node<T> findSuccessor(Node<T> node) {
        while (!isEmpty(node.getLeftChild())) node = node.getLeftChild();

        return node;
    }


    public boolean contains(T value) {
        if (value == null) throw new IllegalArgumentException("value can not be null");

        Node<T> current = root;
        while (!isEmpty(current)) {
            int comparison = value.compareTo(current.getValue());
            if (comparison < 0) current = current.getLeftChild();
            else if (comparison > 0) current = current.getRightChild();
            else return true;
        }
        return false;
    }

    public boolean contains2(T value) {
        if (value == null) throw new IllegalArgumentException("value can not be null");

        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (isEmpty(node)) return false;

        int comparison = value.compareTo(node.getValue());
        if (comparison == 0) return true;

        return containsRecursive(node.getLeftChild(), value) || containsRecursive(root.getRightChild(), value);
    }

    public String traverseLevelOrder() {
        if (isEmpty(root)) return "";

        StringBuilder strBuilder = new StringBuilder();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            strBuilder.append(current.getValue()).append(", ");

            if (!isEmpty(current.getLeftChild())) queue.offer(current.getLeftChild());
            if (!isEmpty(current.getRightChild())) queue.offer(current.getRightChild());
        }

        strBuilder.setLength(strBuilder.length() - 2);
        return strBuilder.toString();
    }

    public String traversePreOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traversePreOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traversePreOrder(Node<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        strBuilder.append(root.getValue()).append(", ");
        traversePreOrder(root.getLeftChild(), strBuilder);
        traversePreOrder(root.getRightChild(), strBuilder);
    }

    public String traversePostOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traversePostOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traversePostOrder(Node<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        traversePostOrder(root.getLeftChild(), strBuilder);
        traversePostOrder(root.getRightChild(), strBuilder);
        strBuilder.append(root.getValue()).append(", ");
    }

    public String traverseInOrder() {
        StringBuilder strBuilder = new StringBuilder();
        traverseInOrder(root, strBuilder);
        strBuilder.replace(strBuilder.lastIndexOf(","), strBuilder.toString().length(), "");

        return strBuilder.toString();
    }

    private void traverseInOrder(Node<T> root, StringBuilder strBuilder) {
        if (isEmpty(root))
            return;
        traverseInOrder(root.getLeftChild(), strBuilder);
        strBuilder.append(root.getValue()).append(", ");
        traverseInOrder(root.getRightChild(), strBuilder);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> root) {
        if (isEmpty(root)) return -1;
        if (isLeaf(root)) return 0;
        return 1 + Math.max(
                height(root.getLeftChild()),
                height(root.getRightChild()));
    }

    public T min() {
        if (isEmpty(root)) throw new IllegalStateException();
        return findMinimum(root);
    }

    private T findMinimum(Node<T> node) {
        if (isLeaf(node)) return node.getValue();

        T leftMin = (!isEmpty(node.getLeftChild())) ? findMinimum(node.getLeftChild()) : null;
        T rightMin = (!isEmpty(node.getRightChild())) ? findMinimum(node.getRightChild()) : null;

        T minValue = leftMin;
        if (rightMin != null &&
                (minValue == null || rightMin.compareTo(minValue) < 0)) minValue = rightMin;

        if (minValue == null
                || node.getValue().compareTo(minValue) < 0) minValue = node.getValue();

        return minValue;
    }

    public T max() {
        if (isEmpty(root)) throw new IllegalStateException();
        return findMaximum(root);
    }

    private T findMaximum(Node<T> node) {
        if (isLeaf(node)) return node.getValue();

        T leftMax = (!isEmpty(node.getLeftChild())) ? findMaximum(node.getLeftChild()) : null;
        T rightMax = (!isEmpty(node.getRightChild())) ? findMaximum(node.getRightChild()) : null;

        T maxValue = leftMax;
        if (rightMax != null &&
                (maxValue == null || rightMax.compareTo(maxValue) > 0)) maxValue = rightMax;

        if (maxValue == null
                || node.getValue().compareTo(maxValue) > 0) maxValue = node.getValue();

        return maxValue;
    }

    public boolean equalsTree(BinaryTree<T> other) {
        if (other == null) throw new IllegalArgumentException();
        return equals(root, other.root);
    }

    private boolean equals(Node<T> firstTreeRoot, Node<T> secondTreeRoot) {
        if (isEmpty(firstTreeRoot) && isEmpty(secondTreeRoot)) return true;
        if (!isEmpty(firstTreeRoot) && !isEmpty(secondTreeRoot))
            return firstTreeRoot.getValue().equals(secondTreeRoot.getValue())
                    && equals(firstTreeRoot.getLeftChild(), secondTreeRoot.getLeftChild())
                    && equals(firstTreeRoot.getRightChild(), secondTreeRoot.getRightChild());

        return false;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, null, null);
    }

    private boolean isBinarySearchTree(Node<T> node, T min, T max) {
        if (isEmpty(node)) return true;
        if ((min != null && node.getValue().compareTo(min) <= 0)
                || (max != null && node.getValue().compareTo(max) >= 0)) return false;

        return isBinarySearchTree(node.getLeftChild(), min, node.getValue()) &&
                isBinarySearchTree(node.getRightChild(), node.getValue(), max);
    }

    public void swapRootChildren() {
        Node<T> temp = root.getLeftChild();
        root.setLeftChild(root.getRightChild());
        root.setRightChild(temp);
    }

    public String nodesAtDistance(int distance) {
        if (distance < 0) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        nodesAtDistance(root, distance, strBuilder);
        if (strBuilder.length() >= 2)
            strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void nodesAtDistance(Node<T> node, int distance, StringBuilder strBuilder) {
        if (isEmpty(node)) return;
        if (distance == 0) strBuilder.append(node.getValue()).append(", ");
        else {
            nodesAtDistance(node.getLeftChild(), distance - 1, strBuilder);
            nodesAtDistance(node.getRightChild(), distance - 1, strBuilder);
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<T> node) {
        if (isEmpty(node)) return true;

        int balanceFactor = height(node.getLeftChild()) - height(node.getRightChild());

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(node.getLeftChild()) &&
                isBalanced(node.getRightChild());
    }

    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
    }

    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (isEmpty(node)) return 0;

        if (isLeaf(node)) return 1;

        return 1 + size(node.getLeftChild()) + size(node.getRightChild());
    }

    public int size2() {
        return size;
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<T> node) {
        if (isEmpty(node)) return 0;

        if (isLeaf(node)) return 1;

        return countLeaves(node.getLeftChild()) + countLeaves(node.getRightChild());
    }


    public boolean areSibling(T first, T second) {
        return areSibling(root, first, second);
    }

    private boolean areSibling(Node<T> node, T first, T second) {
        if (isEmpty(node)) return false;

        boolean areSibling = false;
        if (!isEmpty(node.getLeftChild()) && !isEmpty(node.getRightChild()))
            areSibling = (node.getLeftChild().getValue().equals(first) && node.getRightChild().getValue().equals(second))
                    || (node.getRightChild().getValue().equals(first) && node.getLeftChild().getValue().equals(second));

        return areSibling ||
                areSibling(node.getLeftChild(), first, second) ||
                areSibling(node.getRightChild(), first, second);
    }

    public List<T> getAncestors(T value) {
        List<T> ancestors = new ArrayList<>();
        getAncestors(root, value, ancestors);

        return ancestors;
    }

    private boolean getAncestors(Node<T> node, T value, List<T> list) {
        if (isEmpty(node)) return false;

        if (node.getValue().equals(value)) return true;

        if (getAncestors(node.getLeftChild(), value, list) ||
                getAncestors(node.getRightChild(), value, list)) {
            list.add(node.getValue());
            return true;
        }

        return false;
    }

    private boolean isEmpty(Node<T> node) {
        return node == null || node.getValue() == null;
    }

    private boolean isLeaf(Node<T> node) {
        return node.getRightChild() == null
                && node.getLeftChild() == null;
    }

    public String representTree() {
        StringBuilder stringBuilder = new StringBuilder();
        buildTreeString(root, 0, stringBuilder);

        return stringBuilder.toString();
    }

    private void buildTreeString(Node<T> node, int currentIndentation, StringBuilder stringBuilder) {
        if (isEmpty(node)) return;

        int childIndentation = currentIndentation + 5;

        buildTreeString(node.getRightChild(), childIndentation, stringBuilder);

        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(Math.max(0, currentIndentation)));
        stringBuilder.append(node.getValue());

        buildTreeString(node.getLeftChild(), childIndentation, stringBuilder);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}