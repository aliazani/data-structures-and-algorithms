package io.github.aliazani.linear.linkedlist.doubly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("Doubly Linked-List")
class MyDoublyLinkedListTest {
    private MyDoublyLinkedList<Integer> myDoublyLinkedList;

    @BeforeEach
    void setUp() {
        myDoublyLinkedList = new MyDoublyLinkedList<>();
    }

    @Test
    @DisplayName("Add nodes at the end of the linked list")
    void addNodesAtLastLinkedListOfInteger() {
        myDoublyLinkedList.addLast(0);
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);

        assertEquals(0, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(1, myDoublyLinkedList.getNode(1).getValue());
        assertEquals(2, myDoublyLinkedList.getNode(2).getValue());
        assertEquals("[0 ↔ 1 ↔ 2]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("Remove a node from a linked list with one node")
    void removeANodeFromLinkedListWithOneNode() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.deleteFirst();
        myDoublyLinkedList.deleteLast();

        assertEquals(0, myDoublyLinkedList.size());
        assertEquals("[]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("Add nodes at the beginning of the linked list")
    void addNodesAtFirst() {
        myDoublyLinkedList.addFirst(3);
        myDoublyLinkedList.addFirst(2);
        myDoublyLinkedList.addFirst(1);
        myDoublyLinkedList.addFirst(0);
        myDoublyLinkedList.addLast(4);

        assertEquals(0, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(1, myDoublyLinkedList.getNode(1).getValue());
        assertEquals(2, myDoublyLinkedList.getNode(2).getValue());
        assertEquals(3, myDoublyLinkedList.getNode(3).getValue());
        assertEquals(4, myDoublyLinkedList.getNode(4).getValue());
        assertEquals("[0 ↔ 1 ↔ 2 ↔ 3 ↔ 4]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting at First")
    void addAtGivenIndexInsertingAtFirst() {
        myDoublyLinkedList.addLast(20);
        myDoublyLinkedList.addLast(40);
        myDoublyLinkedList.addLast(60);

        MyDoublyLinkedList<Integer> spy = spy(myDoublyLinkedList);
        spy.add(10, 0);
        verify(spy, times(1)).addFirst(10);
        assertEquals("[10 ↔ 20 ↔ 40 ↔ 60]", spy.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting at End")
    void addAtGivenIndexInsertingAtEnd() {
        myDoublyLinkedList.addLast(20);
        myDoublyLinkedList.addLast(40);
        myDoublyLinkedList.addLast(60);

        MyDoublyLinkedList<Integer> spy = spy(myDoublyLinkedList);
        spy.add(70, 3);
        verify(spy, times(1)).addLast(70);
        assertEquals("[20 ↔ 40 ↔ 60 ↔ 70]", spy.toString());
    }

    @Test
    @DisplayName("Add node at a given index - Inserting in between")
    void addAtGivenIndexInsertingBetween() {
        myDoublyLinkedList.addLast(20);
        myDoublyLinkedList.addLast(40);
        myDoublyLinkedList.addLast(60);

        myDoublyLinkedList.add(30, 1);
        assertEquals("[20 ↔ 30 ↔ 40 ↔ 60]", myDoublyLinkedList.toString());
        assertEquals(30, myDoublyLinkedList.getNodeValue(1));
    }

    @Test
    @DisplayName("Deletes first node of linked list")
    void deleteFirstNodeOfLinkedList() {
        myDoublyLinkedList.addFirst(0);
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.deleteFirst();
        myDoublyLinkedList.deleteFirst();

        assertEquals(2, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(3, myDoublyLinkedList.getNode(1).getValue());
        assertEquals("[2 ↔ 3]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("Deletes last node of linked list")
    void deleteLastNodeOfLinkedList() {
        myDoublyLinkedList.addLast(0);
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addLast(4);
        myDoublyLinkedList.deleteFirst();
        myDoublyLinkedList.deleteLast();
        myDoublyLinkedList.deleteLast();

        assertEquals(1, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(2, myDoublyLinkedList.getNode(1).getValue());
        assertEquals("[1 ↔ 2]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("verifies that the first node is deleted when the node to be deleted is the first node.")
    void deleteNodeWhileIsFirstNode() {
        myDoublyLinkedList.addLast(10);
        myDoublyLinkedList.addLast(20);
        myDoublyLinkedList.addLast(30);
        myDoublyLinkedList.addLast(40);

        MyDoublyLinkedList<Integer> spy = spy(myDoublyLinkedList);
        spy.delete(0);
        verify(spy, times(1)).deleteFirst();
        assertEquals("[20 ↔ 30 ↔ 40]", spy.toString());
    }

    @Test
    @DisplayName("verifies that the last node is deleted when the node to be deleted is the last node.")
    void deleteNodeWhileIsLastNode() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);

        MyDoublyLinkedList<Integer> spy = spy(myDoublyLinkedList);
        spy.delete(2);
        verify(spy, times(1)).deleteLast();
        assertEquals("[1 ↔ 2]", spy.toString());
    }

    @Test
    @DisplayName("verifies that the middle node is deleted when the node to be deleted is a middle node.")
    void testDeleteMiddle() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.delete(1);

        assertEquals(2, myDoublyLinkedList.size());
        assertEquals(1, myDoublyLinkedList.getNodeValue(0));
        assertEquals(3, myDoublyLinkedList.getNodeValue(1));
        assertEquals("[1 ↔ 3]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("verifies that the linked list contains a specified node.")
    void containsANodeInLinkedList() {
        myDoublyLinkedList.addLast(0);
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.deleteFirst();
        myDoublyLinkedList.deleteFirst();

        assertTrue(myDoublyLinkedList.contains(2));
        assertFalse(myDoublyLinkedList.contains(1));
    }


    @Test
    @DisplayName("verifies the index of a specified node in the linked list.")
    void indexOfNodeInLinkedList() {
        myDoublyLinkedList.addLast(0);
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.deleteLast();
        myDoublyLinkedList.deleteFirst();

        assertEquals(0, myDoublyLinkedList.indexOf(1));
        assertEquals(1, myDoublyLinkedList.indexOf(2));
    }

    @Test
    @DisplayName("verifies the size of the linked list after adding and deleting nodes.")
    void getSizeOfLinkedList() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addLast(4);
        myDoublyLinkedList.addFirst(0);
        myDoublyLinkedList.deleteLast();
        myDoublyLinkedList.deleteFirst();

        assertEquals(3, myDoublyLinkedList.size());
    }

    @Test
    @DisplayName("converts the linked list to an array and verifies its correctness.")
    void convertLinkedListToArray() {
        myDoublyLinkedList.addLast(1);
        assertEquals("[1]", myDoublyLinkedList.toArray().toString());
        myDoublyLinkedList.addLast(2);
        assertEquals("[1, 2]", myDoublyLinkedList.toArray().toString());
        myDoublyLinkedList.addLast(3);
        assertEquals("[1, 2, 3]", myDoublyLinkedList.toArray().toString());
        myDoublyLinkedList.addLast(4);
        assertEquals("[1, 2, 3, 4]", myDoublyLinkedList.toArray().toString());
        myDoublyLinkedList.addFirst(0);

        assertEquals("[0, 1, 2, 3, 4]", myDoublyLinkedList.toArray().toString());
    }

    @Test
    @DisplayName("verifies the reverse of the linked list with three nodes.")
    void reverseLinkedListWith3Nodes() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.reverse();

        assertEquals(3, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(2, myDoublyLinkedList.getNode(1).getValue());
        assertEquals(1, myDoublyLinkedList.getNode(2).getValue());
        assertEquals("[3 ↔ 2 ↔ 1]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("verifies the reverse of the linked list with more than three nodes.")
    void reverseLinkedListWithMoreThan3Nodes() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addFirst(0);
        assertEquals("[0 ↔ 1 ↔ 2 ↔ 3]", myDoublyLinkedList.toString());
        myDoublyLinkedList.reverse();

        assertEquals(3, myDoublyLinkedList.getNode(0).getValue());
        assertEquals(2, myDoublyLinkedList.getNode(1).getValue());
        assertEquals(1, myDoublyLinkedList.getNode(2).getValue());
        assertEquals(0, myDoublyLinkedList.getNode(3).getValue());
        assertEquals("[3 ↔ 2 ↔ 1 ↔ 0]", myDoublyLinkedList.toString());
        myDoublyLinkedList.deleteFirst();
        assertEquals("[2 ↔ 1 ↔ 0]", myDoublyLinkedList.toString());
        myDoublyLinkedList.deleteLast();
        assertEquals("[2 ↔ 1]", myDoublyLinkedList.toString());
    }

    @Test
    @DisplayName("Test getting the kth node from the end of a singly linked list")
    void getKthNearestNode() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);

        assertEquals(1, myDoublyLinkedList.getKthFromTheEnd(3));
    }

    @Test
    @DisplayName("Test finding the middle node of an odd-length singly linked list")
    void printMiddleForOdd() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addLast(4);
        myDoublyLinkedList.addLast(5);

        assertEquals("Middle = 3", myDoublyLinkedList.getMiddle());
    }

    @Test
    @DisplayName("Test finding the middle nodes of an even-length singly linked list")
    void printMiddleForEven() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addLast(4);

        assertEquals("Middle = 2, 3", myDoublyLinkedList.getMiddle());
    }

    @Test
    @DisplayName("Test detecting if a singly linked list contains a loop")
    void hastLoop() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);
        myDoublyLinkedList.addLast(4);
        myDoublyLinkedList.createWithLoop();

        assertTrue(myDoublyLinkedList.hasLoop());
    }

    @Test
    @DisplayName("Test detecting if a singly linked list does not contain a loop")
    void hastNotLoop() {
        myDoublyLinkedList.addLast(1);
        myDoublyLinkedList.addLast(2);
        myDoublyLinkedList.addLast(3);

        assertFalse(myDoublyLinkedList.hasLoop());
    }
}