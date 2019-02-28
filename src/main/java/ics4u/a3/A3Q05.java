package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 4
 * Write a program that asks the user for a word.  As the program runs, output an increasing
 * sequence of letters, starting at the first character and ending with the entire word.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A3Q05 {
    public static void main(String[] args) {
        String s;
        // run while input is positice
        while (!(s = _In.getString()).equals("exit")) {
            // create a string buffer for the word
            StringBuffer buffer = new StringBuffer();
            // loop through the word
            for (int i = 0; i < s.length(); i++) {
                // loop through the word with the previous index as the end
                for (int j = 0; j < i + 1; j++) {
                    buffer.append(s.charAt(j));
                }
                buffer.append("\n");
            }
            System.out.print(buffer.toString());
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