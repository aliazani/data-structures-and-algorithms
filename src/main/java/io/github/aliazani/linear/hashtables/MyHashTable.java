package io.github.aliazani.linear.hashtables;

/**
 * Interface representing a hash table data structure.
 *
 * @param <K> the type of keys stored in the hash table
 * @param <V> the type of values stored in the hash table
 */
public interface MyHashTable<K extends Comparable<K>, V extends Comparable<V>> {
    /**
     * Inserts a key-value pair into the hash table.
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Retrieves the value associated with the given key from the hash table.
     *
     * @param key the key
     * @return the value associated with the key
     * @throws java.util.NoSuchElementException if the key is not found in the hash table
     */
    V get(K key);

    /**
     * Removes the key-value pair with the given key from the hash table.
     *
     * @param key the key
     * @throws java.util.NoSuchElementException if the key is not found in the hash table
     */
    void remove(K key);

    /**
     * Returns the number of key-value pairs stored in the hash table.
     *
     * @return the number of key-value pairs
     */
    int size();

    /**
     * Checks if the hash table is empty.
     *
     * @return {@code true} if the hash table is empty, {@code false} otherwise
     */
    boolean isEmpty();
}
