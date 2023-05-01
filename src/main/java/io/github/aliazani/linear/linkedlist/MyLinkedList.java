package io.github.aliazani.linear.linkedlist;

import io.github.aliazani.linear.arrays.MyArray;

public interface MyLinkedList<N extends Comparable<N>> {
    void addFirst(N item);

    void addLast(N item);

    void add(N item, int index);

    void deleteFirst();

    void deleteLast();

    void delete(int index);

    int indexOf(N item);

    boolean contains(N item);

    int size();

    boolean hasLoop();

    MyArray<N> toArray();

    void reverse();

    N getKthFromTheEnd(int k);

    String getMiddle();

    LinkedListNode<N> getNode(int index);

    N getNodeValue(int index);
}
