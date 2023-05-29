package io.github.aliazani.nonlinear.trie.using_arrays;

import io.github.aliazani.nonlinear.trie.MyTrieNode;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a trie node using an array to store children.
 *
 * @param <T> the type of value stored in the trie node
 */
@Getter
@Setter
class TrieNodeUsingArray<T extends Comparable<T>> implements MyTrieNode<T> {
    private final T value;
    private final TrieNodeUsingArray<T>[] children;
    private final int sizeOfChildren;
    private boolean isEndOfWord;

    /**
     * Constructs a trie node with the specified value and size of children.
     *
     * @param value          the value stored in the trie node
     * @param sizeOfChildren the size of the children array
     */
    public TrieNodeUsingArray(T value, int sizeOfChildren) {
        this.value = value;
        this.sizeOfChildren = sizeOfChildren;
        children = (TrieNodeUsingArray<T>[]) new TrieNodeUsingArray[this.sizeOfChildren];
    }

    /**
     * Adds a child node with the specified item.
     *
     * @param item the item to add as a child
     */
    @Override
    public void addChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] == null) children[index] = new TrieNodeUsingArray<>(item, sizeOfChildren);
    }

    /**
     * Removes the child node with the specified item.
     *
     * @param item the item to remove from the children
     */
    @Override
    public void removeChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] != null && children[index].getValue().equals(item)) children[index] = null;
    }

    /**
     * Retrieves the child node with the specified item.
     *
     * @param item the item to retrieve from the children
     * @return the child node with the specified item, or null if not found
     */
    @Override
    public MyTrieNode<T> getChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        if (children[index] != null && children[index].getValue().equals(item)) return children[index];

        return null;
    }

    /**
     * Checks if the trie node has a child with the specified item.
     *
     * @param item the item to check for in the children
     * @return true if the trie node has a child with the specified item, false otherwise
     */
    @Override
    public boolean hasChild(T item) {
        int index = Math.abs(item.hashCode() % sizeOfChildren);
        return children[index] != null && children[index].getValue().equals(item);
    }

    /**
     * Checks if the trie node has any children.
     *
     * @return true if the trie node has children, false otherwise
     */
    @Override
    public boolean hasChildren() {
        return Arrays.stream(children).anyMatch(Objects::nonNull);
    }

    /**
     * Returns a string representation of the trie node.
     *
     * @return a string representation of the trie node
     */
    @Override
    public String toString() {
        return MessageFormat.format("(value={0}, children={1})", value, Arrays.toString(children));
    }
}