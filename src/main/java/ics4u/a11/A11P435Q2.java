package ics4u.a11;

/**
 * Assignment 11
 * Second Question
 *
 * @author Yu Liu
 */
public class A11P435Q2 {
    public static void main(String[] args) {
        String[] arr = new String[]{"A", "B", "C", "D"};
        System.out.println(seqSearchModified(arr, "C"));
        System.out.println(seqSearchModified(arr, "E"));
    }

    /**
     * Sequential Search
     * search arr from beginning of array
     * <p>
     * improves efficiency when the array is sorted (i.e. when item is not present)
     */
    public static int seqSearchModified(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            int c = arr[i].compareTo(item);
            if (c == 0) {
                return i;
            } else if (c > 0) { // already past the range where the item could be
                return -1;
            }
        }
        return -1;
    }
}
