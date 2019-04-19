package ics4u.a7;

/**
 * Assignment 7
 * Pg 262
 * Question 3 g
 * The diagram shows a Circle object of the type that we have been using in exercises throughout this chapter.
 *
 * (g) Write a toString method for the Circle class. For a Circle object with x = 3, y = -4, and r = 2,
 * the toString method should return a String with the value: "centre: (3,-4) radius: 2".
 *
 * @author Yu Liu
 */

public class A7P262Q3 {

    public static void main(String[] args) {
        Circle c1 = new Circle(new Point2D(2, 3), 1);
        // test toString method
        System.out.println("c1.toString(): " + c1);
    }

    private static class Point2D {
        double x;
        double y;

        private Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private static class Circle {
        Point2D point;
        double radius;

        private Circle(Point2D point, double radius) {
            this.point = point;
            this.radius = radius;
        }

        public String toString() {
            return "centre: " + point + "  radius: " + radius;
        }
    }
}
