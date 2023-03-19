package io.github.aliazani.linear.linkedlist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class LinkedListNode<N> {
    private final N value;
    private LinkedListNode<N> next;
}
