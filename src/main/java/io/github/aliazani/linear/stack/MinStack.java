package io.github.aliazani.linear.stack;

import java.util.Stack;

public class MinStack<T extends Comparable<T>> {
    private final Stack<T> stack = new Stack();
    private final Stack<T> minStack = new Stack();

    public void push(T item) {
        stack.push(item);

        if (minStack.isEmpty() || isLessThanOrEqualToMin(item))
            minStack.push(item);
    }

    public T pop() {
        if (stack.isEmpty())
            throw new IllegalStateException();

        T top = stack.pop();

        if (minStack.peek().equals(top))
            minStack.pop();

        return top;
    }

    public T min() {
        return minStack.peek();
    }

    private boolean isLessThanOrEqualToMin(T item) {
        return item.compareTo(minStack.peek()) <= 0;
    }
}
