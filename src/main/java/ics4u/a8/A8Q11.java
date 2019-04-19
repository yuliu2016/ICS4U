package ics4u.a8;

/**
 * Assignment 7
 * Pg 262
 * Question 4
 *  You have been hired by a wicked witch with a strong interest in children.
 *  The program that she uses to help her maintain records has a class
 *  Child that contains the ﬁelds
 *  private int height; // height in cm private
 *  double mass; // mass in kg T
 *  he witch wants you to write an equals method for the class.
 *  The witch considers two Child objects to be equal if their heights diﬀer
 *  by no more than 2 cm and their masses diﬀer by no more than 0.5 kg.
 *
 * @author Yu Liu
 */

public class A8Q11 {

    public static void main(String[] args) {
        // create and test Child objects
        Child child1 = new Child(120, 27.0);
        Child child2 = new Child(122, 26.6);
        System.out.println("child1: " + child1);
        System.out.println("child2: " + child2);
        if (child1.equals(child2)){
            System.out.println("child1 equals to child2");
        } else {
            System.out.println("child1 not equal to child2");
        }
    }

    private static class Child{
        private int height; // cm
        double mass; // kg

        Child(int height, double mass) {
            this.height = height;
            this.mass = mass;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Child){
                Child other = (Child) obj;
                // check if height difference is less than 2 and mass difference is less than 0.5
                return epsilonEquals(height, other.height, 2.0) && epsilonEquals(mass, other.mass, 0.5);
            }
            return false;
        }

        /**
         * Returns whether two numbers differ by no more than an epsilon number
         */
        private static boolean epsilonEquals(double a, double b, double epsilon){
            return a - epsilon <= b &&  a + epsilon >= b;
        }

        public String toString() {
            return "Child(height=" + height + ", mass=" + mass + ")";
        }
    }
}
