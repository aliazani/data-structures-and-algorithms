package io.github.aliazani.nonlinear.trie.using_hashmap;

import io.github.aliazani.nonlinear.trie.MyTrie;
import io.github.aliazani.nonlinear.trie.MyTrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

/**
 * Represents a trie data structure implemented using a HashMap for storing children.
 *
 * @param <T> the type of values stored in the trie
 */
public class TrieUsingHashMap<T extends Comparable<T>> implements MyTrie<T> {
    private final MyTrieNode<T> root = new TrieNodeUsingHashMap<>(null);

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
     * Checks if the trie contains the given array of items.
     *
     * @param array the array of items to check for in the trie
     * @return true if the trie contains the array of items, false otherwise
     */
    public boolean contains(T[] array) {
        if (array == null) return false;

        MyTrieNode<T> current = root;

        for (T item : array) {
            if (!current.hasChild(item)) return false;
            current = current.getChild(item);
        }
        return current.isEndOfWord();
    }

    /**
     * Checks if the trie contains the given array of items using recursive traversal.
     *
     * @param array the array of items to check for in the trie
     * @return true if the trie contains the array of items, false otherwise
     */
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

    /**
     * Performs a pre-order traversal of the trie and returns a string representation of the nodes.
     *
     * @return a string representation of the trie nodes in pre-order traversal
     */
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

    /**
     * Performs a post-order traversal of the trie and returns a string representation of the nodes.
     *
     * @return a string representation of the trie nodes in post-order traversal
     */
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

    /**
     * Removes the given array of items from the trie.
     *
     * @param array the array of items to remove from the trie
     * @throws IllegalArgumentException if the array is null
     */
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

    /**
     * Finds all the words in the trie that have the given prefix.
     *
     * @param prefix the prefix to search for in the trie
     * @return a list of arrays containing the words with the given prefix
     */
    public List<T[]> findWords(T[] prefix) {
        if (prefix == null) return Collections.emptyList();

        MyTrieNode<T> lastNode = findLastNodeOf(prefix);
        List<T[]> commonItems = new ArrayList<>();
        findWords(lastNode, prefix, commonItems);

        return commonItems;
    }

    private MyTrieNode<T> findLastNodeOf(T[] prefix) {
        if (prefix == null) return null;
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

    /**
     * Counts the number of words in the trie.
     *
     * @return the number of words in the trie
     */
    public int countWords() {
        return countWords(root);
    }

    private int countWords(MyTrieNode<T> root) {
        int total = 0;
        if (root.isEndOfWord()) total++;

        for (MyTrieNode<T> child : root.getChildren()) total += countWords(child);
        return total;
    }

    /**
     * Finds the longest common prefix among the given arrays of items.
     *
     * @param array the array of arrays of items to find the longest common prefix from
     * @return a list of items representing the longest common prefix
     */
    public List<T> longestCommonPrefix(T[][] array) {
        if (array == null) return null;

        TrieUsingHashMap<T> trie = new TrieUsingHashMap<>();
        for (T[] item : array) trie.insert(item);

        int maxPrefixLength = (getShortest(array) == null) ? 0 : getShortest(array).length;
        List<T> prefix = new ArrayList<>();
        MyTrieNode<T> currentNode = trie.root;

        while (prefix.size() < maxPrefixLength) {
            MyTrieNode<T>[] children = currentNode.getChildren();
            if (children.length != 1) break;
            currentNode = children[0];
            prefix.add(currentNode.getValue());
        }

        return prefix;
    }

    private T[] getShortest(T[][] array) {
        if (array == null || array.length == 0) return null;

        T[] shortest = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i].length < shortest.length) shortest = array[i];

        return shortest;
    }

    /**
     * Returns a string representation of the trie.
     *
     * @return a string representation of the trie
     */
    @Override
    public String toString() {
        return root.toString();
    }
}