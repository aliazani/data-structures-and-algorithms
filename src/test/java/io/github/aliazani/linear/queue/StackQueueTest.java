package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackQueueTest {
    StackQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new StackQueue<>();

    }

    @Test
    void enqueue() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("[10, 20, 30]", queue.toString());
        queue.dequeue();
        queue.dequeue();
        assertEquals("[30]", queue.toString());
        queue.enqueue(50);
        assertEquals("[30, 50]", queue.toString());
        queue.enqueue(60);
        assertEquals("[30, 50, 60]", queue.toString());
    }

    @Test
    void dequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.dequeue();
        assertEquals("[20, 30]", queue.toString());
        queue.dequeue();
        assertEquals("[30]", queue.toString());
        queue.dequeue();
        assertEquals("[]", queue.toString());
        assertTrue(queue.isEmpty());
    }

    @Test
    void dequeueWhenQueueIsEmpty() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    void peek() {
        queue.enqueue(10);
        assertEquals(10, queue.peek());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    void isEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testToString() {
        queue.enqueue(10);
        assertEquals("[10]", queue.toString());
        queue.enqueue(20);
        assertEquals("[10, 20]", queue.toString());
        queue.dequeue();
        assertEquals("[20]", queue.toString());
        queue.enqueue(30);
        assertEquals("[20, 30]", queue.toString());
    }
}