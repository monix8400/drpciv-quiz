package backend.controller;

import backend.model.Question;

import java.util.List;

public class QuestionController {
    private Question model;

    public QuestionController(Question model) {
        this.model = model;
    }

    public void setQuestion(String question) {
        this.model.setQuestion(question);
    }

    public String getQuestion() {
        return this.model.getQuestion();
    }

    public void setAnswers(List<String> answers) {
        this.model.setAnwers(answers);
    }

    public List<String> getAnswers() {
        return this.model.getAnwers();
    }

    public void setCorrectAnswers(List<Integer> correctAnswers) {
        this.model.setCorrectAnswers(correctAnswers);
    }

    public List<Integer> getCorrectAnswers() {
        return this.model.getCorrectAnswers();
    }
}
