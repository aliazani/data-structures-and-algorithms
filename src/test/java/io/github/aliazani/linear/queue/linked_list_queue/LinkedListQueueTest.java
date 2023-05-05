package io.github.aliazani.linear.queue.linked_list_queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedListQueue")
class LinkedListQueueTest {
    LinkedListQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedListQueue<>();
    }

    @Test
    @DisplayName("enqueue - " +
            "When adding an element to a non-full queue - " +
            "Should add the element to the rear of the queue")
    void enqueue_queueIsNotFull_addElementToRearOfQueue() {
        queue.enqueue(1);
        assertEquals("[1]", queue.toString());

        queue.enqueue(2);
        assertEquals("[1, 2]", queue.toString());

        queue.enqueue(3);
        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When queue is empty - " +
            "Should throw NoSuchElementException")
    void dequeue_queueIsEmpty_throwNoSuchElementException() {
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing an element from a non-empty queue - " +
            "Should return the front item and remove it from the queue")
    void dequeue_queueIsNotEmptyAndOneDequeue_returnFrontItemAndRemoveIt() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals("[2]", queue.toString());
    }

    @Test
    @DisplayName("dequeue - " +
            "When removing multiple elements from a non-empty queue - " +
            "Should return the front items and remove them from the queue in order")
    void dequeue_queueIsNotEmptyAndMultipleDequeue_removeFrontItemsInOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    @DisplayName("peek - " +
            "When queue is empty - " +
            "Should throw NoSuchElementException")
    void peek_queueIsEmpty_throwNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    @DisplayName("peek - " +
            "When getting the front item of a non-empty queue - " +
            "Should return the front item without removing it")
    void peek_queueIsNotEmpty_returnFrontItem() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.peek());
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
    @DisplayName("isEmpty - when checking if a non-empty queue is empty - should return false")
    void isEmpty_checkNonEmptyQueue_returnFalse() {
        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - when checking if an empty queue is empty - should return true")
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

        assertEquals("[10, 20, 30]", queue.toString());
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

        assertEquals("[20, 30, 40]", queue.toString());
    }
}