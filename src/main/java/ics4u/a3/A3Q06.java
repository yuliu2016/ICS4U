package ics4u.a3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 3
 * Question 6
 * Write a program that asks the user to enter an integer number (recommend input as a string).
 * Add each of the digits together ONLY IF the digits are either the same or larger.
 * If the digits get smaller, subtract the digits.  For example:
 * input = "123455" would give an output of 20 (1 + 2 + 3 + 4 + 5 + 5)
 * input = "123115" would give an output of 11 (1 + 2 + 3 - 1 + 1 + 5)
 * input = "553215" would give an output of 9 (5 + 5 - 3 - 2 - 1 + 5)
 *
 * @author Yu Liu
 */

public class A3Q06 {
    public static void main(String[] args) {
        String s;
        // run while input is positice
        while (!(s = _In.getString()).equals("exit")) {
            // convert to char array
            char[] chars = s.toCharArray();
            // create an array to hold digits
            int[] digits = new int[chars.length];
            try {
                for (int i = 0; i < chars.length; i++){
                    // convert each digit to string
                    digits[i] = Integer.parseInt(String.valueOf(chars[i]));
                }
            } catch (NumberFormatException e){
                continue;
            }
            // keep track of sum and last digit
            int sum = 0;
            int lastDigit = 0;
            for (int i = 0; i < digits.length; i++){
                // add each of the digits together ONLY IF the digits are either the same or larger.
                // if the digits get smaller, subtract the digits.
                int current = digits[i];
                if (current >= lastDigit){
                    sum += current;
                } else {
                    sum -= current;
                }
                lastDigit = current;
            }
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