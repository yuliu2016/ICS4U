package ics4u.ma4;

/**
 * Major Assignment 4
 *
 * @author Yu
 */
public class Student {
    private int id; // the student id
    private double gpa; // the GPA grade

    // Default constructor
    public Student() {
        id = 0;
        gpa = 0;
    }

    // Full constructor
    public Student(int id, double gpa) {
        this.id = id;
        this.gpa = gpa;
    }

    // Getters

    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    public String toString() {
        return "Student(id=" + id + ", gpa=" + gpa + ")";
    }
}
