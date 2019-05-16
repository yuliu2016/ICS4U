package ics4u.a10;

import java.io.*;
import java.text.NumberFormat;

/**
 * Assignment 10
 * Question 1
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A10Q1 {

    public static void main(String[] args) {

        File file = new File("name_address.txt");
        System.out.println("File path: " + file.getAbsolutePath());

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            // Write CSV headers
            writer.println("Name,Address,City,Postal Code,Phone Number");
            int i = 0;
            do {
                // print out entry number
                i++;
                System.out.println("Entry #" + i + " ----------- (type exit after entry to exit)");

                // get name and write to file
                System.out.print("Name: ");
                writer.print(_In.getString().trim());
                writer.print(",");

                // get name and write to file
                System.out.print("Address: ");
                writer.print(_In.getString().trim());
                writer.print(",");

                // get name and write to file
                System.out.print("City: ");
                writer.print(_In.getString().trim());
                writer.print(",");

                // get name and write to file
                System.out.print("Postal Code: ");
                writer.print(_In.getString().trim());
                writer.print(",");

                // get name and write to file
                System.out.print("Phone Number: ");
                writer.print(_In.getString().trim());
                writer.println();
            } while (!_In.getString().equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
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
