package io.github.aliazani.nonlinear.trie.using_arrays;

import io.github.aliazani.nonlinear.trie.MyTrieNode;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
class TrieNodeUsingArray<T extends Comparable<T>> implements MyTrieNode<T> {
    private final T value;
    private final TrieNodeUsingArray<T>[] children;
    private final int sizeOfChildren;
    private boolean isEndOfWord;

    public TrieNodeUsingArray(T value, int sizeOfChildren) {
        this.value = value;
        this.sizeOfChildren = sizeOfChildren;
        children = (TrieNodeUsingArray<T>[]) new TrieNodeUsingArray[this.sizeOfChildren];
    }

    @Override
    public void addChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] == null) children[index] = new TrieNodeUsingArray<>(item, sizeOfChildren);
    }

    @Override
    public void removeChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] != null && children[index].getValue().equals(item)) children[index] = null;
    }

    @Override
    public MyTrieNode<T> getChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] != null && children[index].getValue().equals(item)) return children[index];

        return null;
    }

    @Override
    public boolean hasChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        return children[index] != null && children[index].getValue().equals(item);
    }

    @Override
    public boolean hasChildren() {
        return Arrays.stream(children).anyMatch(Objects::nonNull);
    }

    @Override
    public String toString() {
        return MessageFormat.format("(value={0}, children={1})", value, Arrays.toString(children));
    }
}