package src.main.java.utils;

public class MenuUtil {
    public static void displayMainMenu() {
        System.out.println("Welcome to the Quiz Application");
        System.out.println("1. Student Login");
        System.out.println("2. Test Taker Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void displayStudentMenu() {
        System.out.println("1. Solve Quiz");
        System.out.println("2. Logout");
        System.out.print("Enter your choice: ");
    }

    public static void displayTestTakerMenu() {
        System.out.println("1. Create Quiz");
        System.out.println("2. View Quiz Attempts");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }
}
