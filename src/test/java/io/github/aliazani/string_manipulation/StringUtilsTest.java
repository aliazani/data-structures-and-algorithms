package io.github.aliazani.string_manipulation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @DisplayName("countVowels - " +
            "When string is empty - " +
            "Should return 0")
    @Test
    void countVowels_emptyString_returnZero() {
        String str = "";
        assertEquals(0, StringUtils.countVowels(str));
    }

    @DisplayName("countVowels - " +
            "When string has no vowels - " +
            "Should return 0")
    @Test
    void countVowels_noVowels_returnZero() {
        String str = "xyz";
        assertEquals(0, StringUtils.countVowels(str));
    }

    @DisplayName("countVowels - " +
            "When string has all vowels - " +
            "Should return the length of the string")
    @Test
    void countVowels_allVowels_returnStringLength() {
        String str = "aeiou";
        assertEquals(str.length(), StringUtils.countVowels(str));
    }

    @DisplayName("countVowels - " +
            "When string has mixed vowels and consonants - " +
            "Should return the count of vowels")
    @Test
    void countVowels_mixedVowelsConsonants_returnCountOfVowels() {
        String str = "Hello World";
        assertEquals(3, StringUtils.countVowels(str));
    }

    @DisplayName("countVowels - " +
            "When string has uppercase vowels - " +
            "Should return the count of vowels")
    @Test
    void countVowels_uppercaseVowels_returnCountOfVowels() {
        String str = "AEIOU";
        assertEquals(str.length(), StringUtils.countVowels(str));
    }

    @DisplayName("reverse - " +
            "When string is empty - " +
            "Should return an empty string")
    @Test
    void reverse_emptyString_returnEmptyString() {
        String str = "";
        assertEquals("", StringUtils.reverse(str));
    }

    @DisplayName("reverse - " +
            "When string has one character - " +
            "Should return the same character")
    @Test
    void reverse_singleCharacterString_returnSameCharacter() {
        String str = "a";
        assertEquals("a", StringUtils.reverse(str));
    }

    @DisplayName("reverse - " +
            "When string has multiple characters - " +
            "Should return the reversed string")
    @Test
    void reverse_multipleCharacterString_returnReversedString() {
        String str = "Hello, World!";
        assertEquals("!dlroW ,olleH", StringUtils.reverse(str));
    }

    @DisplayName("reverseWords - " +
            "When string is empty - " +
            "Should return an empty string")
    @Test
    void reverseWords_emptyString_returnEmptyString() {
        String str = "";
        assertEquals("", StringUtils.reverseWords(str));
    }

    @DisplayName("reverseWords - " +
            "When string has one word - " +
            "Should return the same word")
    @Test
    void reverseWords_singleWordString_returnSameWord() {
        String str = "Hello";
        assertEquals("Hello", StringUtils.reverseWords(str));
    }

    @DisplayName("reverseWords - " +
            "When string has multiple words - " +
            "Should return the reversed word order")
    @Test
    void reverseWords_multipleWordString_returnReversedWordOrder() {
        String str = "Hello, World!";
        assertEquals("World! Hello,", StringUtils.reverseWords(str));
    }

    @DisplayName("areRotations - " +
            "When both strings are empty - " +
            "Should return true")
    @Test
    void areRotations_bothStringsEmpty_returnTrue() {
        String str1 = "";
        String str2 = "";
        assertTrue(StringUtils.areRotations(str1, str2));
    }

    @DisplayName("areRotations - " +
            "When one string is empty and the other is not - " +
            "Should return false")
    @Test
    void areRotations_oneStringEmpty_returnFalse() {
        String str1 = "";
        String str2 = "Hello";
        assertFalse(StringUtils.areRotations(str1, str2));
    }

    @DisplayName("areRotations - " +
            "When both strings have the same length and are rotations - " +
            "Should return true")
    @Test
    void areRotations_sameLengthRotations_returnTrue() {
        String str1 = "Hello";
        String str2 = "loHel";
        assertTrue(StringUtils.areRotations(str1, str2));
    }

    @DisplayName("areRotations - " +
            "When both strings have the same length but are not rotations - " +
            "Should return false")
    @Test
    void areRotations_sameLengthNonRotations_returnFalse() {
        String str1 = "Hello";
        String str2 = "World";
        assertFalse(StringUtils.areRotations(str1, str2));
    }

    @DisplayName("areRotations - " +
            "When both strings have different lengths - " +
            "Should return false")
    @Test
    void areRotations_differentLengths_returnFalse() {
        String str1 = "Hello";
        String str2 = "World!";
        assertFalse(StringUtils.areRotations(str1, str2));
    }

    @DisplayName("removeDuplicate - " +
            "When input has duplicate characters - " +
            "Should return string with duplicates removed")
    @Test
    void removeDuplicate_inputWithDuplicates_returnStringWithoutDuplicates() {
        String input = "Hello  World";
        String expectedOutput = "helo wrd";
        assertEquals(expectedOutput, StringUtils.removeDuplicate(input));
    }

    @DisplayName("removeDuplicate - " +
            "When input has no duplicate characters - " +
            "Should return the same string")
    @Test
    void removeDuplicate_inputWithoutDuplicates_returnSameString() {
        String input = "OpenAI";
        String expectedOutput = "openai";
        assertEquals(expectedOutput, StringUtils.removeDuplicate(input));
    }

    @DisplayName("removeDuplicate - " +
            "When input is an empty string - " +
            "Should return an empty string")
    @Test
    void removeDuplicate_emptyInput_returnEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, StringUtils.removeDuplicate(input));
    }

    @DisplayName("findMostRepeatedCharacter - " +
            "When input string has one character - " +
            "Should return that character")
    @Test
    void findMostRepeatedCharacter_singleCharacterInput_returnCharacter() {
        assertEquals('a', StringUtils.findMostRepeatedCharacter("a"));
    }

    @DisplayName("findMostRepeatedCharacter - " +
            "When input string has multiple characters with equal frequencies - " +
            "Should return the first character")
    @Test
    void findMostRepeatedCharacter_equalFrequencies_returnFirstCharacter() {
        assertEquals('a', StringUtils.findMostRepeatedCharacter("abab"));
    }

    @DisplayName("findMostRepeatedCharacter - " +
            "When input string has multiple characters with different frequencies - " +
            "Should return the character with the highest frequency")
    @Test
    void findMostRepeatedCharacter_differentFrequencies_returnCharacterWithHighestFrequency() {
        assertEquals('b', StringUtils.findMostRepeatedCharacter("aabbbccdd"));
    }

    @DisplayName("findMostRepeatedCharacter - " +
            "When input string is empty - " +
            "Should return null")
    @Test
    void findMostRepeatedCharacter_emptyString_returnsNull() {
        assertNull(StringUtils.findMostRepeatedCharacter(""));
    }

    @DisplayName("capitalize - " +
            "When input is a single word - " +
            "Should capitalize the word")
    @Test
    void capitalize_singleWord_capitalizeWord() {
        String input = "hello";
        assertEquals("Hello", StringUtils.capitalize(input));
    }

    @DisplayName("capitalize - " +
            "When input has leading and trailing spaces - " +
            "Should capitalize each word")
    @Test
    void capitalize_leadingAndTrailingSpaces_capitalizeEachWord() {
        String input = "   hello   world   ";
        assertEquals("Hello World", StringUtils.capitalize(input));
    }

    @DisplayName("capitalize - " +
            "When input has multiple consecutive spaces - " +
            "Should replace them with a single space")
    @Test
    void capitalize_multipleConsecutiveSpaces_replaceWithSingleSpace() {
        String input = "hello    world";
        assertEquals("Hello World", StringUtils.capitalize(input));
    }

    @DisplayName("capitalize - " +
            "When input has mixed case - " +
            "Should capitalize each word and convert the rest to lowercase")
    @Test
    void capitalize_mixedCase_capitalizeEachWordAndConvertToLowercase() {
        String input = "HeLlO WoRlD";
        assertEquals("Hello World", StringUtils.capitalize(input));
    }

    @DisplayName("capitalize - " +
            "When input is an empty string - " +
            "Should return an empty string")
    @Test
    void capitalize_emptyString_returnEmptyString() {
        String input = "";
        assertEquals("", StringUtils.capitalize(input));
    }


    @DisplayName("areAnagrams - " +
            "When two strings are anagrams - " +
            "Should return true")
    @Test
    void areAnagrams_twoAnagrams_returnTrue() {
        String str1 = "listen";
        String str2 = "silent";
        assertTrue(StringUtils.areAnagrams(str1, str2));
    }

    @DisplayName("areAnagrams - " +
            "When two strings are not anagrams - " +
            "Should return false")
    @Test
    void areAnagrams_twoNonAnagrams_returnFalse() {
        String str1 = "hello";
        String str2 = "world";
        assertFalse(StringUtils.areAnagrams(str1, str2));
    }

    @DisplayName("areAnagrams - " +
            "When two strings have different lengths - " +
            "Should return false")
    @Test
    void areAnagrams_differentLengths_returnFalse() {
        String str1 = "abcd";
        String str2 = "abcde";
        assertFalse(StringUtils.areAnagrams(str1, str2));
    }

    @DisplayName("areAnagrams - " +
            "When one or both strings are empty - " +
            "Should return true")
    @Test
    void areAnagrams_oneOrBothEmptyStrings_returnTrue() {
        String str1 = "";
        String str2 = "";
        assertTrue(StringUtils.areAnagrams(str1, str2));

        String str3 = "";
        String str4 = "abc";
        assertFalse(StringUtils.areAnagrams(str3, str4));

        String str5 = "def";
        String str6 = "";
        assertFalse(StringUtils.areAnagrams(str5, str6));
    }

    @DisplayName("areAnagrams2 - " +
            "When two strings are anagrams - " +
            "Should return true")
    @Test
    void areAnagrams2_twoAnagrams_returnTrue() {
        String str1 = "listen";
        String str2 = "silent";
        assertTrue(StringUtils.areAnagrams2(str1, str2));
    }

    @DisplayName("areAnagrams2 - " +
            "When two strings are not anagrams - " +
            "Should return false")
    @Test
    void areAnagrams2_twoNonAnagrams_returnFalse() {
        String str1 = "hello";
        String str2 = "world";
        assertFalse(StringUtils.areAnagrams2(str1, str2));
    }

    @DisplayName("areAnagrams2 - " +
            "When two strings have different lengths - " +
            "Should return false")
    @Test
    void areAnagrams2_differentLengths_returnFalse() {
        String str1 = "abcd";
        String str2 = "abcde";
        assertFalse(StringUtils.areAnagrams2(str1, str2));
    }

    @DisplayName("areAnagrams2 - " +
            "When one or both strings are empty - " +
            "Should return true")
    @Test
    void areAnagrams2_oneOrBothEmptyStrings_returnTrue() {
        String str1 = "";
        String str2 = "";
        assertTrue(StringUtils.areAnagrams2(str1, str2));

        String str3 = "";
        String str4 = "abc";
        assertFalse(StringUtils.areAnagrams2(str3, str4));

        String str5 = "def";
        String str6 = "";
        assertFalse(StringUtils.areAnagrams2(str5, str6));
    }

    @DisplayName("isPalindrome - " +
            "When the input is an empty string - " +
            "Should return true")
    @Test
    void isPalindrome_emptyString_returnTrue() {
        assertTrue(StringUtils.isPalindrome(""));
    }

    @DisplayName("isPalindrome - " +
            "When the input is a palindrome string - " +
            "Should return true")
    @Test
    void isPalindrome_palindromeString_returnTrue() {
        assertTrue(StringUtils.isPalindrome("racecar"));
        assertTrue(StringUtils.isPalindrome("Madam"));
        assertTrue(StringUtils.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(StringUtils.isPalindrome("12321"));
    }

    @DisplayName("isPalindrome - " +
            "When the input is not a palindrome string - " +
            "Should return false")
    @Test
    void isPalindrome_nonPalindromeString_returnFalse() {
        assertFalse(StringUtils.isPalindrome("hello"));
        assertFalse(StringUtils.isPalindrome("world"));
        assertFalse(StringUtils.isPalindrome("OpenAI"));
        assertFalse(StringUtils.isPalindrome("12345"));
    }
}