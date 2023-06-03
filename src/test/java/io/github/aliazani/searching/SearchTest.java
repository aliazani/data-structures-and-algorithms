package io.github.aliazani.searching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Search")
class SearchTest {
    Search<Integer> search;

    @BeforeEach
    void setUp() {
        search = new Search<>();
    }

    @DisplayName("linearSearch - " +
            "When item exists in the array - " +
            "Should return the correct index")
    @Test
    void linearSearch_itemExistsInArray_returnCorrectIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int item = 3;

        int result = search.linearSearch(array, item);

        assertEquals(2, result);
    }

    @DisplayName("linearSearch - " +
            "When item does not exist in the array - " +
            "Should return -1")
    @Test
    void linearSearch_itemDoesNotExistInArray_returnNegativeOne() {
        Integer[] array = {1, 2, 3, 4, 5};
        int item = 6;

        assertEquals(-1, search.linearSearch(array, item));
    }

    @DisplayName("linearSearch - " +
            "When the array is empty - " +
            "Should return -1")
    @Test
    void linearSearch_emptyArray_returnNegativeOne() {
        Integer[] array = {};
        int item = 5;

        assertEquals(-1, search.linearSearch(array, item));
    }

    @DisplayName("linearSearch - " +
            "When there are multiple occurrences of the item - " +
            "Should return the first matching index")
    @Test
    void linearSearch_multipleOccurrencesOfItem_returnFirstMatchingIndex() {
        Integer[] array = {1, 2, 3, 4, 5, 3};
        int item = 3;

        assertEquals(2, search.linearSearch(array, item));
    }
}