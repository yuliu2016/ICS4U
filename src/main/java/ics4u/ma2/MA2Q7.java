package ics4u.ma2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 1
 * Question 9
 * Write a class Lock that could be used to create electronic lock objects.
 * Each lock may be in either an open (unlocked) or a closed (locked) state
 * and each one is protected by its own integer key which must be used to
 * unlock it. The class should contain the following methods.
 * • public Lock
 * (int key) Create a lock that is initially open.
 * • public void close () Close the lock.
 * • public boolean isOpen () Return true if and only if the lock is open.
 * • public void open (int key) Open the lock if and only if the parameter
 * key matches the lock’s own key. If the lock is closed and the keys do not match,
 * count the failed attempt. If the same lock receives three or more failed attempts
 * in a row, print the message "ALARM".
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA2Q7 {
    public static void main(String[] args) {
        Lock lock1 = new Lock(111);
        Lock lock2 = new Lock(222);
        lock1.close();
        lock2.close();
        System.out.println(lock1.isOpen()); // prints false
        lock1.open(123); // fails to open lock1
        lock1.open(456); // fails to openlock1
        lock2.open(222); // opens lock2
        lock1.open(789); // fails - prints ALARM
    }

    static class Lock {
        private int key;
        private boolean isOpen;
        private int attempts;

        Lock(int key) {
            this.key = key;
            this.isOpen = true;
        }

        boolean isOpen() {
            return isOpen;
        }

        void close() {
            System.out.println("Lock closed!");
            isOpen = false;
        }

        void open(int key) {
            if (isOpen) {
                System.out.println("Lock is already opened");
            }
            if (key == this.key) {
                isOpen = true;
                attempts = 0;
                System.out.println("Lock opened!");
            } else {
                attempts++;
                if (attempts >= 3) {
                    System.out.println("ALARM");
                } else {
                    System.out.println("Attempt #" + attempts);
                }
            }
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