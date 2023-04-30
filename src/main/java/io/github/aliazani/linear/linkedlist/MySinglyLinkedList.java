package io.github.aliazani.linear.linkedlist;

import io.github.aliazani.linear.arrays.MyArray;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MySinglyLinkedList represents a singly linked list that implements the MyLinkedList interface and
 * <p>
 * Iterable interface. It contains methods to manipulate the list such as add, delete, reverse, etc.
 *
 * @param <N> the type of elements in this list, must implement Comparable interface
 */
public class MySinglyLinkedList<N extends Comparable<N>> implements Iterable<SinglyLinkedListNode<N>>, MyLinkedList<N> {
    private SinglyLinkedListNode<N> first;
    private SinglyLinkedListNode<N> last;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MySinglyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Adds an element to the beginning of this list.
     *
     * @param item the element to be added to the list
     */
    @Override
    public void addFirst(N item) {
        SinglyLinkedListNode<N> node = new SinglyLinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeToEmptyLinkedList(node);
        else
            insertNewNodeAtTheBeginning(node);

        size++;
    }

    private void insertNewNodeAtTheBeginning(SinglyLinkedListNode<N> node) {
        node.setNext(first);
        first = node;
    }

    /**
     * Adds an element to the end of this list.
     *
     * @param item the element to be added to the list
     */
    @Override
    public void addLast(N item) {
        SinglyLinkedListNode<N> node = new SinglyLinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeToEmptyLinkedList(node);
        else
            insertNewNodeAtTheEnd(node);

        size++;
    }

    private boolean isEmpty() {
        return first == null;
    }

    private void insertNewNodeToEmptyLinkedList(SinglyLinkedListNode<N> node) {
        first = last = node;
    }

    private void insertNewNodeAtTheEnd(SinglyLinkedListNode<N> node) {
        last.setNext(node);
        last = node;
    }


    /**
     * Adds an element at a specific index of this list.
     *
     * @param item  the element to be added to the list
     * @param index the index at which the element is to be inserted
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(N item, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index: " + index);

        if (index == 0)
            addFirst(item);
        else if (index == size)
            addLast(item);
        else {
            SinglyLinkedListNode<N> insertedNode = new SinglyLinkedListNode<>(item);
            SinglyLinkedListNode<N> previousNode = getNode(index - 1);
            SinglyLinkedListNode<N> nodeAtIndex = previousNode.getNext();

            previousNode.setNext(insertedNode);
            insertedNode.setNext(nodeAtIndex);
        }

        size++;
    }

    /**
     * Deletes the first element of this list.
     *
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public void deleteFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        if (hasOneItem()) first = last = null;
        else removeFirstNode();

        size--;
    }

    private void removeFirstNode() {
        SinglyLinkedListNode<N> second = first.getNext();
        first.setNext(null);
        first = second;
    }

    /**
     * Removes the last element from this linked list.
     *
     * @throws NoSuchElementException if the linked list is empty.
     */
    @Override
    public void deleteLast() {
        if (isEmpty()) throw new NoSuchElementException();

        if (hasOneItem()) first = last = null;
        else replaceTheLastNodeWithPrevious(getPrevious(last));

        size--;
    }

    private boolean hasOneItem() {
        return first == last;
    }

    private SinglyLinkedListNode<N> getPrevious(SinglyLinkedListNode<N> node) {
        for (SinglyLinkedListNode<N> curr : this)
            if (curr.getNext().equals(node)) return curr;

        return null;
    }

    private void replaceTheLastNodeWithPrevious(SinglyLinkedListNode<N> previous) {
        if (previous == null) throw new NullPointerException();

        last = previous;
        last.setNext(null);
    }

    /**
     * Removes the element at the specified position in this linked list.
     *
     * @param index The index of the element to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public void delete(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) deleteFirst();
        else if (index == size - 1) deleteLast();
        else {
            SinglyLinkedListNode<N> prevNode = getNode(index - 1);
            SinglyLinkedListNode<N> currentNode = prevNode.getNext();

            prevNode.setNext(currentNode.getNext());
            currentNode.setNext(null);
        }

        size--;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param item the element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(N item) {
        int index = 0;
        if (item == null) return -1;

        for (SinglyLinkedListNode<N> curr : this) {
            if (curr.getValue().equals(item)) return index;

            index++;
        }

        return -1;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param item the element to check for
     * @return true if this list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(N item) {
        return indexOf(item) != -1;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a loop in the linked list by setting the last node's "next" reference to the first node.
     */
    @Override
    public void createWithLoop() {
        last.setNext(first);
    }

    /**
     * Returns true if this linked list contains a loop, false otherwise.
     *
     * @return true if this linked list contains a loop, false otherwise
     */
    @Override
    public boolean hasLoop() {
        SinglyLinkedListNode<N> slow = first;
        SinglyLinkedListNode<N> fast = first;

        while (slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow.equals(fast)) return true;
        }

        return false;
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence.
     *
     * @return an array containing all the elements in this list in proper sequence
     */
    @Override
    public MyArray<N> toArray() {
        MyArray<N> array = new MyArray<>(size);

        for (SinglyLinkedListNode<N> curr : this)
            array.insert(curr.getValue());

        return array;
    }

    /**
     * Reverses the order of the elements in this list.
     */
    @Override
    public void reverse() {
        if (isEmpty()) return;

        SinglyLinkedListNode<N> previous = first;
        SinglyLinkedListNode<N> currentNode = first.getNext();
        while (currentNode != null) {
            SinglyLinkedListNode<N> next = currentNode.getNext();
            currentNode.setNext(previous);

            previous = currentNode;
            currentNode = next;
        }
        swapFirstAndLast(previous);
    }

    private void swapFirstAndLast(SinglyLinkedListNode<N> lastNode) {
        last = first;
        last.setNext(null);
        first = lastNode;
    }

    /**
     * Returns the value of the k-th element from the end of this list.
     *
     * @param k the distance from the end of the list (1 <= k <= size)
     * @return the value of the k-th element from the end of this list
     * @throws IllegalArgumentException if this list is empty or if k is greater than the size of the list
     */
    @Override
    public N getKthFromTheEnd(int k) {
        if (isEmpty()) throw new IllegalArgumentException();

        if (k > size) throw new IllegalArgumentException();

        SinglyLinkedListNode<N> firstNode = first;
        SinglyLinkedListNode<N> secondNode = first;

        secondNode = createDistanceFromFirst(k, secondNode);
        while (!secondNode.equals(last)) {
            firstNode = firstNode.getNext();
            secondNode = secondNode.getNext();
        }

        return firstNode.getValue();
    }

    private SinglyLinkedListNode<N> createDistanceFromFirst(int k, SinglyLinkedListNode<N> secondNode) {
        for (int n = 0; n < k - 1; n++)
            secondNode = secondNode.getNext();

        return secondNode;
    }

    /**
     * Returns the value of the middle element(s) in this list.
     *
     * @return the value of the middle element(s) in this list
     * @throws IllegalArgumentException if this list is empty
     */
    @Override
    public String getMiddle() {
        if (isEmpty()) throw new IllegalArgumentException();

        SinglyLinkedListNode<N> middle = first;
        SinglyLinkedListNode<N> end = first;

        while (isNotAtTheEndOfTheList(end)) {
            middle = middle.getNext();
            end = end.getNext().getNext();
        }

        if (isNumberOfNodeOdd(end))
            return MessageFormat.format("Middle = {0}", middle.getValue());
        else
            return MessageFormat.format("Middle = {0}, {1}", middle.getValue(), middle.getNext().getValue());
    }

    private boolean isNotAtTheEndOfTheList(SinglyLinkedListNode<N> end) {
        return !end.equals(last) &&
                !end.getNext().equals(last);
    }

    private boolean isNumberOfNodeOdd(SinglyLinkedListNode<N> end) {
        return end.equals(last);
    }

    /**
     * Returns the node at the specified index in this list.
     *
     * @param index the index of the node to return
     * @return the node at the specified index in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public SinglyLinkedListNode<N> getNode(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException();

        SinglyLinkedListNode<N> node = first;
        for (int i = 0; i < index; i++)
            node = node.getNext();

        return node;
    }

    /**
     * Returns the value of the node at the specified index in this list.
     *
     * @param index the index of the node to return
     * @return the value of the node at the specified index in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public N getNodeValue(int index) {
        return getNode(index).getValue();
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder stringFormOfLinkedList = new StringBuilder("[");

        if (!isEmpty()) appendNodes(stringFormOfLinkedList);

        stringFormOfLinkedList.append("]");

        return stringFormOfLinkedList.toString();
    }

    private void appendNodes(StringBuilder stringFormOfLinkedList) {
        for (SinglyLinkedListNode<N> node : this)
            if (node.getNext() != null)
                stringFormOfLinkedList.append(node.getValue()).append(" -> ");

        stringFormOfLinkedList.append(last.getValue());
    }

    @Override
    public Iterator<SinglyLinkedListNode<N>> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<SinglyLinkedListNode<N>> {
        private SinglyLinkedListNode<N> current;

        public SinglyLinkedListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public SinglyLinkedListNode<N> next() {
            if (!hasNext()) throw new NoSuchElementException();

            SinglyLinkedListNode<N> result = current;
            current = current.getNext();

            return result;
        }
    }
}