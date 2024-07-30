<<<<<<< HEAD
package models;

import java.io.Serializable;

public class QuizAttempt implements Serializable {
=======
package src.main.java.models;

public class QuizAttempt {
>>>>>>> 730aef23d043601fee3f058f4d17cc7f595376eb
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 730aef23d043601fee3f058f4d17cc7f595376eb
