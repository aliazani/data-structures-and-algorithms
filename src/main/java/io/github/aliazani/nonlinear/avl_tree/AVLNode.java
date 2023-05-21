package io.github.aliazani.nonlinear.avl_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * Represents a node in an AVL tree.
 *
 * @param <T> the type of value stored in the node
 */
@Getter
@Setter
public class AVLNode<T> {
    private final T value;
    private int height;
    private AVLNode<T> rightChild;
    private AVLNode<T> leftChild;

    /**
     * Constructs a new AVLNode with the specified value.
     *
     * @param value the value to be stored in the node
     */
    public AVLNode(T value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the AVLNode.
     *
     * @return a string representation of the AVLNode
     */
    @Override
    public String toString() {
        return MessageFormat.format("(value: {0}, [rightChild: {1}], [leftChild: {2}])",
                value, rightChild, leftChild);
    }
}