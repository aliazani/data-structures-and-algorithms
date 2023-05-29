package io.github.aliazani.nonlinear.binary_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * Represents a node in a binary tree.
 *
 * @param <T> the type of value stored in the node
 */
@Getter
@Setter
class BinaryTreeNode<T> {
    private T value;
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    /**
     * Constructs a binary tree node with the specified value and parent node.
     *
     * @param value  the value stored in the node
     * @param parent the parent node of the current node
     */
    public BinaryTreeNode(T value, BinaryTreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    /**
     * Returns a string representation of the binary tree node.
     *
     * @return a string representation of the binary tree node
     */
    @Override
    public String toString() {
        return MessageFormat.format("(Value: {0}, RightChild: {1}, LeftChild: {2})",
                value, rightChild, leftChild);
    }
}