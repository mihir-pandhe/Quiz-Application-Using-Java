package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
