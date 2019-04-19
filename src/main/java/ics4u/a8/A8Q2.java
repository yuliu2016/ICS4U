package ics4u.a8;

/**
 * Assignment 8
 * Question 2
 * (a) For the child development study discussed in this section, write a method
 * that could be added to the Child class that prints the data (name, age, height,
 * and sex) for a child on two lines with the name on the ﬁrst line and the other
 * data on the second line. Print the height in centimetres, rounded to the nearest
 * centimetre.
 * <p>
 * (b) Write a fragment that uses this method to print the data on all the children.
 * <p>
 * (c) Write a method that would produce the same output as the method shown in part
 * (a) if we had chosen to use parallel arrays rather than an array of objects to
 * represent our data.
 *
 * @author Yu Liu
 */

public class A8Q2 {

    public static void main(String[] args) {
        // test array of objects
        Child[] children = new Child[]{new Child("Yu", 16, 1.65, 'M')};
        printAllChildren(children);

        // test parallel arrays
        String[] names = new String[]{"Yu"};
        int[] ages = new int[]{16};
        double[] heights = new double[] {1.65};
        char[] sexes = new char[] {'M'};
        printAllChildrenParallel(names, ages, heights, sexes);
    }

    static void printAllChildren(Child[] children) {
        // loop through and print out each child using class method
        for (int i = 0; i < children.length; i++) {
            System.out.println(children[i]);
        }
    }

    static void printAllChildrenParallel(String[] names, int[] ages, double[] heights, char[] sexes) {
        // check for bounding error
        if (ages.length < names.length || heights.length < names.length || sexes.length < names.length) {
            throw new IllegalArgumentException();
        }
        // loop through and print out each child
        for (int i = 0; i < names.length; i++) {
            String output = "Name: " + names[i] + "\nAge: " + ages[i] +
                    "  Height: " + Math.round(heights[i] * 100) + "  Sex: " + sexes[i];
            System.out.println(output);
        }
    }

    static class Child {
        String name; // family, given
        int age; // in years
        double height; // in metres
        char sex; // M or F

        public Child(String name, int age, double height, char sex) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.sex = sex;
        }

        /**
         * Returns a string representation in two lines
         */
        public String toString() {
            return "Name: " + name + "\nAge: " + age + "  Height: " + Math.round(height * 100) + "  Sex: " + sex;
        }
    }
}
