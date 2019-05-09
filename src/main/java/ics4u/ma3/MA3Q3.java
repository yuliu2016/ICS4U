package ics4u.ma3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 3
 * Question 3
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA3Q3 {
    public static void main(String[] args) {
        System.out.println("Enter L, W: ");
        String input;
        while ((input = _In.getString()).length() > 0) {
            String[] split = input.split(" ");
            int L = Integer.parseInt(split[0].trim());
            int W = Integer.parseInt(split[1].trim());
            TileResult result = solveTiles(L, W);
            System.out.println("For a (" + L + " x " + W + ") floor: Use " +
                    result.count + " (" + result.length + " by " + result.width + ") tiles");
            System.out.println("Enter L, W: ");
        }
    }

    /**
     * Represents the result of the tile problem
     */
    static class TileResult {
        /**
         * Length of the required tile
         */
        int length;
        /**
         * Width of the required tile
         */
        int width;
        /**
         * Count of the required tiles
         */
        int count;

        public TileResult(int length, int width, int count) {
            this.length = length;
            this.width = width;
            this.count = count;
        }
    }

    /**
     * Solves the tile problem by producing a Result
     * @param L length of the room
     * @param W width of the room
     * @return the Result
     */
    public static TileResult solveTiles(int L, int W) {
        // Re-orient the room if it's more wide than long
        if (W > L) return solveTiles(W, L);
        // Check that the room size is reasonable
        if (L < 1 || W < 1) throw new IllegalArgumentException();

        // Setup result variable
        TileResult r;
        // Calculate area of the room
        int A = W * L;


        // Calculate the maximum multiplier for a 3*2 tile to fit in the room
        int i = (int) Math.sqrt(A / 6.0);
        // Decrease i until it reaches zero, or until it satisfies as a factor of the room area
        // Special case: check if width and length is bigger than the minimum tile size
        while (i > 0 && !(A % (i * i * 6) == 0 && W >= 2 * i && L >= 3 * i)) i--;
        // Calculate the count of tiles according to i
        int k = i == 0 ? Integer.MAX_VALUE : A / (i * i * 6);
        // Update the result
        r = new TileResult(i * 3, i * 2, k);


        // Calculate the maximum multiplier for a 2*1 tile to fit in the room
        i = (int) Math.sqrt(A / 2.0);
        // Decrease i until it reaches zero, or until it satisfies as a factor of the room area
        // Special case: check if width and length is bigger than the minimum tile size
        while (i > 0 && !(A % (i * i * 2) == 0 && W >= i && L >= 2 * i)) i--;
        // Calculate the count of tiles according to i
        k = i == 0 ? Integer.MAX_VALUE : A / (i * i * 2);
        // Update the result if the count is less than the previous case
        if (k < r.count) r = new TileResult(i * 2, i, k);
        // Calculate the maximum multiplier for a 1*1 tile to fit in the room


        i = (int) Math.sqrt(A);
        // Decrease i until it reaches zero, or until it satisfies as a factor of the room area
        // in both sides
        while (i > 0 && !(L % i == 0 && W % i == 0)) i--;
        // Calculate the count of tiles according to i
        k = i == 0 ? Integer.MAX_VALUE : A / (i * i);
        // Update the result if the count is less than the previous case
        if (k < r.count) r = new TileResult(i, i, k);

        return r;
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