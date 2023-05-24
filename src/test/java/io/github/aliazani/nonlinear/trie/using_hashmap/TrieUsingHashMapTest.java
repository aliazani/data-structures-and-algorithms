package io.github.aliazani.nonlinear.trie.using_hashmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieUsingHashMapTest {
    private TrieUsingHashMap<Character> trie;

    @BeforeEach
    void setUp() {
        trie = new TrieUsingHashMap<>();
    }

    @Test
    @DisplayName("insert - " +
            "When inserting a single item - " +
            "Should insert the item in the trie")
    void insert_insertSingleItem_insertItemInTrie() {
        trie.insert(new Character[]{'a', 'p', 'p'});

        assertEquals("(value=null, " +
                "children={a=(value=a, " +
                "children={p=(value=p, " +
                "children={p=(value=p, children={})})})})", trie.toString());
    }

    @Test
    @DisplayName("insert - " +
            "When inserting multiple items - " +
            "Should insert all items in the trie")
    void insert_insertMultipleItems_insertAllItemsInTrie() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});

        System.out.println(trie);
        assertEquals("(value=null," +
                        " children={a=(value=a," +
                        " children={p=(value=p," +
                        " children={p=(value=p," +
                        " children={l=(value=l," +
                        " children={e=(value=e," +
                        " children={})})})}), " +
                        "c=(value=c," +
                        " children={i=(value=i," +
                        " children={d=(value=d, children={})})})})})"
                , trie.toString());
    }

    @Test
    @DisplayName("insert - " +
            "When inserting duplicate items - " +
            "Should not add duplicate items")
    void insert_insertDuplicateItems_doNotAddDuplicateItems() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p'});

        assertEquals("(value=null, " +
                "children={a=(value=a, " +
                "children={p=(value=p, " +
                "children={p=(value=p, children={})})})})", trie.toString());
    }

    @Test
    @DisplayName("insert - " +
            "When inserting null items - " +
            "Should throw IllegalArgumentException")
    void insert_insertNullItems_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> trie.insert(null));
    }

    @Test
    @DisplayName("insert - " +
            "When inserting items with null values - " +
            "Should throw IllegalStateException")
    void insert_insertItemsWithNullValues_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> trie.insert(new Character[]{'c', null, 't'}));
    }

    @Test
    @DisplayName("contains - " +
            "When checking if an array is contained in the trie - " +
            "Should return true if it exists")
    void contains_checkExistingArray_returnsTrue() {
        trie.insert(new Character[]{'a', 'p', 'p'});

        assertTrue(trie.contains(new Character[]{'a', 'p', 'p'}));
    }

    @Test
    @DisplayName("contains - " +
            "When checking if an array is contained in the trie - " +
            "Should return false if it does not exist")
    void contains_checkNonExistingArray_returnsFalse() {
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});

        assertFalse(trie.contains(new Character[]{'a', 'p', 'p'}));
    }

    @Test
    @DisplayName("contains - " +
            "When checking if a null array is contained in the trie - " +
            "Should return false")
    void contains_checkNullArray_returnsFalse() {
        assertFalse(trie.contains(null));
    }

    @Test
    @DisplayName("contains2 - " +
            "When checking if an array is contained in the trie - " +
            "Should return true if it exists")
    void contains2_checkExistingArray_returnsTrue() {
        trie.insert(new Character[]{'a', 'p', 'p'});

        assertTrue(trie.contains2(new Character[]{'a', 'p', 'p'}));
    }

    @Test
    @DisplayName("contains2 - " +
            "When checking if an array is contained in the trie - " +
            "Should return false if it does not exist")
    void contains2_checkNonExistingArray_returnsFalse() {
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});

        assertFalse(trie.contains2(new Character[]{'a', 'p', 'p'}));
    }

    @Test
    @DisplayName("contains2 - " +
            "When checking if a null array is contained in the trie - " +
            "Should return false")
    void contains2_checkNullArray_returnsFalse() {
        assertFalse(trie.contains2(null));
    }

    @Test
    @DisplayName("preOrderTraverse - " +
            "When the trie is empty - " +
            "Should return an empty string")
    void preOrderTraverse_emptyTrie_returnEmptyString() {
        assertEquals("null", trie.preOrderTraverse());
    }

    @Test
    @DisplayName("preOrderTraverse - " +
            "When the trie has a single item - " +
            "Should return the item in pre-order traversal")
    void preOrderTraverse_singleItemTrie_returnItem() {
        trie.insert(new Character[]{'a'});
        assertEquals("null, a", trie.preOrderTraverse());
    }

    @Test
    @DisplayName("preOrderTraverse - " +
            "When the trie has multiple items - " +
            "Should return items in pre-order traversal")
    void preOrderTraverse_multipleItemsTrie_returnItemsInPreOrderTraversal() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});
        assertEquals("null, a, p, p, l, e, c, i, d", trie.preOrderTraverse());
    }

    @Test
    @DisplayName("preOrderTraverse - " +
            "When the trie has a complex structure - " +
            "Should return items in pre-order traversal")
    void preOrderTraverse_complexStructureTrie_returnItemsInPreOrderTraversal() {
        trie.insert(new Character[]{'a', 'b', 'c'});
        trie.insert(new Character[]{'a', 'b', 'd'});
        trie.insert(new Character[]{'a', 'x', 'y'});
        trie.insert(new Character[]{'a', 'x', 'z'});

        assertEquals("null, a, b, c, d, x, y, z", trie.preOrderTraverse());
    }

    @Test
    @DisplayName("postOrderTraverse - " +
            "When the trie is empty - " +
            "Should not throw an exception")
    void postOrderTraverse_emptyTrie_noExceptionThrown() {
        assertDoesNotThrow(() -> trie.postOrderTraverse());
        assertEquals("null", trie.preOrderTraverse());
    }

    @Test
    @DisplayName("postOrderTraverse - " +
            "When the trie has a single item - " +
            "Should return the item")
    void postOrderTraverse_singleItemTrie_returnItem() {
        trie.insert(new Character[]{'a'});
        assertEquals("a, null", trie.postOrderTraverse());
    }

    @Test
    @DisplayName("postOrderTraverse - " +
            "When the trie has multiple items - " +
            "Should return items in post-order traversal")
    void postOrderTraverse_multipleItemsTrie_returnItemsInPostOrderTraversal() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});
        assertEquals("e, l, p, p, d, i, c, a, null", trie.postOrderTraverse());
    }

    @Test
    @DisplayName("postOrderTraverse - " +
            "When the trie has a complex structure - " +
            "Should return items in post-order traversal")
    void postOrderTraverse_complexStructureTrie_returnItemsInPostOrderTraversal() {
        trie.insert(new Character[]{'a', 'b', 'c'});
        trie.insert(new Character[]{'a', 'b', 'd'});
        trie.insert(new Character[]{'a', 'x', 'y'});
        trie.insert(new Character[]{'a', 'x', 'z'});
        assertEquals("c, d, b, y, z, x, a, null", trie.postOrderTraverse());
    }

    @Test
    @DisplayName("remove - " +
            "When the array is null - " +
            "Should throw IllegalArgumentException")
    void remove_arrayIsNull_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> trie.remove(null));
    }

    @Test
    @DisplayName("remove - " +
            "When the array is empty - " +
            "Should not throw an exception")
    void remove_emptyArray_noExceptionThrown() {
        assertDoesNotThrow(() -> trie.remove(new Character[]{}));
        assertEquals("(value=null, children={})", trie.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When the trie is empty - " +
            "Should not modify the trie")
    void remove_emptyTrie_noModification() {
        trie.remove(new Character[]{'a', 'p', 'p'});
        assertEquals("(value=null, children={})", trie.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing a single item from the trie - " +
            "Should remove the item from the trie")
    void remove_singleItemTrie_removeItem() {
        trie.insert(new Character[]{'a'});
        trie.remove(new Character[]{'a'});
        assertEquals("(value=null, children={})", trie.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing multiple items from the trie - " +
            "Should remove all items from the trie")
    void remove_multipleItemsTrie_removeItems() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});

        trie.remove(new Character[]{'a', 'p', 'p', 'l', 'e'});
        assertEquals("(value=null," +
                " children={a=(value=a, " +
                "children={p=(value=p, " +
                "children={p=(value=p, children={})}), " +
                "c=(value=c, " +
                "children={i=(value=i, " +
                "children={d=(value=d, children={})})})})})", trie.toString());
        trie.remove(new Character[]{'a', 'p', 'p'});
        assertEquals("(value=null," +
                " children={a=(value=a, " +
                "children={c=(value=c, " +
                "children={i=(value=i, " +
                "children={d=(value=d, children={})})})})})", trie.toString());
        trie.remove(new Character[]{'a', 'c', 'i', 'd'});
        assertEquals("(value=null, children={})", trie.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing non-existing items from the trie - " +
            "Should not modify the trie")
    void remove_nonExistingItems_noModification() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});

        trie.remove(new Character[]{'a', 'b', 'c'});
        trie.remove(new Character[]{'x', 'y', 'z'});

        assertEquals("(value=null, " +
                "children={a=(value=a, " +
                "children={p=(value=p, " +
                "children={p=(value=p, " +
                "children={l=(value=l, " +
                "children={e=(value=e, children={})})})})})})", trie.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing a prefix of another word in the trie - " +
            "Should remove the prefix word but keep the other word intact")
    void remove_prefixWord_removePrefixWord() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});

        trie.remove(new Character[]{'a', 'p', 'p'});

        assertEquals("(value=null," +
                        " children={a=(value=a, " +
                        "children={p=(value=p, " +
                        "children={p=(value=p, " +
                        "children={l=(value=l, " +
                        "children={e=(value=e, children={})})})})})})"
                , trie.toString());
    }

    @Test
    @DisplayName("findWords - " +
            "When prefix is null - " +
            "Should return an empty list")
    void findWords_nullPrefix_returnEmptyList() {
        assertEquals(Collections.emptyList(), trie.findWords(null));
    }

    @Test
    @DisplayName("findWords - " +
            "When prefix does not exist in the trie - " +
            "Should return an empty list")
    void findWords_nonExistingPrefix_returnEmptyList() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});

        assertEquals(Collections.emptyList(), trie.findWords(new Character[]{'c', 'a', 't'}));
    }

    @Test
    @DisplayName("findWords - " +
            "When prefix matches a single word in the trie - " +
            "Should return a list containing the matching word")
    void findWords_matchingPrefix_returnMatchingWord() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'b', 'a', 't'});
        List<String> result = trie.findWords(new Character[]{'b', 'a'}).stream()
                .map(Arrays::toString)
                .toList();
        assertEquals(List.of("[b, a, t]")
                , result);
    }

    @Test
    @DisplayName("findWords - " +
            "When prefix matches multiple words in the trie - " +
            "Should return a list containing all matching words")
    void findWords_multipleMatchingPrefix_returnMatchingWords() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});

        List<String> result = trie.findWords(new Character[]{'a'}).stream()
                .map(Arrays::toString)
                .toList();
        assertEquals(List.of("[a, p, p]", "[a, p, p, l, e]", "[a, c, i, d]"), result);
    }

    @Test
    @DisplayName("findWords - " +
            "When prefix matches the root node of the trie - " +
            "Should return all words in the trie")
    void findWords_prefixMatchesRoot_returnAllWords() {
        trie.insert(new Character[]{'a', 'p', 'p'});
        trie.insert(new Character[]{'a', 'p', 'p', 'l', 'e'});
        trie.insert(new Character[]{'a', 'c', 'i', 'd'});
        trie.insert(new Character[]{'e', 'g', 'g'});

        List<String> result = trie.findWords(new Character[0]).stream()
                .map(Arrays::toString)
                .toList();

        assertEquals(List.of("[a, p, p]", "[a, p, p, l, e]", "[a, c, i, d]", "[e, g, g]"), result);
    }
}