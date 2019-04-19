package ics4u.a7;

/**
 * Assignment 7
 * Question 6
 *
 *  Modify the toString method of the Fraction class so that, if the magnitude of the
 *  numerator is equal to or greater than that of the denominator, the method returns
 *  a string that has the form of either an integer, if appropriate, or a mixed fraction.
 *  The table shows examples.
 *
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A7P274Q6 {

    public static void main(String[] args) {
        // Run test cases for toString method
        System.out.println(new Fraction(3, 5));
        System.out.println(new Fraction(13, 3));
        System.out.println(new Fraction(20, 4));
        System.out.println(new Fraction(-11, 6));
        System.out.println(new Fraction(-15, 5));
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
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            if (Math.abs(num) > Math.abs(den)) {
                if (num % den == 0) {
                    return "" + (num / den);
                }
                return (num / den) + " and " + Math.abs(num % den) + "/" + den;
            }
            return num + "/" + den;
        }
    }
}
