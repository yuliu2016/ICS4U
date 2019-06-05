package ics4u.a12;

/**
 * Assignment 12
 * ICS4U class 2019
 *
 * @author Yu Liu
 */
public class BinSearch {

    public static void main(String[] args) {
        double[] v = new double[]{0.5, 0.4, 0.3, 0.2, 0.1};

        System.out.println("Array: {0.5, 0.4, 0.3, 0.2, 0.1}");
        System.out.println("Searching 0.34 -- Result: " + binSearchDescendingNearest(v, 0.34));
        System.out.println("Searching 0.27 -- Result: " + binSearchDescendingNearest(v, 0.27));
    }

    public static int binSearchDescendingNearest(double[] list, double item) {
        // lower bound of subarray
        int bottom = 0;
        // upper bound of subarray
        int top = list.length - 1;
        // middle of subarray
        int middle;
        // index of item in array
        int location = -1;
        while (bottom <= top) {
            // Find the middle
            middle = (bottom + top) / 2;
            if (list[middle] == item) return middle; // success
            else if (list[middle] > item) bottom = middle + 1; // not in bottom half
            else top = middle - 1; // not in top half
        }
        // Return the nearest number
        if (list[top] - item < item - list[bottom]) return top;
        else return bottom;
    }
}
