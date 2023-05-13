package io.github.aliazani.nonlinear.binary_tree;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;
//    private int countLeaves;

    public BinaryTree(T root) {
        this.root = new Node<>(root);
        size++;
    }

    public void insert(T value) {
        Node<T> node = new Node<>(value);

        if (root == null) {
            root = node;
            return;
        }

        Node<T> current = root;
        while (true) {
            if (value.compareTo(current.getValue()) < 0) {
                if (current.getLeftChild() == null) {
                    current.setLeftChild(node);
                    break;
                }
                current = current.getLeftChild();
            } else {
                if (current.getRightChild() == null) {
                    current.setRightChild(node);
                    break;
                }
                current = current.getRightChild();
            }
        }
    }

    public boolean find(T value) {
        Node<T> current = root;
        while (current != null) {
            if (value.compareTo(current.getValue()) < 0)
                current = current.getLeftChild();
            else if (value.compareTo(current.getValue()) > 0)
                current = current.getRightChild();
            else
                return true;
        }
        return false;
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
//    public void traversePreOrder() {
//        traversePreOrder(root);
//    }
//
//    private void traversePreOrder(Node<T> root) {
//        if (isEmpty(root))
//            return;
//        System.out.println(root.value);
//        traversePreOrder(root.leftChild);
//        traversePreOrder(root.rightChild);
//    }
//
//    public void traversePostOrder() {
//        traversePostOrder(root);
//    }
//
//    private void traversePostOrder(Node<T> root) {
//        if (isEmpty(root))
//            return;
//        traversePostOrder(root.leftChild);
//        traversePostOrder(root.rightChild);
//        System.out.println(root.value);
//    }
//
//    public void traverseInOrder() {
//        traverseInOrder(root);
//    }
//
//    private void traverseInOrder(Node<T> root) {
//        if (isEmpty(root))
//            return;
//        traverseInOrder(root.leftChild);
//        System.out.println(root.value);
//        traverseInOrder(root.rightChild);
//    }
//
//    public int height() {
//        return height(root);
//    }
//
//    private int height(Node<T> root) {
//        if (isEmpty((Node<T>) root))
//            return -1;
//        if (isLeaf(root))
//            return 0;
//        return 1 + Math.max(
//                height(root.leftChild),
//                height(root.rightChild));
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
//    public T minimum() {
//        return minimum(root);
//    }
//
//    private T minimum(Node<T> root) {
//        if (isLeaf(root))
//            return root.value;
//
//        T minValue = null;
//        var left = minimum(root.leftChild);
//        var right = minimum(root.rightChild);
//        minValue = (right.compareTo(left) < 0) ? right : left;
//        minValue = root.value.compareTo(minValue) < 0 ? root.value : minValue;
//
//        return minValue;
//    }
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
//
//    private boolean isLeaf(Node<T> root) {
//        return root.rightChild == null && root.leftChild == null;
//    }
//
//    private boolean isEmpty(Node<T> root) {
//        return root == null;
//    }
//
//    @Override
//    public String toString() {
//        return root.toString();
//    }
}
