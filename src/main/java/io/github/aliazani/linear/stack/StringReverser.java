package io.github.aliazani.linear.stack;

import java.util.Stack;

public class StringReverser {
    private StringReverser() {
    }

    public static String reverseString(String str) {
        Stack<Character> stringStack = new Stack<>();
        StringBuilder reversed = new StringBuilder();

        if (str == null)
            throw new NullPointerException();

        for (char ch: str.toCharArray())
            stringStack.push(ch);

        while (!stringStack.empty())
            reversed.append(stringStack.pop());

        return reversed.toString();
    }
}
