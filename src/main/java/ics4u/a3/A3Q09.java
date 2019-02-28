package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 9
 * .  A palindrome is a word that is spelled the same forwards and backwards.
 * For example, "noon" and "radar" are palindromes, while "hello" and "goodbye" are not.
 * (a) Write a program to accept a single word as input and determine if it is a
 * palindrome.  If you have learned about subroutines, create a subroutine called
 * "isPalindrome" that accepts a single word as a parameter, and returns a boolean (true/false).
 * (b) A palindrome can always be created by reversing the letters in the original
 * word and adding those letters to either the beginning or the end.  Write a program,
 * or subroutine, that will accept a string as input and automatically produce a palindrome
 * (but make sure you check if it's already a palindrome first).
 * (c) The method in (b) for creating a palindrome is called a "brute force" method,
 * which means that it works, but may not be the best solution.  Sometimes you don't
 * need to add every reversed letter to create the palindrome.  For example, "anagram"
 * only requires the last 4 letters to be reversed (creating "marganagram".  Write a
 * program, or subroutine, that will create the palindrome using the fewest extra
 * characters possible.
 *
 * @author Yu Liu
 */

public class A3Q09 {
    public static void main(String[] args) {
        String s;
        // run while input is positice
        while (!(s = _In.getString()).equals("exit")) {
            // check if s is a palindrome
            boolean palindrome = isPalindrome(s);
            if (palindrome){
                System.out.println(s + " is a palindrome");
            } else {
                System.out.println(s + " is not a palindrome");
                System.out.println("reversed palindrome: " + reversedPalindrome(s));
                System.out.println("optimal palindrome: " + optimalPalindrome(s));
            }
        }
    }

    /**
     * (a) Write a program to accept a single word as input and determine if it is a
     * palindrome.  If you have learned about subroutines, create a subroutine called
     * "isPalindrome" that accepts a single word as a parameter, and returns a boolean (true/false).
     */
    private static boolean isPalindrome(String s) {
        // check if the string is equal to its reverse
        return s.equals(new StringBuffer(s).reverse().toString());
    }

    /**
     * (b) A palindrome can always be created by reversing the letters in the original
     * word and adding those letters to either the beginning or the end.  Write a program,
     * or subroutine, that will accept a string as input and automatically produce a palindrome
     * (but make sure you check if it's already a palindrome first).
     */
    private static String reversedPalindrome(String s) {
        // add a reversed word to reverse the palindrome
        return s + reversed(s.substring(0, s.length() - 1));
    }

    /**
     * (c) The method in (b) for creating a palindrome is called a "brute force" method,
     * which means that it works, but may not be the best solution.  Sometimes you don't
     * need to add every reversed letter to create the palindrome.  For example, "anagram"
     * only requires the last 4 letters to be reversed (creating "marganagram".  Write a
     * program, or subroutine, that will create the palindrome using the fewest extra
     * characters possible.
     *
     * This method finds the largest palindrome at both ends of the word and flips the
     * word around it
     */
    private static String optimalPalindrome(String s) {
        int n = s.length();
        int i  = 0;
        int j = n;
        while (i < n && !isPalindrome(s.substring(i))) i++;
        while (j > 0 && !isPalindrome(s.substring(0, j))) j--;
        return n - i >= j ? s + reversed(s.substring(0, i)) : reversed(s.substring(j)) + s;
    }

    private static String reversed(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    private static class _In {
        private static InputStreamReader r = new InputStreamReader(System.in);
        private static BufferedReader br = new BufferedReader(r);

        // Read a String from standard system input
        static String getString() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return "";
            }
        }

        // Read a Number as a String from standard system input
        // Return the Number
        private static Number getNumber() {
            String numberString = getString();
            try {
                numberString = numberString.trim().toUpperCase();
                return NumberFormat.getInstance().parse(numberString);
            } catch (Exception e) {
                // If any exception occurs, just return zero
                return new Integer(0);
            }
        }

        // Read an int from standard system input
        static int getInt() {
            return getNumber().intValue();
        }

        // Read a long from standard system input
        static long getLong() {
            return getNumber().longValue();
        }

        // Read a float from standard system input
        static float getFloat() {
            return getNumber().floatValue();
        }

        // Read a double from standard system input
        static double getDouble() {
            return getNumber().doubleValue();
        }

        // Read a char from standard system input
        static char getChar() {
            String s = getString();
            if (s.length() >= 1)
                return s.charAt(0);
            else
                return '\n';
        }
    }
}