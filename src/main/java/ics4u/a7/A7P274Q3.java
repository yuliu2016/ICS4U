package ics4u.a7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 7
 * Pg 274
 * Question 3
 * <p>
 * Write a program that has a main method that uses the Fraction class to solve the following
 * problem: prompt the user for three fractions, a, b, and c, compute the value of ab−c2, print
 * the result, and print the sum of the squares of the numerator and denominator of the result.
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A7P274Q3 {

    public static void main(String[] args) {
        Fraction a = getUserInputFraction("a");
        Fraction b = getUserInputFraction("b");
        Fraction c = getUserInputFraction("c");
        // calculate the result of a * n - c^2
        Fraction result = a.times(b).plus(c.times(c).negate()).reduced();
        System.out.println("a * b - c^2 = " + result);
        // calculate sum of squares of numerator and denominator
        System.out.println("sum of squares = " + result.num * result.num + result.den * result.den);
    }

    /**
     * Get the user input of a Fraction
     */
    private static Fraction getUserInputFraction(String name) {
        System.out.print("Enter the numerator for fraction " + name + ": ");
        int num = _In.getInt();
        System.out.print("Enter the denominator for fraction " + name + ": ");
        int den = _In.getInt();
        while (den == 0) {
            System.out.println("The denominator cannot be zero");
            System.out.print("Enter the denominator for fraction " + name + ": ");
            den = _In.getInt();
        }
        return new Fraction(num, den);
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
         * Check if the Fraction is equal to another in value
         */
        public boolean equals(Object obj) {
            if (obj instanceof Fraction) {
                Fraction other = (Fraction) obj;
                return num / den == other.num / other.den;
            }
            return false;
        }


        /**
         * Reduces the fraction to lowest terms
         */
        Fraction reduced() {
            int gcd = gcd(num, den);
            return gcd > 1 ? new Fraction(num / gcd, den / gcd) : new Fraction(num, den);
        }

        /**
         * Uses the Euclidean algorithm to calculate the greatest common divisor
         */
        private static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        /**
         * Returns the sum of this fraction and another fraction
         */
        Fraction plus(Fraction other) {
            return new Fraction(num * other.den + other.num * den, den * other.den);
        }

        /**
         * Returns the inverse (reciprocal) of this fraction
         */
        private Fraction negate() {
            return new Fraction(-num, den);
        }

        /**
         * Returns the product of this fraction and another fraction
         */
        private Fraction times(Fraction other) {
            return new Fraction(num * other.num, den * other.den);
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
