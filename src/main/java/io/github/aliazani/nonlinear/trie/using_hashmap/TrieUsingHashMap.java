package io.github.aliazani.nonlinear.trie.using_hashmap;

import io.github.aliazani.nonlinear.trie.MyTrie;
import io.github.aliazani.nonlinear.trie.MyTrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

public class TrieUsingHashMap<T extends Comparable<T>> implements MyTrie<T> {
    private final MyTrieNode<T> root = new TrieNodeUsingHashMap<>(null);

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

    public boolean contains(T[] array) {
        if (array == null) return false;

        MyTrieNode<T> current = root;

        for (T item : array) {
            if (!current.hasChild(item)) return false;
            current = current.getChild(item);
        }
        return current.isEndOfWord();
    }

    public boolean contains2(T[] array) {
        if (array == null) return false;
        return recursiveContains(array, root, 0);
    }

    private boolean recursiveContains(T[] array, MyTrieNode<T> root, int index) {
        if (index == array.length) return root.isEndOfWord();

        if (root == null) return false;

        T item = array[index];
        MyTrieNode<T> child = root.getChild(item);
        if (child == null) return false;
        return recursiveContains(array, child, index + 1);
    }

    public String preOrderTraverse() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderTraverse(root, strBuilder);
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void preOrderTraverse(MyTrieNode<T> root, StringBuilder stringBuilder) {
        stringBuilder.append(root.getValue()).append(", ");
        for (MyTrieNode<T> child : root.getChildren())
            preOrderTraverse(child, stringBuilder);
    }

    public String postOrderTraverse() {
        StringBuilder strBuilder = new StringBuilder();
        postOrderTraverse(root, strBuilder);
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void postOrderTraverse(MyTrieNode<T> root, StringBuilder strBuilder) {
        for (MyTrieNode<T> child : root.getChildren())
            postOrderTraverse(child, strBuilder);

        strBuilder.append(root.getValue()).append(", ");
    }

    public void remove(T[] array) {
        if (array == null) throw new IllegalArgumentException();
        remove(root, array, 0);
    }

    private void remove(MyTrieNode<T> root, T[] array, int index) {
        if (index == array.length) {
            root.setEndOfWord(false);
            return;
        }

        T item = array[index];
        MyTrieNode<T> child = root.getChild(item);
        if (child == null) return;

        remove(child, array, index + 1);
        if (!child.hasChildren() && !child.isEndOfWord()) root.removeChild(item);
    }

    public List<T[]> findWords(T[] prefix) {
        if (prefix == null) return Collections.emptyList();

        MyTrieNode<T> lastNode = findLastNodeOf(prefix);
        List<T[]> commonItems = new ArrayList<>();
        findWords(lastNode, prefix, commonItems);

        return commonItems;
    }

    private MyTrieNode<T> findLastNodeOf(T[] prefix) {
        MyTrieNode<T> current = root;
        for (T item : prefix) {
            MyTrieNode<T> child = current.getChild(item);
            if (child == null) return null;
            current = child;
        }
        return current;
    }

    private void findWords(MyTrieNode<T> root, T[] prefix, List<T[]> items) {
        if (root == null) return;

        if (root.isEndOfWord()) items.add(prefix);
        for (MyTrieNode<T> child : root.getChildren()) {
            T[] newPrefix = Arrays.copyOf(prefix, prefix.length + 1);
            newPrefix[newPrefix.length - 1] = child.getValue();
            findWords(child, newPrefix, items);
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }
}