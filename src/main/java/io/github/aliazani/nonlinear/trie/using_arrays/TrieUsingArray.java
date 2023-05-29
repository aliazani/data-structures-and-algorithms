package io.github.aliazani.nonlinear.trie.using_arrays;

import io.github.aliazani.nonlinear.trie.MyTrie;
import io.github.aliazani.nonlinear.trie.MyTrieNode;

/**
 * Represents a trie data structure implemented using an array for storing children.
 *
 * @param <T> the type of values stored in the trie
 */
class TrieUsingArray<T extends Comparable<T>> implements MyTrie<T> {
    private final MyTrieNode<T> root;

    /**
     * Constructs a trie using an array with the specified size of children.
     *
     * @param sizeOfChildren the size of the children array in each trie node
     */
    public TrieUsingArray(int sizeOfChildren) {
        root = new TrieNodeUsingArray<>(null, sizeOfChildren);
    }

    /**
     * Inserts an array of items into the trie.
     *
     * @param items the items to insert into the trie
     * @throws IllegalArgumentException if the items array is null
     * @throws IllegalStateException    if an item in the array is null
     */
    @Override
    public void insert(T[] items) {
        if (items == null) throw new IllegalArgumentException();

        MyTrieNode<T> current = root;
        for (T item : items) {
            if (item == null) throw new IllegalStateException();
            if (!current.hasChild(item))
                current.addChild(item);
            current = current.getChild(item);
        }

        current.setEndOfWord(true);
    }

    /**
     * Returns a string representation of the trie.
     *
     * @return a string representation of the trie
     */
    @Override
    public String toString() {
        return root.toString()
                .replaceFirst("null", "root")
                .replaceAll(", null|null, |^null$", "");
    }
}