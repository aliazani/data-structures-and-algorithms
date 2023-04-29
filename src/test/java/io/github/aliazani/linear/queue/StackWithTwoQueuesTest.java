package io.github.aliazani.linear.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackWithTwoQueuesTest {
    StackWithTwoQueues<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new StackWithTwoQueues<>();
    }

    @Test
    void push() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals("[10, 20, 30]", stack.toString());
    }

    @Test
    void pop() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popEmptyStack() {
        stack.push(10);
        stack.pop();
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(10);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void size() {
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void peek() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.pop();
        assertEquals(20, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void peekEmptyStack() {
        stack.push(10);
        stack.pop();
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    void testToString() {
        assertEquals("[]", stack.toString());
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals("[10, 20, 30]", stack.toString());
        stack.pop();
        assertEquals("[10, 20]", stack.toString());
        stack.pop();
        assertEquals("[10]", stack.toString());
        stack.push(40);
        assertEquals("[10, 40]", stack.toString());
        stack.push(50);
        assertEquals("[10, 40, 50]", stack.toString());
    }
}