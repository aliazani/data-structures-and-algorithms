package io.github.aliazani.nonlinear.trie.using_hashmap;

import io.github.aliazani.nonlinear.trie.MyTrieNode;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Represents a trie node using a HashMap to store children.
 *
 * @param <T> the type of value stored in the trie node
 */
@Getter
@Setter
public class TrieNodeUsingHashMap<T extends Comparable<T>> implements MyTrieNode<T> {
    private final HashMap<T, TrieNodeUsingHashMap<T>> children = new HashMap<>();
    private final T value;
    private boolean isEndOfWord;

    /**
     * Constructs a trie node with the specified value.
     *
     * @param value the value stored in the trie node
     */
    public TrieNodeUsingHashMap(T value) {
        this.value = value;
    }

    /**
     * Adds a child node with the specified item.
     *
     * @param item the item to add as a child
     */
    @Override
    public void addChild(T item) {
        children.put(item, new TrieNodeUsingHashMap<>(item));
    }

    /**
     * Removes the child node with the specified item.
     *
     * @param item the item to remove from the children
     */
    @Override
    public void removeChild(T item) {
        children.remove(item);
    }

    /**
     * Retrieves the child node with the specified item.
     *
     * @param item the item to retrieve from the children
     * @return the child node with the specified item, or null if not found
     */
    @Override
    public MyTrieNode<T> getChild(T item) {
        return children.get(item);
    }

    /**
     * Retrieves an array of children nodes.
     *
     * @return an array of children nodes
     */
    @Override
    public MyTrieNode<T>[] getChildren() {
        return children.values().toArray(new MyTrieNode[0]);
    }

    /**
     * Checks if the trie node has a child with the specified item.
     *
     * @param item the item to check for in the children
     * @return true if the trie node has a child with the specified item, false otherwise
     */
    @Override
    public boolean hasChild(T item) {
        return children.containsKey(item);
    }

    /**
     * Checks if the trie node has any children.
     *
     * @return true if the trie node has children, false otherwise
     */
    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Returns a string representation of the trie node.
     *
     * @return a string representation of the trie node
     */
    @Override
    public String toString() {
        return MessageFormat.format("(value={0}, children={1})", value, children);
    }
}