/*
 * CS2050 - Computer Science II - Summer 2021
 * Instructor: Thyago Mota
 * Description: Hwk 01 - Student
 */

public class Student {

    // TODO #1: define 3 instance variables to hold id, name, and major

    private int id;
   private String name;
   private String major;

   final String DEFAULT_MAJOR = "Undeclared";

    // TODO #2: write a constructor with all 3 parameters

    public Student(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    // TODO #3: write a constructor with only the student's id and name (in that case, major should be set to "undeclared")
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.major = DEFAULT_MAJOR;
    }


    // TODO #4: write a method called getMajor that returns the major of the student

    public String getMajor() {
        return major;
    }

    // in order to print all instances in the driver class. need to create a toString method.

    @Override
    public String toString() {
        return "Student " +
                "id=" + id +
                " name='" + name + '\'' +
                " major='" + major + '\'' ;
    }
}