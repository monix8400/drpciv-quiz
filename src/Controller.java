import backend.model.Question;
import backend.model.Quiz;
import backend.model.Reader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // potential answers
    public Text answer_A;
    public Text answer_B;
    public Text answer_C;

    // user choice checkboxes
    public CheckBox check_A;
    public CheckBox check_B;
    public CheckBox check_C;

    // timer field
    public Text timer;

    // counter for wrong questions
    int wrongAnswers = 0;

    // frontend elements
    @FXML
    public Text remainingQuestions;
    @FXML
    public Text correctQuestions;
    @FXML
    public Text wrongQuestions;
    @FXML
    private Text current;
    @FXML
    private Text question;

    // reading questions from file
    Reader r = new Reader();
    List<Question> questionList = r.getQuestions();
    Quiz quiz = new Quiz(questionList);
    List<Question> questions = quiz.getQuestions();

    int currentQuestion = 0;

    /**
     * verifying answer and changing question on button click
     */
    public void next() throws IOException {
        // checking answers chosen by the user
        List<Integer> chosenAnswers = new ArrayList<>();
        if (check_A.isSelected())
            chosenAnswers.add(0);
        if (check_B.isSelected())
            chosenAnswers.add(1);
        if (check_C.isSelected())
            chosenAnswers.add(2);

        // checking to see if user answer matches correct answer
        if (!chosenAnswers.toString().equals(questions.get(currentQuestion).getCorrectAnswers().toString()))
            wrongAnswers += 1;

        // ending the quiz if the user exceeded 5 wrong answers or if he finished all 26 questions
        if (currentQuestion == 26 || wrongAnswers == 5) {
            // writing quiz results in data.json file to pass it to Finish.xml
            Reader.writeData(currentQuestion - wrongAnswers + 1, 26 - currentQuestion - 1);

            // switching scene
            Main.changeScene("frontend/Finish.fxml");
        }

        // deselecting check boxes
        check_A.setSelected(false);
        check_B.setSelected(false);
        check_C.setSelected(false);

        // loads and displays a new question
        loadNewQuestion();
    }

    // variables to keep track of the time
    int min = 30;
    int s = 0;

    /** initializing window on app load with first question displayed
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // starting timer
        startTimer();

        // initializing window with first question
        current.setText("1");
        question.setText(questions.get(0).getQuestion());

        answer_A.setText(questions.get(0).getAnwers().get(0));
        answer_B.setText(questions.get(0).getAnwers().get(1));
        answer_C.setText(questions.get(0).getAnwers().get(2));

        wrongQuestions.setText("0");
        correctQuestions.setText("0");
        remainingQuestions.setText("26");
    }

    /**
     * function to keep track of users time to complete the quiz
     */
    private void startTimer() {
        // initial time
        timer.setText("30:00");

        // starting timer
        // timer will update each second the "Timer" object on the frontend by decreasing the second counter
        Timeline oneSecond = new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {
            if (s == 0) {
                min -= 1;
                s = 60;
            }
            s -= 1;
            if (s < 10)
                timer.setText(min + ":0" + s);
            else
                timer.setText(min + ":" + s);
        }));

        oneSecond.setCycleCount(1800);
        oneSecond.play();

        // after timer expires, we will close the current window and open a new Start.fxml
        oneSecond.setOnFinished(event -> Platform.runLater(() -> {
            // close current window
            Main.primaryStage.fireEvent(new WindowEvent(Main.primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));

            // writing results of quiz to data.json
            Reader.writeData(currentQuestion - wrongAnswers, 26 - currentQuestion - 1);

            // change scene to Finish.fxml
            try {
                openWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    /**
     * helper function to open a new window
     */
    private void openWindow() throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("frontend/Finish.fxml"));

        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.setTitle("DRPCIV Wannabe");
        Main.primaryStage.show();
    }

    /**
     * loads a new question on the screen and increases counters
     */
    public void loadNewQuestion() {
        // increasing counter to go to next question
        currentQuestion += 1;

        // setting question name for next question
        question.setText(questions.get(currentQuestion).getQuestion());

        // setting next questions potential answers
        answer_A.setText(questions.get(currentQuestion).getAnwers().get(0));
        answer_B.setText(questions.get(currentQuestion).getAnwers().get(1));
        answer_C.setText(questions.get(currentQuestion).getAnwers().get(2));

        // adjusting new wrong answers counter
        wrongQuestions.setText(String.valueOf(wrongAnswers));

        // adjusting new correct answers counter
        correctQuestions.setText(String.valueOf(currentQuestion-wrongAnswers));

        // adjusting new remaining answers counter
        remainingQuestions.setText(String.valueOf(26-currentQuestion));

        // setting counter for next question
        current.setText(String.valueOf(currentQuestion + 1));
    }

    public Controller() throws IOException {

    }

}
