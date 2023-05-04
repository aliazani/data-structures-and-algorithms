package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoStacksTest {
    TwoStacks<Integer> twoStacks;

    @BeforeEach
    void setUp() {
        twoStacks = new TwoStacks<>(4);
    }

    @Test
    @DisplayName("push1 - " +
            "When stack is not full - " +
            "Should add one element to the beginning of the stack")
    void push1_stackIsNotFull_addElementToTheBeginningOfStack() {
        twoStacks.push1(1);
        assertEquals("[1, null, null, null]", twoStacks.toString());

        twoStacks.push1(2);
        assertEquals("[1, 2, null, null]", twoStacks.toString());
    }

    @Test
    @DisplayName("push1 - " +
            "When stack is full - " +
            "Should throw IllegalStateException")
    void push1_stackIsFull_throwIllegalStateException() {
        twoStacks.push1(1);
        twoStacks.push1(2);
        twoStacks.push1(3);
        twoStacks.push1(4);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> twoStacks.push1(5));
    }

    @Test
    @DisplayName("pop1 - " +
            "When stack is not empty - " +
            "Should remove and return element from stack1")
    void pop1_stackIsNotEmpty_removeAndReturnElementFromStack1() {
        twoStacks.push1(1);
        twoStacks.push1(2);
        twoStacks.push1(3);

        assertEquals(3, twoStacks.pop1());
        assertEquals("[1, 2, null, null]", twoStacks.toString());

        assertEquals(2, twoStacks.pop1());
        assertEquals("[1, null, null, null]", twoStacks.toString());

        assertEquals(1, twoStacks.pop1());
        assertEquals("[null, null, null, null]", twoStacks.toString());
    }

    @Test
    @DisplayName("pop1 - " +
            "When stack is empty - " +
            "Should throw IllegalStateException")
    void pop1_stackIsEmpty_throwIllegalState() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, twoStacks::pop1);
    }

    @Test
    @DisplayName("isEmpty1 - " +
            "When stack is empty - " +
            "Should return true")
    void isEmpty1_stackIsEmpty_returnTrue() {
        assertTrue(twoStacks.isEmpty1());
    }

    @Test
    @DisplayName("isEmpty1 - " +
            "When stack is not empty - " +
            "Should return false")
    void isEmpty1_stackIsNotEmpty_returnFalse() {
        twoStacks.push1(1);
        assertFalse(twoStacks.isEmpty1());
    }

    @Test
    @DisplayName("isFull1 - " +
            "When stack is not full - " +
            "Should return false")
    void isFull1_stackIsNotFull_returnFalse() {
        assertFalse(twoStacks.isFull1());
        twoStacks.push1(1);
        assertFalse(twoStacks.isFull1());
    }

    @Test
    @DisplayName("isFull1 - " +
            "When stack is full - " +
            "Should return true")
    void isFull1_stackIsFull_returnTrue() {
        twoStacks.push1(1);
        twoStacks.push1(2);
        twoStacks.push1(3);
        twoStacks.push1(4);
        assertTrue(twoStacks.isFull1());
    }

    @Test
    @DisplayName("push2 - " +
            "When stack is not full - " +
            "Should add one element to the end of the stack")
    void push2_stackIsNotFull_addElementToTheEndOfStack() {
        twoStacks.push2(1);
        assertEquals("[null, null, null, 1]", twoStacks.toString());

        twoStacks.push2(2);
        assertEquals("[null, null, 2, 1]", twoStacks.toString());
    }

    @Test
    @DisplayName("push2 - " +
            "When stack is full - " +
            "Should throw IllegalStateException")
    void push2_stackIsFull_throwIllegalStateException() {
        twoStacks.push2(1);
        twoStacks.push2(2);
        twoStacks.push2(3);
        twoStacks.push2(4);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> twoStacks.push2(5));
    }

    @Test
    @DisplayName("pop2 - " +
            "When stack is not empty - " +
            "Should remove and return element from stack1")
    void pop2_stackIsNotEmpty_removeAndReturnElementFromStack2() {
        twoStacks.push2(1);
        twoStacks.push2(2);
        twoStacks.push2(3);

        assertEquals(3, twoStacks.pop2());
        assertEquals("[null, null, 2, 1]", twoStacks.toString());

        assertEquals(2, twoStacks.pop2());
        assertEquals("[null, null, null, 1]", twoStacks.toString());

        assertEquals(1, twoStacks.pop2());
        assertEquals("[null, null, null, null]", twoStacks.toString());
    }

    @Test
    @DisplayName("pop2 - " +
            "When stack is empty - " +
            "Should throw IllegalStateException")
    void pop2_stackIsEmpty_throwIllegalState() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, twoStacks::pop2);
    }

    @Test
    @DisplayName("isEmpty2 - " +
            "When stack is empty - " +
            "Should return true")
    void isEmpty2_stackIsEmpty_returnTrue() {
        assertTrue(twoStacks.isEmpty2());
    }

    @Test
    @DisplayName("isEmpty2 - " +
            "When stack is not empty - " +
            "Should return false")
    void isEmpty2_stackIsNotEmpty_returnFalse() {
        twoStacks.push2(1);
        assertFalse(twoStacks.isEmpty2());
    }

    @Test
    @DisplayName("isFull2 - " +
            "When stack is not full - " +
            "Should return false")
    void isFull2_stackIsNotFull_returnFalse() {
        assertFalse(twoStacks.isFull2());
        twoStacks.push2(1);
        assertFalse(twoStacks.isFull2());
    }

    @Test
    @DisplayName("isFull2 - " +
            "When stack is full - " +
            "Should return true")
    void isFull2_stackIsFull_returnTrue() {
        twoStacks.push2(1);
        twoStacks.push2(2);
        twoStacks.push2(3);
        twoStacks.push2(4);
        assertTrue(twoStacks.isFull2());
    }

    @Test
    @DisplayName("toString")
    void testToString() {
        twoStacks.push1(3);
        twoStacks.push1(5);
        twoStacks.push2(2);
        twoStacks.push2(4);

        assertEquals("[3, 5, 4, 2]", twoStacks.toString());
    }
}