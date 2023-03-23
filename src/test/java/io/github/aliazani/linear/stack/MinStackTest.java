package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {
    MinStack<Integer> minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack<>();
        minStack.push(10);
        minStack.push(5);
        minStack.push(4);
        minStack.push(25);
        minStack.push(3);
        minStack.push(3);
        minStack.push(1);
    }

    @Test
    void push() {
        minStack.push(20);

        assertEquals(20, minStack.pop());
    }

    @Test
    void pop() {
        assertEquals(1, minStack.pop());
    }

    @Test
    void min() {
        assertEquals(1, minStack.min());

        minStack.pop();
        assertEquals(3, minStack.min());

        minStack.pop();
        assertEquals(3, minStack.min());
    }
}