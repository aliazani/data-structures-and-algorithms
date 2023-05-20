package io.github.aliazani.nonlinear.avl_tree;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private int size;

    public AVLTree(T value) {
        if (value != null) {
            this.root = new AVLNode<>(value);
            size++;
        }
    }

    public int size() {
        return size;
    }

    public void insert(T value) {
        if (value == null) throw new IllegalArgumentException("Cannot assign null value to a node.");

        root = insert(root, value);
        size++;
    }

    private AVLNode<T> insert(AVLNode<T> current, T value) {
        if (current == null) return new AVLNode<>(value);

        int comparison = value.compareTo(current.getValue());
        if (comparison == 0) throw new IllegalStateException("AVL Tree cannot have duplicate values.");

        if (comparison < 0) current.setLeftChild(insert(current.getLeftChild(), value));
        else current.setRightChild(insert(current.getRightChild(), value));

        setHeight(current);

        return balance(current);
    }

    private void setHeight(AVLNode<T> node) {
        node.setHeight(Math.max(height(node.getRightChild()),
                height(node.getLeftChild())) + 1);
    }

    private int height(AVLNode<T> node) {
        return (node == null) ? -1 : node.getHeight();
    }

    private AVLNode<T> balance(AVLNode<T> node) {
        if (isRightHeavy(node)) {
            if (balancedFactor(node.getRightChild()) > 0) node.setRightChild(rightRotation(node.getRightChild()));
            return leftRotation(node);
        } else if (isLeftHeavy(node)) {
            if (balancedFactor(node.getLeftChild()) < 0) node.setLeftChild(leftRotation(node.getLeftChild()));
            return rightRotation(node);
        }

        return node;
    }

    private int balancedFactor(AVLNode<T> node) {
        return (node == null) ? 0 : height(node.getLeftChild()) - height(node.getRightChild());
    }

    private boolean isRightHeavy(AVLNode<T> node) {
        return balancedFactor(node) < -1;
    }

    private boolean isLeftHeavy(AVLNode<T> node) {
        return balancedFactor(node) > 1;
    }

    private AVLNode<T> leftRotation(AVLNode<T> root) {
        AVLNode<T> newRoot = root.getRightChild();

        root.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(root.getLeftChild());
        newRoot.setLeftChild(root);

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private AVLNode<T> rightRotation(AVLNode<T> root) {
        AVLNode<T> newRoot = root.getLeftChild();

        root.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(root);

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public String representTree() {
        StringBuilder stringBuilder = new StringBuilder();
        buildTreeString(root, 0, stringBuilder);

        return stringBuilder.toString();
    }

    private void buildTreeString(AVLNode<T> node, int currentIndentation, StringBuilder stringBuilder) {
        if (node == null || node.getValue() == null) return;

        int childIndentation = currentIndentation + 5;

        buildTreeString(node.getRightChild(), childIndentation, stringBuilder);

        stringBuilder.append("\n");
        stringBuilder.append(" ".repeat(Math.max(0, currentIndentation)));
        stringBuilder.append(node.getValue());

        buildTreeString(node.getLeftChild(), childIndentation, stringBuilder);
    }
}