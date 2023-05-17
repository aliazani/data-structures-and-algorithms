package io.github.aliazani.nonlinear.binary_tree;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.TreeSet;

@Slf4j
public class BinaryTreeDemo {
    public static void main(String[] args) {
        showJavaImplementationOfBinaryTree();
        showMyImplementationOfBinaryTree();
    }

    public static void showJavaImplementationOfBinaryTree() {
        log.warn("Java Implementation of Binary Search Tree (BTS)");
        TreeSet<Integer> bts = new TreeSet<>();

        bts.add(10);
        bts.add(15);
        bts.add(5);
        bts.add(0);

        log.info(MessageFormat.format("bts: {0}", bts));
        log.info(MessageFormat.format("bts first: {0}", bts.first()));
        log.info(MessageFormat.format("bts last: {0}", bts.last()));
        log.info(MessageFormat.format("bts size: {0}", bts.size()));
        log.info(MessageFormat.format("bts contains(10): {0}", bts.contains(10)));
        log.info(MessageFormat.format("bts remove(0): {0}", bts.remove(0)));
        log.info(MessageFormat.format("bts after removing 0: {0}", bts));
    }

    public static void showMyImplementationOfBinaryTree() {
        log.warn("My implementation of binary tree");
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>(10);
        binaryTree1.insert(20);
        binaryTree1.insert(12);
        binaryTree1.insert(30);
        binaryTree1.insert(4);
        binaryTree1.insert(8);
        binaryTree1.insert(3);
        log.info(MessageFormat.format("binaryTree1 contains(10): {0}", binaryTree1.contains(10)));
        log.info(MessageFormat.format("binaryTree1 contains(23): {0}", binaryTree1.contains(23)));
        log.info(binaryTree1.representTree());
        log.info(MessageFormat.format("binaryTree1 Post-order: {0}", binaryTree1.traversePostOrder()));
        log.info(MessageFormat.format("binaryTree1 Pre-order: {0}", binaryTree1.traversePreOrder()));
        log.info(MessageFormat.format("binaryTree1 In-order: {0}", binaryTree1.traverseInOrder()));
        log.info(MessageFormat.format("binaryTree1 level-order: {0}", binaryTree1.traverseLevelOrder()));
        log.info(MessageFormat.format("binaryTree1 nodesAtDistance(3): {0}", binaryTree1.nodesAtDistance(3)));
        log.info(MessageFormat.format("binaryTree1 height: {0}", binaryTree1.height()));
        log.info(MessageFormat.format("binaryTree1 min: {0}", binaryTree1.min()));
        log.info(MessageFormat.format("binaryTree1 max: {0}", binaryTree1.max()));
        log.info(MessageFormat.format("binaryTree1 size: {0}", binaryTree1.size()));
        log.info(MessageFormat.format("binaryTree1 isBalanced: {0}", binaryTree1.isBalanced()));
        log.info(MessageFormat.format("binaryTree1 isPerfect: {0}", binaryTree1.isPerfect()));
        log.info(MessageFormat.format("binaryTree1 countLeaves: {0}", binaryTree1.countLeaves()));
        log.info(MessageFormat.format("binaryTree1 isBinarySearchTree: {0}", binaryTree1.isBinarySearchTree()));

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(10);
        binaryTree2.insert(20);
        binaryTree2.insert(12);
        binaryTree2.insert(30);
        binaryTree2.insert(4);
        binaryTree2.insert(8);
        binaryTree2.insert(3);
        log.info(MessageFormat.format("binaryTree1 equalsTree: {0}", binaryTree1.equalsTree(binaryTree2)));

        binaryTree1.swapRootChildren();
        log.info(MessageFormat.format("binaryTree1 after swapping root children isBinarySearchTree: {0}",
                binaryTree1.isBinarySearchTree()));


        BinaryTree<Float> binaryTree3 = new BinaryTree<>(12f);
        binaryTree3.insert(14f);
        binaryTree3.insert(9f);
        log.info(MessageFormat.format("binaryTree3 isBalanced: {0}", binaryTree3.isBalanced()));
        log.info(MessageFormat.format("binaryTree3 isPerfect: {0}", binaryTree3.isPerfect()));

        BinaryTree<Integer> binaryTree4 = new BinaryTree<>(10);
        binaryTree4.insert(20);
        binaryTree4.insert(30);
        binaryTree4.insert(35);
        binaryTree4.insert(32);
        binaryTree4.insert(40);
        binaryTree4.insert(5);
        log.info(MessageFormat.format("binaryTree4 isBalanced: {0}", binaryTree4.isBalanced()));
        log.info(binaryTree4.representTree());
        binaryTree4.remove(35);
        log.info("binaryTree4 after removing 35: " + binaryTree4.representTree());
    }
}
