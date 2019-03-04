package ics4u.ma1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 1
 * Question 7
 * A palindrome is a string that reads the same both forward and backward.
 * Examples of palindromes are “radar”, “31413”, and “god a dog”.
 * (a) Write a boolean-valued method isPalindrome that has a single String
 * parameter. The method should return true if and only if its parameter is a palindrome.
 * (b) Modify your method so that it ignores cases of letters, punctuation marks,
 * and blanks in making a decision about whether or not a string is a palindrome.
 * For example, the string: "A man, a plan, a canal: Panama!" (a reference to
 * Ferdinand de Lessups, the chief engineer of the ﬁrst attempt to build the
 * Panama Canal) should be taken to be a palindrome.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA1Q07 {
    public static void main(String[] args) {
        String s;
        // run while input is not exit
        while (!(s = _In.getString()).equals("exit")) {
            // check if the string is a palindrome and print out the appropriate message
            if (isPalindrome(s)) {
                System.out.println(s + " is a palindrome");
            } else {
                System.out.println(s + " is not a palindrome");
            }
        }
    }

    /**
     * Checks if a string is a palindrome when ignoring cases, punctuation, and spaces
     */
    private static boolean isPalindrome(String s) {
        return isStrictPalindrome(toAlphaNumeric(s).toLowerCase());
    }

    /**
     * Strips all characters that is not alphanumeric and returns the result
     */
    private static String toAlphaNumeric(String s) {
        // convert to char array
        char[] chars = s.toCharArray();
        // create a buffer
        StringBuffer buffer = new StringBuffer();
        // loop through the array of chars
        for (int i = 0; i < chars.length; i++) {
            // get the current char
            char ch = chars[i];
            // check if the char is alphanumeric
            if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch < 'Z') || (ch >= 'a' && ch < 'z')) {
                // append it to the buffer if it is
                buffer.append(ch);
            }
        }
        // return the string in the buffer
        return buffer.toString();
    }

    /**
     * Checks if the string is strictly a palindrome (i.e. it is equal to its reverse)
     */
    private static boolean isStrictPalindrome(String s) {
        // check if the string is equal to its reverse
        return s.equals(reversed(s));
    }

    /**
     * Reverses a string
     */
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