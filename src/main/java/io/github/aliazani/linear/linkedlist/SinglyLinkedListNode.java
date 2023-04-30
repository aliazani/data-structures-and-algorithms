package io.github.aliazani.linear.linkedlist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


/**
 * Represents a node in a singly linked list.
 *
 * @param <N> the type of the value contained in the node.
 */
@Getter
@Setter
@RequiredArgsConstructor
class SinglyLinkedListNode<N> implements LinkedListNode<N> {
    private final N value;
    private SinglyLinkedListNode<N> next;
}
