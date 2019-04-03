package ics4u.ma2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 2
 * Question 6
 * Write an instance method harmonicSum for the Fraction class discussed throughout this
 * chapter. The method should have one int parameter, n. It should set its implicit
 * parameter to the value of the fraction found by adding the terms of the harmonic series
 * The fraction should be reduced to lowest terms. If n < 1, then the fraction should be set to 0 1.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA2Q6 {
    public static void main(String[] args) {
        Fraction theFraction = new Fraction(1, 1);
        int n;
        System.out.println("Enter n for the harmonic sum:");
        // run while input is not exit
        while ((n = _In.getInt()) >= 0) {
            // calculate harmonic sum and print it
            theFraction.harmonicSum(n);
            System.out.println("result: " + theFraction);
            System.out.println("Enter n for the harmonic sum:");
        }
    }

    /**
     * Represents a fraction with integer numerators and denominators
     */
    private static class Fraction {
        int num; // numerator
        int den; // denominator

        private Fraction(int num, int den) {
            // make sure the denominator is not zero
            if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
            this.num = num;
            this.den = den;
        }

        /**
         * Gets the harmonic sum and sets it to the implicit parameter
         */
        private void harmonicSum(int n) {
            Fraction result = calculateHarmonicSum(n);
            num = result.num;
            den = result.den;
        }

        /**
         * Calculate the harmonic sum
         */
        private static Fraction calculateHarmonicSum(int n) {
            // check for special cases
            if (n == 0) return new Fraction(0, 1);
            else if (n >= 24) throw new IllegalArgumentException("The int type cannot handle input bigger than 23");
            int num = 0;
            int den = 1;
            // go through all fractions in the series
            for (int i = 1; i <= n; i++) {
                // add 1/n to current fraction
                num *= i;
                num += den;
                den *= i;
                // reduce to lowest terms
                int gcd = gcd(num, den);
                num /= gcd;
                den /= gcd;
            }
            return new Fraction(num, den);
        }

        /**
         * Uses the Euclidean algorithm to calculate the greatest common divisor
         */
        private static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        /**
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
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