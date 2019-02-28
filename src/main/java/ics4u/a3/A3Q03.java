package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 3
 * Write a program that asks the user to enter a word and a letter.  Your
 * program should count how many times the letter appears in the word and find
 * the location of the LAST occurrence of that letter.  If the letter is not in
 * the word, output a message saying so.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A3Q03 {
    public static void main(String[] args) {
        String s;
        char ch;
        while (true) {
            // get word
            System.out.print("Enter word: ");
            s = _In.getString();
            if (s.equals("exit")) break;
            // get char
            System.out.print("Enter char: ");
            ch = _In.getChar();
            // convert to array
            char[] chars = s.toCharArray();
            // count of char in word
            int count = 0;
            // last occurence of ch
            // last does not exist when it's -1
            int last = -1;
            // iterate in reverse to find last
            for (int i = chars.length - 1; i >= 0; i--) {
                // chech if the current char is the wanted char
                if (chars[i] == ch) {
                    // assign last if it's not set
                    if (last == -1) {
                        last = i;
                    }
                    // increment count
                    count++;
                }
            }
            System.out.println("Charcater occured: " + count);
            // print depending on if the last occurence is found
            if (last == -1) {
                System.out.println("No last location");
            } else {
                System.out.println("Last location: " + last);
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