package io.github.aliazani.nonlinear.binary_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class Node<T> {
    private T value;
    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(Value: {0}, RightChild: {1}, LeftChild: {2})",
                value, rightChild, leftChild);
    }
}