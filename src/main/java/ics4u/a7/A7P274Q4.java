package ics4u.a7;

/**
 * Assignment 7
 * Question 4
 *
 * Write a program that uses the Fraction class to ﬁnd and print, as fractions in lowest terms,
 * the values of the expression n X i=1 1 2i−1 − 1 2i + 1 for n = 1,2,...,10.
 *
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A7P274Q4 {

    public static void main(String[] args) {
        // keep track of the sum of fractions
        Fraction sum = Fraction.additiveIdentity;
        for (int i = 1; i <= 10; i++) {
            // do the arithmetic calculation, add it to the sum, then reduce it
            sum = sum.plus(new Fraction(1, (2 * i - 1)).plus(new Fraction(-1, 2 * i + 1))).reduced();
            System.out.println("n=" + i + ", Σ=" + sum);
        }
    }

    /**
     * Represents a fraction with integer numerators and denominators
     */
    private static class Fraction {

        static Fraction additiveIdentity = new Fraction(0, 1);

        int num; // numerator
        int den; // denominator

        private Fraction(int num, int den) {
            // make sure the denominator is not zero
            if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
            this.num = num;
            this.den = den;
        }

        /**
         * Reduces the fraction to lowest terms
         */
        Fraction reduced() {
            int gcd = gcd(num, den);
            return new Fraction(num / gcd, den / gcd);
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
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
        }
    }
}
