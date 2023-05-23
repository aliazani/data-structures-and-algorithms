package io.github.aliazani.nonlinear.trie.using_arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieUsingArrayTest {
    private TrieUsingArray<Character> trie;

    @BeforeEach
    void setUp() {
        trie = new TrieUsingArray<>(26);
    }

    @Test
    @DisplayName("insert - " +
            "When inserting a single item - " +
            "Should insert the item in the trie")
    void insert_insertSingleItem_insertItemInTrie() {
        trie.insert(new Character[]{'a'});

        assertEquals("(value=root, children=[(value=a, children=[null])])", trie.toString());
    }

    @Test
    @DisplayName("insert - " +
            "When inserting multiple items - " +
            "Should insert all items in the trie")
    void insert_insertMultipleItems_insertAllItemsInTrie() {
        trie.insert(new Character[]{'b', 'a', 'r'});
        trie.insert(new Character[]{'c', 'a', 'r'});
        trie.insert(new Character[]{'c', 'o', 'r', 'e'});

        assertEquals("(value=root," +
                " children=[(value=b, children=[(value=a, children=[(value=r, children=[null])])])," +
                " (value=c, children=[(value=o, children=[(value=r, children=[(value=e, children=[null])])]), " +
                "(value=a, children=[(value=r, children=[null])])])])", trie.toString());
    }

    @Test
    @DisplayName("insert - " +
            "When inserting null - " +
            "Should throw IllegalArgumentException")
    void insert_insertNull_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> trie.insert(null));
    }

    @Test
    @DisplayName("insert - " +
            "When inserting items with null values - " +
            "Should throw IllegalStateException")
    void insert_insertItemsWithNullValues_throwsIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> trie.insert(new Character[]{'a', null, 'v'}));
    }

    @Test
    @DisplayName("toString - " +
            "When the trie has no children - " +
            "Should return string representation without children")
    void toString_trieHasNoChildren_returnsStringWithoutChildren() {
        assertEquals("(value=root, children=[null])", trie.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the trie has children - " +
            "Should return string representation with children")
    void toString_trieHasChildren_returnsStringWithChildren() {
        trie.insert(new Character[]{'b', 'a', 't'});

        assertEquals("(value=root, " +
                "children=[(value=b, children=[(value=a, children=[(value=t, children=[null])])])])", trie.toString());
    }
}