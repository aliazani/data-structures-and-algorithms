package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueueReverser")
class QueueReverserTest {
    private QueueReverser<Integer> queueReverser;
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queueReverser = new QueueReverser<>();
    }

    @Test
    @DisplayName("reverse - " +
            "When reverse is called correctly - " +
            "Should reverse the queue")
    void reverse_isCalled_reverseTheQueue() {
        queue = new ArrayDeque<>(Arrays.asList(10, 20, 30, 40, 50));

        queueReverser.reverse(queue, 4);

        assertEquals("[40, 30, 20, 10, 50]", queue.toString());
    }

    @Test
    @DisplayName("reverse - " +
            "When queue is empty - " +
            "Should return an empty queue")
    void reverse_queueIsEmpty_returnEmptyQueue() {
        queue = new ArrayDeque<>();

        queueReverser.reverse(queue, 0);

        assertEquals("[]", queue.toString());
    }

    @Test
    @DisplayName("reverse - " +
            "When n is less than 0 or greater than queue size - " +
            "Should throw IllegalStateException")
    void reverse_nIsLessThanZeroOrGreaterThanQueueSize_throwIllegalStateException() {
        queue = new ArrayDeque<>(Arrays.asList(10, 20, 30, 40, 50));

        assertThrows(IllegalArgumentException.class, () -> queueReverser.reverse(queue, 6));
        assertThrows(IllegalArgumentException.class, () -> queueReverser.reverse(queue, -1));
    }
}