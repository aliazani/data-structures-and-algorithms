package io.github.aliazani.linear.queue.priority_queue.with_heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PriorityQueueWithHeap")
class PriorityQueueWithHeapTest {
    PriorityQueueWithHeap<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new PriorityQueueWithHeap<>(3);
    }

    @Test
    @DisplayName("enqueue - " +
            "When adding an element to a full queue - " +
            "Should throw IllegalStateException")
    void enqueue_queueIsFull_throwIllegalStateException() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertThrows(IllegalStateException.class, () -> queue.enqueue(4));
    }

    @Test
    @DisplayName("enqueue - " +
            "When adding an element to a non-full queue - " +
            "Should add the element to the its correct position")
    void enqueue_queueIsNotFull_addElementToItsCorrectPosition() {
        queue.enqueue(1);
        assertEquals("[1]", queue.toString());

        queue.enqueue(2);
        assertEquals("[2, 1]", queue.toString());

        queue.enqueue(0);
        assertEquals("[2, 1, 0]", queue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When queue is empty - " +
            "Should throw IllegalStateException")
    void dequeue_queueIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing an element from a non-empty queue - " +
            "Should return the item with the highest priority and remove it from the queue")
    void dequeue_queueIsNotEmptyAndOneDequeue_returnTheHighestPriorityItemAndRemoveIt() {
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(2);

        assertEquals(4, queue.dequeue());
        assertEquals("[2, 1]", queue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing multiple elements from a non-empty queue - " +
            "Should return the items with the highest priority and remove them from the queue")
    void dequeue_queueIsNotEmptyAndMultipleDequeue_removeItemsInOrderBasedOnTheirPriorities() {
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);

        assertEquals(3, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

    @Test
    @DisplayName("peek - " +
            "When queue is empty - " +
            "Should throw IllegalStateException")
    void peek_queueIsEmpty_throwIllegalState() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    @DisplayName("peek - " +
            "When getting the front item of a non-empty queue - " +
            "Should return the item with the highest priority and without removing it")
    void peek_queueIsNotEmpty_returnTheHighestPriorityItem() {
        queue.enqueue(2);
        queue.enqueue(1);

        assertEquals(2, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("size - " +
            "When getting the size of an empty queue - " +
            "Should return 0")
    void size_queueIsEmpty_returnZero() {
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("size - " +
            "When getting the size of a non-empty queue - " +
            "Should return the number of items in the queue")
    void size_queueIsNotEmpty_returnNumberOfItemsInQueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
    }

    @Test
    @DisplayName("isEmpty - " +
            "when checking if a non-empty queue is empty - " +
            "should return false")
    void isEmpty_checkNonEmptyQueue_returnFalse() {
        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - " +
            "when checking if an empty queue is empty - " +
            "should return true")
    void isEmpty_checkEmptyQueue_returnTrue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("toString - " +
            "When queue is empty - " +
            "Should return string representation of empty queue")
    void toString_queueIsEmpty_returnEmptyQueueString() {
        assertEquals("[]", queue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has one element - " +
            "Should return string representation of queue with one element")
    void toString_queueHasOneElement_returnQueueStringWithOneElement() {
        queue.enqueue(1);

        assertEquals("[1]", queue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has multiple elements - " +
            "Should return string representation of queue with multiple elements")
    void toString_queueHasMultipleElements_returnQueueStringWithMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals("[30, 10, 20]", queue.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When queue has wrapped around - " +
            "Should return string representation of queue with wrapped elements")
    void toString_queueHasWrappedAround_returnQueueStringWithWrappedElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.dequeue();
        queue.enqueue(40);

        assertEquals("[40, 10, 20]", queue.toString());
    }
}