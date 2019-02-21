package ics4u.a1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Assignment 1
 * <p>
 * Problem 1 – Word Frame
 * creating a frame around the stars with words
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A1Q1 {
    public static void main(String[] args) {
        String word;
        // Run while input is not "exit"
        while (!(word = _In.getString()).equals("exit")) {
            // get array size
            int len = word.length() + 2;
            // create empty array
            char[][] charArray = new char[len][len];
            // fill array
            for (int i = 0; i < len; i++) Arrays.fill(charArray[i], '*');
            // index through word and add to four sides of the array using length
            for (int j = 1; j < len - 1; j++) {
                char ch = word.charAt(j - 1);
                charArray[0][j] = ch;
                charArray[len - 1][len - j - 1] = ch;
                charArray[len - j - 1][0] = ch;
                charArray[j][len - 1] = ch;
            }
            // print out array with string builder
            String builder = "";
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    System.out.print(charArray[i][j]);
                    System.out.print(' ');
                }
                System.out.println();
            }
        }
    }
}

class _In {
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