package io.github.aliazani.nonlinear.avl_tree;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class AVLNode<T> {
    private final T value;
    private int height;
    private AVLNode<T> rightChild;
    private AVLNode<T> leftChild;

    public AVLNode(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(value: {0}, [rightChild: {1}], [leftChild: {2}])",
                value, rightChild, leftChild);
    }
}