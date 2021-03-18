package backend.controller;

import backend.model.Question;
import backend.model.Quiz;
import java.util.List;

public class QuizController {
    private Quiz model;

    public QuizController(Quiz model){
        this.model = model;
    }

    public void setQuestions(List<Question> questions) {
        this.model.setQuestions(questions);
    }

    public List<Question> getQuestions() {
        return this.model.getQuestions();
    }
}
