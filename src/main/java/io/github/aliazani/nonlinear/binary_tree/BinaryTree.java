package io.github.aliazani.nonlinear.binary_tree;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;
//    private int countLeaves;

    public BinaryTree(T root) {
        this.root = new Node<>(root, null);
        size++;
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
            if (nextChild == null) {
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
            else if (node.getLeftChild() == null) node = node.getRightChild();
            else if (node.getRightChild() == null) node = node.getLeftChild();
                // Scenario 3: Node<T has two children
            else {
                Node<T> successor = findSuccessor(node.getRightChild());
                node.setValue(successor.getValue());
                node.setRightChild(removeRecursive(node.getRightChild(), successor.getValue()));
            }
        }
        return node;
    }

    private Node<T> findSuccessor(Node<T> node) {
        while (node.getLeftChild() != null) node = node.getLeftChild();

        return node;
    }


    public boolean contains(T value) {
        if (value == null) throw new IllegalArgumentException("value can not be null");

        Node<T> current = root;
        while (current != null) {
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
        if (node == null) return false;

        int comparison = value.compareTo(node.getValue());
        if (comparison < 0) return containsRecursive(node.getLeftChild(), value);
        else if (comparison > 0) return containsRecursive(node.getRightChild(), value);
        else return true;
    }

    public String traverseLevelOrder() {
        if (isEmpty(root)) return "";

        StringBuilder strBuilder = new StringBuilder();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            strBuilder.append(current.getValue()).append(", ");

            if (current.getLeftChild() != null) queue.offer(current.getLeftChild());
            if (current.getRightChild() != null) queue.offer(current.getRightChild());
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

    public T minimum() {
        return minimum(root);
    }

    private T minimum(Node<T> root) {
        if (isLeaf(root)) return root.getValue();

        T minValue;
        T left = minimum(root.getLeftChild());
        T right = minimum(root.getRightChild());
        minValue = (right.compareTo(left) < 0) ? right : left;
        minValue = root.getValue().compareTo(minValue) < 0 ? root.getValue() : minValue;

        return minValue;
    }

    //    public boolean isBalanced() {
//        var current = root;
//        while (!isLeaf(current)) {
//            if (Math.abs(height(current.leftChild) - height(current.rightChild)) > 1)
//                return false;
//            current = current.leftChild;
//        }
//        current = root;
//        while (!isLeaf(current)) {
//            if (Math.abs(height(current.leftChild) - height(current.rightChild)) > 1)
//                return false;
//            current = current.rightChild;
//        }
//        return true;
//    }
//
//    public boolean isPerfect() {
//        return (2 * (height(this.root) + 1) - 1) == this.size;
//    }
//
//    public boolean contains(T item) {
//        return contains(item, root);
//    }
//
//    private boolean contains(T item, Node<T> root) {
//        if (root == null)
//            return false;
//        else if (root.value.compareTo(item) == 0)
//            return true;
//
//        return contains(item, root.leftChild) || contains(item, root.rightChild);
//    }
//
//    public void swapRoot() {
//        var temp = root.leftChild;
//        root.leftChild = root.rightChild;
//        root.rightChild = temp;
//    }
//
//    public boolean isBinarySearchTree(T min, T max) {
//        return isBinarySearchTree(root, min, max);
//    }
//
//    private boolean isBinarySearchTree(Node<T> root, T min, T max) {
//        if (isEmpty(root))
//            return true;
//        if (root.value.compareTo(min) <= 0 || root.value.compareTo(max) >= 0)
//            return false;
//
//        return isBinarySearchTree(root.leftChild, min, root.value) &&
//                isBinarySearchTree(root.rightChild, root.value, max);
//    }

    //
//    public void printNodesAtDistance(int distance) {
//        printNodesAtDistance(root, distance);
//    }
//
//    private void printNodesAtDistance(Node<T> root, int distance) {
//        if (isEmpty(root))
//            return;
//        if (distance == 0)
//            System.out.println(root.value);
//        else {
//            printNodesAtDistance(root.leftChild, distance - 1);
//            printNodesAtDistance(root.rightChild, distance - 1);
//        }
//    }
//
//    public void traverseLevelOrder() {
//        for (int i = 0; i <= height(root); i++)
//            printNodesAtDistance(i);
//    }
//
//    public boolean equals(BinaryTree<T> other) {
//        if (other == null)
//            return false;
//        return equals(root, other.root);
//    }
//
//    private boolean equals(Node<T> first, Node<T> second) {
//        if (first == null && second == null)
//            return true;
//        if (first != null && second != null)
//            return first.value == second.value
//                    && equals(first.leftChild, second.leftChild)
//                    && equals(first.rightChild, second.rightChild);
//        return false;
//    }
//
//
//    public T maximum() {
//        return maximum(root);
//    }
//
//    private T maximum(Node<T> root) {
//        if (isLeaf(root))
//            return root.value;
//
//        T maxValue = null;
//        var left = maximum(root.leftChild);
//        var right = maximum(root.rightChild);
//        maxValue = (right.compareTo(left) > 0) ? right : left;
//        maxValue = root.value.compareTo(maxValue) > 0 ? root.value : maxValue;
//
//        return maxValue;
//    }
//
//    public int size() {
//        return size;
//    }

    //
//    public int countLeaves() {
//        return countLeaves(root);
//    }
//
//    private int countLeaves(Node<T> root) {
//        if (isLeaf(root)) {
//            return countLeaves++;
//        }
//        countLeaves(root.leftChild);
//        countLeaves(root.rightChild);
//        return countLeaves;
//    }

//    public void printTree(T[][] M, Node<T> root, int col, int row, int height) {
//        if (root == null)
//            return;
//        M[row][col] = root.getValue();
//        printTree(M, root.getLeftChild(), col - (int) Math.pow(2, height - 2), row + 1, height - 1);
//        printTree(M, root.getRightChild(), col + (int) Math.pow(2, height - 2), row + 1, height - 1);
//    }
//    public void TreePrinter() {
//        int h = height(root);
//        int col = getcol(h);
//        T[][] M = (T[][]) new Comparable[h][col];
//        printTree(M, root, col / 2, 0, h);
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < col; j++) {
//                if (M[i][j] == 0)
//                    System.out.print("  ");
//                else
//                    System.out.print(M[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    private boolean isEmpty(Node<T> node) {
        return node == null || node.getValue() == null;
    }

    private boolean isLeaf(Node<T> node) {
        return node.getRightChild() == null
                && node.getLeftChild() == null;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public void print() {
        print2DUtil(root, 0);
    }

    private void print2DUtil(Node<T> root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 5;


        // Process right child first
        print2DUtil(root.getRightChild(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = 5; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getValue() + "\n");

        // Process left child
        print2DUtil(root.getLeftChild(), space);
    }
}