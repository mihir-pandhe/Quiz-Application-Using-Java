package models;

import java.io.Serializable;

public class Student implements Serializable {
    private String studentID;
    private String name;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}
