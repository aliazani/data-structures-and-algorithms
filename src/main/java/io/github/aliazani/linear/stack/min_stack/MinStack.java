package io.github.aliazani.linear.stack;

import java.util.Stack;

public class MinStack<T extends Comparable<T>> implements MyStack<T> {
    private final Stack<T> stack = new Stack();
    private final Stack<T> minTrackingStack = new Stack();

    @Override
    public void push(T item) {
        stack.push(item);

        if (minTrackingStack.isEmpty() || isLessThanOrEqualToMin(item))
            minTrackingStack.push(item);
    }

    @Override
    public T pop() {
        if (stack.isEmpty())
            throw new IllegalStateException();

        T top = stack.pop();

        if (minTrackingStack.peek().equals(top))
            minTrackingStack.pop();

        return top;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty() && minTrackingStack.isEmpty();
    }

    public T min() {
        return minTrackingStack.peek();
    }

    private boolean isLessThanOrEqualToMin(T item) {
        return item.compareTo(minTrackingStack.peek()) <= 0;
    }
}
