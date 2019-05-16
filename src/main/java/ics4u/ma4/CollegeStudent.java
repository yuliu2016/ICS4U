package ics4u.ma4;

/**
 * Major Assignment 4
 *
 * @author Yu
 */
public class CollegeStudent extends Student {
    private String major; // the major of this college student

    // Default constructor
    public CollegeStudent() {
        major = "None";
    }

    // Full constructor
    public CollegeStudent(int id, double gpa, String major) {
        super(id, gpa);
        this.major = major;
    }

    // Getters

    public String getMajor() {
        return major;
    }

    public String toString() {
        return "CollegeStudent(id=" + getId() + ", gpa=" + getGpa() + ", " + "major=" + major + ")";
    }
}
