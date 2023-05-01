package io.github.aliazani.linear.linkedlist.doubly;

import io.github.aliazani.linear.arrays.MyArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("Doubly Linked-List")
class MyDoublyLinkedListTest {
    private MyDoublyLinkedList<Integer> listOfIntegers;
    private MyDoublyLinkedList<String> listOfStrings;

    @BeforeEach
    void setUp() {
        listOfIntegers = new MyDoublyLinkedList<>();
        listOfStrings = new MyDoublyLinkedList<>();
    }

    @Test
    @DisplayName("Add a node at the beginning of the list - " +
            "When the list is empty - " +
            "Insert node to empty list")
    void addFirst_listIsEmpty_insertIntoEmptyList() {
        listOfIntegers.addFirst(10);

        assertEquals("[10]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Add a node at the beginning of the list - " +
            "When the list is not empty - " +
            "Insert node at the beginning of the list")
    void addFirst_listIsNotEmpty_insertAtTheBeginningOfList() {
        listOfIntegers.addFirst(10);
        listOfIntegers.addFirst(20);

        assertEquals("[20 ↔ 10]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Add a node at the end of the list - " +
            "When the list is empty - " +
            "Insert node to empty list")
    void addLast_listIsEmpty_insertIntoEmptyList() {
        listOfIntegers.addLast(10);

        assertEquals("[10]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Add a node at the end of the list - " +
            "When the list is not empty - " +
            "Insert node at the end of the list")
    void addLast_listIsNotEmpty_insertAtTheEndOfList() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);

        assertEquals("[10 ↔ 20]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Add a node at the given index - " +
            "When index is (less than 0) or (greater than list size)  - " +
            "Throw IndexOutOfBoundsException")
    void add_indexIsLessThanZeroOrGreaterThanListSize_throwIndexOutOfBounds() {
        listOfIntegers.add(20, 0);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.add(10, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.add(10, 2));
    }

    @Test
    @DisplayName("Add a node at the given index - " +
            "When index is 0 (at the beginning of the list) - " +
            "Insert node at the beginning of the list")
    void add_indexIsZero_insertAtTheBeginningOfList() {
        MyDoublyLinkedList<Integer> spy = spy(listOfIntegers);

        spy.add(10, 0);

        verify(spy, times(1)).addFirst(10);
        assertEquals("[10]", spy.toString());
    }

    @Test
    @DisplayName("Add a node at the given index - " +
            "When index is size (at the end of the list) - " +
            "Insert node at the end of the list")
    void add_indexIsEqualToSize_insertAtTheEndOfList() {
        listOfIntegers.add(10, 0);
        listOfIntegers.add(20, 1);
        MyDoublyLinkedList<Integer> spy = spy(listOfIntegers);

        spy.add(30, 2);

        verify(spy, times(1)).addLast(30);
        assertEquals("[10 ↔ 20 ↔ 30]", spy.toString());
    }

    @Test
    @DisplayName("Add a node at the given index - " +
            "When index is between 1 and (size - 1) - " +
            "Insert node between two nodes")
    void add_indexBetweenOneAndSizeMinusOne_insertBetweenTwoNodes() {
        listOfIntegers.add(10, 0);
        listOfIntegers.add(20, 1);

        listOfIntegers.add(15, 1);

        assertEquals("[10 ↔ 15 ↔ 20]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Remove the first node from the list - " +
            "When the list is empty - " +
            "Throw NoSuchElementException")
    void deleteFirst_listIsEmpty_throwNoSuchElement() {
        assertEquals("[]", listOfIntegers.toString());
        assertThrows(NoSuchElementException.class, () -> listOfIntegers.deleteFirst());
    }

    @Test
    @DisplayName("Remove the first node from the list - " +
            "When the list has one item - " +
            "Remove that item and make the list empty")
    void deleteFirst_listHasOneItem_makeTheListEmptyByRemovingItem() {
        listOfIntegers.addLast(10);

        listOfIntegers.deleteFirst();

        assertEquals("[]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Remove the first node from the list - " +
            "When the list has more than one item - " +
            "Remove the first item from the list")
    void deleteFirst_listHasMoreThanOneItem_removeFirstItem() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        listOfIntegers.deleteFirst();

        assertEquals("[20 ↔ 30]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Remove the last node from the list - " +
            "When the list is empty - " +
            "Throw NoSuchElementException")
    void deleteLast_listIsEmpty_throwNoSuchElement() {
        assertEquals("[]", listOfIntegers.toString());
        assertThrows(NoSuchElementException.class, () -> listOfIntegers.deleteLast());
    }

    @Test
    @DisplayName("Remove the last node from the list - " +
            "When the list has one item - " +
            "Remove that item and make the list empty")
    void deleteLast_listHasOneItem_makeTheListEmptyByRemovingItem() {
        listOfIntegers.addLast(10);

        listOfIntegers.deleteLast();

        assertEquals("[]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Remove the last node from the list - " +
            "When the list has more than one item - " +
            "Remove the last item from the list")
    void deleteLast_listHasMoreThanOneItem_removeLastItem() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        listOfIntegers.deleteLast();

        assertEquals("[10 ↔ 20]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Remove a node at the given index - " +
            "When index is (less than 0) or (greater than list size or equal to list size)  - " +
            "Throw IndexOutOfBoundsException")
    void delete_indexIsLessThanZeroOrGreaterEqualToListSize_throwIndexOutOfBounds() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.delete(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.delete(2));
    }

    @Test
    @DisplayName("Remove a node at the given index - " +
            "When index is 0 (at the beginning of the list) - " +
            "Remove node at the beginning of the list")
    void delete_indexIsZero_removeAtTheBeginningOfList() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        MyDoublyLinkedList<Integer> spy = spy(listOfIntegers);

        spy.delete(0);

        verify(spy, times(1)).deleteFirst();
        assertEquals("[20]", spy.toString());
    }

    @Test
    @DisplayName("Remove a node at the given index - " +
            "When index is (size - 1) - " +
            "Remove node at the end of the list")
    void delete_indexIsEqualToSize_removeAtTheEndOfList() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        MyDoublyLinkedList<Integer> spy = spy(listOfIntegers);

        spy.delete(1);

        verify(spy, times(1)).deleteLast();
        assertEquals("[10]", spy.toString());
    }

    @Test
    @DisplayName("Remove a node at the given index - " +
            "When index is between 1 and (size - 2) - " +
            "Remove node between two nodes")
    void delete_indexBetweenOneAndSizeMinusTwo_removeNodeBetweenTwoNodes() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        listOfIntegers.delete(1);

        assertEquals("[10 ↔ 30]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Return the index of first occurrence of an item - " +
            "When item is null - " +
            "Return (-1)")
    void indexOf_itemIsNull_returnNegativeOne() {
        listOfIntegers.addLast(10);

        assertEquals(-1, listOfIntegers.indexOf(null));
    }

    @Test
    @DisplayName("Return the index of first occurrence of an item - " +
            "When item is at the list - " +
            "Return index of given item")
    void indexOf_itemIsAtList_returnIndexOfItem() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        assertEquals(0, listOfIntegers.indexOf(10));
        assertEquals(2, listOfIntegers.indexOf(20));
    }

    @Test
    @DisplayName("Return the index of first occurrence of an item - " +
            "When item is not at the list - " +
            "Return (-1)")
    void indexOf_itemIsNotAtList_returnNegativeOne() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);

        assertEquals(-1, listOfIntegers.indexOf(3));
    }

    @Test
    @DisplayName("Check that the list contains a specified item - " +
            "When the list does not contain the item -" +
            "Return false")
    void contains_listDoesNotContainItem_returnFalse() {
        listOfIntegers.addLast(10);

        assertFalse(listOfIntegers.contains(20));
    }

    @Test
    @DisplayName("Check that the list contains a specified item - " +
            "When the list contains the item -" +
            "Return true")
    void contains_listContainsItem_returnTrue() {
        listOfIntegers.addLast(10);

        assertTrue(listOfIntegers.contains(10));
    }

    @Test
    @DisplayName("Return number of items in the list" +
            "When list is empty -" +
            "Return 0")
    void size_listIsEmpty_returnZero() {
        assertEquals(0, listOfIntegers.size());
    }

    @Test
    @DisplayName("Return number of items in the list" +
            "When list is not empty -" +
            "Return the size of items in list")
    void size_listIsNotEmpty_returnTheNumberOfItemsInList() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);
        listOfIntegers.deleteFirst();

        assertEquals(2, listOfIntegers.size());
    }

    @Test
    @DisplayName("Detect if a list contains a loop - " +
            "When the list is not empty and does not have a loop" +
            "Return false")
    void hasLoop_listIsNotEmptyAndDoesNotHaveALoop_returnFalse() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(20);

        assertFalse(listOfIntegers.hasLoop());
    }

    @Test
    @DisplayName("Detect if a list contains a loop - " +
            "When the list is not empty and have a loop" +
            "Return false")
    void hasLoop_listIsNotEmptyAndHaveALoop_returnTrue() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        listOfIntegers.getNode(2).setNext(listOfIntegers.getNode(0));
        listOfIntegers.getNode(0).setPrev(listOfIntegers.getNode(2));

        assertTrue(listOfIntegers.hasLoop());
    }

    @Test
    @DisplayName("Convert the list to an array - " +
            "When list is empty - " +
            "Return empty array")
    void toArray_listIsEmpty_returnEmptyArray() {
        MyArray<Integer> array = listOfIntegers.toArray();

        assertEquals(MyArray.class, array.getClass());
        assertEquals("[]", array.toString());
    }

    @Test
    @DisplayName("Convert the list to an array - " +
            "When list is not empty - " +
            "Return an array which is converted from a list")
    void toArray_listIsNotEmpty_returnConvertedList() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);
        MyArray<Integer> array = listOfIntegers.toArray();

        assertEquals(MyArray.class, array.getClass());
        assertEquals("[1, 2, 3]", array.toString());
    }

    @Test
    @DisplayName("Reverse the order of items in list - " +
            "When list is empty - " +
            "Do nothing")
    void reverse_listIsEmpty_doNothing() {
        listOfIntegers.reverse();

        assertEquals("[]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Reverse the order of items in list - " +
            "When list has one item - " +
            "Do nothing")
    void reverse_listHasOneItem_doNothing() {
        listOfIntegers.addLast(10);

        listOfIntegers.reverse();

        assertEquals("[10]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Reverse the order of items in list - " +
            "When list has three items - " +
            "Reverse the order of items")
    void reverse_listHasThreeItems_reverseTheOrderOfItems() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);

        listOfIntegers.reverse();

        assertEquals(3, listOfIntegers.getNode(0).getValue());
        assertEquals(2, listOfIntegers.getNode(1).getValue());
        assertEquals(1, listOfIntegers.getNode(2).getValue());
        assertEquals("[3 ↔ 2 ↔ 1]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Reverse the order of items in list - " +
            "When list has more than three items - " +
            "Reverse the order of items")
    void reverse_listHasMoreThanThreeItems_reverseTheOrderOfItems() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);
        listOfIntegers.addFirst(0);

        listOfIntegers.reverse();

        assertEquals(3, listOfIntegers.getNode(0).getValue());
        assertEquals(2, listOfIntegers.getNode(1).getValue());
        assertEquals(1, listOfIntegers.getNode(2).getValue());
        assertEquals(0, listOfIntegers.getNode(3).getValue());
        assertEquals("[3 ↔ 2 ↔ 1 ↔ 0]", listOfIntegers.toString());
        listOfIntegers.deleteFirst();
        assertEquals("[2 ↔ 1 ↔ 0]", listOfIntegers.toString());
        listOfIntegers.deleteLast();
        assertEquals("[2 ↔ 1]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Reverse the order of items in list and delete first and last item - " +
            "When list has more than three items - " +
            "Reverse the order of items and after properly delete the first and last item")
    void reverseAndDelete_listHasMoreThanThreeItems_reverseTheOrderOfItemsAndDeleteTheProperChangedNode() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);
        listOfIntegers.addFirst(0);

        listOfIntegers.reverse();

        assertEquals(3, listOfIntegers.getNode(0).getValue());
        assertEquals(2, listOfIntegers.getNode(1).getValue());
        assertEquals(1, listOfIntegers.getNode(2).getValue());
        assertEquals(0, listOfIntegers.getNode(3).getValue());
        assertEquals("[3 ↔ 2 ↔ 1 ↔ 0]", listOfIntegers.toString());

        listOfIntegers.deleteFirst();
        assertEquals("[2 ↔ 1 ↔ 0]", listOfIntegers.toString());

        listOfIntegers.deleteLast();
        assertEquals("[2 ↔ 1]", listOfIntegers.toString());
    }

    @Test
    @DisplayName("Get the kth node from the end of a list - " +
            "When list is empty - " +
            "Throw IllegalArgumentException")
    void getKthFromTheEnd_listIsEmpty_throwIllegalArgument() {

        assertThrows(IllegalArgumentException.class, () -> listOfIntegers.getKthFromTheEnd(1));
    }

    @Test
    @DisplayName("Get the kth node from the end of a list - " +
            "When k is greater than number of items in list - " +
            "Throw IllegalArgumentException")
    void getKthFromTheEnd_KIsGreaterThanSize_throwIllegalArgument() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);

        assertThrows(IllegalArgumentException.class, () -> listOfIntegers.getKthFromTheEnd(3));
    }

    @Test
    @DisplayName("Get the kth node from the end of a list - " +
            "When list is not empty and k is less than number of items in list - " +
            "Return the k-th item from the end of the list")
    void getKthFromTheEnd_listIsNotEmptyAndKIsLessThanSize_returnTheKthItemFromTheEnd() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);

        assertEquals(10, listOfIntegers.getKthFromTheEnd(3));
    }

    @Test
    @DisplayName("Find the middle node of a list - " +
            "When list is empty - " +
            "Throw IllegalArgumentException")
    void getMiddle_listIsEmpty_ThrowIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> listOfIntegers.getMiddle());
    }

    @Test
    @DisplayName("Find the middle node of a list - " +
            "When list's length is odd - " +
            "Return middle item")
    void getMiddle_listSizeIsOdd_returnMiddleItem() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);
        listOfIntegers.addLast(4);
        listOfIntegers.addLast(5);

        assertEquals("Middle = 3", listOfIntegers.getMiddle());
    }

    @Test
    @DisplayName("Find the middle node of a list - " +
            "When list's length is even - " +
            "Return two middle items")
    void getMiddle_listSizeIsEven_returnTwoMiddleItems() {
        listOfIntegers.addLast(1);
        listOfIntegers.addLast(2);
        listOfIntegers.addLast(3);
        listOfIntegers.addLast(4);

        assertEquals("Middle = 2, 3", listOfIntegers.getMiddle());
    }

    @Test
    @DisplayName("Return the node at the specified index in this list - " +
            "When list is empty - " +
            "Throw IndexOutOfBoundsException")
    void getNode_listIsEmpty_throwIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.getNode(0));
    }

    @Test
    @DisplayName("Return the node at the specified index in this list - " +
            "When index is (greater than number of items in list - 1) or (less than 0) - " +
            "Throw IndexOutOfBoundsException")
    void getNode_indexIsGreaterThanSize_throwIndexOutOfBounds() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);

        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.getNode(2));
        assertThrows(IndexOutOfBoundsException.class, () -> listOfIntegers.getNode(-1));
    }

    @Test
    @DisplayName("Return the node at the specified index in this list - " +
            "When index is (less than or equal to the number of items in list - 1) or (greater than 0) - " +
            "Return node at the given index")
    void getNode_indexIsGreaterThanZeroAndLessThanSizeMinusOne_returnNodeAtTheGivenIndex() {
        listOfIntegers.addLast(10);
        assertEquals(10, listOfIntegers.getNode(0).getValue());

        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);
        assertEquals(20, listOfIntegers.getNode(1).getValue());
    }

    @Test
    @DisplayName("Return the vale of a node at the specified index in this list - " +
            "When is called - " +
            "Return the value of the node and call getNode")
    void getNodeValue_whenIsCalled_returnTheValueOfANodeAtTheGivenIndexAndCallGetNodeMethod() {
        listOfIntegers.addLast(10);
        listOfIntegers.addLast(20);
        listOfIntegers.addLast(30);
        MyDoublyLinkedList<Integer> spy = spy(listOfIntegers);

        Integer nodeValue = spy.getNodeValue(1);

        verify(spy, times(1)).getNode(1);
        assertEquals(20, nodeValue);
    }

    @Test
    @DisplayName("toString")
    void testToString() {
        listOfIntegers.addLast(10);
        assertEquals("[10]", listOfIntegers.toString());
        listOfIntegers.addLast(20);
        assertEquals("[10 ↔ 20]", listOfIntegers.toString());
        listOfIntegers.addLast(30);
        assertEquals("[10 ↔ 20 ↔ 30]", listOfIntegers.toString());

    }

    @Test
    @DisplayName("Test using the list with a different data type.")
    void checkOneOperationForAnotherType() {
        listOfStrings.addLast("a");
        listOfStrings.addLast("b");
        listOfStrings.addLast("c");

        assertEquals("a", listOfStrings.getNode(0).getValue());
        assertEquals("b", listOfStrings.getNode(1).getValue());
        assertEquals("c", listOfStrings.getNode(2).getValue());
        assertEquals("[a ↔ b ↔ c]", listOfStrings.toString());
    }
}