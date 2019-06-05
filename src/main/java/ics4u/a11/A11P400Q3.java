package ics4u.a11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Assignment 11
 * First Question
 *
 * @author Yu Liu
 */
public class A11P400Q3 {
    public static void main(String[] args) {

        System.out.print("Enter length of the array: ");
        int len = _In.getInt();
        System.out.println("Enter values:");
        int[] values = new int[len];
        for(int i = 0; i < len; i++) {
            values[i] = _In.getInt();
        }

        System.out.println("Test:");
        System.out.println("Array is: " + arrayToString(values));
        int query;

        while ((query = _In.getInt()) != 0) {
            int index = seqSearchModified(values, query);
            System.out.print(query + " is at the index " + index + " of the array");
            System.out.println("Array is: " + arrayToString(values));
        }
    }

    /**
     * Sequential Search
     * search arr from beginning of array
     *
     * improves efficiency when the same item is searched multiple times
     */
    public static int seqSearchModified(int[] arr, int item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == item) {
                // switch with the items that preceeds it if not the first element
                if (i > 0) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
                return i;
            }
        }
        return -1;
    }

    public static String arrayToString(int[] arr) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (int i = 0; i < arr.length - 1; i++) {
            buffer.append(arr[i]);
            buffer.append(", ");
        }
        buffer.append(arr[arr.length - 1]);
        buffer.append("}");
        return buffer.toString();
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
