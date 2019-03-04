package ics4u.ma1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 1
 * Question 10
 * Write a program that reads a string containing a Roman numeral representing a
 * value in the range 1 to 3999. The program should print the Roman numeral and
 * its value in our notation (a HinduArabic numeral).
 * • Roman numerals use the symbols I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, and M = 1000.
 * • Roman numerals use an additive rather than a positional notation. Numerals are
 * formed by writing symbols from the preceding list, from left to right, to represent
 * a sum, each time using the symbol for the largest possible value from the list of symbols.
 * This rule is subject to the limitation that no symbol maybe written more than three times in a row.
 * To avoid this, the system uses IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, and CM = 900.
 * As examples, VIII = 8, XXIV = 24, LXXXIX = 89, MMVIII = 2008, MDCCCLXXIX = 1879
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA1Q10 {
    public static void main(String[] args) {
        String s;
        // run while input is not exit
        while (!(s = _In.getString()).equals("exit")) {
            // print out the roman numeral
            System.out.println("Roman numeral: " + s);
            // print out the parsed number
            System.out.println("Decimal: " + parseInt(s.toUpperCase()));
        }
    }

    /**
     * Parses the roman numeral into an int
     */
    private static int parseInt(String s) {
        int n = s.length() - 1;
        // keep track of the index
        int i = 0;
        // keep track of the sum
        int sum = 0;
        // keep track of whether the last addition is a two-letter numeral
        boolean isTwoLetter = false;
        // loop until the second-last letter
        while (i < n) {
            // peek at the next two letters for a two-letter numeral
            int peek2 = peek2(s, i);
            // check if peek2 is found
            if (peek2 != 0) {
                // if found, add it to the sum
                sum += peek2;
                // increase the index by 2 letters
                i += 2;
                // turn on the two letter flag
                isTwoLetter = true;
            } else {
                // now peek at the single next letter
                int peek = peek(s, i);
                // check if peek is found
                if (peek != 0) {
                    // if found, add it to the sum
                    sum += peek;
                }
                // increase the index by 1 letter
                i++;
                // turn off the two letter flag
                isTwoLetter = false;
            }
        }
        // check if there is still an unprocessed letter
        if (!isTwoLetter) {
            // if so, do a final one-letter peek
            int finalPeek = peek(s, n);
            // if the peek finds a number, add it to the sum
            if (finalPeek != 0) {
                sum += finalPeek;
            }
        }
        return sum;
    }

    /**
     * Peeks at a string at a specified index, and returns the number
     * represented by the character, or returns 0 if none matches
     */
    private static int peek(String s, int i) {
        char ch = s.charAt(i);
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * Peeks two letters in a string at a specified index, and returns the number
     * represented by the two-letter sequence, or returns 0 if none matches
     */
    private static int peek2(String s, int i) {
        String s2 = s.substring(i, i + 2);
        if (s2.equals("IV")) {
            return 4;
        } else if (s2.equals("IX")) {
            return 9;
        } else if (s2.equals("XL")) {
            return 40;
        } else if (s2.equals("XC")) {
            return 90;
        } else if (s2.equals("CD")) {
            return 400;
        } else if (s2.equals("CM")) {
            return 900;
        } else {
            return 0;
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