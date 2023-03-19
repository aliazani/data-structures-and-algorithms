package io.github.aliazani.linear.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    private MyLinkedList<Integer> myLinkedListOfInteger;
    private MyLinkedList<String> myLinkedListOfString;

    @BeforeEach
    public void createLinkedList() {
        myLinkedListOfInteger = new MyLinkedList<>();
        myLinkedListOfString = new MyLinkedList<>();
    }

    @Test
    void addNodesAtLastLinkedListOfInteger() {
        myLinkedListOfInteger.addLast(0);
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);

        assertEquals(0, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(1, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals(2, myLinkedListOfInteger.getNode(2).getValue());
        assertEquals("[0 -> 1 -> 2]", myLinkedListOfInteger.toString());
    }

    @Test
    void removeANodeFromLinkedListWithOneNode() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.deleteFirst();
        myLinkedListOfInteger.deleteLast();

        assertEquals(0, myLinkedListOfInteger.size());
        assertEquals("[]", myLinkedListOfInteger.toString());
    }

    @Test
    void addNodesAtFirst() {
        myLinkedListOfInteger.addFirst(3);
        myLinkedListOfInteger.addFirst(2);
        myLinkedListOfInteger.addFirst(1);
        myLinkedListOfInteger.addFirst(0);
        myLinkedListOfInteger.addLast(4);

        assertEquals(0, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(1, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals(2, myLinkedListOfInteger.getNode(2).getValue());
        assertEquals(3, myLinkedListOfInteger.getNode(3).getValue());
        assertEquals(4, myLinkedListOfInteger.getNode(4).getValue());
        assertEquals("[0 -> 1 -> 2 -> 3 -> 4]", myLinkedListOfInteger.toString());
    }

    @Test
    void deleteFirstNodeOfLinkedList() {
        myLinkedListOfInteger.addFirst(0);
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.deleteFirst();
        myLinkedListOfInteger.deleteFirst();

        assertEquals(2, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(3, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals("[2 -> 3]", myLinkedListOfInteger.toString());
    }

    @Test
    void deleteLastNodeOfLinkedList() {
        myLinkedListOfInteger.addLast(0);
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);
        myLinkedListOfInteger.deleteFirst();
        myLinkedListOfInteger.deleteLast();
        myLinkedListOfInteger.deleteLast();

        assertEquals(1, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals("[1 -> 2]", myLinkedListOfInteger.toString());
    }

    @Test
    void containsANodeInLinkedList() {
        myLinkedListOfInteger.addLast(0);
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.deleteFirst();
        myLinkedListOfInteger.deleteFirst();

        assertTrue(myLinkedListOfInteger.contains(2));
        assertFalse(myLinkedListOfInteger.contains(1));
    }


    @Test
    void indexOfNodeInLinkedList() {
        myLinkedListOfInteger.addLast(0);
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.deleteLast();
        myLinkedListOfInteger.deleteFirst();

        assertEquals(0, myLinkedListOfInteger.indexOf(1));
        assertEquals(1, myLinkedListOfInteger.indexOf(2));
    }

    @Test
    void getSizeOfLinkedList() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);
        myLinkedListOfInteger.addFirst(0);
        myLinkedListOfInteger.deleteLast();
        myLinkedListOfInteger.deleteFirst();

        assertEquals(3, myLinkedListOfInteger.size());
    }

    @Test
    void convertLinkedListToArray() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);
        myLinkedListOfInteger.addFirst(0);

        assertEquals("[0, 1, 2, 3, 4]", myLinkedListOfInteger.toArray().toString());
    }

    @Test
    void reverseLinkedListWith3Nodes() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.reverse();

        assertEquals(3, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals(1, myLinkedListOfInteger.getNode(2).getValue());
        assertEquals("[3 -> 2 -> 1]", myLinkedListOfInteger.toString());
    }

    @Test
    void reverseLinkedListWithMoreThan3Nodes() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addFirst(0);
        myLinkedListOfInteger.reverse();

        assertEquals(3, myLinkedListOfInteger.getNode(0).getValue());
        assertEquals(2, myLinkedListOfInteger.getNode(1).getValue());
        assertEquals(1, myLinkedListOfInteger.getNode(2).getValue());
        assertEquals(0, myLinkedListOfInteger.getNode(3).getValue());
        assertEquals("[3 -> 2 -> 1 -> 0]", myLinkedListOfInteger.toString());

    }

    @Test
    void getKthNearestNode() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);

        assertEquals(1, myLinkedListOfInteger.getKthFromTheEnd(3));
    }

    @Test
    void printMiddleForOdd() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);
        myLinkedListOfInteger.addLast(5);

        assertEquals("Middle = 3", myLinkedListOfInteger.getMiddle());
    }

    @Test
    void printMiddleForEven() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);

        assertEquals("Middle = 2, 3", myLinkedListOfInteger.getMiddle());
    }

    @Test
    void hastLoop() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);
        myLinkedListOfInteger.addLast(4);
        myLinkedListOfInteger.createWithLoop();

        assertTrue(myLinkedListOfInteger.hasLoop());
    }

    @Test
    void hastNotLoop() {
        myLinkedListOfInteger.addLast(1);
        myLinkedListOfInteger.addLast(2);
        myLinkedListOfInteger.addLast(3);

        assertFalse(myLinkedListOfInteger.hasLoop());
    }

    @Test
    void checkOneOperationForAnotherType() {
        myLinkedListOfString.addLast("a");
        myLinkedListOfString.addLast("b");
        myLinkedListOfString.addLast("c");

        assertEquals("a", myLinkedListOfString.getNode(0).getValue());
        assertEquals("b", myLinkedListOfString.getNode(1).getValue());
        assertEquals("c", myLinkedListOfString.getNode(2).getValue());
        assertEquals("[a -> b -> c]", myLinkedListOfString.toString());
    }
}
