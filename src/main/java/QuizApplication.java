package src.main.java;

import src.main.java.models.Student;
import src.main.java.models.TestTaker;
import src.main.java.models.Quiz;
import src.main.java.models.Question;
import src.main.java.utils.LoginUtil;
import src.main.java.utils.MenuUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuizApplication {

    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, TestTaker> testTakers = new HashMap<>();
    private static List<Quiz> quizzes = new ArrayList<>();

    public static void main(String[] args) {
        // Adding some dummy users for testing
        students.put("S1", new Student("S1", "Alice"));
        students.put("S2", new Student("S2", "Bob"));
        testTakers.put("T1", new TestTaker("T1", "Charlie"));
        testTakers.put("T2", new TestTaker("T2", "David"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            MenuUtil.displayMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    LoginUtil.studentLogin(scanner, students);
                    break;
                case 2:
                    LoginUtil.testTakerLogin(scanner, testTakers);
                    break;
                case 3:
                    createQuiz(scanner);
                    break;
                case 4:
                    displayQuizzes();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            int correctAnswerIndex = scanner.nextInt() - 1;

            Question question = new Question(questionText, options, correctAnswerIndex);
            quiz.addQuestion(question);

            System.out.print("Do you want to add another question? (yes/no): ");
            addingQuestions = scanner.next().equalsIgnoreCase("yes");
        }

        quizzes.add(quiz);
    }

    private static void displayQuizzes() {
        for (Quiz quiz : quizzes) {
            System.out.println("Quiz ID: " + quiz.getQuizID());
            System.out.println("Title: " + quiz.getTitle());
            for (Question question : quiz.getQuestions()) {
                System.out.println("Question: " + question.getQuestionText());
                List<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ": " + options.get(i));
                }
            }
            System.out.println();
        }
    }
}
