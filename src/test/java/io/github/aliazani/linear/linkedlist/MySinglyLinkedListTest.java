package io.github.aliazani.linear.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class MySinglyLinkedListTest {
    private MySinglyLinkedList<Integer> mySinglyLinkedListOfInteger;
    private MySinglyLinkedList<String> mySinglyLinkedListOfString;

    @BeforeEach
    void setUp() {
        mySinglyLinkedListOfInteger = new MySinglyLinkedList<>();
        mySinglyLinkedListOfString = new MySinglyLinkedList<>();
    }

    @Test
    @DisplayName("Add nodes at the end of the linked list")
    void addNodesAtLastLinkedListOfInteger() {
        mySinglyLinkedListOfInteger.addLast(0);
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);

        assertEquals(0, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(1, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals(2, mySinglyLinkedListOfInteger.getNode(2).getValue());
        assertEquals("[0 -> 1 -> 2]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("Remove a node from a linked list with one node")
    void removeANodeFromLinkedListWithOneNode() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.deleteFirst();
        mySinglyLinkedListOfInteger.deleteLast();

        assertEquals(0, mySinglyLinkedListOfInteger.size());
        assertEquals("[]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("Add nodes at the beginning of the linked list")
    void addNodesAtFirst() {
        mySinglyLinkedListOfInteger.addFirst(3);
        mySinglyLinkedListOfInteger.addFirst(2);
        mySinglyLinkedListOfInteger.addFirst(1);
        mySinglyLinkedListOfInteger.addFirst(0);
        mySinglyLinkedListOfInteger.addLast(4);

        assertEquals(0, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(1, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals(2, mySinglyLinkedListOfInteger.getNode(2).getValue());
        assertEquals(3, mySinglyLinkedListOfInteger.getNode(3).getValue());
        assertEquals(4, mySinglyLinkedListOfInteger.getNode(4).getValue());
        assertEquals("[0 -> 1 -> 2 -> 3 -> 4]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting at First")
    void addAtGivenIndexInsertingAtFirst() {
        mySinglyLinkedListOfInteger.addLast(20);
        mySinglyLinkedListOfInteger.addLast(40);
        mySinglyLinkedListOfInteger.addLast(60);

        MySinglyLinkedList<Integer> spy = spy(mySinglyLinkedListOfInteger);
        spy.add(10, 0);
        verify(spy, times(1)).addFirst(10);
        assertEquals("[10 -> 20 -> 40 -> 60]", spy.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting at End")
    void addAtGivenIndexInsertingAtEnd() {
        mySinglyLinkedListOfInteger.addLast(20);
        mySinglyLinkedListOfInteger.addLast(40);
        mySinglyLinkedListOfInteger.addLast(60);

        MySinglyLinkedList<Integer> spy = spy(mySinglyLinkedListOfInteger);
        spy.add(70, 3);
        verify(spy, times(1)).addLast(70);
        assertEquals("[20 -> 40 -> 60 -> 70]", spy.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting in between")
    void addAtGivenIndexInsertingBetween() {
        mySinglyLinkedListOfInteger.addLast(20);
        mySinglyLinkedListOfInteger.addLast(40);
        mySinglyLinkedListOfInteger.addLast(60);

        mySinglyLinkedListOfInteger.add(30, 1);
        assertEquals("[20 -> 30 -> 40 -> 60]", mySinglyLinkedListOfInteger.toString());
        assertEquals(30, mySinglyLinkedListOfInteger.getNodeValue(1));
    }

    @Test
    @DisplayName("Deletes first node of linked list")
    void deleteFirstNodeOfLinkedList() {
        mySinglyLinkedListOfInteger.addFirst(0);
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.deleteFirst();
        mySinglyLinkedListOfInteger.deleteFirst();

        assertEquals(2, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(3, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals("[2 -> 3]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("Deletes last node of linked list")
    void deleteLastNodeOfLinkedList() {
        mySinglyLinkedListOfInteger.addLast(0);
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addLast(4);
        mySinglyLinkedListOfInteger.deleteFirst();
        mySinglyLinkedListOfInteger.deleteLast();
        mySinglyLinkedListOfInteger.deleteLast();

        assertEquals(1, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals("[1 -> 2]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("verifies that the first node is deleted when the node to be deleted is the first node.")
    void deleteNodeWhileIsFirstNode() {
        mySinglyLinkedListOfInteger.addLast(10);
        mySinglyLinkedListOfInteger.addLast(20);
        mySinglyLinkedListOfInteger.addLast(30);
        mySinglyLinkedListOfInteger.addLast(40);

        MySinglyLinkedList<Integer> spy = spy(mySinglyLinkedListOfInteger);
        spy.delete(0);
        verify(spy, times(1)).deleteFirst();
        assertEquals("[20 -> 30 -> 40]", spy.toString());
    }

    @Test
    @DisplayName("verifies that the last node is deleted when the node to be deleted is the last node.")
    void deleteNodeWhileIsLastNode() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);

        MySinglyLinkedList<Integer> spy = spy(mySinglyLinkedListOfInteger);
        spy.delete(2);
        verify(spy, times(1)).deleteLast();
        assertEquals("[1 -> 2]", spy.toString());
    }

    @Test
    @DisplayName("verifies that the middle node is deleted when the node to be deleted is a middle node.")
    void testDeleteMiddle() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.delete(1);

        assertEquals(2, mySinglyLinkedListOfInteger.size());
        assertEquals(1, mySinglyLinkedListOfInteger.getNodeValue(0));
        assertEquals(3, mySinglyLinkedListOfInteger.getNodeValue(1));
        assertEquals("[1 -> 3]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("verifies that the linked list contains a specified node.")
    void containsANodeInLinkedList() {
        mySinglyLinkedListOfInteger.addLast(0);
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.deleteFirst();
        mySinglyLinkedListOfInteger.deleteFirst();

        assertTrue(mySinglyLinkedListOfInteger.contains(2));
        assertFalse(mySinglyLinkedListOfInteger.contains(1));
    }


    @Test
    @DisplayName("verifies the index of a specified node in the linked list.")
    void indexOfNodeInLinkedList() {
        mySinglyLinkedListOfInteger.addLast(0);
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.deleteLast();
        mySinglyLinkedListOfInteger.deleteFirst();

        assertEquals(0, mySinglyLinkedListOfInteger.indexOf(1));
        assertEquals(1, mySinglyLinkedListOfInteger.indexOf(2));
    }

    @Test
    @DisplayName("verifies the size of the linked list after adding and deleting nodes.")
    void getSizeOfLinkedList() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addLast(4);
        mySinglyLinkedListOfInteger.addFirst(0);
        mySinglyLinkedListOfInteger.deleteLast();
        mySinglyLinkedListOfInteger.deleteFirst();

        assertEquals(3, mySinglyLinkedListOfInteger.size());
    }

    @Test
    @DisplayName("converts the linked list to an array and verifies its correctness.")
    void convertLinkedListToArray() {
        mySinglyLinkedListOfInteger.addLast(1);
        assertEquals("[1]", mySinglyLinkedListOfInteger.toArray().toString());
        mySinglyLinkedListOfInteger.addLast(2);
        assertEquals("[1, 2]", mySinglyLinkedListOfInteger.toArray().toString());
        mySinglyLinkedListOfInteger.addLast(3);
        assertEquals("[1, 2, 3]", mySinglyLinkedListOfInteger.toArray().toString());
        mySinglyLinkedListOfInteger.addLast(4);
        assertEquals("[1, 2, 3, 4]", mySinglyLinkedListOfInteger.toArray().toString());
        mySinglyLinkedListOfInteger.addFirst(0);

        assertEquals("[0, 1, 2, 3, 4]", mySinglyLinkedListOfInteger.toArray().toString());
    }

    @Test
    @DisplayName("verifies the reverse of the linked list with three nodes.")
    void reverseLinkedListWith3Nodes() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.reverse();

        assertEquals(3, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals(1, mySinglyLinkedListOfInteger.getNode(2).getValue());
        assertEquals("[3 -> 2 -> 1]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("verifies the reverse of the linked list with more than three nodes.")
    void reverseLinkedListWithMoreThan3Nodes() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addFirst(0);
        assertEquals("[0 -> 1 -> 2 -> 3]", mySinglyLinkedListOfInteger.toString());
        mySinglyLinkedListOfInteger.reverse();

        assertEquals(3, mySinglyLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, mySinglyLinkedListOfInteger.getNode(1).getValue());
        assertEquals(1, mySinglyLinkedListOfInteger.getNode(2).getValue());
        assertEquals(0, mySinglyLinkedListOfInteger.getNode(3).getValue());
        assertEquals("[3 -> 2 -> 1 -> 0]", mySinglyLinkedListOfInteger.toString());
        mySinglyLinkedListOfInteger.deleteFirst();
        assertEquals("[2 -> 1 -> 0]", mySinglyLinkedListOfInteger.toString());
        mySinglyLinkedListOfInteger.deleteLast();
        assertEquals("[2 -> 1]", mySinglyLinkedListOfInteger.toString());
    }

    @Test
    @DisplayName("Test getting the kth node from the end of a singly linked list")
    void getKthNearestNode() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);

        assertEquals(1, mySinglyLinkedListOfInteger.getKthFromTheEnd(3));
    }

    @Test
    @DisplayName("Test finding the middle node of an odd-length singly linked list")
    void printMiddleForOdd() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addLast(4);
        mySinglyLinkedListOfInteger.addLast(5);

        assertEquals("Middle = 3", mySinglyLinkedListOfInteger.getMiddle());
    }

    @Test
    @DisplayName("Test finding the middle nodes of an even-length singly linked list")
    void printMiddleForEven() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addLast(4);

        assertEquals("Middle = 2, 3", mySinglyLinkedListOfInteger.getMiddle());
    }

    @Test
    @DisplayName("Test detecting if a singly linked list contains a loop")
    void hastLoop() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);
        mySinglyLinkedListOfInteger.addLast(4);
        mySinglyLinkedListOfInteger.createWithLoop();

        assertTrue(mySinglyLinkedListOfInteger.hasLoop());
    }

    @Test
    @DisplayName("Test detecting if a singly linked list does not contain a loop")
    void hastNotLoop() {
        mySinglyLinkedListOfInteger.addLast(1);
        mySinglyLinkedListOfInteger.addLast(2);
        mySinglyLinkedListOfInteger.addLast(3);

        assertFalse(mySinglyLinkedListOfInteger.hasLoop());
    }

    @Test
    @DisplayName("Test using the singly linked list implementation with a different data type.")
    void checkOneOperationForAnotherType() {
        mySinglyLinkedListOfString.addLast("a");
        mySinglyLinkedListOfString.addLast("b");
        mySinglyLinkedListOfString.addLast("c");

        assertEquals("a", mySinglyLinkedListOfString.getNode(0).getValue());
        assertEquals("b", mySinglyLinkedListOfString.getNode(1).getValue());
        assertEquals("c", mySinglyLinkedListOfString.getNode(2).getValue());
        assertEquals("[a -> b -> c]", mySinglyLinkedListOfString.toString());
    }
}