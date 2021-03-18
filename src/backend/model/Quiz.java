package backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
    List<Question> allQuestions;
    List<Question> questions = new ArrayList<>();

    /**
     * constructor which randomly generates a quiz with unique questions
     * constructor takes a List of questions and chooses 26 unique of them
     * @param allQuestions full list of questions
     */
    public Quiz(List<Question> allQuestions) {
        this.allQuestions = allQuestions;

        // selecting randomly questions from the allQuestions and generating the actual quiz
        Random random = new Random();
        int numberQuestions = allQuestions.size();

        // repeats the process until we have 26 unique questions in list
        do {
            int index = random.nextInt(numberQuestions);
            Question q = allQuestions.get(index);

            // if question is not already in list, we append it
            if (!this.questions.contains(q))
                this.questions.add(q);

        } while(this.questions.size() != 26);

    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
