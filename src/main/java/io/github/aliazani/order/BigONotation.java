package io.github.aliazani.order;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.stream.IntStream;

@Slf4j
public class BigONotation {
    public static void main(String[] args) {
        int[] numbers = IntStream.range(0, 10).toArray();
        String name = "Mohammadali Azani";

        printFirstNumber(numbers);
        printNumbers(numbers);
        printNumbers2(numbers);
        printNumbersAndString(numbers, name);
        printFirstAndSecondNumber(numbers);
    }

    // O(1) -> constant time
    public static void printFirstNumber(int[] numbers) {
        log.warn("printFirstNumber - O(1)");

        log.info("First number in the array: " + numbers[0]);
        log.info("Second number in the array: " + numbers[1]);
    }

    // O(n) -> print n numbers (growth linearly)
    public static void printNumbers(int[] numbers) {
        log.warn("printNumbers - O(n)");

        for (int number : numbers)
            log.info(String.valueOf(number));
    }

    // O(2n), which is equal to O(n)
    public static void printNumbers2(int[] numbers) {
        log.warn("printNumbers2 - O(2n)");

        for (var number : numbers)
            log.info(String.valueOf(number));

        for (var number : numbers)
            log.info(String.valueOf(number));
    }

    // O(n+m), which is equal to O(n)
    public static void printNumbersAndString(int[] numbers, String name) {
        log.warn("printNumbersAndString - O(n + m)");

        for (int number : numbers)
            log.info(String.valueOf(number));

        for (String letter : name.split(""))
            log.info(letter);
    }

    // o(n^2) -> quadratic
    public static void printFirstAndSecondNumber(int[] numbers) {
        log.warn("printFirstAndSecondNumber - O(n * n)");

        for (var firstNumber : numbers)
            for (var secondNumber : numbers)
                log.info(MessageFormat.format("firstNumber={0}, secondNumber={1}",
                        firstNumber, secondNumber));
    }

    // Binary search -> logarithmic
    // Exponential
}