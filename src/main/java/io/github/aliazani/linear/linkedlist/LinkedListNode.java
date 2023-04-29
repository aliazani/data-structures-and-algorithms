package io.github.aliazani.linear.linkedlist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The LinkedListNode class represents a node in a linked list.
 * <p>
 * Each node contains a value of type N and a reference to the next node.
 *
 * @param <N> the type of value stored in the node
 */
@Getter
@Setter
@RequiredArgsConstructor
class LinkedListNode<N> {
    private final N value;
    private LinkedListNode<N> next;
}
