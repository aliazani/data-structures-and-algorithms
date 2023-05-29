package io.github.aliazani.nonlinear.trie.using_arrays;

import io.github.aliazani.nonlinear.trie.MyTrie;
import io.github.aliazani.nonlinear.trie.MyTrieNode;

class TrieUsingArray<T extends Comparable<T>> implements MyTrie<T> {
    private final MyTrieNode<T> root;

    public TrieUsingArray(int sizeOfChildren) {
        root = new TrieNodeUsingArray<>(null, sizeOfChildren);
    }

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

    public String toString() {
        return root.toString()
                .replaceFirst("null", "root")
                .replaceAll(", null|null, |^null$", "");
    }
}