/*
 * CS2050 - Computer Science II - Summer 2021
 * Instructor: Thyago Mota
 * Description: Hwk 01 - StudentDriver
 */

import jdk.swing.interop.SwingInterOpUtils;

public class StudentDriver {

    public static void main(String[] args) {

        // TODO #5: instantiate a student object
        Student s1 = new Student(23,"David","Computer Science");

        // TODO #6: display the student's major
        String major = s1.getMajor();
        System.out.println("My major: " + major);

        System.out.println(s1);
    }
}