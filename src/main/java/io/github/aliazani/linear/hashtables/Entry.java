package io.github.aliazani.linear.hashtables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * An {@code Entry} represents a key-value pair in a hash table.
 *
 * @param <K> the type of keys maintained by this entry
 * @param <V> the type of mapped values
 */
@AllArgsConstructor
@Setter
@Getter
public class Entry<K extends Comparable<K>, V extends Comparable<V>> {
    private final K key;
    private V value;

    /**
     * Returns a string representation of this entry.
     *
     * @return a string representation of this entry
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}={1}", key, value);
    }
}