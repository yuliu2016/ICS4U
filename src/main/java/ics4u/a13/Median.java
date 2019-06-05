package ics4u.a13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 13
 * Page 411
 * Question 4
 *
 * @author Yu Liu
 */
public class Median {
    public static void main(String[] args) {
        System.out.print("Enter number of items: ");
        int n = _In.getInt();
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            arr[i] = _In.getDouble();
        }
        System.out.println("The median is: " + median(arr));
    }


    /**
     * Find the median of the double array
     */
    public static double median(double[] arr) {
        int len = arr.length;
        // Sort the array first
        sort(arr);
        if (len % 2 == 0) {
            return (arr[len / 2 - 1] + arr[len / 2]) / 2;
        } else {
            return arr[len / 2];
        }
    }

    public static void sort(double[] arr) {
        // call sort on the full array
        sort(arr, 0, arr.length - 1);
    }

    /**
     * Sort the array between a start index and end index
     * Finds a pivot; partitions the array into two subarrays;
     * first subarray less than the pivot; second array greater
     * than the pivot; then call this sort recursively
     */
    public static void sort(double[] arr, int start, int end) {
        if (start >= end) return;
        double pivot = arr[start + (end - start) / 2];
        int i = start;
        int j = end;
        while (true) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i >= j) break;
            double temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        sort(arr, start, j - 1);
        sort(arr, j + 1, end);
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
