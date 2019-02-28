package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 10
 * Write a program which asks the user for one word at a time. After the user enters their word,
 * you will concatenate the new word with all previous words. When the user enters the word "quit",
 * output the final string. The final string should not include the word "quit". You will need a
 * loop for this program.
 * Extend: Whenever the user enters a new word, also put a space so the final string looks better.
 * For example:
 * User input? hello
 * Current string: Hello
 * User input? How
 * Current string: HelloHow
 * User input? Are
 * Current string: HelloHowAre
 * User input? You?
 * Current string: HelloHowAreYou?
 * User input? quit
 * Program terminated by user.
 * Final string: HelloHowAreYou?
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A3Q10 {
    public static void main(String[] args) {
        String s;
        // create a string buffer for the built string
        StringBuffer buffer = new StringBuffer();
        // run while input is positice
        while (!(s = _In.getString()).equals("quit")) {
            // add a string to the buffer
            buffer.append(s);
            System.out.println("Current String: " + buffer.toString());
        }
        System.out.println("Final String: " + buffer.toString());
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