package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    ArrayQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>(Integer.class, 4);
    }

    @Test
    void enqueue() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertEquals("[10]", queue.toString());

        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(50);
        queue.enqueue(60);
        assertEquals("[30, 40, 50, 60]", queue.toString());
    }

    @Test
    void enqueueWhenIsFullThrowsException() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        assertThrows(IllegalStateException.class, () -> queue.enqueue(50));
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
    void dequeueWhenIsEmptyThrowsException() {
        queue.enqueue(10);
        queue.dequeue();
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void peek() {
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(10, queue.peek());
    }

    @Test
    void isEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testToString() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals("[10, 20, 30]", queue.toString());
    }
}