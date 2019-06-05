package ics4u.a13;

/**
 * Assignment 13
 * Page 408
 * Question 5
 *
 * @author Yu Liu
 */
public class SelectionSort {

    public static void main(String[] args) {
        double[] arr = new double[]{1, 4, 2, 8, 5, 7};
        System.out.println("Intial: " + arrayToString(arr));
        selectionSort(arr);
        System.out.println("Sorted: " + arrayToString(arr));
    }

    public static void selectionSort(double[] arr) {
        for (int bottom = 0; bottom < arr.length; bottom++) {
            int smallLoc = 0;
            for (int i = bottom; i > 0; i--) if (arr[i] > arr[smallLoc]) smallLoc = i;
            double temp = arr[bottom];
            arr[bottom] = arr[smallLoc];
            arr[smallLoc] = temp;
        }
    }

    public static String arrayToString(double[] arr) {
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
}
