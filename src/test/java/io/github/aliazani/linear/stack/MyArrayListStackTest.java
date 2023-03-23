package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListStackTest {
    MyArrayListStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyArrayListStack<>();
        stack.push(1);
        stack.push(2);
    }

    @Test
    void push() {
        stack.push(3);
        stack.push(4);

        assertFalse(stack.isEmpty());
        assertEquals("[1, 2, 3, 4]", stack.toString());
    }

    @Test
    void pop() {
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals("[]", stack.toString());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popEmptyStack() {
        stack.pop();
        stack.pop();

        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void peek() {
        assertEquals(2, stack.peek());
        assertEquals(2, stack.peek());
        assertEquals("[1, 2]", stack.toString());
        assertFalse(stack.isEmpty());
    }

    @Test
    void peekEmptyStack() {
        stack.pop();
        stack.pop();

        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    void isEmpty() {
        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testToString() {
        assertEquals("[1, 2]", stack.toString());
    }
}