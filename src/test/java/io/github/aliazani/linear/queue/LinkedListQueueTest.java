package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {
    LinkedListQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedListQueue<>();
    }

    @Test
    void enqueue() {
        assertTrue(queue.isEmpty());
        assertEquals("[]", queue.toString());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("[10 -> 20 -> 30]", queue.toString());
        queue.dequeue();
        assertEquals("[20 -> 30]", queue.toString());
        queue.enqueue(40);
        queue.enqueue(50);
        assertEquals("[20 -> 30 -> 40 -> 50]", queue.toString());
        assertFalse(queue.isEmpty());
    }

    @Test
    void dequeue() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertFalse(queue.isEmpty());
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void peek() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertEquals(10, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    void size() {
        assertEquals(0, queue.size());
        queue.enqueue(10);
        assertEquals(1, queue.size());
        queue.enqueue(20);
        assertEquals(2, queue.size());
    }

    @Test
    void isEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testToString() {
        assertEquals("[]", queue.toString());
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals("[10 -> 20]", queue.toString());
        queue.dequeue();
        assertEquals("[20]", queue.toString());
        queue.enqueue(30);
        queue.enqueue(40);
        assertEquals("[20 -> 30 -> 40]", queue.toString());
    }
}