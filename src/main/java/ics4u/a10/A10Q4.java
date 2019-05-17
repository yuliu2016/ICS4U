package ics4u.a10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;

/**
 * Assignment 10
 * Question 4
 * <p>
 * ICS4U class 2019
 *
 * @author Yu Liu
 */

public class A10Q4 {

    public static void main(String[] args) {
        File file = new File("student_marks.txt");
        System.out.println("File path: " + file.getAbsolutePath());

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // skip over the header line
            br.readLine();
            // read line by line
            String line;
            // create array of students
            Student[] students = new Student[20];
            int i = 0;
            // read file; counts how many students there are
            while ((line = br.readLine()) != null) {
                // parses the line into a Student and put it in the array
                students[i++] = new Student(line);
            }
            br.close();
            // copy over the actual number of students
            Student[] actualStudents = new Student[i];
            System.arraycopy(students, 0, actualStudents, 0, i);

            // Print out the class average
            System.out.println("Class Average: " + toPercent(Student.averageForClass(actualStudents)));
            System.out.println();

            // Print out the table headers
            _Out.print("", 15);
            for (int j = 1; j <= 4; j++) {
                _Out.print("Test" + j, 15);
            }
            System.out.println("Student Average");

            // Print out data for each student
            for (int j = 0; j < actualStudents.length; j++) {
                Student student = actualStudents[j];
                _Out.print(student.getName(), 15);
                for (int k = 0; k < 4; k++) {
                    _Out.print(toPercent(student.percentForTest(k)), 15);
                }
                System.out.println(toPercent(student.average()));
            }
            System.out.println();

            // Print out data for each test
            _Out.print("Test Average", 15);
            for (int j = 0; j < 4; j++) {
                _Out.print(toPercent(Student.averageForTest(actualStudents, j)), 15);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats a percentage value
     */
    static String toPercent(double percent) {
        return new DecimalFormat().format(percent * 100) + "%";
    }

    /**
     * Define a student class to parse the input and do averages
     */
    private static class Student {

        private String name;  // name of student
        private int[] tests; // array of tests
        private int[] marks; // array of marks

        Student(String input) {
            // split the input
            String[] split = input.split(",");
            name = split[0].trim();
            // find number of tests
            int numTests = (split.length - 1) / 2;
            // create arrays
            tests = new int[numTests];
            marks = new int[numTests];
            // parse individual values
            for (int i = 0; i < numTests; i++) {
                tests[i] = Integer.parseInt(split[i * 2 + 1].trim());
                marks[i] = Integer.parseInt(split[i * 2 + 2].trim());
            }
        }

        /**
         * Get the percentage for one test
         */
        public double percentForTest(int test) {
            return (double) marks[test] / tests[test];
        }

        /**
         * Get the average of the student
         */
        public double average() {
            double sum = 0.0;
            for (int i = 0; i < tests.length; i++) {
                sum += percentForTest(i);
            }
            return sum / tests.length;
        }

        /**
         * Get the name of the student
         */
        public String getName() {
            return name;
        }

        /**
         * Get the average for the class
         */
        public static double averageForClass(Student[] students) {
            double sum = 0.0;
            for (int i = 0; i < students.length; i++) {
                sum += students[i].average();
            }
            return sum / students.length;
        }

        /**
         * Get the class average for the test
         */
        public static double averageForTest(Student[] students, int test) {
            double sum = 0.0;
            for (int i = 0; i < students.length; i++) {
                sum += students[i].percentForTest(test);
            }
            return sum / students.length;
        }
    }

    private static class _Out {
        public static void print(String str, int fieldWidth) {
            StringBuffer s = new StringBuffer(str);
            int stop = fieldWidth - s.length();
            for (int i = 0; i < stop; i++) s.append(" ");
            System.out.print(s);
        }
    }
}
