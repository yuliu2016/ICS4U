package ics4u.ma2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 1
 * Question 9
 * Write a program that reads names in standard form and prints them in the form
 * last name, any given initials
 * The program should prompt the user for names, halting when the user provides the name ZZZ.
 * As examples, input of
 * Santa Claus
 * Michael J. Fox
 * Madonna
 * William Henry Richard Charles Windsor
 * ZZZ
 * should produce output of
 * Claus, S.
 * Fox, M. J.
 * Madonna
 * Windsor, W. H. R. C.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA2Q7 {
    public static void main(String[] args) {
        String s;
        // run while input is not ZZZ
        while (!(s = _In.getString()).equals("ZZZ")) {
            // split the name by spaces
            String[] names = s.split(" ");
            // first print out the last name
            System.out.print(names[names.length - 1]);
            // print out a comma when there are other parts to the name
            if (names.length > 1) {
                System.out.print(", ");
            }
            // loop through the rest of the name except the last one
            for (int i = 0; i < names.length - 1; i++){
                // print out the capitalized first letter of the name and add a dot after it
                System.out.print(names[i].trim().substring(0, 1).toUpperCase() + ". ");
            }
            // add a new line
            System.out.println();
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