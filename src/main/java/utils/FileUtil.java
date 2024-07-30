package utils;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
    private static final String STUDENTS_FILE = "students.dat";
    private static final String TEST_TAKERS_FILE = "testTakers.dat";
    private static final String QUIZZES_FILE = "quizzes.dat";
    private static final String QUIZ_ATTEMPTS_FILE = "quizAttempts.dat";

    @SuppressWarnings("unchecked")
    public static Map<String, Student> loadStudents() throws IOException, ClassNotFoundException {
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }
        return (Map<String, Student>) loadFromFile(STUDENTS_FILE);
    }

    public static void saveStudents(Map<String, Student> students) throws IOException {
        saveToFile(students, STUDENTS_FILE);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, TestTaker> loadTestTakers() throws IOException, ClassNotFoundException {
        File file = new File(TEST_TAKERS_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }
        return (Map<String, TestTaker>) loadFromFile(TEST_TAKERS_FILE);
    }

    public static void saveTestTakers(Map<String, TestTaker> testTakers) throws IOException {
        saveToFile(testTakers, TEST_TAKERS_FILE);
    }

    @SuppressWarnings("unchecked")
    public static List<Quiz> loadQuizzes() throws IOException, ClassNotFoundException {
        File file = new File(QUIZZES_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return (List<Quiz>) loadFromFile(QUIZZES_FILE);
    }

    public static void saveQuizzes(List<Quiz> quizzes) throws IOException {
        saveToFile(quizzes, QUIZZES_FILE);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, List<QuizAttempt>> loadQuizAttempts() throws IOException, ClassNotFoundException {
        File file = new File(QUIZ_ATTEMPTS_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }
        return (Map<String, List<QuizAttempt>>) loadFromFile(QUIZ_ATTEMPTS_FILE);
    }

    public static void saveQuizAttempts(Map<String, List<QuizAttempt>> quizAttempts) throws IOException {
        saveToFile(quizAttempts, QUIZ_ATTEMPTS_FILE);
    }

    private static Object loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        }
    }

    private static void saveToFile(Object data, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        }
    }
}
