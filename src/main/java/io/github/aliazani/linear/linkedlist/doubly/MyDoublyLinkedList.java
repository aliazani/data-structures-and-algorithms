package io.github.aliazani.linear.linkedlist.doubly;

import io.github.aliazani.linear.arrays.MyArray;
import io.github.aliazani.linear.linkedlist.MyLinkedList;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation of the MyLinkedList interface. It uses DoublyLinkedListNode objects to represent
 * <p>
 * individual elements in the list. This class is also iterable to enable for-each loop usage.
 *
 * @param <N> the type of elements stored in the list
 */
public class MyDoublyLinkedList<N extends Comparable<N>> implements Iterable<DoublyLinkedListNode<N>>, MyLinkedList<N> {
    private DoublyLinkedListNode<N> first;
    private DoublyLinkedListNode<N> last;
    private int size;

    /**
     * Creates an empty doubly linked list.
     */
    public MyDoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Adds the specified item to the beginning of this list.
     *
     * @param item the item to be added to the beginning of this list
     */
    @Override
    public void addFirst(N item) {
        DoublyLinkedListNode<N> node = new DoublyLinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeIntoEmptyLinkedList(node);
        else
            insertNewNodeAtTheBeginning(node);

        size++;
    }

    private boolean isEmpty() {
        return (first == null)
                && (last == null);
    }

    private void insertNewNodeIntoEmptyLinkedList(DoublyLinkedListNode<N> node) {
        first = last = node;
    }

    private void insertNewNodeAtTheBeginning(DoublyLinkedListNode<N> node) {
        node.setNext(first);
        first.setPrev(node);

        first = node;
    }

    /**
     * Adds the specified item to the end of this list.
     *
     * @param item the item to be added to the end of this list
     */
    @Override
    public void addLast(N item) {
        DoublyLinkedListNode<N> node = new DoublyLinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeIntoEmptyLinkedList(node);
        else
            insertNewNodeAtTheEnd(node);

        size++;
    }

    private void insertNewNodeAtTheEnd(DoublyLinkedListNode<N> node) {
        last.setNext(node);
        node.setPrev(last);

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
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0)
            addFirst(item);
        else if (index == size)
            addLast(item);
        else {
            DoublyLinkedListNode<N> insertedNode = new DoublyLinkedListNode<>(item);
            DoublyLinkedListNode<N> previousNode = getNode(index - 1);
            DoublyLinkedListNode<N> nodeAtIndex = previousNode.getNext();

            insertedNode.setPrev(previousNode);
            insertedNode.setNext(nodeAtIndex);

            previousNode.setNext(insertedNode);
            nodeAtIndex.setPrev(insertedNode);

            size++;
        }
    }

    /**
     * Deletes the first element of this list.
     *
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public void deleteFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else if (hasOneItem()) first = last = null;
        else removeFirstNode();

        size--;
    }

    private boolean hasOneItem() {
        return first.equals(last);
    }

    private void removeFirstNode() {
        DoublyLinkedListNode<N> second = first.getNext();

        first.setNext(null);
        second.setPrev(null);

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
        else if (hasOneItem()) first = last = null;
        else replaceTheLastNodeWithPrevious();

        size--;
    }

    private void replaceTheLastNodeWithPrevious() {
        DoublyLinkedListNode<N> previous = last.getPrev();

        last.setPrev(null);
        previous.setNext(null);

        last = previous;
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
            DoublyLinkedListNode<N> nodeToDelete = getNode(index);
            DoublyLinkedListNode<N> prevNode = nodeToDelete.getPrev();
            DoublyLinkedListNode<N> nextNode = nodeToDelete.getNext();

            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);

            nodeToDelete.setPrev(null);
            nodeToDelete.setNext(null);

            size--;
        }
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

        for (DoublyLinkedListNode<N> curr : this) {
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
     * Creates a loop in the doubly linked list by setting the next node of the last node to the first node
     * and the previous node of the first node to the last node.
     */
    @Override
    public void createWithLoop() {
        last.setNext(first);
        first.setPrev(last);
    }

    /**
     * Returns true if this linked list contains a loop, false otherwise.
     *
     * @return true if this linked list contains a loop, false otherwise
     */
    @Override
    public boolean hasLoop() {
        DoublyLinkedListNode<N> slow = first;
        DoublyLinkedListNode<N> fast = first;

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

        for (DoublyLinkedListNode<N> curr : this)
            array.insert(curr.getValue());

        return array;
    }

    /**
     * Reverses the order of the elements in this list.
     */
    @Override
    public void reverse() {
        if (isEmpty()) return;

        DoublyLinkedListNode<N> currentNode = last;
        DoublyLinkedListNode<N> previousNode = last.getPrev();
        DoublyLinkedListNode<N> nextNode = last.getNext();

        while (previousNode != null) {
            previousNode.setNext(null);

            currentNode.setNext(previousNode);
            currentNode.setPrev(nextNode);

            nextNode = currentNode;
            currentNode = previousNode;
            previousNode = previousNode.getPrev();
        }

        currentNode.setNext(previousNode);
        currentNode.setPrev(nextNode);

        swapFirstAndLast(currentNode);
    }

    private void swapFirstAndLast(DoublyLinkedListNode<N> lastNode) {
        first = last;
        last = lastNode;
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
        if (isEmpty() || k > size) throw new IllegalArgumentException();

        DoublyLinkedListNode<N> current = last;
        for (int i = 1; i < k; i++) current = current.getPrev();

        return current.getValue();
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

        DoublyLinkedListNode<N> middle = first;
        DoublyLinkedListNode<N> end = first;

        while (isNotAtTheEndOfTheList(end)) {
            middle = middle.getNext();
            end = end.getNext().getNext();
        }

        if (isNumberOfNodeOdd(end))
            return MessageFormat.format("Middle = {0}", middle.getValue());
        else
            return MessageFormat.format("Middle = {0}, {1}", middle.getValue(), middle.getNext().getValue());
    }

    private boolean isNotAtTheEndOfTheList(DoublyLinkedListNode<N> end) {
        return !end.equals(last) &&
                !end.getNext().equals(last);
    }

    private boolean isNumberOfNodeOdd(DoublyLinkedListNode<N> end) {
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
    public DoublyLinkedListNode<N> getNode(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();

        DoublyLinkedListNode<N> node = first;
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
        for (DoublyLinkedListNode<N> node : this)
            if (node.getNext() != null)
                stringFormOfLinkedList.append(node.getValue()).append(" ↔ ");

        stringFormOfLinkedList.append(last.getValue());
    }

    @Override
    public Iterator<DoublyLinkedListNode<N>> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<DoublyLinkedListNode<N>> {

        private DoublyLinkedListNode<N> current;

        public DoublyLinkedListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public DoublyLinkedListNode<N> next() {
            if (!hasNext()) throw new NoSuchElementException();

            DoublyLinkedListNode<N> result = current;
            current = current.getNext();

            return result;
        }
    }
}