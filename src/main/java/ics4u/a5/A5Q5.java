package ics4u.a5;

/**
 * Assignment 5
 * Question 5
 * Consider again the class Circle.
 * class Circle {
 * double x; // x-coordinate of centre
 * double y; // y-coordinate of centre
 * double r; // radius
 * }
 * <p>
 * (a) Write an instance method area that returns, as a double value,
 * the area of its implicit Circle object.
 * <p>
 * (b) Write a method smaller that could be called by a statement like
 * c3 = c1.smaller(c2); where c1, c2, and c3 are objects of type Circle.
 * The method should make c3 refer to the smaller of the circles represented
 * by c1 and c2 (or c1 if c1 and c2 are the same size).
 * <p>
 * (c) Write a boolean-valued instance method isInside that could be called by
 * a statement like boolean contained = c1.isInside(c2); The method should return
 * true if c1 is entirely inside c2 and return false otherwise.
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A5Q5 {

    public static void main(String[] args) {
        // Main program in question 6
    }

    private static class Point2D {
        double x;
        double y;

        private Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // Find the hypotenuse between two points
        private double distanceTo(Point2D other) {
            return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
        }

        @Override
        public String toString() {
            return "Point(x=" + x + ", y=" + y + ")";
        }
    }

    private static class Circle {
        Point2D point;
        double radius;

        private Circle(Point2D point, double radius) {
            this.point = point;
            this.radius = radius;
        }

        /**
         * Returns the area of this circle
         */
        double area() {
            return 2 * Math.PI * radius;
        }

        /**
         * Compare the radius of two circles and returns the reference of the biggerone
         */
        static Circle smaller(Circle c1, Circle c2) {
            return c1.radius <= c2.radius ? c1 : c2;
        }

        /**
         * Check if this circle is in another circle
         *
         * In order for this to be true, the other circle's radius must
         * strictly be less than this circle's radius, and the distance
         * between the centers of the two circles must be less than the
         * difference in radius
         */
        boolean isInside(Circle other) {
            return radius < other.radius && point.distanceTo(other.point) < Math.abs(radius - other.radius);
        }

        @Override
        public String toString() {
            return "Circle(point=" + point +", radius=" + radius + ")";
        }
    }
}
