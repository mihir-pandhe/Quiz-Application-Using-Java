package src.main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String quizID;
    private String title;
    private List<Question> questions;

    public Quiz(String quizID, String title) {
        this.quizID = quizID;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getQuizID() {
        return quizID;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
