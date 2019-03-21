package ics4u.a5;

/**
 * Assignment 5
 * Question 4
 *  Assuming that z1, z2, and z3 are of the type Complex described in the previous question,
 *
 *  (a) write an instance method plus that, if called by the statement z1 = z2.plus(z3);
 *  would set z1 to the sum of z2 and z3,
 *
 *  (b) write an instance method times that, if called by the statement z1 = z2.times(z3); would set z1 to the product of z2 and z3.
 *
 * @author Yu Liu
 */

public class A5Q4 {

    public static void main(String[] args) {
        Complex z1 = new Complex(2, 3);
        Complex z2 = new Complex(5, 4);
        Complex z3 = new Complex(0.5, 3.7);
        System.out.println("Z1: " + z1);
        System.out.println("Z2: " + z2);
        System.out.println("Z3: " + z3);
        System.out.println("Z2 + Z3: " + z2.plus(z3));
        System.out.println("Z2 * Z3: " + z2.times(z3));
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
        private Complex plus(Complex other){
            return new Complex(real + other.real, imag + other.imag);
        }


        /**
         * Returns the product of this complex number and other compex number
         */
        private Complex times(Complex other) {
            return new Complex(real * other.real - imag * other.imag, real * other.imag + other.real * imag);
        }

        /**
         * Converts the complex number to string (for printing)
         */
        public String toString() {
            return "Complex(real = " + real + ", imag = " + imag + ")";
        }
    }
}
