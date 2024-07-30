package models;

import java.io.Serializable;

public class QuizAttempt implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentID;
    private String quizID;
    private int score;

    public QuizAttempt(String studentID, String quizID, int score) {
        this.studentID = studentID;
        this.quizID = quizID;
        this.score = score;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getQuizID() {
        return quizID;
    }

    public int getScore() {
        return score;
    }
}
