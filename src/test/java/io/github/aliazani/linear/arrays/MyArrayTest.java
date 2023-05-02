package io.github.aliazani.linear.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyArrayTest {
    private MyArray<Integer> integerArray;

    @BeforeEach
    void setup() {
        integerArray = new MyArray<>(3);
    }

    @Test
    @DisplayName("insert - " +
            "When is called - " +
            "Should insert an item to the end of the array and call insertAt(size, item)")
    void insert_isCalled_callInsertAtMethod() {
        MyArray<Integer> spy = spy(integerArray);

        spy.insert(10);
        spy.insert(20);
        verify(spy, times(1)).insertAt(0, 10);

        assertEquals("[10, 20]", spy.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is negative - " +
            "Should throw IndexOutOfBoundsException")
    void insertAt_indexIsNegative_throwIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.insertAt(-1, 10));
    }

    @Test
    @DisplayName("insertAt - " +
            "When index greater than array size - " +
            "Should throw IndexOutOfBoundsException")
    void insertAt_indexIsGreaterThanSize_throwIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.insertAt(3, 10));
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is equal to size and array is not full - " +
            "Should insert an item at the end of the array")
    void insertAt_indexIsEqualToSizeAndArrayIsNotFull_insertAnItemAtTheEndOfTheArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(1, 20);
        integerArray.insertAt(2, 30);

        assertEquals("[10, 20, 30]", integerArray.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is equal to size and array is full - " +
            "Should insert an item at the end of the resized array")
    void insertAt_indexIsEqualToSizeAndArrayIsFull_insertAnItemAtTheEndOfTheResizedTheArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(1, 20);
        integerArray.insertAt(2, 30);
        integerArray.insertAt(3, 40);

        assertEquals("[10, 20, 30, 40]", integerArray.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is equal 0 and array is not full - " +
            "Should insert an item at the beginning of the array")
    void insertAt_indexIsEqualToZeroAndArrayIsNotFull_insertAnItemAtTheBeginningOfTheArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(0, 20);
        integerArray.insertAt(0, 30);

        assertEquals("[30, 20, 10]", integerArray.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is equal 0 and array is full - " +
            "Should insert an item at the beginning of the resized array")
    void insertAt_indexIsEqualToZeroAndArrayIsFull_insertAnItemAtTheBeginningOfTheResizedTheArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(0, 20);
        integerArray.insertAt(0, 30);
        integerArray.insertAt(0, 40);

        assertEquals("[40, 30, 20, 10]", integerArray.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is between 1 and (size - 1) and array is not full - " +
            "Should insert an item at the given index of the array")
    void insertAt_indexIsBetweenOneAndSizeMinusOneAndArrayIsNotFull_insertAnItemAtTheGivenIndexOfTheArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(1, 30);
        integerArray.insertAt(1, 20);

        assertEquals("[10, 20, 30]", integerArray.toString());
    }

    @Test
    @DisplayName("insertAt - " +
            "When index is between 1 and (size - 1) and array is full - " +
            "Should insert an item at the given index of the resized array")
    void insertAt_indexIsBetweenOneAndSizeMinusOneAndArrayIsFull_insertAnItemAtTheGivenIndexOfTheResizedArray() {
        integerArray.insertAt(0, 10);
        integerArray.insertAt(1, 30);
        integerArray.insertAt(1, 20);
        integerArray.insertAt(1, 15);

        assertEquals("[10, 15, 20, 30]", integerArray.toString());
    }

    @Test
    @DisplayName("removeAt - " +
            "When index is negative - " +
            "Should throw IndexOutOfBoundsException")
    void removeAt_indexIsNegative_throwIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.removeAt(-1));
    }

    @Test
    @DisplayName("removeAt - " +
            "When index greater than or equal to array size - " +
            "Should throw IndexOutOfBoundsException")
    void removeAt_indexIsGreaterOrEqualToSize_throwIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.removeAt(1));
        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.removeAt(3));
    }

    @Test
    @DisplayName("removeAt - " +
            "When index is equal to (size - 1) - " +
            "Should remove the item at the end of the array")
    void removeAt_indexIsEqualToSizeMinusOne_removeTheLastItemOfArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);

        integerArray.removeAt(2);

        assertEquals("[1, 2]", integerArray.toString());
    }

    @Test
    @DisplayName("removeAt - " +
            "When index is equal to 0 - " +
            "Should remove the item at the beginning of the array")
    void removeAt_indexIsEqualToZero_removeTheFirstItemOfArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);

        integerArray.removeAt(0);

        assertEquals("[2, 3]", integerArray.toString());
    }

    @Test
    @DisplayName("removeAt - " +
            "When index is between 1 and (size - 2) - " +
            "Should remove the item at the given index of the array")
    void removeAt_indexIsBetweenOneAndSizeMinusTwo_removeTheItemAtTheGivenIndexOfArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);

        integerArray.removeAt(1);

        assertEquals("[1, 3]", integerArray.toString());
    }

    @Test
    @DisplayName("removeAt - " +
            "When size is equal to (length of array / 4) - " +
            "Should remove item at given index and resize array to (array length / 2)")
    void removeAt_sizeIsOneForthOfArrayLength_removeTheItemAtTheGivenIndexOfArrayAndShrinkArraySizeByDividingItsLengthByTwo() {
        MyArray<Integer> spy = spy(integerArray);
        spy.insert(1);
        spy.insert(2);
        spy.insert(3);
        spy.insert(4);
        spy.insert(5);
        spy.insert(6);
        spy.insert(7);

        spy.removeAt(0);
        spy.removeAt(0);
        spy.removeAt(0);
        // shrink and remove
        spy.removeAt(0);
        assertEquals("[5, 6, 7]", spy.toString());
    }

    @Test
    @DisplayName("indexOf - " +
            "When array contains the item - " +
            "Should return the index of given item")
    void indexOf_arrayContainsTheItem_returnIndexOfGivenItem() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);

        assertEquals(1, integerArray.indexOf(2));
    }

    @Test
    @DisplayName("indexOf - " +
            "When array does not contain the item - " +
            "Should return (-1)")
    void indexOf_arrayDoesNotContainTheItem_returnNegativeOne() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);

        assertEquals(-1, integerArray.indexOf(4));
    }

    @Test
    @DisplayName("indexOf - " +
            "When array is empty - " +
            "Should return the empty array")
    void reverse_arrayIsEmpty_returnEmptyArray() {
        MyArray<Integer> reversed = integerArray.reverse();

        assertEquals("[]", reversed.toString());
    }

    @Test
    @DisplayName("indexOf - " +
            "When array has one item - " +
            "Should return the original array with one item")
    void reverse_arrayHasOneItem_returnOriginalArray() {
        integerArray.insert(10);

        MyArray<Integer> reversed = integerArray.reverse();

        assertEquals("[10]", reversed.toString());
    }

    @Test
    @DisplayName("indexOf - " +
            "When array has more than one item - " +
            "Should return the reversed array")
    void reverse_arrayHasMoreThanOneItem_returnReversedArray() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);

        MyArray<Integer> reversed = integerArray.reverse();

        assertEquals("[30, 20, 10]", reversed.toString());
    }

    @Test
    @DisplayName("max - " +
            "When array is empty - " +
            "Should throw NoSuchElementException")
    void max_arrayIsEmpty_throwNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> integerArray.max());
    }

    @Test
    @DisplayName("max - " +
            "When array is not empty - " +
            "Should return maximum item")
    void max_arrayIsNotEmpty_returnMaxItem() {
        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);

        assertEquals(30, integerArray.max());
    }

    @Test
    @DisplayName("intersect - " +
            "When both arrays are empty - " +
            "Should return an empty array")
    void intersect_bothArraysAreEmpty_returnEmptyArray() {
        MyArray<Integer> otherArray = new MyArray<>(2);

        MyArray<Integer> intersection = integerArray.intersect(otherArray);

        assertEquals("[]", intersection.toString());
    }

    @Test
    @DisplayName("intersect - " +
            "When this array is empty - " +
            "Should return an empty array")
    void intersect_thisArrayIsEmpty_returnEmptyArray() {
        MyArray<Integer> otherArray = new MyArray<>(3);
        otherArray.insert(10);
        otherArray.insert(20);
        otherArray.insert(30);

        MyArray<Integer> intersection = integerArray.intersect(otherArray);

        assertEquals("[]", intersection.toString());
    }

    @Test
    @DisplayName("intersect - " +
            "When the other array is empty - " +
            "Should return an empty array")
    void intersect_otherArrayIsEmpty_returnEmptyArray() {
        MyArray<Integer> emptyArray = new MyArray<>(3);

        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);

        MyArray<Integer> intersection = integerArray.intersect(emptyArray);

        assertEquals("[]", intersection.toString());
    }

    @Test
    @DisplayName("intersect - " +
            "When there are no common elements - " +
            "Should return an empty array")
    void intersect_noCommonElements_returnEmptyArray() {
        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);

        MyArray<Integer> otherArray = new MyArray<>(3);
        otherArray.insert(40);
        otherArray.insert(50);
        otherArray.insert(60);

        MyArray<Integer> intersection = integerArray.intersect(otherArray);

        assertEquals("[]", intersection.toString());
    }

    @Test
    @DisplayName("intersect - " +
            "When there is one common element - " +
            "Should return an array with that element")
    void intersect_oneCommonElement_returnArrayWithThatElement() {
        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);

        MyArray<Integer> otherArray = new MyArray<>(2);
        otherArray.insert(30);
        otherArray.insert(40);

        MyArray<Integer> intersection = integerArray.intersect(otherArray);

        assertEquals("[30]", intersection.toString());
    }

    @Test
    @DisplayName("intersect - " +
            "When there are multiple common elements - " +
            "Should return an array with those elements")
    void intersect_multipleCommonElements_returnArrayWithThoseElements() {
        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);
        integerArray.insert(30);

        MyArray<Integer> otherArray = new MyArray<>(3);
        otherArray.insert(30);
        otherArray.insert(40);
        otherArray.insert(20);

        MyArray<Integer> intersection = integerArray.intersect(otherArray);

        assertEquals("[30, 20]", intersection.toString());
    }

    @Test
    @DisplayName("size - " +
            "When array is empty - " +
            "Should return 0")
    void size_arrayIsEmpty_returnZero() {
        assertEquals(0, integerArray.size());
    }

    @Test
    @DisplayName("size - " +
            "When array is not empty - " +
            "Should return the correct size")
    void size_arrayIsNotEmpty_returnCorrectSize() {
        integerArray.insert(10);
        integerArray.insert(30);
        integerArray.insert(20);

        assertEquals(3, integerArray.size());
    }

    @Test
    @DisplayName("get - " +
            "When index is negative - " +
            "Should throw IndexOutOfBoundsException")
    void get_indexIsNegative_throwIndexOutOfBoundsException() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);

        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.get(-1));
    }

    @Test
    @DisplayName("get - " +
            "When index is equal to size - " +
            "Should throw IndexOutOfBoundsException")
    void get_indexIsEqualToSize_throwIndexOutOfBoundsException() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);

        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.get(3));
    }

    @Test
    @DisplayName("get - " +
            "When index is greater than size - " +
            "Should throw IndexOutOfBoundsException")
    void get_indexIsGreaterThanSize_throwIndexOutOfBoundsException() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);

        assertThrows(IndexOutOfBoundsException.class, () -> integerArray.get(4));
    }

    @Test
    @DisplayName("get - " +
            "When index is valid - " +
            "Should return the correct element")
    void get_indexIsValid_returnCorrectElement() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);

        assertEquals(20, integerArray.get(1));
    }

    @Test
    @DisplayName("toString - " +
            "When the array is empty - " +
            "Should return an empty string")
    void toString_arrayIsEmpty_returnEmptyString() {
        assertEquals("[]", integerArray.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the array has one element - " +
            "Should return a string with that element")
    void toString_arrayHasOneElement_returnStringWithThatElement() {
        integerArray.insert(10);
        assertEquals("[10]", integerArray.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the array has multiple elements - " +
            "Should return a string with all elements separated by commas")
    void toString_arrayHasMultipleElements_returnStringWithAllElements() {
        integerArray.insert(10);
        integerArray.insert(20);
        integerArray.insert(30);
        assertEquals("[10, 20, 30]", integerArray.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When the array has null elements - " +
            "Should return a string without null elements")
    void toString_arrayHasNullElements_returnStringWithoutNullElements() {
        integerArray.insert(10);
        integerArray.insert(null);
        integerArray.insert(30);
        assertEquals("[10, 30]", integerArray.toString());
    }
}