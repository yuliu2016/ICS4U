package ics4u.a5;

/**
 * Assignment 5
 * Question 2
 * <p>
 * Complete the deﬁnitions of the following instance methods for the Fraction class.
 * (a) public void plusEquals (Fraction other) The method should have the eﬀect (for Fraction objects) that
 * the += operator has for primitive numeric types. Thus, if called by the statement p.plusEquals(q);
 * (where p and q are objects of type Fraction), the method would make p represent the sum of the
 * fractions currently represented by p and q while the value of q would be left unchanged.
 * <p>
 * (b) public Fraction plus (Fraction f) The method should return a Fraction object whose value
 * is the sum of the implicit object parameter and the explicit parameter, f. The method should
 * leave both its explicit and implicit parameters unchanged.
 * <p>
 * (c) public void reduce () The method should reduce its implicit Fraction parameter to lowest terms.
 * For example, if f represents the fraction 16 24, the statement f.reduce(); should change f so that
 * it represents the fraction 2 3.
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A5Q2 {

    public static void main(String[] args) {
        MutableFraction p = new MutableFraction(-1, 2);
        Fraction q = new Fraction(16, 24);

        System.out.println("p: \t\t\t\t\t\t" + p);
        System.out.println("q: \t\t\t\t\t\t" + q);

        // add q to p and print out the result
        p.plusEquals(q);
        System.out.println("p.plusEquals(q): \t\t" + p);

        // add q and p and print out the result
        System.out.println("p.plus(q): \t\t\t\t" + p.plus(q));

        // reduce q and print out the result
        System.out.println("q.reduced(): \t\t\t" + q.reduced());

        // reduce p + q and print out the result
        System.out.println("p.plus(q).reduced(): \t" + p.plus(q).reduced());
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
         * Returns the sum of this fraction and another fraction
         */
        Fraction plus(Fraction other) {
            return new Fraction(num * other.den + other.num * den, den * other.den);
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
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
        }
    }

    /**
     * Represents a {@link Fraction} that can be changed
     */
    private static class MutableFraction extends Fraction {

        private MutableFraction(int num, int den) {
            super(num, den);
        }

        /**
         * Add another fraction to the current fraction by calling the immutable
         * version then assigning the results
         */
        void plusEquals(Fraction other) {
            Fraction result = super.plus(other);
            num = result.num;
            den = result.den;
        }
    }
}
