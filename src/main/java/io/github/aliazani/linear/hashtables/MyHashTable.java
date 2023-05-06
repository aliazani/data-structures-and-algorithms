package io.github.aliazani.linear.hashtables;

public interface MyHashTable<K extends Comparable<K>, V extends Comparable<V>> {
    void put(K key, V value);

    V get(K key);

    void remove(K key);

    int size();

    boolean isEmpty();
}
