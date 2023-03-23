package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringReverserTest {
    @Test
    void reverseString() {
        assertEquals("DCBA", StringReverser.reverseString("ABCD"));
    }

    @Test
    void reverseAnEmptyString() {
        assertEquals("", StringReverser.reverseString(""));
    }

    @Test
    void reverseACharacter() {
        assertEquals("A", StringReverser.reverseString("A"));
    }

    @Test
    void reverseNull() {
        assertThrows(NullPointerException.class, () -> StringReverser.reverseString(null));
    }
}