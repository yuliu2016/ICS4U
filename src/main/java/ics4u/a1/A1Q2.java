package ics4u.a1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 1
 * <p>
 * Problem 2 – Braille
 * <p>
 * - Convert groups of 3 lines of braille patterns to single-line text
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

/*
Sample Input:
oxxoxooxoooxoxoooxxoxoooxooxxoooxoxxooxoooxoxoxoxo
xxxxooxxooxoxoooxxxxoxooooxooxoooxxoooooooxooxoxoo
oxooooxoooooxoooxoooooooxxxoooooxoooooooooooxoxoxo
oxoxoxxoxoxooxooxxoxxxoxxoxoxooxooxoxoooxxxoxxxoxoxooxxooxoxxoxxox
xxxoxxxxoxooxxooxoxoooxxooxxoxxooooxxxoooooxoxxooxxxxoooxxxooxoxxo
oxooxoooxoxxxoooxoooooxoxxxoooxoooxoxoooooxoxoxxooxoxoooxoooxoxoxo
xoxoooxxxxooxoxoxooxooxoxxxxoooxxooxoxxoxoxooxoo
oxxxoooooxoooxooxxxooooooxoxooxxxxxoxooooxxxxooo
xoooooxoxxooooooxoxoooooxooooooxooooxoxoooxoxooo
xoxooxooxoxooxxooooxoxoxooxxxooxoxoxxxxx
xxoxxxooxoooxxoxooxoxxxoooxxoxxxxxxooxxx
ooxooxooxoooxoooooooxoxoooooooxoxoooxooo
xxxoxooxxoxooxxoxoooxoxxxxooxxxoxooxxoxooxxoxo
ooooxxxooxooxooxxxoooooxoxooooooxxxooxooxooxxx
ooxxxoooxoxxxoooxoooooxoooooooxxxoooxoxxxoooxo

Sample Output:
whatistheuseofabook
withouticturesorconversations
ohmyearsandwhiskers
howlpateitsgetting
 */
public class A1Q2 {

    public static void main(String[] args) {
        // create an array of binary numbers representing braille patterns
        int[] patterns = new int[]{
                32, // 0b100000 - A
                40, // 0b101000 - B
                48, // 0b110000 - C
                52, // 0b110100 - D
                36, // 0b100100 - E
                56, // 0b111000 - F
                60, // 0b111100 - G
                44, // 0b101100 - H
                24, // 0b011000 - I
                28, // 0b011100 - J
                34, // 0b100010 - K
                42, // 0b101010 - L
                50, // 0b110010 - M
                54, // 0b110110 - N
                38, // 0b100110 - O
                58, // 0b111010 - P
                62, // 0b111110 - Q
                46, // 0b101110 - R
                26, // 0b011010 - S
                30, // 0b011110 - T
                35, // 0b100011 - U
                43, // 0b101011 - V
                29, // 0b011101 - W
                51, // 0b110011 - X
                55, // 0b110111 - Y
                39, // 0b100111 - Z
        };
        char[][] lines;
        while ((lines = getThreeLines()) != null) {
            // find number of characters
            int len = lines[0].length;
            // go through each character in the line
            for (int i = 0; i < len; i+= 2) {
                // convert characters to 1s and 0s and shift them by their bit number
                // then adding the result up using the bitwise OR operation
                int code = xoToInt(lines[0][i]) << 5
                        | xoToInt(lines[0][i + 1]) << 4
                        | xoToInt(lines[1][i]) << 3
                        | xoToInt(lines[1][i + 1]) << 2
                        | xoToInt(lines[2][i]) << 1
                        | xoToInt(lines[2][i + 1]);
                boolean foundCode = false;
                // searches the result code in the patterns array
                for (int j = 0; j < patterns.length; j++)
                    if (patterns[j] == code) {
                        // convert index to character (`a` starts at 97 in ASCII)
                        System.out.print((char) (j + 97));
                        foundCode = true;
                        break;
                    }
                if (!foundCode) System.out.print(' ');
            }
            // Add a newline
            System.out.println();
        }
    }

    /**
     * Get three lines of input
     */
    private static char[][] getThreeLines() {
        char[][] lines = new char[3][];
        for (int i = 0; i < 3; i++) {
            String line = _In.getString();
            if (line.equals("exit")) return null;
            lines[i] = line.toCharArray();
        }
        return lines;
    }

    /**
     * Converts the characters `x` and `o` to an integer representation
     */
    private static int xoToInt(char xo) {
        if (xo == 'x') return 1;
        if (xo == 'o') return 0;
        throw new IllegalStateException();
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


