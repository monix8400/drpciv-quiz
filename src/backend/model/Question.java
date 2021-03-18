package backend.model;

import java.util.List;

public class Question {
    String question;
    List<String> answers;
    List<Integer> correctAnswers;

    public Question(String question, List<String> answers, List<Integer> correctAnswers) {
        this.question = question;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
    }

    public List<Integer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<Integer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnwers() {
        return answers;
    }

    public void setAnwers(List<String> anwers) {
        this.answers = anwers;
    }
}
