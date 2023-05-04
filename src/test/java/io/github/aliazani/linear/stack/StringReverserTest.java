package io.github.aliazani.linear.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringReverserTest {
    @Test
    void reverseString_stringIsEmpty_returnEmptyString() {
        assertEquals("", StringReverser.reverseString(""));
    }

    @Test
    void reverseString_stringHasOneCharacter_returnThatCharacter() {
        assertEquals("A", StringReverser.reverseString("A"));
    }

    @Test
    void reverseString_stringHasMoreThanOneCharacter_returnReversedString() {
        assertEquals("DCBA", StringReverser.reverseString("ABCD"));
    }

    @Test
    void reverseString_stringIsNull_throwNullPointer() {
        assertThrows(NullPointerException.class, () -> StringReverser.reverseString(null));
    }
}