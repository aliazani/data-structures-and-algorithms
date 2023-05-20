package io.github.aliazani.nonlinear.binary_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class BinaryTreeNode<T> {
    private T value;
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T value, BinaryTreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(Value: {0}, RightChild: {1}, LeftChild: {2})",
                value, rightChild, leftChild);
    }
}