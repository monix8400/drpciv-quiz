package backend.model;

public class Result {
    int correctAnswers;
    int remainingQuestions;

    public Result(int correctAnswers, int remainingQuestions) {
        this.correctAnswers = correctAnswers;
        this.remainingQuestions = remainingQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getRemainingQuestions() {
        return remainingQuestions;
    }

    public void setRemainingQuestions(int remainingQuestions) {
        this.remainingQuestions = remainingQuestions;
    }
}
