package ics4u.a5;

/**
 * Assignment 5
 * Question 3
 *  Suppose that a class Complex has been deﬁned as follows:
 * class Complex { double re; double im; }
 *
 * (a) Write an instance method modulus for this class that could be called by a
 * statement like double size = z.modulus(); where z is of type Complex. If z represented
 * the value a + ib, then the call would set the variable size to the value of |z| = √a2 + b2.
 *
 * (b) Write an instance method called scale for the class Complex that could be called by a
 * statement like z.scale(x); where z is of type Complex and x is a double value. If, before
 * the call, z represented the value a + ib, then, after the call, it should represent the value x(a + ib).
 *
 * @author Yu Liu
 */

public class A5Q3 {

    public static void main(String[] args) {
        Complex z1 = new Complex(4, 3);
        System.out.println("z1: " + z1);
        System.out.println("z1 modulus: " + z1.modulus());
        System.out.println("z1 scaled by 5: " + z1.scaled(5));
    }

    private static class Complex {
        double real;
        double imag;

        private Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        /**
         * Find the hypotenuse of the complex number
         */
        double modulus() {
            return Math.sqrt(real * real + imag * imag);
        }

        /**
         * Returns this complex number scaled by a scalar value
         */
        private Complex scaled(double by){
            return new Complex(real * by, imag * by);
        }

        /**
         * Converts the complex number to string (for printing)
         */
        public String toString() {
            return "Complex(real = " + real + ", imag = " + imag + ")";
        }
    }
}
