package ics4u.ma3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 3
 * Question 1
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA3Q1 {
    public static void main(String[] args) {
        System.out.print("Enter M, N, P, Q: ");
        String input;
        while ((input = _In.getString()).length() > 0) {
            String[] split = input.split(" ");
            int M = Integer.parseInt(split[0].trim());
            int N = Integer.parseInt(split[1].trim());
            int P = Integer.parseInt(split[2].trim());
            int Q = Integer.parseInt(split[3].trim());
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < P; i++) {
                for (int j = 0; j < M + (P + Q) * 2; j++) buffer.append('#');
                buffer.append('\n');
            }
            for (int i = 0; i < Q; i++) {
                for (int j = 0; j < P; j++) buffer.append('#');
                for (int j = 0; j < M + Q * 2; j++) buffer.append('+');
                for (int j = 0; j < P; j++) buffer.append('#');
                buffer.append('\n');
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < P; j++) buffer.append('#');
                for (int j = 0; j < Q; j++) buffer.append('+');
                for (int j = 0; j < M; j++) buffer.append('.');
                for (int j = 0; j < Q; j++) buffer.append('+');
                for (int j = 0; j < P; j++) buffer.append('#');
                buffer.append('\n');
            }
            for (int i = 0; i < Q; i++) {
                for (int j = 0; j < P; j++) buffer.append('#');
                for (int j = 0; j < M + Q * 2; j++) buffer.append('+');
                for (int j = 0; j < P; j++) buffer.append('#');
                buffer.append('\n');
            }
            for (int i = 0; i < P; i++) {
                for (int j = 0; j < M + (P + Q) * 2; j++) buffer.append('#');
                buffer.append('\n');
            }
            System.out.println(buffer.toString());
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