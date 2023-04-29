package io.github.aliazani.linear.linkedlist;

import io.github.aliazani.linear.arrays.MyArray;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list implementation.
 *
 * @param <N> the type of the elements in the linked list, must be comparable.
 */
public class MyLinkedList<N extends Comparable<N>> implements Iterable<LinkedListNode<N>> {

    private LinkedListNode<N> first;
    private LinkedListNode<N> last;
    private LinkedListNode<N> current;
    private int size;

    /**
     * Creates an empty linked list.
     */
    public MyLinkedList() {
        first = null;
        last = null;
        current = null;
        size = 0;
    }

    /**
     * Creates a circular linked list by setting the last node's next pointer to the first node.
     */
    public void createWithLoop() {
        current = first;

        while (!current.equals(last))
            current = current.getNext();

        current.setNext(first);
    }

    /**
     * Inserts the specified element at the beginning of the linked list.
     *
     * @param item the element to insert.
     */
    public void addFirst(N item) {
        LinkedListNode<N> node = new LinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeToEmptyLinkedList(node);
        else
            insertNewNodeAtTheBeginning(node);

        size++;
    }

    /**
     * Inserts the specified element at the end of the linked list.
     *
     * @param item the element to insert.
     */
    public void addLast(N item) {
        LinkedListNode<N> node = new LinkedListNode<>(item);

        if (isEmpty())
            insertNewNodeToEmptyLinkedList(node);
        else
            insertNewNodeAtTheEnd(node);
        size++;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    private boolean isEmpty() {
        return first == null;
    }

    /**
     * Inserts a new node at the beginning of an empty linked list.
     *
     * @param node the new node to be inserted.
     */
    private void insertNewNodeToEmptyLinkedList(LinkedListNode<N> node) {
        first = last = node;
    }

    /**
     * Inserts a new node at the end of the linked list.
     *
     * @param node the new node to be inserted.
     */
    private void insertNewNodeAtTheEnd(LinkedListNode<N> node) {
        last.setNext(node);
        last = node;
    }

    /**
     * Inserts a new node at the beginning of the linked list.
     *
     * @param node the new node to be inserted.
     */
    private void insertNewNodeAtTheBeginning(LinkedListNode<N> node) {
        node.setNext(first);
        first = node;
    }

    /**
     * Removes the first element from the linked list.
     *
     * @throws NoSuchElementException if the linked list is empty.
     */
    public void deleteFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else if (hasOneItem()) first = last = null;
        else replaceFirstNodeWithSecondNode();

        size--;
    }

    /**
     * This method replaces the first node of the linked list with the second node.
     * It does so by setting the next node of the first node to null and then setting the first node to be the second node.
     * Note: This method assumes that there are at least two nodes in the linked list.
     */
    private void replaceFirstNodeWithSecondNode() {
        LinkedListNode<N> second = first.getNext();
        first.setNext(null);
        first = second;
    }

    /**
     * Removes the last element from the linked list.
     *
     * @throws NoSuchElementException if the linked list is empty.
     */
    public void deleteLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else if (hasOneItem()) first = last = null;
        else replaceTheLastNodeWithPrevious(getPrevious(last));
        size--;
    }

    /**
     * Checks if the linked-list has only one node.
     *
     * @return true if the list has only one node, false otherwise
     */
    private boolean hasOneItem() {
        return first.equals(last);
    }

    /**
     * Returns the previous node of the given node in the linked list.
     *
     * @param node the node whose previous node needs to be found
     * @return the previous node of the given node, or null if the given node is the first node in the linked list
     */
    private LinkedListNode<N> getPrevious(LinkedListNode<N> node) {
        for (LinkedListNode<N> eachNode : this)
            if (eachNode.getNext().equals(node)) return eachNode;

        return null;
    }

    /**
     * Replaces the last node in the linked list with its previous node.
     *
     * @param previous the previous node of the last node in the linked list
     * @throws NullPointerException if the previous node is null
     */
    private void replaceTheLastNodeWithPrevious(LinkedListNode<N> previous) {
        if (previous == null) throw new NullPointerException();

        last = previous;
        last.setNext(null);
    }

    /**
     * Returns the index of the first occurrence of the specified element in the linked list, or -1 if the linked list does not contain the element.
     *
     * @param item the element to search for.
     * @return the index of the first occurrence of the specified element in the linked list, or -1 if the linked list does not contain the element.
     */
    public int indexOf(N item) {
        int index = 0;

        if (size == 1 && this.first.getValue().equals(item)) return index;

        for (LinkedListNode<N> node : this) {
            if (node.getValue().equals(item)) return index;
            index++;
        }
        return -1;
    }

    /**
     * Returns true if the linked list contains the specified element, false otherwise.
     *
     * @param item the element to search for.
     * @return true if the linked list contains the specified element, false otherwise.
     */
    public boolean contains(N item) {
        return indexOf(item) != -1;
    }

    /**
     * Returns the size of the linked list.
     *
     * @return the size of the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a new MyArray object with the elements of the current MyLinkedList object.
     *
     * @return MyArray object with the elements of the current MyLinkedList object.
     */
    public MyArray<N> toArray() {
        MyArray<N> array = new MyArray<>(size);

        for (LinkedListNode<N> node : this)
            array.insert(node.getValue());

        return array;
    }

    /**
     * Reverses the order of the elements in the MyLinkedList object.
     */
    public void reverse() {
        if (isEmpty()) return;

        LinkedListNode<N> previous = first;
        LinkedListNode<N> currentNode = first.getNext();

        while (currentNode != null) {
            LinkedListNode<N> next = currentNode.getNext();
            currentNode.setNext(previous);

            previous = currentNode;
            currentNode = next;
        }
        swapFirstAndLast(previous);
    }

    /**
     * Swaps the positions of the first and last nodes in the linked list.
     *
     * @param previous the node preceding the first node.
     * @throws NullPointerException if previous node is null.
     */
    private void swapFirstAndLast(LinkedListNode<N> previous) {
        last = first;
        last.setNext(null);
        first = previous;
    }

    /**
     * Returns the element at the kth position from the end of the MyLinkedList object.
     *
     * @param k The position of the element from the end of the MyLinkedList object.
     * @return The element at the kth position from the end of the MyLinkedList object.
     * @throws IllegalArgumentException if the MyLinkedList object is empty or if k is greater than the size of the MyLinkedList object.
     */
    public N getKthFromTheEnd(int k) {
        if (isEmpty()) throw new IllegalArgumentException();

        if (k > size) throw new IllegalArgumentException();

        LinkedListNode<N> firstNode = first;
        LinkedListNode<N> secondNode = first;

        secondNode = createDistanceFromFirst(k, secondNode);
        while (!secondNode.equals(last)) {
            firstNode = firstNode.getNext();
            secondNode = secondNode.getNext();
        }

        return firstNode.getValue();
    }

    /**
     * Creates a distance of k-1 nodes from the first node of the linked list to the second node.
     *
     * @param k          The position of the node from the first node of the linked list.
     * @param secondNode The second node of the linked list.
     * @return The second node with k-1 nodes distance from the first node of the linked list.
     */
    private LinkedListNode<N> createDistanceFromFirst(int k, LinkedListNode<N> secondNode) {
        for (int n = 0; n < k - 1; n++)
            secondNode = secondNode.getNext();

        return secondNode;
    }

    /**
     * Returns the middle element(s) of the MyLinkedList object.
     * If the number of elements in the MyLinkedList object is odd, returns the single middle element.
     * If the number of elements in the MyLinkedList object is even, returns the two middle elements.
     *
     * @return The middle element(s) of the MyLinkedList object.
     * @throws IllegalArgumentException if the MyLinkedList object is empty.
     */
    public String getMiddle() {
        if (isEmpty()) throw new IllegalArgumentException();

        LinkedListNode<N> middle = first;
        LinkedListNode<N> end = first;

        while (isNotAtTheEndOfTheList(end)) {
            middle = middle.getNext();
            end = end.getNext().getNext();
        }

        if (isNumberOfNodeOdd(end))
            return MessageFormat.format("Middle = {0}", middle.getValue());
        else
            return MessageFormat.format("Middle = {0}, {1}", middle.getValue(), middle.getNext().getValue());
    }

    /**
     * Returns a boolean indicating if the given LinkedListNode object is not at the end of the MyLinkedList object.
     *
     * @param end The LinkedListNode object to check.
     * @return true if the given LinkedListNode object is not at the end of the MyLinkedList object, false otherwise.
     */
    private boolean isNotAtTheEndOfTheList(LinkedListNode<N> end) {
        return !end.equals(last) &&
                !end.getNext().equals(last);
    }

    /**
     * Returns a boolean indicating if the number of nodes in the MyLinkedList object is odd.
     *
     * @param end The last LinkedListNode object in the MyLinkedList object.
     * @return true if the number of nodes in the MyLinkedList object is odd, false otherwise.
     */
    private boolean isNumberOfNodeOdd(LinkedListNode<N> end) {
        return end.equals(last);
    }

    /**
     * Returns true if the MyLinkedList object contains a loop, false otherwise.
     *
     * @return True if the MyLinkedList object contains a loop, false otherwise.
     */
    public boolean hasLoop() {
        LinkedListNode<N> slow = first;
        LinkedListNode<N> fast = first;

        while (slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow.equals(fast)) return true;
        }

        return false;
    }

    /**
     * Returns the node at the specified index in the linked list.
     *
     * @param index the index of the node to get
     * @return the node at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public LinkedListNode<N> getNode(int index) {
        int i = 0;
        if (size == 1 && index == 0) return first;
        for (LinkedListNode<N> node : this)
            if (i++ == index) return node;
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns the value of the node at the specified index in the linked list.
     *
     * @param index the index of the node to get the value of
     * @return the value of the node at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public N getNodeValue(int index) {
        int i = 0;
        if (size == 1 && index == 0) return first.getValue();
        for (LinkedListNode<N> node : this)
            if (i++ == index) return node.getValue();
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns a string representation of the MyLinkedList object.
     *
     * @return A string representation of the MyLinkedList object.
     */
    @Override
    public String toString() {
        StringBuilder stringFormOfLinkedList = new StringBuilder("[");

        if (!isEmpty()) appendNodes(stringFormOfLinkedList);

        stringFormOfLinkedList.append("]");

        return stringFormOfLinkedList.toString();
    }

    /**
     * Appends the value of each node in the MyLinkedList to a StringBuilder in the format "value -> ".
     * <p>
     * Does not append "->" after the last node's value.
     *
     * @param stringFormOfLinkedList The StringBuilder object to which the node values are appended.
     */
    private void appendNodes(StringBuilder stringFormOfLinkedList) {
        for (LinkedListNode<N> node : this)
            if (node.getNext() != null)
                stringFormOfLinkedList.append(node.getValue()).append(" -> ");

        stringFormOfLinkedList.append(last.getValue());
    }

    @Override
    public Iterator<LinkedListNode<N>> iterator() {
        current = this.first;
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<M extends Comparable<M>> implements Iterator<LinkedListNode<M>> {

        private int numberOfCallGetNext = 0;

        @Override
        public boolean hasNext() {
            return (current.getNext() != null);
        }

        @Override
        public LinkedListNode<M> next() {
            current = first;

            if (!hasNext())
                throw new NoSuchElementException();
            for (int j = 0; j < numberOfCallGetNext; j++)
                current = current.getNext();
            numberOfCallGetNext++;

            return (LinkedListNode<M>) current;
        }
    }
}