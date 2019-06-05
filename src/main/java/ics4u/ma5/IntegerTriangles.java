package ics4u.ma5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 5
 * ICS4U class 2019
 *
 * @author Yu
 */
public class IntegerTriangles {

    public static void main(String[] args) {
        int p;
        while ((p = _In.getInt()) > 0) {
            Triangle best = findBestIntegerTriangle(p);
            if (best == null) {
                System.out.println("There are no integer triangles with the perimeter of " + p);
            } else {
                best.reorder();
                System.out.print("sides");
                _Out.print(best.a, 8);
                _Out.print(best.b, 8);
                _Out.print(best.c, 8);
                System.out.print("  yield the largest area:");
                _Out.print((int) best.area(), 10);
                System.out.println();
            }
        }
    }

    /**
     * Finds the Triangle with the largest area, that has
     * this perimeter and integer sides
     *
     * Area = sqrt(s * (s - a) * (s - b) * (s - c))
     * where s = (a + b + c) / 2
     *
     * Since we know s, we can reduce a variable
     *
     * 2s = a + b + c
     * c = 2s - a - b
     * (s - c) = (s - (2s - a - b))
     * (s - c) = a + b - s
     *
     * Area is therefore sqrt(s * (s - a) * (s - b) * (a + b - s))
     *
     * where:
     * a < s
     * b < s
     *
     * we start by making a guess for a; assume it's the longest
     * side, it must be more than a third of the perimeter.
     *
     * Based on a, we make a guess for b; assume it's the longest
     * remaining side; it must be more than (perimeter - a) / 2
     *
     * We can calculate the area based on these two guess; if
     * it is an integer, then it holds mathematically that this
     * is the best triangle; otherwise increase guess b. When
     * nothing is found after b reaches s; increase guess a
     *
     * @return the best triangle
     */
    static Triangle findBestIntegerTriangle(int perimeter) {
        if (perimeter < 1 || perimeter % 2 == 1) return null;
        int s = perimeter / 2;
        for (int a = perimeter / 3; a < s; a++) {
            for (int b = (perimeter - a) / 2; b < s; b++) {
                double area = Math.sqrt(s * (s - a) * (s - b) * (a + b - s));
                if (area - Math.floor(area) == 0) return new Triangle(a, b, 2 * s - a - b);
            }
        }
        return null;
    }


    static class Triangle {
        int a;
        int b;
        int c;

        public Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        double area() {
            int s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }

        /**
         * Reorders the values so they go in descending order
         */
        void reorder() {
            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (c > b) {
                int temp = b;
                b = c;
                c = temp;
            }
            if (b > a) {
                int temp = a;
                a = b;
                b = temp;
            }
        }
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
