package io.github.aliazani.linear.stack.linked_list_stack;

import io.github.aliazani.linear.stack.MyStack;

import java.util.LinkedList;

public class MyLinkedListStack<E extends Comparable<E>> implements MyStack<E> {
    private final LinkedList<E> linkedList;
    private int size;

    public MyLinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E item) {
        linkedList.add(item);
        size++;
    }


    @Override
    public E pop() {
        if (isEmpty())
            throw new IllegalStateException();

        E top = linkedList.removeLast();
        size--;

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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}