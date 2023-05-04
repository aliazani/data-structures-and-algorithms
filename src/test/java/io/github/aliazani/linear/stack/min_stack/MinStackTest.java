package io.github.aliazani.linear.stack.min_stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {
    MinStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MinStack<>();
    }

    @Test
    @DisplayName("push - " +
            "When adding an element - " +
            "Should insert one element to the top of stack")
    void push_addOneElement_insertOneElement() {
        stack.push(10);

        assertEquals("MinStack: [10]\nStack: [10]", stack.toString());
    }

    @Test
    @DisplayName("push - " +
            "When adding multiple elements - " +
            "Should insert multiple elements to the top of stack")
    void push_addMultipleElements_insertMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals("MinStack: [10]\nStack: [10, 20, 30]", stack.toString());
        assertEquals(30, stack.peek());
    }

    @Test
    @DisplayName("pop - " +
            "When stack is empty - " +
            "Should throw EmptyStackException")
    void pop_stackIsEmpty_throwEmptyStack() {
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("pop - " +
            "When popping an element from a non-empty stack - " +
            "Should return the top item and remove it from the stack")
    void pop_stackIsNotEmptyAndOnePop_returnTopItemAndRemoveIt() {
        stack.push(1);

        assertEquals(1, stack.pop());
        assertEquals("MinStack: []\nStack: []", stack.toString());
    }

    @Test
    @DisplayName("pop - " +
            "When popping multiple elements from a non-empty stack - " +
            "Should return the top item and remove it from the stack")
    void pop_stackIsNotEmptyAndMultiplePop_popItemsInOrder() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals("MinStack: [10]\nStack: [10, 20]", stack.toString());
        assertEquals(20, stack.pop());
        assertEquals("MinStack: [10]\nStack: [10]", stack.toString());
        assertEquals(10, stack.pop());
        assertEquals("MinStack: []\nStack: []", stack.toString());
    }


    @Test
    @DisplayName("peek - " +
            "When stack is empty - " +
            "Should throw EmptyStackException")
    void peek_stackIsEmpty_throwEmptyStack() {
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    @DisplayName("peek - " +
            "When peeking at a non-empty stack - " +
            "Should return the top item without removing it from the stack")
    void peek_stackIsNotEmpty_returnTopItemWithoutRemovingIt() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.peek());
        assertEquals("MinStack: [1]\nStack: [1, 2]", stack.toString());
    }

    @Test
    @DisplayName("isEmpty - " +
            "When checking a non-empty stack - " +
            "Should return false")
    void isEmpty_stackIsNotEmpty_returnFalse() {
        stack.push(1);

        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - " +
            "When checking an empty stack - " +
            "Should return true")
    void isEmpty_stackIsEmpty_returnTrue() {
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("size - " +
            "When the stack is empty - " +
            "Should return zero")
    void size_stackIsEmpty_returnZero() {
        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("size - " +
            "When the stack is not empty - " +
            "Should return the number of elements in the stack")
    void size_stackIsNotEmpty_returnNumberOfElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.size());
    }

    @Test
    @DisplayName("min - " +
            "When stack is empty - " +
            "Should throw EmptyStackException")
    void min_stackIsEmpty_throwEmptyStack() {
        assertThrows(EmptyStackException.class, stack::min);
    }

    @Test
    @DisplayName("min - " +
            "When stack contains one element - " +
            "Should return that element")
    void min_stackHasOneElement_returnThatElement() {
        stack.push(1);

        assertEquals(1, stack.min());
    }

    @Test
    @DisplayName("min() - " +
            "When stack contains multiple elements - " +
            "Should return the minimum element")
    void min_stackHasMultipleElements_returnMinimumElement() {
        stack.push(3);
        stack.push(5);
        stack.push(2);
        stack.push(6);
        stack.push(1);

        assertEquals(1, stack.min());
    }

    @Test
    @DisplayName("min - " +
            "When stack contains multiple elements and minimum element is removed - " +
            "Should return the next minimum element")
    void min_stackHasMultipleElementsAndMinimumElementIsRemoved_returnNextMinimumElement() {
        stack.push(3);
        stack.push(5);
        stack.push(2);
        stack.push(6);
        stack.push(1);

        stack.pop();

        assertEquals(2, stack.min());
    }

    @Test
    @DisplayName("toString - " +
            "When the stack is empty - " +
            "Should return empty array representation")
    void toString_stackIsEmpty_returnEmptyString() {
        MinStack<Double> stack = new MinStack<>();

        assertEquals("MinStack: []\nStack: []", stack.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the stack is not empty - " +
            "Should return array representation of elements in array")
    void toString_stackIsNotEmpty_returnString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("MinStack: [1]\nStack: [1, 2, 3]", stack.toString());
    }
}