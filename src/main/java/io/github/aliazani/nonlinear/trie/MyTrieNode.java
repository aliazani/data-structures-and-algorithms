package io.github.aliazani.nonlinear.trie;

public interface MyTrieNode<T extends Comparable<T>> {

    void addChild(T item);

    void removeChild(T item);

    MyTrieNode<T> getChild(T item);

    boolean hasChild(T item);

    boolean hasChildren();
}
