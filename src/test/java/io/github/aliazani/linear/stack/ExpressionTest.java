package io.github.aliazani.linear.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {
    private static Stream<Arguments> provideExpressionsAndIsBalanced() {
        return Stream.of(
                Arguments.of("[]", true),
                Arguments.of("", true),
                Arguments.of("[(1 * 4) + (2 * 8)]", true),
                Arguments.of("A", true),
                Arguments.of("(1 + 2}", false),
                Arguments.of("(", false),
                Arguments.of("]", false),
                Arguments.of("(]", false)

        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideExpressionsAndIsBalanced")
    void isBalanced(String expr, boolean expected) {
        assertEquals(expected, new Expression(expr).isBalanced());
    }
}