package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Assignment 3
 * Question 1
 * Write a program that reads a six-digit integer and prints the sum of the six digits.  Use string
 * commands to extract each digit.
 * Modify the program so the user can enter any integer value (i.e., any length at all, not just
 * 6 digits).
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A3Q1 {
    public static void main(String[] args) {
        int n;
        // run while input is positice
        while ((n = _In.getInt()) > 0) {
            // convert to string
            String s = String.valueOf(n);
            // keep track of sum
            int sum = 0;
            // iterate through string
            for (int i = 0; i < s.length(); i++){
                // get char and parse it into int
                sum += Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            // print out sum
            System.out.println(sum);
        }
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