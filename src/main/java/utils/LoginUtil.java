package src.main.java.utils;

import src.main.java.models.Student;
import src.main.java.models.TestTaker;

import java.util.Map;
import java.util.Scanner;

public class LoginUtil {

    public static void studentLogin(Scanner scanner, Map<String, Student> students) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        Student student = students.get(studentID);
        if (student != null) {
            System.out.println("Welcome, " + student.getName());
        } else {
            System.out.println("Invalid Student ID. Please try again.");
        }
    }

    public static void testTakerLogin(Scanner scanner, Map<String, TestTaker> testTakers) {
        System.out.print("Enter TestTaker ID: ");
        String testTakerID = scanner.next();
        TestTaker testTaker = testTakers.get(testTakerID);
        if (testTaker != null) {
            System.out.println("Welcome, " + testTaker.getName());
        } else {
            System.out.println("Invalid TestTaker ID. Please try again.");
        }
    }
}