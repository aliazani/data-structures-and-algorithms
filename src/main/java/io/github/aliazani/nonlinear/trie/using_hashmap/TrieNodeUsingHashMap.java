package io.github.aliazani.nonlinear.trie.using_hashmap;

import io.github.aliazani.nonlinear.trie.MyTrieNode;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.HashMap;

@Getter
@Setter
public class TrieNodeUsingHashMap<T extends Comparable<T>> implements MyTrieNode<T> {
    private final HashMap<T, TrieNodeUsingHashMap<T>> children = new HashMap<>();
    private final T value;
    private boolean isEndOfWord;

    public TrieNodeUsingHashMap(T value) {
        this.value = value;
    }

    @Override
    public void addChild(T item) {
        children.put(item, new TrieNodeUsingHashMap<>(item));
    }

    @Override
    public void removeChild(T item) {
        children.remove(item);
    }

    @Override
    public MyTrieNode<T> getChild(T item) {
        return children.get(item);
    }

    @Override
    public MyTrieNode<T>[] getChildren() {
        return children.values().toArray(new MyTrieNode[0]);
    }

    @Override
    public boolean hasChild(T item) {
        return children.containsKey(item);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public String toString() {
        return MessageFormat.format("(value={0}, children={1})", value, children);
    }
}