package io.github.aliazani.linear.linkedlist;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class LinkedListDemo {
    public static void main(String[] args) {
        showLinkedListDefaultImplementation();

        showMyLinkedList();
    }

    public static void showLinkedListDefaultImplementation() {
        log.warn("Linked-list default implementation: ");
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addFirst(5);
        linkedList.remove(0);

        log.info("contains 5: " + linkedList.contains(5));
        log.info("index of 20: " + linkedList.indexOf(20));
        log.info("linked list size: " + linkedList.size());
        log.info("linked list: " + linkedList);
    }

    public static void showMyLinkedList() {
        log.warn("Linked-list my implementation: ");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        myLinkedList.addLast(10);
        myLinkedList.addLast(20);
        myLinkedList.addLast(30);
        myLinkedList.addFirst(5);
        myLinkedList.deleteFirst();

        log.info("contains 5: " + myLinkedList.contains(5));
        log.info("index of 20: " + myLinkedList.indexOf(20));
        log.info("linked list size: " + myLinkedList.size());
        log.info("linked list: " + myLinkedList);
    }
}