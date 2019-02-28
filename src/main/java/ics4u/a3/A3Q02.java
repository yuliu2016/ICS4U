package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 2
 * Ask the user for one letter at a time. For each letter, "add" the new letter and start building a
 * word. After you add each letter, tell the user the current word they are buildings. Keep going
 * until the user enters a period, and then tell them the final word.
 * ICS4U class 2019
 * @author Yu Liu
 */

public class A3Q02 {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        char ch;
        System.out.print("Letter ? ");
        // Run while input is not "exit"
        while ((ch = _In.getChar()) != '.') {
            if (ch == '\n') continue;
            // add to buffer
            buffer.append(ch);
            // print current word
            System.out.println("Current Word: " + buffer.toString());
            // print next prompt
            System.out.print("Letter ? ");
        }
        // print final word
        System.out.println("Final Word: " + buffer.toString());
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