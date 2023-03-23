package io.github.aliazani.linear.stack;

import java.util.LinkedList;

public class MyLinkedListStack<E extends Comparable<E>> implements MyStack<E> {
    private final LinkedList<E> linkedList;
    private int count;

    public MyLinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E item) {
        linkedList.add(item);
        count++;
    }


    @Override
    public E pop() {
        if (isEmpty())
            throw new IllegalStateException();

        E top = linkedList.removeLast();
        count--;

        return top;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return linkedList.getLast();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}