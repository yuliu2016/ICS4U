package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 8
 * When creating variables, we try to use the camelCase naming convention.
 * The first word is lowercase, and all following words start with a capital
 * letter, and there are no spaces between words.  Write a program that asks
 * the user for one or more words and the creates a camelCase variable name.
 * For example,
 * "Welcome to Class" becomes "welcomeToClass"
 * "THE QUICK BROWN FOX" becomes "theQuickBrownFox"
 * "HellO HOW aRe you" becomes "helloHowAreYou"
 *
 * @author Yu Liu
 */

public class A3Q08 {
    public static void main(String[] args) {
        String s;
        // run while input is positice
        while (!(s = _In.getString()).equals("exit")) {
            // split the string
            String[] strings = s.split(" ");
            // make string into lowercase
            for(int i = 0; i < strings.length; i++){
                strings[i] = strings[i].toLowerCase();
            }
            // create a string buffer for the word
            StringBuffer buffer = new StringBuffer();
            // add the first string into the buffer
            buffer.append(strings[0]);
            // loop through the word
            for (int i = 1; i < strings.length; i++) {
                // append capitalized word
                buffer.append(capitalized(strings[i]));
            }
            System.out.print(buffer.toString());
        }
    }

    private static String capitalized(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
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