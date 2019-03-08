package ics4u.a4;

import java.util.Map;

/**
 * Assignment 4
 * Question 6
 * A circle in the Cartesian plane can be described uniquely by its centre and its radius.
 * Thus, a class called Circle that could be used to represent such circles might start with the following:
 * class Circle {
 * double x; // x-coordinate of centre
 * double y; // y-coordinate of centre
 * double r; // radius
 * }
 * An object of this class, representing a circle with centre at (5,2) and having radius 3 is shown in the next diagram.
 * Using this class, write a Java program that performs the following actions.
 * (a) Create Circle objects c1 and c2 where c1 represents a circle with centre at (1,2)
 * having radius 4 while c2 represents a circle with centre at (−2,0) having radius 2.
 * (b) Set the double variable distance to the distance from the origin to the centre of c1 and then print this value.
 * (c) Set the double variable centreSeparationequal to the distance between the centres of c1 and c2 and then print this value.
 * (d) Set the double variable minDistance to the minimum distance from a point on c1 to a point on c2 and then print this value.
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A4Q6 {

    public static void main(String[] args) {
        Point2D origin = new Point2D(0, 0);
        Circle c1 = new Circle(new Point2D(1, 2), 4);
        Circle c2 = new Circle(new Point2D(-2, 0), 4);

        // Find distance to origin
        System.out.println("C1 To Origin Distance: " + origin.distanceTo(c1.point));
        // Find distance between circles
        System.out.println("C1 To C2 Distance: " + c2.point.distanceTo(c1.point));
        // Find min distance between circles
        double minDist = c2.point.distanceTo(c1.point) - c2.radius - c1.radius;
        // Check if the two circle overlapses, in which case there must be an intersection point
        if (minDist < 0) {
            minDist = 0;
        }
        System.out.println("Minimum distance between a point on C1 and a point on C2: " + minDist);
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
    }

    private static class Circle {
        Point2D point;
        double radius;

        private Circle(Point2D point, double radius) {
            this.point = point;
            this.radius = radius;
        }
    }
}
