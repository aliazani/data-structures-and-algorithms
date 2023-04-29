package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class QueueReverserTest {
    private QueueReverser<Integer> queueReverser;
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queueReverser = new QueueReverser<>();
    }

    @Test
    void reverse() {
        queue = new ArrayDeque<>(Arrays.asList(10, 20, 30, 40, 50));

        queueReverser.reverse(queue, 4);

        assertEquals("[40, 30, 20, 10, 50]", queue.toString());
    }

    @Test
    void reverse_shouldReturnEmptyQueueIfQueueIsEmpty() {
        queue = new ArrayDeque<>();

        queueReverser.reverse(queue, 0);

        assertEquals("[]", queue.toString());
    }

    @Test
    void reverse_shouldThrowExceptionIfNumberOfElementsIsGreaterThanQueueSizeOrLessThanZero() {
        queue = new ArrayDeque<>(Arrays.asList(10, 20, 30, 40, 50));

        assertThrows(IllegalArgumentException.class, () -> queueReverser.reverse(queue, 6));
        assertThrows(IllegalArgumentException.class, () -> queueReverser.reverse(queue, -1));
    }
}