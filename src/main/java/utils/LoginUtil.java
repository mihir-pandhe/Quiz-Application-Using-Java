package utils;

import models.Student;
import models.TestTaker;

import java.util.Map;
import java.util.Scanner;

public class LoginUtil {
    public static Student studentLogin(Scanner scanner, Map<String, Student> students) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return null;
        }
        System.out.println("Welcome, " + student.getName() + "!");
        return student;
    }

    public static TestTaker testTakerLogin(Scanner scanner, Map<String, TestTaker> testTakers) {
        System.out.print("Enter Test Taker ID: ");
        String testTakerID = scanner.next();
        TestTaker testTaker = testTakers.get(testTakerID);
        if (testTaker == null) {
            System.out.println("Test Taker not found.");
            return null;
        }
        System.out.println("Welcome, " + testTaker.getName() + "!");
        return testTaker;
    }
}