package io.github.aliazani.linear.hashtables.open_addressing.linear_probing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyHashTableLinearProbing")
class MyHashTableLinearProbingTest {
    MyHashTableLinearProbing<Integer, String> table;

    @BeforeEach
    void setUp() {
        table = new MyHashTableLinearProbing<>(5);
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair - " +
            "Should add it to the table")
    void put_addNewKeyValuePair_addToTable() {
        table.put(0, "a");
        table.put(1, "b");
        table.put(2, "c");
        table.put(3, "d");
        table.put(5, "f");


        assertEquals("[0=a, 1=b, 2=c, 3=d, 5=f]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When updating an existing key-value pair - " +
            "Should update the value")
    void put_updateExistingKeyValuePair_updateValue() {
        table.put(1, "a");
        table.put(2, "b");
        table.put(3, "c");

        table.put(1, "d");

        assertEquals("[null, 1=d, 2=b, 3=c, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair with a hash collision - " +
            "Should add it to the correct index")
    void put_addNewKeyValuePairWithHashCollision_addToCorrectSlot() {
        table.put(0, "a");
        table.put(1, "b");
        table.put(5, "c");
        table.put(10, "d");

        assertEquals("[0=a, 1=b, 5=c, 10=d, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When updating an existing key-value pair with a hash collision - " +
            "Should update the value")
    void put_updateExistingKeyValuePairWithHashCollision_updateValue() {
        table.put(0, "a");
        table.put(1, "b");
        table.put(5, "e");

        table.put(5, "y");
        table.put(0, "z");

        assertEquals("[0=z, 1=b, 5=y, null, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair with null key and null value - " +
            "Should add it to the table")
    void put_addNewKeyValuePairWithNullKeyAndNullValue_addToTable() {
        table.put(0, "a");
        table.put(1, "b");
        table.put(null, null);

        assertEquals("[0=a, 1=b, null=null, null, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair with null key and non-null value - " +
            "Should add it to the table")
    void put_addNewKeyValuePairWithNullKeyAndNonNullValue_addToTable() {
        table.put(1, "b");
        table.put(null, "d");

        assertEquals("[null=d, 1=b, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair with non-null key and null value - " +
            "Should add it to the table")
    void put_addNewKeyValuePairWithNonNullKeyAndNullValue_addToTable() {
        table.put(10, null);

        assertEquals("[10=null, null, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When updating an existing key-value pair with null key and null value - " +
            "Should update the value")
    void put_updateExistingKeyValuePairWithNullKeyAndNullValue_updateValue() {
        table.put(null, null);
        table.put(null, "a");

        assertEquals("[null=a, null, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("put - " +
            "When adding a new key-value pair and table is full - " +
            "Should throw IllegalStateException")
    void put_addNewEntryAndTableIsFull_throwIllegalState() {
        table.put(1, "a");
        table.put(2, "b");
        table.put(3, "d");
        table.put(5, "e");
        table.put(6, "f");

        assertThrows(IllegalStateException.class, () -> table.put(7, "g"));
    }

    @Test
    @DisplayName("get - " +
            "When table is empty - " +
            "Should throw NoSuchElementException")
    void get_tableIsEmpty_throwNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> table.get(1));
    }

    @Test
    @DisplayName("get - " +
            "When the key is in the table - " +
            "Should return the corresponding value")
    void get_keyInTable_returnValue() {
        table.put(0, "a");
        table.put(1, "b");
        table.put(2, "c");
        table.put(3, "d");

        assertEquals("a", table.get(0));
        assertEquals("b", table.get(1));
        assertEquals("c", table.get(2));
        assertEquals("d", table.get(3));
    }

    @Test
    @DisplayName("get - " +
            "When the key is not in the table - " +
            "Should throw NoSuchElementException")
    void get_keyNotInTable_throwNoSuchElement() {
        table.put(0, "a");
        table.put(1, "b");

        assertThrows(NoSuchElementException.class, () -> table.get(2));
    }

    @Test
    @DisplayName("get - " +
            "When the key has a hash collision with another key - " +
            "Should return the value of the correct key")
    void get_keyWithHashCollision_returnCorrectValue() {
        table.put(0, "a");
        table.put(5, "e");

        assertEquals("a", table.get(0));
        assertEquals("e", table.get(5));
    }

    @Test
    @DisplayName("get - " +
            "When the key with hash collision is not in the table - " +
            "Should throw NoSuchElementException")
    void get_keyWithHashCollisionNotInTable_throwNoSuchElement() {
        table.put(0, "a");
        table.put(5, "e");

        assertThrows(NoSuchElementException.class, () -> table.get(10));
    }

    @Test
    @DisplayName("get - " +
            "When the value is null - " +
            "Should return null")
    void get_keyWithNullValue_returnNull() {
        table.put(0, null);

        assertNull(table.get(0));
    }

    @Test
    @DisplayName("get - " +
            "When key is null - " +
            "Should return corresponding value")
    void get_keyIsNull_returnCorrespondingValue() {
        table.put(null, "a");

        assertEquals("a", table.get(null));
    }

    @Test
    @DisplayName("remove - " +
            "When removing the only key-value pair in the table - " +
            "Should remove the only entry")
    void remove_onlyKeyValuePairInMap_removeTheOnlyEntry() {
        table.put(0, "a");
        table.remove(0);

        assertEquals("[null, null, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing the last key-value pair with collision - " +
            "Should remove the last key-value pair with collision")
    void remove_lastKeyValuePairWithCollision_removeTheLastEntryWithCollision() {
        table.put(0, "a");
        table.put(5, "b");
        table.put(10, "c");

        table.remove(10);

        assertEquals("[0=a, 5=b, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing the first key-value pair with collision - " +
            "Should remove the first key-value pair")
    void remove_firstKeyValuePairWithCollision_removeTheFirstKeyValuePairWithCollision() {
        table.put(0, "a");
        table.put(5, "b");
        table.put(10, "c");

        table.remove(0);

        assertEquals("[null, 5=b, 10=c, null, null]", table.toString());
    }

    @DisplayName("remove - " +
            "When removing the middle key-value pair with collision - " +
            "Should remove the middle key-value pair with collision")
    @Test
    void remove_middleKeyValuePairWithCollision_removeTheMiddleKeyValuePairWithCollision() {
        table.put(0, "a");
        table.put(5, "b");
        table.put(10, "c");

        table.remove(5);

        assertEquals("[0=a, null, 10=c, null, null]", table.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing a key that is null - " +
            "Should remove the key-value pair")
    void remove_nullKey_removeKeyValuePair() {
        table.put(null, "a");
        table.put(1, "b");

        table.remove(null);

        assertEquals("[null, 1=b, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("remove - " +
            "When removing a non-existing key without collision - " +
            "Should throw a NoSuchElementException")
    void remove_nonExistingKey_throwNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> table.remove(5));
    }

    @Test
    @DisplayName("remove - " +
            "When removing a non-existing key from the table with collision - " +
            "Should throw a NoSuchElementException")
    void remove_nonExistingKeyFromTableWithCollision_throwNoSuchElementException() {
        table.put(0, "a");
        table.put(5, "b");
        table.put(10, "c");

        assertThrows(NoSuchElementException.class, () -> table.remove(15));
    }

    @Test
    @DisplayName("size - " +
            "When the table is empty - " +
            "Should return 0")
    void size_emptyTable_returnZero() {
        assertEquals(0, table.size());
    }

    @Test
    @DisplayName("size - " +
            "When the table has one entry - " +
            "Should return 1")
    void size_tableHasOneEntry_returnOne() {
        table.put(1, "value");

        assertEquals(1, table.size());
    }

    @Test
    @DisplayName("size - " +
            "When the table has multiple entries - " +
            "Should return the correct size")
    void size_tableHasMultipleEntries_returnCorrectSize() {
        table.put(1, "value1");
        table.put(2, "value2");
        table.put(3, "value3");

        assertEquals(3, table.size());
    }

    @Test
    @DisplayName("size - " +
            "When adding and removing entries - " +
            "Should update the size correctly")
    void size_addAndRemoveEntries_updateSizeCorrectly() {
        table.put(0, "value0");
        table.put(1, "value1");
        table.put(2, "value2");
        table.put(3, "value3");
        table.put(5, "value3");

        table.remove(2);
        table.remove(1);
        table.remove(3);
        table.remove(5);

        assertEquals(1, table.size());
    }

    @Test
    @DisplayName("isEmpty - " +
            "When the table is empty - " +
            "Should return true")
    void isEmpty_emptyTable_returnTrue() {
        assertTrue(table.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - " +
            "When the table has one entry - " +
            "Should return false")
    void isEmpty_tableHasOneEntry_returnFalse() {
        table.put(1, "value");

        assertFalse(table.isEmpty());
    }

    @Test
    @DisplayName("isEmpty - " +
            "When the table has multiple entries - " +
            "Should return false")
    void isEmpty_tableHasMultipleEntries_returnFalse() {
        table.put(1, "value1");
        table.put(2, "value2");
        table.put(3, "value3");

        assertFalse(table.isEmpty());
    }

    @Test
    @DisplayName("toString - " +
            "When calling toString on an empty map - " +
            "Should return  [null, null, null, null, null]")
    void toString_emptyMap_returnCorrectString() {
        assertEquals("[null, null, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When calling toString on a map with one entry - " +
            "Should return the correct string representation")
    void toString_mapWithOneEntry_returnCorrectString() {
        table.put(0, "a");

        assertEquals("[0=a, null, null, null, null]", table.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When calling toString on a map with multiple entries with collision - " +
            "Should return the correct string representation")
    void toString_mapWithMultipleEntriesWithCollision_returnCorrectString() {
        table.put(0, "a");
        table.put(5, "b");
        table.put(10, "c");

        assertEquals("[0=a, 5=b, 10=c, null, null]", table.toString());
    }

    @Test
    @DisplayName("toString - " +
            "When calling toString on a map with multiple entries - " +
            "Should return the correct string representation")
    void toString_mapWithMultipleEntries_returnCorrectString() {
        table.put(0, "a");
        table.put(6, "b");
        table.put(12, "c");
        table.put(15, "d");
        table.put(21, "e");
        table.remove(6);
        table.put(8, "b");

        assertEquals("[0=a, 8=b, 12=c, 15=d, 21=e]", table.toString());
    }
}