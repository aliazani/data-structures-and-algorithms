package io.github.aliazani.linear.stack;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Stack;

@Slf4j
public class StackDemo {
    public static void main(String[] args) {
        showStackJavaImplementation();
        showMyStackImplementation();

        log.warn(MessageFormat.format("Reverse a string: {0}", StringReverser.reverseString("ABCD")));
        Expression exp = new Expression("(a + b]");
        log.warn(MessageFormat.format("Is (a + b] balanced: {0}", exp.isBalanced()));
    }

    public static void showStackJavaImplementation() {
        log.warn("Stack - Java implementation:");
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);


        log.info(MessageFormat.format("Stack: {0}", stack));

        int top = stack.pop();
        log.info(MessageFormat.format("top: {0}", top));
        log.info(MessageFormat.format("Stack: {0}", stack));
        log.info(MessageFormat.format("Top: {0}", stack.peek()));
        log.info(MessageFormat.format("Stack: {0}", stack));
    }

    public static void showMyStackImplementation() {
        log.warn("Stack - My Implementations");
        MyArrayListStack<Integer> myArrayListStack = new MyArrayListStack<>();
        myArrayListStack.push(1);
        myArrayListStack.push(2);
        myArrayListStack.push(3);

        log.info(MessageFormat.format("ArrayList stack: {0}", myArrayListStack));
        log.info(MessageFormat.format("Minimum item in ArrayList stack: {0}", myArrayListStack));
        log.info(MessageFormat.format("Top item in ArrayList stack: {0}", myArrayListStack.pop()));
        log.info(MessageFormat.format("ArrayList stack after pop an item: {0}", myArrayListStack));
        log.info(MessageFormat.format("Top item in ArrayList stack: {0}", myArrayListStack.peek()));
        log.info(MessageFormat.format("ArrayList stack after peek an item: {0}", myArrayListStack));
        log.info(MessageFormat.format("ArrayList stack is Empty: {0}", myArrayListStack.isEmpty()));

        MyLinkedListStack<Integer> myLinkedListStack = new MyLinkedListStack<>();
        myLinkedListStack.push(1);
        myLinkedListStack.push(2);
        myLinkedListStack.push(3);

        log.info(MessageFormat.format("Linkedlist stack: {0}", myLinkedListStack));
        log.info(MessageFormat.format("Minimum item in Linkedlist stack: {0}", myLinkedListStack));
        log.info(MessageFormat.format("Top item in Linkedlist stack: {0}", myLinkedListStack.pop()));
        log.info(MessageFormat.format("Linkedlist stack after pop an item: {0}", myLinkedListStack));
        log.info(MessageFormat.format("Top item in Linkedlist stack: {0}", myLinkedListStack.peek()));
        log.info(MessageFormat.format("Linkedlist stack after peek an item: {0}", myLinkedListStack));
        log.info(MessageFormat.format("Linkedlist stack is Empty: {0}", myLinkedListStack.isEmpty()));
    }

}