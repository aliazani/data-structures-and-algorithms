package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueWithArrayTest {
    PriorityQueueWithArray<Integer> priorityQueue;

    @BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueueWithArray<>(Integer.class, 5);
    }

    @Test
    void add() {
        priorityQueue.add(80);
        priorityQueue.add(90);
        priorityQueue.add(100);
        priorityQueue.add(10);
        priorityQueue.add(20);

        assertEquals("[10, 20, 80, 90, 100]", priorityQueue.toString());

        assertThrows(IllegalStateException.class, () -> priorityQueue.add(110));
    }

    @Test
    void isFull() {
        assertFalse(priorityQueue.isFull());
        priorityQueue.add(80);
        assertFalse(priorityQueue.isFull());
        priorityQueue.add(90);
        priorityQueue.add(10);
        priorityQueue.add(30);
        priorityQueue.add(20);
        assertTrue(priorityQueue.isFull());
        priorityQueue.remove();
        assertFalse(priorityQueue.isFull());
    }

    @Test
    void remove() {
        priorityQueue.add(80);
        priorityQueue.add(90);
        priorityQueue.add(10);
        priorityQueue.add(20);
        assertEquals("[10, 20, 80, 90, null]", priorityQueue.toString());
        assertEquals(90, priorityQueue.remove());
        assertEquals("[10, 20, 80, null, null]", priorityQueue.toString());
        assertEquals(80, priorityQueue.remove());
        assertEquals("[10, 20, null, null, null]", priorityQueue.toString());
        assertEquals(20, priorityQueue.remove());
        assertEquals("[10, null, null, null, null]", priorityQueue.toString());
        assertEquals(10, priorityQueue.remove());
        assertEquals("[null, null, null, null, null]", priorityQueue.toString());
        assertThrows(IllegalStateException.class, () -> priorityQueue.remove());
    }

    @Test
    void isEmpty() {
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.add(80);
        priorityQueue.add(90);
        assertFalse(priorityQueue.isEmpty());
        priorityQueue.remove();
        priorityQueue.remove();
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    void testToString() {
        priorityQueue.add(80);
        priorityQueue.add(90);
        priorityQueue.add(10);
        priorityQueue.add(20);

        assertEquals("[10, 20, 80, 90, null]", priorityQueue.toString());
        priorityQueue.remove();
        assertEquals("[10, 20, 80, null, null]", priorityQueue.toString());
        priorityQueue.remove();
        assertEquals("[10, 20, null, null, null]", priorityQueue.toString());
        priorityQueue.remove();
        assertEquals("[10, null, null, null, null]", priorityQueue.toString());
        priorityQueue.remove();
        assertEquals("[null, null, null, null, null]", priorityQueue.toString());
        priorityQueue.add(10);
        assertEquals("[10, null, null, null, null]", priorityQueue.toString());
        priorityQueue.add(1);
        assertEquals("[1, 10, null, null, null]", priorityQueue.toString());
    }
}