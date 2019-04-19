package ics4u.a8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 8
 * Question 11
 * The Sieve of Eratosthenes is the name given to an algorithm for ﬁnding prime numbers
 * (natural numbers having exactly two distinct natural number divisors, one and themselves).
 * The sieve starts by considering all numbers greater than one as possible primes.
 * <p>
 * 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ...
 * <p>
 * The ﬁrst number in this list, 2, must be prime but all multiples of two cannot be prime
 * and hence can be eliminated from the list to give us
 * <p>
 * 2 3 /4 5 /6 7 /8 9 1/0/ 11 1/2/ 13 1/4/ 15 ...
 * <p>
 * The next number still in the list, 3, must also be prime since it was not eliminated when
 * we found multiples of two. We can, however, eliminate all multiples of three to obtain
 * <p>
 * 2 3 /4 5 /6 \ 7 /8 9 \ 1/0/ 11 1/2/ \\ 13 1/4/ 1 \5 \ ...
 * <p>
 * In the algorithm, this process continues (eliminating multiples of ﬁve, seven, and so on) until
 * it is no longer possible to eliminate any multiples of values left in the table. The values that
 * remain must all be primes. Write a program to implement Eratosthene’s Sieve. The program should
 * ask the user for an upper bound and should then print all the prime numbers less than or equal
 * to that upper bound. In your program, use a boolean array to maintain a record of the numbers
 * that are possible primes. The output should have eight values per line with each value
 * right-justiﬁed in a ﬁeld that is ten characters wide. You may ﬁnd the methods of the Out
 * class shown in Appendix A useful for this.
 *
 * @author Yu Liu
 */

public class A8Q11 {

    public static void main(String[] args) {
        // get the upper bound for the sieve
        int n;
        System.out.print("Enter the upper bound between [2, 10000]: ");
        while ((n = _In.getInt()) >= 2 && n <= 10000) {
            // calculate prime numbers
            int[] primes = sieveOfEratosthenes(n);
            // print them out in a table
            int rows = primes.length / 8;
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < 8; j++) {
                    _Out.print(primes[i * 8 + j], 10);
                }
                System.out.println();
            }
            for (int j = 0; j < primes.length % 8; j++) {
                _Out.print(primes[rows * 8 + j], 10);
            }
            System.out.println();
            System.out.print("Enter the upper bound between [2, 10000]: ");
        }
    }

    /**
     * Computes the set of all prime numbers between 2 and an upper bound (inclusive)
     * with the Sieve of Eratosthenes algorithm
     *
     * @param N the integer upper bound of the sieve. Note that the JVM doesn't usually have
     *          enough memory configured to store the entire sieve because this implementation
     *          is not segmented. Upper bound must be greater or equal to 2
     * @return an integer array containing all prime numbers between the interval of [2, n]
     */
    static int[] sieveOfEratosthenes(int N) {
        // check bounds
        if (N < 2) throw new IllegalArgumentException("The Sieve cannot have an upper bound less than 2");
        // initialize array of candidate numbers
        int[] C = new int[N - 1];
        for (int i = 0; i < N - 1; i++) C[i] = i + 2;
        // q represents the upper bound of indices that actually need to be checked
        int q = (int) Math.sqrt(N);
        // loop through numbers between [2, q] with the variable p
        for (int p = 2; p <= q; p++) {
            // skip if the number is already determined to be not prime
            if (C[p - 2] == -1) continue;
            // loop through multiples (excluding itself) of p and mark it as not prime
            for (int i = p * 2; i < N + 1; i += p) C[i - 2] = -1;
        }
        // Find the number of primes in the sieve
        int k = 0;
        for (int i = 0; i < N - 1; i++) if (C[i] > 0) k++;
        // create an array with the proper size
        int[] P = new int[k];
        // copy over prime values
        for (int i = 0, j = 0; i < N - 1; i++) if (C[i] > 0) P[j++] = C[i];
        return P;
    }

    private static class _Out {

        public static void print(int n, int fieldWidth) {
            StringBuffer s = new StringBuffer(String.valueOf(n));
            int stop = fieldWidth - s.length();
            for (int i = 0; i < stop; i++) s.insert(0, " ");
            System.out.print(s);
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
