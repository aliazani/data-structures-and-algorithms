package io.github.aliazani.nonlinear.avl_tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.MessageFormat;

@Getter
@Setter
public class AVLNode<T> {
    private final T value;
    private AVLNode<T> parent;
    private int height;
    private AVLNode<T> rightChild;
    private AVLNode<T> leftChild;

    public AVLNode(T value, AVLNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return MessageFormat.format("(value: {0} [rightChild: {1}], [leftChild: {2}])",
                value, rightChild, leftChild);
    }
}
