package io.github.aliazani.linear.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyArrayTest {
    private MyArray<Integer> integerArray;
    private MyArray<String> stringArray;

    @BeforeEach
    void setup() {
        integerArray = new MyArray<>(3);
        stringArray = new MyArray<>(2);
    }

    @Test
    void InsertAnItemToIntegerArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(4);

        assertEquals(4, integerArray.size());
    }

    @Test
    void removeAnItemFromIntegerArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(4);
        integerArray.insert(5);
        integerArray.removeAt(0);
        integerArray.removeAt(1);

        assertEquals(2, integerArray.getItemAtIndex(0));
        assertEquals(4, integerArray.getItemAtIndex(1));
        assertEquals(3, integerArray.size());
    }

    @Test
    void CheckInsertionsForIntegerArray() {
        integerArray.insert(0);
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(4);
        integerArray.insert(5);
        integerArray.insert(6);

        assertEquals(0, integerArray.getItemAtIndex(0));
        assertEquals(1, integerArray.getItemAtIndex(1));
        assertEquals(2, integerArray.getItemAtIndex(2));
        assertEquals(3, integerArray.getItemAtIndex(3));
        assertEquals(4, integerArray.getItemAtIndex(4));
        assertEquals(5, integerArray.getItemAtIndex(5));
        assertEquals(6, integerArray.getItemAtIndex(6));
        assertEquals("[0, 1, 2, 3, 4, 5, 6]", integerArray.toString());
    }

    @Test
    void checkDeletionForIntegerArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(4);
        integerArray.insert(5);
        integerArray.insert(6);
        integerArray.removeAt(0);

        assertEquals(2, integerArray.getItemAtIndex(0));
        assertEquals("[2, 3, 4, 5, 6]", integerArray.toString());

    }

    @Test
    void CheckIndexOfWithBunchOfInsertionAndDeletionForIntegerArray() {
        integerArray.insert(0);
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.removeAt(3);
        integerArray.removeAt(2);
        integerArray.insert(10);

        assertEquals(0, integerArray.getItemAtIndex(0));
        assertEquals(1, integerArray.indexOf(1));
        assertEquals(10, integerArray.getItemAtIndex(2));
        assertEquals(2, integerArray.indexOf(10));
    }

    @Test
    void insertionForOtherTypesLikeStringArray() {
        bunchOfInsertion();

        assertEquals("a", stringArray.getItemAtIndex(0));
        assertEquals("b", stringArray.getItemAtIndex(1));
        assertEquals("c", stringArray.getItemAtIndex(2));
        assertEquals("d", stringArray.getItemAtIndex(3));
        assertEquals("[a, b, c, d]", stringArray.toString());
    }

    @Test
    void bunchOfInsertionAndDeletionForOtherTypesLikeStringArray() {
        bunchOfInsertion();

        stringArray.removeAt(0);
        stringArray.removeAt(0);
        stringArray.removeAt(0);
        stringArray.insert("e");
        stringArray.insert("f");

        assertEquals("d", stringArray.getItemAtIndex(0));
        assertEquals("e", stringArray.getItemAtIndex(1));
        assertEquals("f", stringArray.getItemAtIndex(2));
        assertEquals("[d, e, f]", stringArray.toString());
    }

    @Test
    void indexOfStringItemInStringArray() {
        bunchOfInsertion();

        stringArray.removeAt(0);
        stringArray.removeAt(0);
        stringArray.removeAt(0);
        stringArray.insert("e");
        stringArray.insert("f");

        assertEquals(0, stringArray.indexOf("d"));
        assertEquals(1, stringArray.indexOf("e"));
        assertEquals(2, stringArray.indexOf("f"));

    }

    @Test
    void ArrayWithLengthOfZero() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(4);
        integerArray.insert(5);
        integerArray.insert(6);
        integerArray.removeAt(0);
        integerArray.removeAt(0);
        integerArray.removeAt(0);
        integerArray.removeAt(0);
        integerArray.removeAt(0);
        integerArray.removeAt(0);

        assertEquals(0, integerArray.size());
        assertEquals("[]", integerArray.toString());
    }

    @Test
    void maximumValueInAnArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        integerArray.insert(10);
        integerArray.insert(5);
        integerArray.insert(6);

        assertEquals(10, integerArray.max());
    }

    @Test
    void maximumValueInStringArray() {
        stringArray.insert("a");
        stringArray.insert("b");
        stringArray.insert("z");
        stringArray.insert("d");
        stringArray.insert("e");

        assertEquals("z", stringArray.max());
    }

    @Test
    void checkReverseOfArray() {
        integerArray.insert(1);
        integerArray.insert(2);
        integerArray.insert(3);
        MyArray<Integer> reversedArray = integerArray.reverse();

        assertEquals(3, reversedArray.getItemAtIndex(0));
        assertEquals(2, reversedArray.getItemAtIndex(1));
        assertEquals(1, reversedArray.getItemAtIndex(2));
        assertEquals("[3, 2, 1]", reversedArray.toString());
    }

    @Test
    void checkInsertAtInArrayAtFirst() {
        integerArray.insertAt(0, 1);
        integerArray.insertAt(0, 2);
        integerArray.insertAt(0, 3);
        integerArray.insertAt(0, 10);

        assertEquals(10, integerArray.getItemAtIndex(0));
        assertEquals("[10, 3, 2, 1]", integerArray.toString());
    }

    @Test
    void checkInsertAtInTheMiddleOfArray() {
        integerArray.insertAt(0, 1);
        integerArray.insertAt(1, 2);
        integerArray.insertAt(2, 3);
        integerArray.insertAt(1, 10);

        assertEquals(10, integerArray.getItemAtIndex(1));
        assertEquals("[1, 10, 2, 3]", integerArray.toString());
    }

    @Test
    void checkInsertAtArrayAtLast() {
        integerArray.insertAt(0, 1);
        integerArray.insertAt(1, 2);
        integerArray.insertAt(2, 3);
        integerArray.insertAt(3, 10);

        assertEquals(10, integerArray.getItemAtIndex(3));
        assertEquals("[1, 2, 3, 10]", integerArray.toString());
    }

    @Test
    void intersectCheck() {
        var integerArray1 = new MyArray<Integer>(3);
        var integerArray2 = new MyArray<Integer>(3);

        integerArray1.insert(1);
        integerArray1.insert(2);
        integerArray1.insert(3);
        integerArray1.insert(4);
        integerArray2.insert(1);
        integerArray2.insert(2);
        integerArray2.insert(3);
        integerArray2.insert(4);
        integerArray2.insert(5);
        MyArray<Integer> intersectedArray = integerArray1.intersect(integerArray2);

        assertEquals(1, intersectedArray.getItemAtIndex(0));
        assertEquals(2, intersectedArray.getItemAtIndex(1));
        assertEquals(3, intersectedArray.getItemAtIndex(2));
        assertEquals(4, intersectedArray.getItemAtIndex(3));
        assertEquals("[1, 2, 3, 4]", intersectedArray.toString());
    }

    @Test
    void intersectCheckForStringArray() {
        var stringArray1 = new MyArray<String>(3);
        var stringArray2 = new MyArray<String>(3);

        stringArray1.insert("a");
        stringArray1.insert("b");
        stringArray1.insert("c");
        stringArray1.insert("d");
        stringArray2.insert("a");
        stringArray2.insert("b");
        stringArray2.insert("c");
        stringArray2.insert("d");
        MyArray<String> intersectedArray = stringArray1.intersect(stringArray2);

        assertEquals("a", intersectedArray.getItemAtIndex(0));
        assertEquals("b", intersectedArray.getItemAtIndex(1));
        assertEquals("c", intersectedArray.getItemAtIndex(2));
        assertEquals("d", intersectedArray.getItemAtIndex(3));
        assertEquals("[a, b, c, d]", intersectedArray.toString());
    }

    private void bunchOfInsertion() {
        stringArray.insert("a");
        stringArray.insert("b");
        stringArray.insert("c");
        stringArray.insert("d");
    }
}
