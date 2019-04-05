package ics4u.ma2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;

/**
 * Major Assignment 1
 * Question 10
 * Suppose that rectangles in a Cartesian plane are represented by a class
 * Rectangle that has four private ﬁelds as shown:
 * class Rectangle {
 * private int left; // x-coordinate of left edge
 * private int bottom; // y-coordinate of bottom edge
 * private int width; // width of rectangle
 * private int height // height of rectangle
 * }
 * (a) Write a constructor method that has four parameters representing the
 * four ﬁelds of the class. The constructor should replace any negative length
 * parameters with zero.
 * <p>
 * (b) Write a toString method for the Rectangle class. For the rectangle with lower left
 * corner located at (3,2) and having width 4 and height 5,
 * the method should return "base:(3,2) w:4 h:5".
 * <p>
 * (c) Write an instance method, area, that returns the area of a rectangle.
 * <p>
 * (d) Write an instance method, perimeter, that returns the perimeter of a rectangle.
 * <p>
 * (e) Write a class method, intersection, that has two Rectangle parameters.
 * The method should return the rectangle formed by the area common to the two rectangles.
 * If they do not intersect, the method should return the rectangle whose instance ﬁelds are all zero.
 * If the rectangles touch, the method should return a “rectangle” of zero width or zero length.
 * <p>
 * (f) Write a class method, totalPerimeter, that has two Rectangle parameters.
 * The method should return the total perimeter of the ﬁgure formed by the two rectangles.
 * It should only count as part of the perimeter those portions of the edges of a rectangle
 * that are on the exterior of the resulting ﬁgure. If one rectangle is completely contained
 * by the other, then the method should return the perimeter of the outer rectangle.
 * If the rectangles do not intersect, then the method should return the sum of the
 * individual perimeters
 * <p>
 * (g) Write an instance method, contains, that has one explicit parameter of type Rectangle.
 * The method should return true if every point of the rectangle determined by the explicit
 * parameter is on or within the rectangle determined by the implicit parameter. It should
 * return false otherwise.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class MA2Q8 {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 0, 3, 2);
        Rectangle r2 = new Rectangle(2, 1, 2, 3);
        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);
        System.out.println("r1 area: " + r1.area());
        System.out.println("r2 perimeter: " + r2.perimeter());
        System.out.println("r1 intersect r2: " + Rectangle.intersection(r1, r2));
        System.out.println("totalPerimeter: " + Rectangle.totalPerimeter(r1, r2));
        System.out.println("r1 contains r2: " + r1.contains(r2));
    }

    static class Rectangle {
        private int left;
        private int bottom;
        private int width;
        private int height;

        /**
         * Constructor method, which takes in dimensions for the rectange
         * and limit it to positive values
         */
        public Rectangle(int left, int bottom, int width, int height) {
            this.left = left;
            this.bottom = bottom;
            this.width = width;
            this.height = height;

            if (this.left < 0) this.left = 0;
            if (this.bottom < 0) this.bottom = 0;
            if (this.width < 0) this.width = 0;
            if (this.height < 0) this.height = 0;
        }

        /**
         * Convert to a string representation
         */
        public String toString() {
            return "base:(" + left + "," + bottom + ") w:" + width + " h:" + height;
        }

        /**
         * Calculates the area of the rectangle
         */
        public int area() {
            return width * height;
        }

        /**
         * Calculates the perimeter of the rectangle
         */
        public int perimeter() {
            return width * 2 + height * 2;
        }

        /**
         * Calculates the right position of the rectangle
         */
        public int right() {
            return left + width;
        }

        /**
         * Calculates the top position of the rectangle
         */
        public int top() {
            return bottom + height;
        }

        /**
         * Get the intersection of two rectangles
         * <p>
         * This function first assumes the intersection rectangle at the origin with
         * dimensions of 0, then for each opposite pair of sides, it checks if they
         * overlap. If they do, the overlapping part must be part of the intersection
         * rectangle. In such a case, the overlapping edge must be in a defining edge
         * of the intersection rectangle and the width must either defined by the other
         * overlapping edge, or by the width of the other rectangle, whichever is smaller
         */
        public static Rectangle intersection(Rectangle r1, Rectangle r2) {
            Rectangle result = new Rectangle(0, 0, 0, 0);
            if (r1.left > r2.left && r1.left < r2.right()) {
                result.left = r1.left;
                result.width = Math.min(r1.width, r2.right() - r1.left);
            } else if (r1.right() > r2.left && r1.right() < r2.right()) {
                result.width = Math.min(r1.right() - r2.left, r1.width);
                result.left = r1.right() - result.width;
            }
            if (r1.bottom > r2.bottom && r1.bottom < r2.top()) {
                result.bottom = r1.bottom;
                result.height = Math.min(r1.height, r2.top() - r1.bottom);
            } else if (r1.top() > r2.bottom && r1.top() < r2.top()) {
                result.height = Math.min(r1.top() - r2.bottom, r1.height);
                result.bottom = r1.top() - result.height;
            }
            return result;
        }

        /**
         * Gets the total perimeter defined by the two rectangles, which is equivalent
         * to the total perimeter of the individual rectangles minus the perimeter
         * of the intersecting rectangle
         */
        public static int totalPerimeter(Rectangle r1, Rectangle r2) {
            return r1.perimeter() + r2.perimeter() - intersection(r1, r2).perimeter();
        }

        /**
         * Check if anthor rectangle is entirely contained within this rectangle.
         * True only it's area is equivalent to the intersecting rectangle's area.
         */
        public boolean contains(Rectangle other) {
            return intersection(this, other).area() == other.area();
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