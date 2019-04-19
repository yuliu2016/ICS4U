package ics4u.a8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Assignment 8
 * Question 3
 * . Assuming that a Fraction class has been deﬁned as shown, write a fragment
 * that could be used to create an array of 100 Fraction objects representing
 * the fractions
 * 0 1
 * ,
 * 1 2
 * ,
 * 2 3
 * ,...,
 * 99 100
 * class Fraction { private int num; private int den;
 * public Fraction (int n, int d) { num = n; den = d; }
 * }
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A8Q3 {

    public static void main(String[] args) {
        Fraction[] fractions = new Fraction[100];
        for (int i = 0; i <fractions.length; i++) {
            // create the fraction at the index
            fractions[i] = new Fraction(i, i + 1);
            // print out the fraction
            System.out.println(fractions[i]);
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
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
        }
    }
}
