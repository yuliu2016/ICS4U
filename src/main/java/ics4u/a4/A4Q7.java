package ics4u.a4;

/**
 * Assignment 3
 * Question 5
 *  Assuming that two objects f1 and f2 of type Fraction have been created
 *  and assigned values, write statements to perform each task.
 * (a) Double the value of f1.
 * (b) Invert f2.
 * (c) Make f1 equal to the (unsimpliﬁed) product of f1 and f2.
 * (d) Make f2 equal to the (unsimpliﬁed) sum of f1 and f2.
 * (e) Make f1 equal to |f1|.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A4Q7 {

    public static void main(String[] args) {
        Fraction f1 = new Fraction(-1, 2);
        Fraction f2 = new Fraction(3, 4);

        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);
        System.out.println();

        // Double the value of f1
        System.out.println("Double of f1: " + f1.scaledBy(2));
        // Invert f2
        System.out.println("Inverted f2: " + f2.inverse());
        // Multiply f1 with f2
        System.out.println("Product: " + f1.times(f2));
        // Sum f1 with s2
        System.out.println("Sum: " + f1.add(f2));
        // Absolute value
        System.out.println("Absolute value: " + f1.abs());
    }

    private static class Fraction {
        int num; // numerator
        int den; // denominator

        private Fraction(int num, int den) {
            if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
            this.num = num;
            this.den = den;
        }

        /**
         * Returns the this fraction scaled by a integer
         */
        private Fraction scaledBy(int scale) {
            return new Fraction(num * scale, den);
        }

        /**
         * Returns the inverse (reciprocal) of this fraction
         */
        private Fraction inverse(){
            return new Fraction(den, num);
        }

        /**
         * Returns the product of this fraction and another fraction
         */
        private Fraction times(Fraction other) {
            return new Fraction(num * other.num, den * other.den);
        }

        /**
         * Returns the sum of this fraction and another fraction
         */
        private Fraction add(Fraction other) {
            return new Fraction(num * other.den + other.num * den, den * other.den);
        }

        /**
         * Returns the absolute value of this fraction
         */
        private Fraction abs() {
            return new Fraction(Math.abs(num), Math.abs(den));
        }

        /**
         * Converts the fraction to string (for printing)
         */
        public String toString() {
            return "Fraction(num = " + num + ", den = " + den + ")";
        }
    }
}
