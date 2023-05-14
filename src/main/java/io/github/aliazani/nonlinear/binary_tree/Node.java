package io.github.aliazani.nonlinear.binary_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(Value: {0}, RightChild: {1}, LeftChild: {2})",
                value, rightChild, leftChild);
    }
}