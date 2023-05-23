package io.github.aliazani.nonlinear.trie;

public interface MyTrie<T extends Comparable<T>> {
    void insert(T[] items);
}