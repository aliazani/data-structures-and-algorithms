package io.github.aliazani.linear.stack;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
public class Expression {
    private final String input;
    private final List<Character> leftBrackets = Arrays.asList('{', '<', '[', '(');
    private final List<Character> rightBrackets = Arrays.asList('}', '>', ']', ')');
    private final Stack<Character> stack = new Stack<>();

    public boolean isBalanced() {
        for (char ch : input.toCharArray())
            if (isOpeningBracket(ch)) stack.push(ch);
            else if (isClosingBracket(ch) &&
                    (stack.empty() || !bracketsMatch(stack.pop(), ch))) return false;

        return stack.empty();
    }

    private boolean isOpeningBracket(char ch) {
        return leftBrackets.contains(ch);
    }

    private boolean isClosingBracket(char ch) {
        return rightBrackets.contains(ch);
    }

    private boolean bracketsMatch(char left, char right) {
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }
}