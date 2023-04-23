package io.github.aliazani.linear.arrays;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class ArrayDemo {
    public static void main(String[] args) {
        ArrayDemo arrayDemo = new ArrayDemo();

        arrayDemo.showJavaImplementationOfArray();
        arrayDemo.showMyImplementationOfArray();
    }

    public void showMyImplementationOfArray() {
        log.warn("showMyImplementationOfArray");
        MyArray<Integer> numbers1 = new MyArray<>(3);
        numbers1.insert(1);
        numbers1.insert(2);
        numbers1.insert(3);
        numbers1.insert(4);
        log.info(MessageFormat.format("First Array: {0}", numbers1));
        log.info(MessageFormat.format("Max number in first Array: {0}", numbers1.max()));
        numbers1.removeAt(2);
        log.info(MessageFormat.format("First Array after deletion of a number at index 2: {0}", numbers1));

        MyArray<Integer> numbers2 = new MyArray<>(2);
        numbers2.insert(1);
        numbers2.insert(2);
        log.info(MessageFormat.format("numbers 1 intersect numbers 2: {0}", numbers2.intersect(numbers1)));
    }

    public void showJavaImplementationOfArray() {
        log.warn("showJavaImplementationOfArray");

//        int[] arrayOfIntegers = new int[3];
        int[] arrayOfIntegers = {10, 20, 30};
        log.info(MessageFormat.format("Empty array of integers: {0} \nLength of array: {1}",
                Arrays.toString(arrayOfIntegers), arrayOfIntegers.length));
//        arrayOfIntegers[0] = 10;
//        arrayOfIntegers[1] = 20;
//        arrayOfIntegers[2] = 30;
        log.info(MessageFormat.format("Initialized array of integers: {0} \nLength of array: {1}",
                Arrays.toString(arrayOfIntegers), arrayOfIntegers.length));


        ArrayList<Double> doubleArrayList = new ArrayList<>();
        doubleArrayList.add(1d);
        doubleArrayList.add(2d);
        doubleArrayList.add(3d);
        doubleArrayList.remove(0);
        log.info(MessageFormat.format("Double Array list: {0}\nSize: {1}\nlast index of 2: {2}",
                doubleArrayList, doubleArrayList.size(), doubleArrayList.lastIndexOf(2d)));
    }
}
