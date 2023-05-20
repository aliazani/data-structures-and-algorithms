package io.github.aliazani.linear.stack;

import java.util.Stack;

/**
 * Utility class for reversing a string using a stack.
 */
public class StringReverser {
    private StringReverser() {
    }

    /**
     * Reverses the input string.
     *
     * @param str the string to be reversed
     * @return the reversed string
     * @throws NullPointerException if the input string is null
     */
    public static String reverseString(String str) {
        Stack<Character> stringStack = new Stack<>();
        StringBuilder reversed = new StringBuilder();

        if (str == null)
            throw new NullPointerException();

        for (char ch : str.toCharArray())
            stringStack.push(ch);

        while (!stringStack.empty())
            reversed.append(stringStack.pop());

        return reversed.toString();
    }
}