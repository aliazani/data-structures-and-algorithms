package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoStacksTest {
    TwoStacks<Integer> twoStacks;

    @BeforeEach
    void setUp() {
        twoStacks = new TwoStacks<>(4);
        twoStacks.push1(1);
        twoStacks.push2(2);
    }

    @Test
    void push1() {
        twoStacks.push1(3);
        twoStacks.push1(5);

        assertTrue(twoStacks.isFull1());
        assertEquals("[1, 3, 5, 2]", twoStacks.toString());
    }

    @Test
    void pop1() {
        twoStacks.pop1();

        assertTrue(twoStacks.isEmpty1());
        assertEquals("[null, null, null, 2]", twoStacks.toString());
    }

    @Test
    void isEmpty1() {
        assertFalse(twoStacks.isEmpty1());
        twoStacks.pop1();
        assertTrue(twoStacks.isEmpty1());
    }

    @Test
    void isFull1() {
        twoStacks.push1(3);
        twoStacks.push1(5);

        assertTrue(twoStacks.isFull1());
    }

    @Test
    void push2() {
        twoStacks.push2(4);
        twoStacks.push2(6);

        assertTrue(twoStacks.isFull2());
        assertEquals("[1, 6, 4, 2]", twoStacks.toString());
    }

    @Test
    void pop2() {
        twoStacks.pop2();

        assertTrue(twoStacks.isEmpty2());
        assertEquals("[1, null, null, null]", twoStacks.toString());
    }

    @Test
    void isEmpty2() {
        assertFalse(twoStacks.isEmpty2());
        twoStacks.pop2();
        assertTrue(twoStacks.isEmpty2());
    }

    @Test
    void isFull2() {
        twoStacks.push2(3);
        twoStacks.push2(5);

        assertTrue(twoStacks.isFull2());
    }

    @Test
    void testToString() {
        twoStacks.push1(3);
        twoStacks.push2(4);

        assertEquals("[1, 3, 4, 2]", twoStacks.toString());
    }
}