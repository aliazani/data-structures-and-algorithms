package io.github.aliazani.linear.queue.priority_queue.with_heap;

import io.github.aliazani.linear.queue.priority_queue.with_heap.PriorityQueueWithHeap;

public class PriorityQueueWithHeapDemo {
    public static void main(String[] args) {
        var priorityQueue = new PriorityQueueWithHeap<Integer>(10);
        priorityQueue.enqueue(10);
        priorityQueue.enqueue(8);
        priorityQueue.enqueue(4);
        priorityQueue.enqueue(12);
        priorityQueue.enqueue(18);
        priorityQueue.enqueue(20);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(15);

        System.out.println(priorityQueue);
    }
}
