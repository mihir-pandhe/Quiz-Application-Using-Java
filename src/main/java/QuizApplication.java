import models.*;
import utils.FileUtil;
import utils.MenuUtil;
import utils.LoginUtil;

import java.io.IOException;
import java.util.*;

public class QuizApplication {

    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, TestTaker> testTakers = new HashMap<>();
    private static List<Quiz> quizzes = new ArrayList<>();
    private static Map<String, List<QuizAttempt>> quizAttempts = new HashMap<>();

    public static void main(String[] args) {
        try {
            students = FileUtil.loadStudents();
            testTakers = FileUtil.loadTestTakers();
            quizzes = FileUtil.loadQuizzes();
            quizAttempts = FileUtil.loadQuizAttempts();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return;
        }
        initializeSampleData();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            MenuUtil.displayMainMenu();
            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    handleStudentLogin(scanner);
                    break;
                case 2:
                    handleTestTakerLogin(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        try {
            FileUtil.saveStudents(students);
            FileUtil.saveTestTakers(testTakers);
            FileUtil.saveQuizzes(quizzes);
            FileUtil.saveQuizAttempts(quizAttempts);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static int getValidInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleStudentLogin(Scanner scanner) {
        Student student = LoginUtil.studentLogin(scanner, students);
        if (student != null) {
            boolean studentLoggedIn = true;
            while (studentLoggedIn) {
                MenuUtil.displayStudentMenu();
                int choice = getValidInteger(scanner);
                switch (choice) {
                    case 1:
                        solveQuiz(scanner, student);
                        break;
                    case 2:
                        studentLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void handleTestTakerLogin(Scanner scanner) {
        TestTaker testTaker = LoginUtil.testTakerLogin(scanner, testTakers);
        if (testTaker != null) {
            boolean testTakerLoggedIn = true;
            while (testTakerLoggedIn) {
                MenuUtil.displayTestTakerMenu();
                int choice = getValidInteger(scanner);
                switch (choice) {
                    case 1:
                        createQuiz(scanner);
                        break;
                    case 2:
                        viewQuizAttempts();
                        break;
                    case 3:
                        testTakerLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void createQuiz(Scanner scanner) {
        System.out.print("Enter Quiz ID: ");
        String quizID = scanner.next();
        System.out.print("Enter Quiz Title: ");
        String title = scanner.next();
        Quiz quiz = new Quiz(quizID, title);

        boolean addingQuestions = true;
        while (addingQuestions) {
            System.out.print("Enter Question: ");
            scanner.nextLine();
            String questionText = scanner.nextLine();

            List<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.print("Enter Option " + (i + 1) + ": ");
                options.add(scanner.nextLine());
            }

            System.out.print("Enter correct option number (1-4): ");
            int correctAnswerIndex = getValidInteger(scanner) - 1;

            Question question = new Question(questionText, options, correctAnswerIndex);
            quiz.addQuestion(question);

            System.out.print("Do you want to add another question? (yes/no): ");
            addingQuestions = scanner.next().equalsIgnoreCase("yes");
        }

        quizzes.add(quiz);
    }

    private static void solveQuiz(Scanner scanner, Student student) {
        System.out.print("Enter Quiz ID to solve: ");
        String quizID = scanner.next();

        Quiz quiz = quizzes.stream().filter(q -> q.getQuizID().equals(quizID)).findFirst().orElse(null);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        if (quizAttempts.containsKey(student.getStudentID()) &&
                quizAttempts.get(student.getStudentID()).stream()
                        .anyMatch(attempt -> attempt.getQuizID().equals(quizID))) {
            System.out.println("You have already attempted this quiz.");
            return;
        }

        int score = 0;
        for (Question question : quiz.getQuestions()) {
            System.out.println("Question: " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }
            System.out.print("Enter your answer (1-4): ");
            int answer = getValidInteger(scanner) - 1;
            if (answer == question.getCorrectAnswerIndex()) {
                score++;
            }
        }
        System.out.println("You scored: " + score + "/" + quiz.getQuestions().size());

        QuizAttempt attempt = new QuizAttempt(student.getStudentID(), quizID, score);
        quizAttempts.putIfAbsent(student.getStudentID(), new ArrayList<>());
        quizAttempts.get(student.getStudentID()).add(attempt);
    }

    private static void viewQuizAttempts() {
        for (Map.Entry<String, List<QuizAttempt>> entry : quizAttempts.entrySet()) {
            String studentID = entry.getKey();
            List<QuizAttempt> attempts = entry.getValue();
            System.out.println("Student ID: " + studentID);
            for (QuizAttempt attempt : attempts) {
                System.out.println("Quiz ID: " + attempt.getQuizID() + ", Score: " + attempt.getScore());
            }
        }
    }
    private static void initializeSampleData() {
        if (students.isEmpty()) {
            students.put("S001", new Student("S001", "Alice"));
            students.put("S002", new Student("S002", "Bob"));
        }
    
        if (testTakers.isEmpty()) {
            testTakers.put("T001", new TestTaker("T001", "Charlie"));
            testTakers.put("T002", new TestTaker("T002", "David"));
        }
    }
}

