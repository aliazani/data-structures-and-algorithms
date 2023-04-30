package io.github.aliazani.linear.linkedlist;

import io.github.aliazani.linear.linkedlist.doubly.MyDoublyLinkedList;
import io.github.aliazani.linear.linkedlist.singly.MySinglyLinkedList;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.LinkedList;

@Slf4j
public class LinkedListDemo {
    public static void main(String[] args) {
        showLinkedListDefaultImplementation();

        showMySinglyLinkedList();
        showMyDoublyLinkedList();
    }

    public static void showLinkedListDefaultImplementation() {
        log.warn("Linked-list default implementation: ");
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addFirst(5);
        linkedList.remove(0);

        log.info(MessageFormat.format("contains 5: {0}", linkedList.contains(5)));
        log.info(MessageFormat.format("index of 20: {0}", linkedList.indexOf(20)));
        log.info(MessageFormat.format("linked list size: {0}", linkedList.size()));
        log.info(MessageFormat.format("linked list: {0}", linkedList));
    }

    public static void showMySinglyLinkedList() {
        log.warn("Singly linked-list my implementation: ");
        MySinglyLinkedList<Integer> mySinglyLinkedList = new MySinglyLinkedList<>();

        mySinglyLinkedList.addLast(10);
        mySinglyLinkedList.addLast(20);
        mySinglyLinkedList.addLast(30);
        mySinglyLinkedList.addFirst(5);
        log.info(MessageFormat.format("contains 5: {0}", mySinglyLinkedList.contains(5)));
        mySinglyLinkedList.deleteFirst();
        log.info(MessageFormat.format("contains 5 after deleting first element: {0}",
                mySinglyLinkedList.contains(5)));
        log.info(MessageFormat.format("index of 20: {0}", mySinglyLinkedList.indexOf(20)));
        log.info(MessageFormat.format("linked list size: {0}", mySinglyLinkedList.size()));
        log.info(MessageFormat.format("Singly linked list: {0}", mySinglyLinkedList));
    }

    public static void showMyDoublyLinkedList() {
        log.warn("Doubly linked-list my implementation: ");
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();

        myDoublyLinkedList.addLast(10);
        myDoublyLinkedList.addLast(20);
        myDoublyLinkedList.addLast(30);
        myDoublyLinkedList.addFirst(5);
        log.info(MessageFormat.format("contains 5: {0}", myDoublyLinkedList.contains(5)));
        myDoublyLinkedList.deleteFirst();
        log.info(MessageFormat.format("contains 5 after deleting first element: {0}",
                myDoublyLinkedList.contains(5)));
        log.info(MessageFormat.format("index of 20: {0}", myDoublyLinkedList.indexOf(20)));
        log.info(MessageFormat.format("linked list size: {0}", myDoublyLinkedList.size()));
        log.info(MessageFormat.format("Doubly linked list: {0}", myDoublyLinkedList));
    }
}