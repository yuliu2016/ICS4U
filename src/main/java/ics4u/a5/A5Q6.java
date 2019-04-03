package ics4u.a5;

/**
 * Assignment 4
 * Question 6
 *  Write a main method that uses the Circle class developed in the previous question.
 *  The main method should perform the following actions.
 *
 * (a) Create two Circle objects c1, representing the circle with centre (4,−1)
 * and radius 3, and c2, representing the circle with centre (3,−2) and radius 5.
 *
 * (b) Find and print the area of c1.
 *
 * (c) Determine the smaller of c1 and c2 and then print its centre and radius.
 *
 * (d) Determine whether or not c2 lies entirely within c1 and print an appropriate statement.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A5Q6 {

    public static void main(String[] args) {
        Circle c1 = new Circle(new Point2D(4, -1), 3);
        Circle c2 = new Circle(new Point2D(3, -2), 5);

        System.out.println("Area of c1: " + c1.area());

        System.out.println("The smaller of c1 and c2 is: " + Circle.smaller(c1, c2));

        if (c2.isInside(c1)) {
            System.out.println("c2 inside c1");
        } else {
            System.out.println("c2 not inside c1");
        }
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

        public String toString() {
            return "Circle(point=" + point +", radius=" + radius + ")";
        }
    }
}
