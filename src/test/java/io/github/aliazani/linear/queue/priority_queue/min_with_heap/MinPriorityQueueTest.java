package io.github.aliazani.linear.queue.priority_queue.min_with_heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MinPriorityQueue")
class MinPriorityQueueTest {
    MinPriorityQueue<Integer, String> minPriorityQueue;

    @BeforeEach
    void setUp() {
        minPriorityQueue = new MinPriorityQueue<>(5);
    }

    @Test
    @DisplayName("enqueue - " +
            "When adding an element to a full queue - " +
            "Should throw IllegalStateException")
    void enqueue_queueIsFull_throwIllegalStateException() {
        minPriorityQueue.enqueue(1, "A");
        minPriorityQueue.enqueue(2, "B");
        minPriorityQueue.enqueue(3, "C");
        minPriorityQueue.enqueue(4, "D");
        minPriorityQueue.enqueue(5, "E");

        assertThrows(IllegalStateException.class, () -> minPriorityQueue.enqueue(6, "F"));
    }

    @Test
    @DisplayName("enqueue - " +
            "When adding an element to a non-full queue - " +
            "Should add the element to its correct position")
    void enqueue_queueIsNotFull_addElementToItsCorrectPosition() {
        minPriorityQueue.enqueue(3, "C");
        assertEquals("[(key=3, value=C)]", minPriorityQueue.toString());

        minPriorityQueue.enqueue(2, "B");
        assertEquals("[(key=2, value=B), (key=3, value=C)]", minPriorityQueue.toString());

        minPriorityQueue.enqueue(1, "A");
        assertEquals("[(key=1, value=A), (key=3, value=C), (key=2, value=B)]", minPriorityQueue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When queue is empty - " +
            "Should throw IllegalStateException")
    void dequeue_queueIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, minPriorityQueue::dequeue);
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing an element from a non-empty queue - " +
            "Should return the item with the highest priority and remove it from the queue")
    void dequeue_queueIsNotEmptyAndOneDequeue_returnTheHighestPriorityItemAndRemoveIt() {
        minPriorityQueue.enqueue(1, "A");
        minPriorityQueue.enqueue(4, "D");
        minPriorityQueue.enqueue(2, "B");

        assertEquals("A", minPriorityQueue.dequeue());
        assertEquals("[(key=2, value=B), (key=4, value=D)]", minPriorityQueue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing multiple elements from a non-empty queue - " +
            "Should return the items with the highest priority and remove them from the queue")
    void dequeue_queueIsNotEmptyAndMultipleDequeue_removeItemsInOrderBasedOnTheirPriorities() {
        minPriorityQueue.enqueue(3, "C");
        minPriorityQueue.enqueue(2, "B");
        minPriorityQueue.enqueue(1, "A");

        assertEquals("A", minPriorityQueue.dequeue());
        assertEquals("B", minPriorityQueue.dequeue());
        assertEquals("C", minPriorityQueue.dequeue());
    }

    @Test
    @DisplayName("peek - " +
            "When queue is empty - " +
            "Should throw IllegalStateException")
    void peek_queueIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> minPriorityQueue.peek());
    }

    @Test
    @DisplayName("peek - " +
            "When getting the front item of a non-empty queue - " +
            "Should return the item with the highest priority and without removing it")
    void peek_queueIsNotEmpty_returnTheHighestPriorityItem() {
        minPriorityQueue.enqueue(2, "B");
        minPriorityQueue.enqueue(1, "A");

        assertEquals("A", minPriorityQueue.peek());
        assertEquals(2, minPriorityQueue.size());
    }

    @Test
    @DisplayName("isEmpty - " +
            "when checking if a non-empty queue is empty - " +
            "should return false")
    void isEmpty_checkNonEmptyQueue_returnFalse() {
        minPriorityQueue.enqueue(1, "A");

        assertFalse(minPriorityQueue.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - " +
            "when checking if an empty queue is empty - " +
            "should return true")
    void isEmpty_checkEmptyQueue_returnTrue() {
        assertTrue(minPriorityQueue.isEmpty());
    }

    @Test
    @DisplayName("size - " +
            "When getting the size of an empty queue - " +
            "Should return 0")
    void size_queueIsEmpty_returnZero() {
        assertEquals(0, minPriorityQueue.size());
    }

    @Test
    @DisplayName("size - " +
            "When getting the size of a non-empty queue - " +
            "Should return the number of items in the queue")
    void size_queueIsNotEmpty_returnNumberOfItemsInQueue() {
        minPriorityQueue.enqueue(1, "A");
        minPriorityQueue.enqueue(2, "B");
        minPriorityQueue.enqueue(3, "C");
        minPriorityQueue.dequeue();
        minPriorityQueue.enqueue(4, "D");

        assertEquals(3, minPriorityQueue.size());
    }

    @Test
    @DisplayName("toString - " +
            "When queue is empty - " +
            "Should return string representation of empty queue")
    void toString_queueIsEmpty_returnEmptyQueueString() {
        assertEquals("[]", minPriorityQueue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has one element - " +
            "Should return string representation of queue with one element")
    void toString_queueHasOneElement_returnQueueStringWithOneElement() {
        minPriorityQueue.enqueue(1, "A");

        assertEquals("[(key=1, value=A)]", minPriorityQueue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has multiple elements - " +
            "Should return string representation of queue with multiple elements")
    void toString_queueHasMultipleElements_returnQueueStringWithMultipleElements() {
        minPriorityQueue.enqueue(10, "Z");
        minPriorityQueue.enqueue(20, "Y");
        minPriorityQueue.enqueue(30, "X");

        assertEquals("[(key=10, value=Z), (key=20, value=Y), (key=30, value=X)]", minPriorityQueue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has wrapped around - " +
            "Should return string representation of queue with wrapped elements")
    void toString_queueHasWrappedAround_returnQueueStringWithWrappedElements() {
        minPriorityQueue.enqueue(10, "Z");
        minPriorityQueue.enqueue(20, "Y");
        minPriorityQueue.enqueue(30, "X");
        minPriorityQueue.dequeue();
        minPriorityQueue.enqueue(40, "W");

        assertEquals("[(key=20, value=Y), (key=30, value=X), (key=40, value=W)]", minPriorityQueue.toString());
    }
}