package io.github.aliazani.linear.linkedlist.doubly;

import io.github.aliazani.linear.linkedlist.LinkedListNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <N> the type of the value contained in the node.
 */
@Getter
@Setter
@RequiredArgsConstructor
class DoublyLinkedListNode<N> implements LinkedListNode<N> {
    private final N value;
    private DoublyLinkedListNode<N> next;
    private DoublyLinkedListNode<N> prev;
}