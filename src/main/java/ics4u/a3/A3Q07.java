package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 7
 * (a) Write a program that asks the user to enter a word (string variable) and a letter (string variable).
 * Find the first location of the letter in the word and save it in a variable (integer).
 * Output the result for the user.
 * (b) Find all locations of the letter, and output them for the user.  You will need a loop.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A3Q07 {
    public static void main(String[] args) {
        String s;
        // run while input is positice
        while (!(s = _In.getString()).equals("exit")) {
            // get the letter to find
            char ch = _In.getChar();
            // convert string to char array
            char[] chars = s.toCharArray();
            // loop through string and check for equality
            for (int i = 0; i < chars.length; i++){
                if (chars[i] == ch){
                    System.out.println("Found " + ch + " at location " + i);
                }
            }
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