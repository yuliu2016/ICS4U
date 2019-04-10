package ics4u.a7;

/**
 * Assignment 6
 * Question 5
 *
 * Write a deﬁnition of an equals method for the Fraction class. Your method should
 * return true if and only if the Fraction objects being compared represent equivalent fractions.
 *
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A7P274Q3 {

    public static void main(String[] args) {
        Fraction f1 = new Fraction(10, 2);
        Fraction f2 = new Fraction(15, 3);
        if (f1.equals(f2)) {
            System.out.println("F1 equals F2");
        } else {
            System.out.println("F1 does not equal F2");
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
         * Check if the Fraction is equal to another in value
         */
        public boolean equals(Object obj) {
            if (obj instanceof Fraction) {
                Fraction other = (Fraction) obj;
                return num/den == other.num/other.den;
            }
            return false;
        }

        /**
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
        }
    }
}
