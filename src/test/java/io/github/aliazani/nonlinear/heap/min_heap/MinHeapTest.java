package io.github.aliazani.nonlinear.heap.min_heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("MinHeap")
class MinHeapTest {
    private MinHeap<Integer, String> minHeap;

    @BeforeEach
    void setUp() {
        minHeap = new MinHeap<>(10);
    }

    @DisplayName("insert - " +
            "When inserting into an empty heap - " +
            "Should insert the item as the root")
    @Test
    void insert_insertIntoEmptyHeap_insertAsRoot() {
        minHeap.insert(10, "A");

        assertEquals("[(key=10, value=A)]", minHeap.toString());
    }

    @DisplayName("insert - " +
            "When inserting into a non-full heap - " +
            "Should insert the item in the correct position")
    @Test
    void insert_insertIntoNonFullHeap_insertInCorrectPosition() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.insert(0, "D");

        assertEquals("[(key=0, value=D), (key=5, value=B), (key=15, value=C), (key=10, value=A)]"
                , minHeap.toString());
    }

    @DisplayName("insert - " +
            "When inserting into a full heap - " +
            "Should throw IllegalStateException")
    @Test
    void insert_insertIntoFullHeap_throwIllegalState() {
        minHeap.insert(1, "A");
        minHeap.insert(2, "B");
        minHeap.insert(3, "C");
        minHeap.insert(4, "D");
        minHeap.insert(5, "E");
        minHeap.insert(6, "F");
        minHeap.insert(7, "G");
        minHeap.insert(8, "H");
        minHeap.insert(9, "I");
        minHeap.insert(10, "J");

        assertThrows(IllegalStateException.class, () -> minHeap.insert(11, "K"));
    }

    @DisplayName("remove - " +
            "When removing from an empty heap - " +
            "Should throw IllegalStateException")
    @Test
    void remove_removeFromEmptyHeap_throwIllegalState() {
        assertThrows(IllegalStateException.class, minHeap::remove);
    }

    @DisplayName("remove - " +
            "When removing from a heap with a single element - " +
            "Should remove the root and make the heap empty")
    @Test
    void remove_removeFromSingleElementHeap_removeRootAndMakeHeapEmpty() {
        minHeap.insert(10, "A");


        assertEquals("(key=10, value=A)", minHeap.remove().toString());
        assertEquals("[]", minHeap.toString());
    }

    @DisplayName("remove - " +
            "When removing from a heap with multiple elements and left child is smaller - " +
            "Should remove the root and rearrange the heap")
    @Test
    void remove_removeFromMultipleElementHeapAndLeftChildIsSmaller_removeRootAndRearrangeHeap() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.insert(2, "D");
        minHeap.insert(12, "E");

        assertEquals("(key=2, value=D)", minHeap.remove().toString());
        assertEquals("[(key=5, value=B), (key=10, value=A), (key=15, value=C), (key=12, value=E)]",
                minHeap.toString());
    }

    @DisplayName("remove - " +
            "When removing from a heap with multiple elements and right child is smaller - " +
            "Should remove the root and rearrange the heap")
    @Test
    void remove_removeFromMultipleElementHeapAndRightChildIsSmaller_removeRootAndRearrangeHeap() {
        minHeap.insert(10, "A");
        minHeap.insert(15, "C");
        minHeap.insert(5, "B");
        minHeap.insert(2, "D");
        minHeap.insert(12, "E");
        minHeap.insert(1, "F");

        assertEquals("(key=1, value=F)", minHeap.remove().toString());
        assertEquals("[(key=2, value=D), (key=5, value=B), (key=10, value=A)," +
                        " (key=15, value=C), (key=12, value=E)]", minHeap.toString());
    }

    @DisplayName("get - " +
            "When getting an element from an empty heap - " +
            "Should throw IllegalStateException")
    @Test
    void get_getFromEmptyHeap_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> minHeap.get(0));
    }

    @DisplayName("get - " +
            "When getting an element with an invalid index (negative index) - " +
            "Should throw IllegalArgumentException")
    @Test
    void get_getWithNegativeIndex_throwIllegalArgumentException() {
        minHeap.insert(10, "A");

        assertThrows(IllegalArgumentException.class, () -> minHeap.get(-1));
    }

    @DisplayName("get - " +
            "When getting an element with an invalid index (index greater than or equal to size) - " +
            "Should throw IllegalArgumentException")
    @Test
    void get_getWithInvalidIndex_throwIllegalArgumentException() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");

        assertThrows(IllegalArgumentException.class, () -> minHeap.get(2));
        assertThrows(IllegalArgumentException.class, () -> minHeap.get(3));
    }

    @DisplayName("get - " +
            "When getting an element at a valid index - " +
            "Should return the element at that index")
    @Test
    void get_getAtValidIndex_returnElement() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.insert(2, "D");
        minHeap.insert(12, "E");

        assertEquals("(key=2, value=D)", minHeap.get(0).toString());
        assertEquals("(key=15, value=C)", minHeap.get(2).toString());
        assertEquals("(key=12, value=E)", minHeap.get(4).toString());
    }

    @DisplayName("min - " +
            "When getting the minimum element from a heap with a single element - " +
            "Should return the element as the minimum")
    @Test
    void min_getMinFromHeapWithSingleElement_returnElementAsMin() {
        minHeap.insert(10, "A");

        assertEquals("(key=10, value=A)", minHeap.min().toString());
    }

    @DisplayName("min - " +
            "When getting the minimum element from a heap with multiple elements - " +
            "Should return the minimum element")
    @Test
    void min_getMinFromHeapWithMultipleElements_returnMinimumElement() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.insert(2, "D");
        minHeap.insert(12, "E");

        assertEquals("(key=2, value=D)", minHeap.min().toString());
    }

    @DisplayName("min - " +
            "When getting the minimum element after removing elements from the heap - " +
            "Should return the new minimum element")
    @Test
    void min_getMinAfterRemovingElements_returnNewMinimumElement() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.insert(2, "D");
        minHeap.insert(12, "E");
        minHeap.remove();

        assertEquals("(key=5, value=B)", minHeap.min().toString());
    }

    @DisplayName("min - " +
            "When heap is empty - " +
            "Should throw IllegalStateException")
    @Test
    void min_heapIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> minHeap.min());
    }

    @DisplayName("size - " +
            "When getting the size of an empty heap - " +
            "Should return 0")
    @Test
    void size_getSizeOfEmptyHeap_returnZero() {
        assertEquals(0, minHeap.size());
    }

    @DisplayName("size - " +
            "When getting the size of a heap with elements - " +
            "Should return the correct size")
    @Test
    void size_getSizeOfHeapWithElements_returnCorrectSize() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");

        assertEquals(3, minHeap.size());
    }

    @DisplayName("size - " +
            "When getting the size after removing elements from the heap - " +
            "Should return the updated size")
    @Test
    void size_getSizeAfterRemovingElements_returnUpdatedSize() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");
        minHeap.remove();

        assertEquals(2, minHeap.size());
    }

    @DisplayName("toString - " +
            "When the heap is empty - " +
            "Should return an empty string representation")
    @Test
    void toString_emptyHeap_returnEmptyString() {
        assertEquals("[]", minHeap.toString());
    }

    @DisplayName("toString - " +
            "When the heap has elements - " +
            "Should return a string representation with the elements in heap order")
    @Test
    void toString_heapWithElements_returnStringRepresentation() {
        minHeap.insert(10, "A");
        minHeap.insert(5, "B");
        minHeap.insert(15, "C");

        assertEquals("[(key=5, value=B), (key=10, value=A), (key=15, value=C)]", minHeap.toString());
    }

    @DisplayName("toString - " +
            "When the heap has a single element - " +
            "Should return a string representation with that element")
    @Test
    void toString_heapWithSingleElement_returnStringRepresentation() {
        minHeap.insert(10, "A");

        assertEquals("[(key=10, value=A)]", minHeap.toString());
    }
}