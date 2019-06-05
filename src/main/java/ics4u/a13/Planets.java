package ics4u.a13;

/**
 * Assignment 13
 * Page 408
 * Question 4
 *
 * @author Yu Liu
 */
public class Planets {

    public static void main(String[] args) {
        String[] planets = new String[]{"Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
        System.out.println("Initial array: " + arrayToString(planets));
        insertionSort(planets);
        System.out.println("Sorted array: " + arrayToString(planets));
    }

    public static String arrayToString(String[] arr) {
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

    /**
     * Insertion sort on an array of string
     */
    public static void insertionSort(String[] arr) {
        for (int top = 1; top < arr.length; top++) {
            String item = arr[top];
            int i = top;
            while (i > 0 && item.compareTo(arr[i - 1]) < 0) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = item;
        }
    }
}
