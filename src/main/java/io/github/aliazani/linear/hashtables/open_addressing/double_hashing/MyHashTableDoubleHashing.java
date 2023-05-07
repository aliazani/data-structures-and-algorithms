package io.github.aliazani.linear.hashtables.open_addressing.double_hashing;

import io.github.aliazani.linear.hashtables.Entry;
import io.github.aliazani.linear.hashtables.MyHashTable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashTableDoubleHashing<K extends Comparable<K>, V extends Comparable<V>> implements MyHashTable<K, V> {
    private final Entry<K, V>[] entries;
    private final int prime;
    private int size;

    public MyHashTableDoubleHashing(int size) {
        entries = (Entry<K, V>[]) new Entry[size];
        prime = findLargestPrime(entries.length);
    }

    @Override
    public void put(K key, V value) {
        int index = getIndexOfNonEmptyEntry(key);

        if (index >= 0) {
            entries[index].setValue(value);
            return;
        }

        if (isFull()) throw new IllegalStateException();

        entries[getIndexOfEmptyEntry(key)] = new Entry<>(key, value);
        size++;
    }

    @Override
    public V get(K key) {
        int index = getIndexOfNonEmptyEntry(key);

        if (index < 0) throw new NoSuchElementException();

        return entries[index].getValue();
    }

    @Override
    public void remove(K key) {
        int index = getIndexOfNonEmptyEntry(key);

        if (index < 0) throw new NoSuchElementException();

        entries[index] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndexOfEmptyEntry(K key) {
        for (int probeDistance = 0; probeDistance < entries.length; probeDistance++) {
            int index = getIndex(key, probeDistance);
            Entry<K, V> entry = entries[index];
            if (Objects.equals(entry, null)) return index;
        }

        return -1;
    }

    private int getIndexOfNonEmptyEntry(K key) {
        for (int probeDistance = 0; probeDistance < entries.length; probeDistance++) {
            int index = getIndex(key, probeDistance);
            Entry<K, V> entry = entries[index];
            if (entry != null && Objects.equals(entry.getKey(), key)) return index;
        }

        return -1;
    }

    private boolean isFull() {
        return size == entries.length;
    }

    private int getIndex(K key, int i) {
        int keyValue = key == null ? 0 : Integer.parseInt(String.valueOf(key));
        int hash2 = prime - (keyValue % prime);

        return (hash(key) + (i * hash2)) % entries.length;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % entries.length);
    }

    private int findLargestPrime(int n) {
        for (int i = n - 1; i >= 2; i--)
            if (isPrime(i)) return i;
        return 2;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;

        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(entries);
    }
}