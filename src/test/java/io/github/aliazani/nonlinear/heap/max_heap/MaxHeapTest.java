package io.github.aliazani.nonlinear.heap.max_heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MaxHeap")
class MaxHeapTest {
    MaxHeap<Integer> maxHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new MaxHeap<>(10);
    }

    @DisplayName("insert - " +
            "When item is the first to be inserted - " +
            "Should set that item as the root")
    @Test
    void insert_itemIsFirstToBeInserted_setAsRoot() {
        maxHeap.insert(50);

        assertEquals("[50]", maxHeap.toString());
    }

    @DisplayName("insert - " +
            "When item is greater than the parent - " +
            "Should insert that item in the correct position")
    @Test
    void insert_itemIsGreaterThanParent_insertInCorrectPosition() {
        maxHeap.insert(50);
        maxHeap.insert(70);

        assertEquals("[70, 50]", maxHeap.toString());
    }

    @DisplayName("insert - " +
            "When item is less than the parent - " +
            "Should insert that item in the correct position")
    @Test
    void insert_itemIsLessThanParent_insertInCorrectPosition() {
        maxHeap.insert(50);
        maxHeap.insert(30);

        assertEquals("[50, 30]", maxHeap.toString());
    }

    @DisplayName("insert - " +
            "When inserting multiple items in a random order - " +
            "Should build the maxHeap correctly")
    @Test
    void insert_multipleItemsInRandomOrder_buildHeapCorrectly() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(10);
        maxHeap.insert(40);
        maxHeap.insert(60);
        maxHeap.insert(20);
        maxHeap.insert(80);
        maxHeap.insert(90);
        maxHeap.insert(55);

        assertEquals("[90, 80, 60, 70, 55, 50, 20, 10, 40, 30]", maxHeap.toString());
    }

    @DisplayName("insert - " +
            "When inserting an item when the maxHeap is full - " +
            "Should throw IllegalStateException")
    @Test
    void insert_heapIsFull_throwIllegalState() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(10);
        maxHeap.insert(40);
        maxHeap.insert(60);
        maxHeap.insert(20);
        maxHeap.insert(80);
        maxHeap.insert(90);
        maxHeap.insert(55);

        assertThrows(IllegalStateException.class, () -> maxHeap.insert(100));
    }

    @DisplayName("remove - " +
            "When removing the root from a heap with one item - " +
            "Should remove the root and return it")
    @Test
    void remove_heapWithOneItem_removeAndReturnRoot() {
        maxHeap.insert(50);

        assertEquals(50, maxHeap.remove());
        assertEquals("[]", maxHeap.toString());
    }

    @DisplayName("remove - " +
            "When removing the root from a heap with multiple items and right child is larger - " +
            "Should remove the root and return it, and rearrange the heap correctly")
    @Test
    void remove_heapWithMultipleItemsAndRightChildIsLarger_removeAndReturnRootAndRearrangeHeap() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(45);
        maxHeap.insert(35);

        assertEquals(70, maxHeap.remove());
        assertEquals("[50, 30, 45, 10, 5, 35]", maxHeap.toString());
    }

    @DisplayName("remove - " +
            "When removing the root from a heap with multiple items and left child is larger - " +
            "Should remove the root and return it, and rearrange the heap correctly")
    @Test
    void remove_heapWithMultipleItemsAndLeftChildIsLarger_removeAndReturnRootAndRearrangeHeap() {
        maxHeap.insert(50);
        maxHeap.insert(80);
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.insert(10);

        assertEquals(80, maxHeap.remove());
        assertEquals("[50, 40, 30, 10]", maxHeap.toString());
    }

    @DisplayName("remove - " +
            "When removing from an empty heap - " +
            "Should throw IllegalStateException")
    @Test
    void remove_emptyHeap_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> maxHeap.remove());
    }

    @DisplayName("isFull - " +
            "When the heap is not full - " +
            "Should return false")
    @Test
    void isFull_heapNotFull_returnFalse() {
        maxHeap.insert(50);
        maxHeap.insert(30);

        assertFalse(maxHeap.isFull());
    }

    @DisplayName("isFull - " +
            "When the heap is full - " +
            "Should return true")
    @Test
    void isFull_heapIsFull_returnTrue() {
        maxHeap = new MaxHeap<>(3);
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.remove();
        maxHeap.remove();
        maxHeap.insert(70);
        maxHeap.insert(80);
        maxHeap.insert(90);

        assertTrue(maxHeap.isFull());
    }

    @DisplayName("isEmpty - " +
            "When the heap is empty - " +
            "Should return true")
    @Test
    void isEmpty_heapIsEmpty_returnTrue() {
        assertTrue(maxHeap.isEmpty());
    }

    @DisplayName("isEmpty - " +
            "When the heap is not empty - " +
            "Should return false")
    @Test
    void isEmpty_heapIsNotEmpty_returnFalse() {
        maxHeap.insert(50);

        assertFalse(maxHeap.isEmpty());
    }

    @DisplayName("isEmpty - " +
            "When the heap becomes empty after removing items - " +
            "Should return true")
    @Test
    void isEmpty_heapBecomesEmptyAfterRemovingItems_returnTrue() {
        maxHeap.insert(50);
        maxHeap.remove();
        assertTrue(maxHeap.isEmpty());
    }

    @DisplayName("get - " +
            "When the heap is empty - " +
            "Should throw IllegalStateException")
    @Test
    void get_heapIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> maxHeap.get(0));
    }

    @DisplayName("get - " +
            "When the index is out of range (greater than or equal to size) - " +
            "Should throw IllegalArgumentException")
    @Test
    void get_indexOutOfRange_throwIllegalArgumentException() {
        maxHeap.insert(50);
        maxHeap.insert(30);

        assertThrows(IllegalArgumentException.class, () -> maxHeap.get(2));
    }

    @DisplayName("get - " +
            "When the index is negative - " +
            "Should throw IllegalArgumentException")
    @Test
    void get_negativeIndex_throwIllegalArgumentException() {
        maxHeap.insert(50);
        maxHeap.insert(30);

        assertThrows(IllegalArgumentException.class, () -> maxHeap.get(-1));
    }

    @DisplayName("get - " +
            "When the index is valid - " +
            "Should return the element at the specified index")
    @Test
    void get_validIndex_returnElementAtIndex() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(20);

        assertEquals(50, maxHeap.get(2));
    }

    @DisplayName("isMaxHeap - " +
            "When the array represents a max heap - " +
            "Should return true")
    @Test
    void isMaxHeap_arrayRepresentsMaxHeap_returnTrue() {
        Integer[] array = {90, 80, 70, 55, 50, 60, 20, 10, 30, 40};
        assertTrue(maxHeap.isMaxHeap(array));
    }

    @DisplayName("isMaxHeap - " +
            "When the array does not represent a max heap - " +
            "Should return false")
    @Test
    void isMaxHeap_arrayDoesNotRepresentMaxHeap_returnFalse() {
        Integer[] array = {50, 70, 30, 40, 20};
        assertFalse(maxHeap.isMaxHeap(array));
    }

    @DisplayName("isMaxHeap - " +
            "When the array has only one element - " +
            "Should return true")
    @Test
    void isMaxHeap_arrayHasOneElement_returnTrue() {
        Integer[] array = {50};
        assertTrue(maxHeap.isMaxHeap(array));
    }

    @DisplayName("isMaxHeap - " +
            "When the array is empty - " +
            "Should return true")
    @Test
    void isMaxHeap_arrayIsEmpty_returnTrue() {
        Integer[] array = new Integer[0];
        assertTrue(maxHeap.isMaxHeap(array));
    }

    @DisplayName("max - " +
            "When the heap is not empty - " +
            "Should return the maximum element")
    @Test
    void max_heapNotEmpty_returnMaximumElement() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);

        assertEquals(70, maxHeap.max());
    }

    @DisplayName("max - " +
            "When the heap is empty - " +
            "Should throw IllegalStateException")
    @Test
    void max_heapEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> maxHeap.max());
    }

    @DisplayName("size - " +
            "When the heap is empty - " +
            "Should return 0")
    @Test
    void size_heapIsEmpty_returnZero() {
        assertEquals(0, maxHeap.size());
    }

    @DisplayName("size - " +
            "When the heap is not empty - " +
            "Should return the number of elements in the heap")
    @Test
    void size_heapIsNotEmpty_returnNumberOfElements() {
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(20);
        maxHeap.insert(25);
        maxHeap.insert(40);
        maxHeap.remove();
        maxHeap.remove();
        maxHeap.remove();
        maxHeap.insert(45);
        maxHeap.insert(50);

        assertEquals(4, maxHeap.size());
    }

    @DisplayName("toString - " +
            "When the heap is not empty - " +
            "Should return the string representation of the heap")
    @Test
    void toString_heapNotEmpty_returnHeapStringRepresentation() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);

        assertEquals("[70, 30, 50]", maxHeap.toString());
    }

    @DisplayName("toString - " +
            "When the heap is empty - " +
            "Should return an empty string representation")
    @Test
    void toString_heapEmpty_returnEmptyStringRepresentation() {
        assertEquals("[]", maxHeap.toString());
    }
}