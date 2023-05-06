package io.github.aliazani.linear.hashtables.chaining;

import io.github.aliazani.linear.hashtables.Entry;
import io.github.aliazani.linear.hashtables.MyHashTable;

import java.util.*;

/**
 * A hash table implementation using chaining to handle collisions.
 *
 * @param <K> the type of keys stored in the hash table, must implement Comparable interface.
 * @param <V> the type of values stored in the hash table, must implement Comparable interface.
 */
public class MyHashTableChaining<K extends Comparable<K>, V extends Comparable<V>> implements MyHashTable<K, V> {
    private final LinkedList<Entry<K, V>>[] entries;
    private int size;

    /**
     * Constructs a new hash table with the specified size.
     *
     * @param size the size of the hash table.
     */
    public MyHashTableChaining(int size) {
        entries = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
    }

    /**
     * Adds a new entry to the hash table with the specified key and value.
     *
     * @param key   the key of the entry.
     * @param value the value of the entry.
     */
    @Override
    public void put(K key, V value) {
        Entry<K, V> entry = getEntry(key);

        if (entry != null) entry.setValue(value);
        else {
            LinkedList<Entry<K, V>> slot = getOrCreateSlot(key);
            slot.addLast(new Entry<>(key, value));

            size++;
        }
    }

    private Entry<K, V> getEntry(K key) {
        LinkedList<Entry<K, V>> slot = getSlot(key);

        if (slot != null)
            for (Entry<K, V> entry : slot)
                if (Objects.equals(entry.getKey(), key)) return entry;

        return null;
    }

    private LinkedList<Entry<K, V>> getSlot(K key) {
        return entries[hash(key)];
    }

    private LinkedList<Entry<K, V>> getOrCreateSlot(K key) {
        int index = hash(key);

        if (entries[index] == null) entries[index] = new LinkedList<>();

        return entries[index];
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % entries.length);
    }

    /**
     * Returns the value associated with the specified key in the hash table.
     *
     * @param key the key of the entry to get the value for.
     * @return the value associated with the specified key.
     * @throws NoSuchElementException if there is no entry with the specified key in the hash table.
     */
    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) throw new NoSuchElementException();

        return entry.getValue();
    }

    /**
     * Removes the entry with the specified key from the hash table.
     *
     * @param key the key of the entry to remove.
     * @throws NoSuchElementException if there is no entry with the specified key in the hash table.
     */
    @Override
    public void remove(K key) {
        LinkedList<Entry<K, V>> slot = getSlot(key);

        if (slot == null || slot.isEmpty()) throw new NoSuchElementException();
        if (slot.removeIf(entry -> Objects.equals(entry.getKey(), key))) size--;
        else throw new NoSuchElementException();
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the number of entries in the hash table.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the hash table is empty or not.
     *
     * @return true if the hash table is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the hash table.
     *
     * @return a string representation of the hash table.
     */
    @Override
    public String toString() {
        return Arrays.toString(entries);
    }
}