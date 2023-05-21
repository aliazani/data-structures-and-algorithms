package io.github.aliazani.linear.queue;

import io.github.aliazani.linear.queue.array_queue.ArrayQueue;
import io.github.aliazani.linear.queue.priority_queue.with_array.PriorityQueueWithArray;
import io.github.aliazani.linear.queue.priority_queue.with_heap.PriorityQueueWithHeap;
import io.github.aliazani.linear.queue.stack_queue.StackQueue;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.*;

@Slf4j
public class QueueDemo {
    public static void main(String[] args) {
        showQueueDefaultImplementation();
        Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2, 3));

        log.warn("Reversing a queue:");
        log.info(MessageFormat.format("Queue before reversing: {0}", queue));
        reverse(queue);
        log.warn(MessageFormat.format("queue after reversing: {0}", queue));

        arrayQueueShow();
        stackQueueShow();
        showPriorityQueueDefaultImplementation();
        showMyPriorityQueue();

        log.warn("Queue Reverser Class:");
        QueueReverser<Integer> queueReverser = new QueueReverser<>();
        Queue<Integer> integerQueue = new ArrayDeque<>(Arrays.asList(10, 20, 30, 40, 50, 60));
        queueReverser.reverse(integerQueue, 3);
        log.info(MessageFormat.format("Queue after reversing the first three items:{0}", integerQueue));
    }

    public static void showQueueDefaultImplementation() {
        log.warn("Queue default implementation: ");

        Queue<Float> floatQueue = new ArrayDeque<>();
        floatQueue.add(1F);
        floatQueue.add(2F);
        floatQueue.add(3F);
        log.info(MessageFormat.format("Queue of Float elements: {0}", floatQueue));
        log.info(MessageFormat.format("Front element in floatQueue: {0}", floatQueue.remove()));
        log.info(MessageFormat.format("floatQueue after dequeue an item: {0}", floatQueue));

        Queue<Integer> integerQueue = new ArrayDeque<>();
        integerQueue.add(10);
        integerQueue.add(20);
        integerQueue.add(30);
        integerQueue.add(40);
        integerQueue.add(50);
        log.info("Queue of Integer elements: " + integerQueue);
    }

    public static <E> void reverse(Queue<E> queue) {
        Stack<E> stack = new Stack<>();

        while (!queue.isEmpty())
            stack.push(queue.remove());

        while (!stack.isEmpty())
            queue.add(stack.pop());
    }

    public static void arrayQueueShow() {
        log.warn("Queue my implementation with array: ");
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(Integer.class, 5);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);
        log.info(MessageFormat.format("Queue of Integer elements: {0}", arrayQueue));
        arrayQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after one dequeue: {0}", arrayQueue));
        arrayQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after two dequeues: {0}", arrayQueue));
        arrayQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after three dequeues: {0}", arrayQueue));
        log.info(MessageFormat.format("Peek an element from the queue: {0}", arrayQueue.peek()));
        log.info(MessageFormat.format("Queue of Integer elements after peeking an item: {0}", arrayQueue));
        arrayQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after another dequeue: {0}", arrayQueue));
        log.info(MessageFormat.format("Queue isEmpty: {0}", arrayQueue.isEmpty()));
    }

    public static void stackQueueShow() {
        log.warn("Queue my implementation with stack: ");
        StackQueue<Integer> stackQueue = new StackQueue<>();
        stackQueue.enqueue(10);
        stackQueue.enqueue(20);
        stackQueue.enqueue(30);
        stackQueue.enqueue(40);
        log.info(MessageFormat.format("Queue of Integer elements: {0}", stackQueue));

        stackQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after one dequeue: {0}", stackQueue));

        stackQueue.enqueue(50);
        log.info(MessageFormat.format("Queue of Integer elements after enqueue 50: {0}", stackQueue));

        log.info(MessageFormat.format("dequeue one element from the queue: {0}", stackQueue.dequeue()));
        log.info(MessageFormat.format("Queue of Integer elements after two dequeue: {0}", stackQueue));

        stackQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after three dequeue: {0}", stackQueue));

        stackQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after four dequeue: {0}", stackQueue));
        stackQueue.enqueue(60);
        stackQueue.enqueue(70);
        log.info(MessageFormat.format("Queue of Integer elements after enqueue 60 and 70: {0}", stackQueue));
        stackQueue.dequeue();
        log.info(MessageFormat.format("Queue of Integer elements after five dequeue: {0}", stackQueue));
    }

    public static void showPriorityQueueDefaultImplementation() {
        log.warn("PriorityQueue default implementation: ");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(4);
        priorityQueue.add(2);
        priorityQueue.add(6);

        priorityQueue
                .forEach(element -> log.info(element.toString()));
    }

    public static void showMyPriorityQueue() {
        log.warn("PriorityQueue my implementation: ");
        log.warn("PriorityQueue with array: ");
        PriorityQueueWithArray<Integer> priorityQueueWithArray = new PriorityQueueWithArray<>(Integer.class, 6);
        priorityQueueWithArray.enqueue(0);
        priorityQueueWithArray.enqueue(10);
        priorityQueueWithArray.enqueue(8);
        priorityQueueWithArray.enqueue(2);
        priorityQueueWithArray.enqueue(11);
        priorityQueueWithArray.enqueue(1);
        log.info(priorityQueueWithArray.toString());
        log.info(MessageFormat.format("PriorityQueue of Integer removes an item: {0}", priorityQueueWithArray.dequeue()));
        log.info(MessageFormat.format("PriorityQueue of Integer after removing an item: {0}", priorityQueueWithArray));

        log.warn("PriorityQueue with heap: ");
        PriorityQueueWithHeap<Integer> priorityQueueWithHeap = new PriorityQueueWithHeap<>(10);
        priorityQueueWithHeap.enqueue(10);
        priorityQueueWithHeap.enqueue(8);
        priorityQueueWithHeap.enqueue(4);
        priorityQueueWithHeap.enqueue(12);
        priorityQueueWithHeap.enqueue(18);
        priorityQueueWithHeap.enqueue(20);
        priorityQueueWithHeap.enqueue(2);
        priorityQueueWithHeap.enqueue(15);
        log.info(priorityQueueWithHeap.toString());
        log.info(MessageFormat.format("PriorityQueue of Integer removes an item: {0}", priorityQueueWithHeap.dequeue()));
        log.info(MessageFormat.format("PriorityQueue of Integer after removing an item: {0}", priorityQueueWithHeap));
    }
}