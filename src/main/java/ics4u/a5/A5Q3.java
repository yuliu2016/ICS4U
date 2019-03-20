package ics4u.a5;

/**
 * Assignment 4
 * Question 7
 * A complex number z = a+bi can be speciﬁed by its real part, a, and its imaginary part, b.
 * (a) Create a class Complex that could be used to represent complex numbers.
 * (b) Create objects z1 and z2 that represent the complex numbers 2 + 3i and 5−4i.
 * (c) Write a fragment that assigns to z1 the sum of z1 and z2.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A5Q3 {

    public static void main(String[] args) {
        Complex z1 = new Complex(2, 3);
        Complex z2 = new Complex(5, 4);
        System.out.println("Z1: " + z1);
        System.out.println("Z2: " + z2);
        System.out.println("Sum: " + z1.add(z2));
    }

    private static class Complex {
        double real;
        double imag;

        private Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        /**
         * Returns the sum of this complex number and other complex number
         */
        private Complex add(Complex other){
            return new Complex(real + other.real, imag + other.imag);
        }

        /**
         * Converts the complex number to string (for printing)
         */
        public String toString() {
            return "Complex(real = " + real + ", imag = " + imag + ")";
        }
    }
}
